/*
 * DADA DOS LISTAS SE CREA OTRA LISTA CON LOS NUMERO REPETIDOS DE LAS DOS
 * LISTAS INICIALES
 */


/**
 *
 * @author Francisco
 */



import java.util.ArrayList;
import java.util.Scanner;

public class manejolistas {

    //estructuras
    
    public static void main(String[]args)
    {
        ArrayList<Integer> A =new ArrayList<Integer>();
        ArrayList<Integer> B =new ArrayList<Integer>();
        ArrayList<Integer> C =new ArrayList<Integer>();
        Scanner consola=new Scanner(System.in);

        int x,y;
        System.out.print("con cuantos numeros quieres que se ingrese la primera lista: ");
        x=consola.nextInt();
        for(int i=0;i<x;i++)
        {
            System.out.print("ingres el numero    "+(i+1)+"   ");
            A.add(consola.nextInt());
        }
        System.out.println();
        System.out.print("con cuantos numeros quieres que se ingrese la segunda lista: ");
        y=consola.nextInt();
        for(int i=0;i<y;i++)
        {
            System.out.print("ingres el numero    "+(i+1)+"   ");
            B.add(consola.nextInt());
        }
        A=retornar(A);
        B=retornar(B);

        // SE VE CUAL ESTAN REPETIDOS
        for(int i=0;i<A.size();i++)
        {
            for(int j=0;j<B.size();j++)
            {
                if(A.get(i).equals(B.get(j)))
                {
                    C.add(B.get(j));
                    B.remove(j);

                }

            }
        }

        System.out.println();
        System.out.print("la lista con los numero repetidos es: ");

        for(int i=0;i<C.size();i++)
        {
            System.out.print(C.get(i));

        }

        System.out.println();


    }
    //ESTA FUNCION SI LE ENVIAS UNA LISTA CON NUMEROS REPETIDOS
    //TE LA DEVUELVE SIN NINGUN NUMERO REPETIDO
    public static ArrayList<Integer> retornar(ArrayList<Integer> temp)
    {
        
        for(int i=0;i<temp.size();i++)
        {
            for(int j=i+1;j<temp.size();j++)
            {
                if(temp.get(i).equals(temp.get(j)))
                    temp.remove(j);

            }
        }
        return temp;

    }

    
}
