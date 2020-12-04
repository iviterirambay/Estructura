

import java.io.*;

public class TestArrayTDA_struct2 {

    /** Main method */
    public static void main(String[] args) {
        final int TOTAL_NUMBERS = 5;
        int i, idM;
        BufferedReader readbuffer;
        String strRead;
        
        estudiante temp;
        estudiante[] curso = new estudiante[TOTAL_NUMBERS];

        System.out.println(curso.length);

        try {
            readbuffer = new BufferedReader(new FileReader("CSI_data.txt"));
      

        i = 0;
        
            while (i < TOTAL_NUMBERS) {
                strRead = readbuffer.readLine();
                temp = new estudiante();
                String splitarray[] = strRead.split("\t");
                temp.num_mat = Integer.parseInt(splitarray[0]);
                temp.nombre = splitarray[1];
                temp.nota_fund = Double.parseDouble(splitarray[2]);
                curso[i] = temp;
                i++;
            
            }
        
            readbuffer.close();
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());

        }

        
        idM = 0;
        for (i = 1; i < curso.length; i++) {
            if (Mejor(curso, idM, i) != idM) {
                idM = i;
            }
        }
        
        /*for(i = 0; i <curso.length; i++) {
        System.out.println(curso[i].num_mat+ " "+curso[i].nombre+ " "curso[i].nota_fund+ " "curso[i].nota_std);
    }*/

        System.out.println("el mejor estudiante del curso pasado fue = " + curso[idM].nombre);

    }

    static int Mejor(estudiante[] curso, int id1, int id2) {
        if (curso[id2].nota_fund > curso[id1].nota_fund) {
            return (id2);
        }
        return (id1);
    }
}

/*class estudiante {

    public int num_mat;
    public String nombre;
    public double nota_fund;
    public double nota_std;
    public boolean ayudante;
}
*/

