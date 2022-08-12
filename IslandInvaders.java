// inports

import java.lang.Math;
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Scanner;

class IslandInvaders {
   private static Scanner sc;

   public static void wwait() {
      System.out.println("press enter to contiue");
      sc.nextLine();
   }

   public static void setStartingIsland(String player, ArrayList<Island> islands) {
      System.out.println(player + " what island would you like to start out at?");
      for (int asdf = 0; asdf < islands.size(); asdf++) {
         if (islands.get(asdf).owner.equals("neutral")) {
            System.out.print(asdf + " ");
         }
      }
      int starting_island = sc.nextInt();
      islands.get(starting_island).owner = player;
      islands.get(starting_island).strength = 5;
   }

   public static void main(String[] args) {
      sc = new Scanner(System.in);

      // game start
      System.out.println("welcome to island invaders please enter player1's name");
      String player1 = sc.nextLine();
      System.out.println("hello " + player1);
      System.out.println("welcome to island invaders please enter player2's name");
      String player2 = sc.nextLine();
      System.out.println("hello " + player2);

      wwait();

      ArrayList<Island> islands = new ArrayList<Island>();
      islands.add(new Island(-1.1, -0.7)); // 0
      islands.add(new Island(-0.2, -0.4)); // 1
      islands.add(new Island(-0.4, 0.5)); // 2
      islands.add(new Island(0.4, 0.2)); // 3
      islands.add(new Island(0.9, 0.9)); // 4

      setStartingIsland(player1, islands);
      setStartingIsland(player2, islands);

      wwait();

      String player = "place holder";
      boolean is_p1_turn = false;
      boolean finished = false;

      // This is the main game loop
      while (!finished) {
         is_p1_turn = !is_p1_turn;
         if (is_p1_turn) {
            player = player1;
         } else {
            player = player2;
         }

         // This loop is for a player to do stuff with all their islands
         while (true) {
            System.out.println(player + " what would you like to do on your turn? you own");
            int owned_count = 0;
            int ready_count = 0;
            for (int i = 0; i < islands.size(); i++) {
               Island island = islands.get(i);
               if (island.owner.equals(player)) {
                  owned_count++;
                  System.out.println("the " + i + "th island: " + island);
                  if (!island.turn_done) {
                     ready_count++;
                  }
               }
            }
            System.out.println("Or type 99 to end your turn early");
            if (owned_count == 0) {
               System.out.println("Oh...you lost :(");
               finished = true;
               break;
            }
            if (ready_count == 0) {
               System.out.println("No more islands to do stuff with, turn over");
               break;
            }

            System.out.println("select an island you own");
            int selected_island = sc.nextInt();

            // End turn early
            if (selected_island == 99) {
               break;
            }

            System.out.println("would you like to do a (p)lane or (s)hip attack?");
            String attack = sc.next();
            if (attack.startsWith("p")) {
               System.out.println("which island would you like to attack?");
               for (int i = 0; i < islands.size(); i++) {
                  double dist = islands.get(selected_island).distanceTo(islands.get(i));
                  if (dist < Island.plane_range && !islands.get(i).owner.equals(player)) {
                     System.out.println(i);
                  }
               }
               int target_island = sc.nextInt();
               islands.get(selected_island).planeAttack(islands.get(target_island));
            }

            else if (attack.startsWith("s")) {
               System.out.println("which island would you like to attack?");
               for (int i = 0; i < islands.size(); i++) {
                  double dist = islands.get(selected_island).distanceTo(islands.get(i));
                  if (dist < Island.ship_range && !islands.get(i).owner.equals(player)) {
                     System.out.println(i);
                  }
               }
               int target_island = sc.nextInt();
               islands.get(selected_island).shipAttack(islands.get(target_island));
            }
         }
      }
      //       Island test1 = islands.get(0);
      //       test1.owner = "lucas";
      //       Island test2 = islands.get(1);
      //       test2.strength = 2;
      //       test1.planeAttack(test2);
      //       test1.planeAttack(test2);
      //       test1.planeAttack(test2);
      //       test1.shipAttack(test2);
      //       for(int i = 0; i < islands.size(); i++)
      //       {
      //          for(int j = 0; j < islands.size(); j++)
      //          {
      //             double dist = islands.get(i).distanceTo(islands.get(j));
      //             System.out.println(i + " --> " + j + ": " + dist);
      //          }
      //       }

      int yourisland = sc.nextInt();
      for (int i = 0; i < islands.size(); i++) {
         double dist = islands.get(yourisland).distanceTo(islands.get(i));
         if (dist != 0.0 && dist < Island.ship_range) {
            System.out.println(yourisland + " --> " + i + " " + dist);
         }
      }
   }
}
