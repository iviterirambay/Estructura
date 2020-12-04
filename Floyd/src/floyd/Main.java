/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package floyd;

/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int AdjMat[][] = new int[5][5];
        int P[][] = new int[5][5];

         for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
               AdjMat[i][j]=5000;
            }
        }

        AdjMat[0][3] = 1;
        AdjMat[1][3] = 3;
        AdjMat[2][1] = 5;
        AdjMat[3][4] = 2;
        AdjMat[4][2] = 1;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                P[i][j] = AdjMat[i][j];
            }
        }

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (P[i][j] < (P[i][k] + P[k][j]))
                        P[i][j] =  P[i][j];
                    else
                        P[i][j] = (P[i][k] + P[k][j]);
                }
            }
            PrintMatrix(P);

        }
    }
public static void PrintMatrix(int P[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (P[i][j]<5000) {
                    System.out.print(P[i][j]+",");
                } else {
                    System.out.print("-,");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}



