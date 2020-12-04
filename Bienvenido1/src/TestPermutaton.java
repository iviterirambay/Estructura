/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Familia Rambay
 */
public class TestPermutaton {
    public static void main(String [] args){
        for(int i=0;i<9;i++){
            for(int j =0;j<=i;j++)
                System.out.print(p(i,j) + "\t");
            System.out.println();
        }
    }
    public static long p(int n, int k){
        long p=1;
        for(int i=0;i<k;i++)
            p *=n--;
        return p;
    }
}
