import java.util.*;
public class codeBreaker
{
   static ArrayList<String> possibilities=new ArrayList<String>();
   static String guess="12345", temp, g;
   static int black=0, white, r, w;
   public static void editTemp(int i, int j)
   {
      if(i<1)
         temp=temp.substring(1);
      else
         temp=temp.substring(0, i)+temp.substring(i+1);
      if(j<1)
         g=g.substring(1);
      else
         g=g.substring(0, j)+g.substring(j+1);
   }
   public static boolean shouldRemove(int index)
   {
      g=guess;
      if(index>=possibilities.size())
         return false;
      boolean[] keks=new boolean[5];
      for(int i=0; i<5; i++)
         keks[i]=false;
      temp=possibilities.get(index);
      r=0;
      w=0;
      for(int i=0; i<5; i++)
         if(temp.charAt(i)==g.charAt(i))
         {
            r++;
            keks[i]=true;
         }
      for(int i=4; i>=0; i--)
         if(keks[i])
            editTemp(i, i);
      for(int i=0; i<g.length(); i++)
         while(i<g.length()&&temp.contains(""+g.charAt(i)))
         {
            w++;
            editTemp(temp.indexOf(""+g.charAt(i)), i);
         }
      return r!=black||w!=white;
   }
   public static void main(String[] args)
   {
      Scanner input=new Scanner(System.in);
      for(int a=1; a<9; a++)
         for(int b=1; b<9; b++)
            for(int c=1; c<9; c++)
               for(int d=1; d<9; d++)
                  for(int e=1; e<9; e++)
                     possibilities.add(a+""+b+""+c+""+d+""+e);
      for(int i=0; i<8&&black<5; i++)
      {
         System.out.println("My guess is "+guess+".");
         System.out.println("How many black pegs are there?");
         black=input.nextInt();
         System.out.println("How many white pegs are there?");
         white=input.nextInt();
         for(int j=0; j<possibilities.size(); j++)
            while(shouldRemove(j))
               possibilities.remove(j);
         guess=possibilities.get((int)(Math.random()*possibilities.size()));
      }
      if(black>4)
         System.out.println("I guessed your number!");
      else
      {
         System.out.println("Your number could have been...");
         for(int i=possibilities.size()-1; i>=0; i--)
            System.out.println(possibilities.get(i));
      }
   }
}