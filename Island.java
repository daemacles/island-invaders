import java.lang.Math;

public class Island {

// island stats
   public static double ship_range = 1.00;
   public static double plane_range = 2.0;

   public int strength;
   public String owner;
   public double x;
   public double y;
   public boolean turn_done;

   public Island (double xx, double yy) {
      strength = 0;
      owner = "neutral";
      x = xx;
      y = yy;
      turn_done = false;
   }
   
   public double distanceTo (Island other){
      double dx = x - other.x;
      double dy = y - other.y;
      double dist = Math.sqrt(dx*dx + dy*dy); 
      return dist;
   }   
   
   public void shipAttack (Island other){
      if (distanceTo(other) <= ship_range)
      {
         other.strength -= 2;
         System.out.println("you hit your target, it's strength is now " + other.strength);
         if (other.strength < 0)
         {
            other.owner = owner;
            other.strength = 5;
            System.out.println(owner + ", you now own island" + other);
         }
         turn_done = true;
      }
      else 
      {
         System.out.println("how did you fail??");
      }   
   }
   
   public void planeAttack (Island other){
      if ( other.strength > 0 && distanceTo(other) <= plane_range)
      {
         other.strength -= 1;
         System.out.println("you hit your target, it's strength is now " + other.strength);
         turn_done = true;
      }
      else
      {
         System.out.println("Your planes return with all their armaments...");
      }
   }
   
   public void nextTurn() {
      if( owner != "neutral" && strength <= 5 )
      {
         strength++;
      }
   }
   
   public String toString() {
      String done = turn_done ? "*" : " ";
      return String.format("[(%f, %f) %s str=%d owner=%s]", x, y, done, strength, owner); 
   }
}
