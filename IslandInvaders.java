//inports

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
import java.lang.Runnable;

class IslandInvaders {
   private static Scanner sc;
   
   public static void wwait()
   {
      System.out.println("press enter to contiue");
      sc.nextLine();   
   }
   
   public static void main(String[] args) {
      sc = new Scanner(System.in);
      
      //game start
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
      islands.add(new Island(-0.4,  0.5)); // 2
      islands.add(new Island( 0.4,  0.2)); // 3
      islands.add(new Island( 0.9,  0.9)); // 4
      
      System.out.println(player1 + " what island would you like to start out at");
      for(int asdf = 0; asdf < islands.size(); asdf++ )
      {
         System.out.print(asdf + " ");
      }
      int starting_island = sc.nextInt();
      islands.get(starting_island).owner = player1;
      islands.get(starting_island).strength = 5;
      wwait();
      
      System.out.println(player2 + " what island would you like to start out at");
      for(int asdf = 0; asdf < islands.size(); asdf++ )
      {
         System.out.print(asdf + " ");
      }
      starting_island = sc.nextInt();
      islands.get(starting_island).owner = player2;
      islands.get(starting_island).strength = 5;
      wwait();      
      
      String player = "place holder";
      boolean playerr = false;
      boolean finished = false;
      while(! finished)
      {
         playerr = !playerr;
         if (playerr)
         {
            player = player1;
         }
         else
         {
            player = player2;
         }
         System.out.println( player + " what would you like to do on your turn? you own");
         for (int i = 0; i < islands.size(); i++ )
         {
            if (islands.get(i).owner.equals(player))
            {
               System.out.println("the " + i + "th island");
            }
         }
         System.out.println("select a island you own");
         int selected_island = sc.nextInt();
         System.out.println("would you like to do a plane or ship attack?");
         String attack = sc.next();
         if (attack.equals("plane"))
         {
            System.out.println("which island would you like to attack?");
            for(int i = 0; i < islands.size(); i++ )  
            {
               double dist = islands.get(selected_island).distanceTo(islands.get(i)); 
               if (dist < Island.plane_range && !islands.get(i).owner.equals(player))
               {
                  System.out.println(i);
               }
            }
            int target_island = sc.nextInt();
            islands.get(selected_island).planeAttack(islands.get(target_island)); 
         }
       
         else if (attack.equals("ship"))
         {
            System.out.println("which island would you like to attacK?");
            for(int i = 0; i < islands.size(); i++)
            {
               double dist = islands.get(selected_island).distanceTo(islands.get(i));
               if (dist < Island.ship_range && !islands.get(i).owner.equals(player))
               {
                  System.out.println(i);
               }
            }
            int target_island = sc.nextInt();
            islands.get(selected_island).shipAttack(islands.get(target_island));
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
      for(int i = 0; i < islands.size(); i++ )  
      {
         double dist = islands.get(yourisland).distanceTo(islands.get(i)); 
         if (dist != 0.0 && dist < Island.ship_range)
         {
            System.out.println(yourisland + " --> " + i + " " + dist);
         }
      }  
   }
}