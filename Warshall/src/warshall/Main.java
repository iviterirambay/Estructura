/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package warshall;

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
        boolean AdjMat[][] = new boolean[5][5];
        boolean P[][] = new boolean[5][5];

        AdjMat[0][3] = true;
        AdjMat[1][3] = true;
        AdjMat[2][1] = true;
        AdjMat[3][4] = true;
        AdjMat[4][2] = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                P[i][j] = AdjMat[i][j];
            }
        }

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    P[i][j] = P[i][j] | (P[i][k] & P[k][j]);
                }
            }
            PrintMatrix(P);

        }



    }

    public static void PrintMatrix(boolean P[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (P[i][j]) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}

