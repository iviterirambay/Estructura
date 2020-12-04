/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author irabvite
 */
public class TestCombination {
   public static void main(String []args){
       for(int i=0;i<9;i++){
           for(int j=0;j<=i;j++)
               System.out.print(c(i,j) + "\t");
           System.out.println();
       }
   }
   public static long c(int n, int k){
       return p(n,k)/f(k);
   }
   public static long f(int n){
       long f=1;
       while(n>1)
           f *=n--;
       return f;
   }
   public static long p (int n, int k){
       long p=1;
       for (int i = 0;i < k; i++)
           p*=n--;
       return p;
   }
}
