/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Francisco
 */
import java.util.Scanner;
import java.util.Stack;
public class problemadepilasexamen {
public static void main(String[]Args)
    {

        Stack<Integer> B=new Stack<Integer>();

       Scanner consola =new Scanner(System.in);

       int x,y,suma;

        B.push(1);
        B.push(1);
        System.out.print("cuantos numeros de la serie requiere: ");
        x=consola.nextInt();

        for(int i=0;i<x-2;i++)
        {
            y=B.pop();
            suma=B.peek()+y;
            B.push(y);
            B.push(suma);
        }

        imprimir(B);

    }

public static void imprimir( Stack<Integer> B)
    {
    
    
        Stack<Integer> temp=new Stack<Integer>();

        int tem=B.size();
        for(int i=0;i<tem;i++)
        {
            temp.push(B.pop());

        }

        System.out.println();

         System.out.println("la serie de fibonachi es ");

            System.out.println();
        for(int i=0;i<tem;i++)
        {
            System.out.print(temp.pop()+",");

        }

            System.out.println();

        }

}
