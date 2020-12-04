/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Francisco
 */
public class VIAJEROINTERGALACTICO {

    /**
     * @param args the command line arguments
     */
    static class Viajero
    {
        int IDviajero;
        String Viajero;
        Stack<Integer> Paradas=new Stack<Integer>();
    }
    static class Camino
    {
        int []GuiaCompleta=new int[43];
        int IDviajero;
    }

    public static void main(String[] args) {

        int X;
        Scanner consola=new Scanner(System.in);
        ArrayList<Viajero> listaviajeros=new ArrayList<Viajero>();
        do
        {        
            System.out.print("Cuantos viajeros habran:");
            X=consola.nextInt();
        }while(X<=1);
        for(int i=0;i<X;i++)
        {
            Viajero temp=new Viajero();
            temp.IDviajero=i+1;
            System.out.print("Ingresa el nombre del viajero N "+(i+1)+" :");            
            temp.Viajero=consola.next();
            listaviajeros.add(temp);
        }
            System.out.println();
            System.out.println();
          turno(listaviajeros);
        // TODO code application logic here
    }
    public static void turno(ArrayList<Viajero> listaviajeros) 
    {
        
        Scanner consola=new Scanner(System.in);
        Camino GUIA=new Camino();
        for(int i=0;i<GUIA.GuiaCompleta.length;i++)
        {
            GUIA.GuiaCompleta[i]=0;
        }
        for(int i=0;i<listaviajeros.size();i++)
            {
                listaviajeros.get(i).Paradas.push(0);
            }

        do
        {
            
            for(int i=0;i<listaviajeros.size();i++)
            {
                int dado1,dado2,dados;
                dado1=1+(int)(Math.random()*7);
                dado2=1+(int)(Math.random()*7);
                dados=dado1+dado2;
                
                
                if(listaviajeros.get(i).Paradas.peek()+dados<=42)
                {
                    if(GUIA.GuiaCompleta[listaviajeros.get(i).Paradas.peek()+dados]==0)
                    {

                        listaviajeros.get(i).Paradas.push(listaviajeros.get(i).Paradas.peek()+dados);
                        GUIA.GuiaCompleta[listaviajeros.get(i).Paradas.peek()]=listaviajeros.get(i).IDviajero;
                        System.out.println("El jugador "+listaviajeros.get(i).Viajero+" saco en los dados "+dados+" y esta en la posicion "+listaviajeros.get(i).Paradas.peek());
                       // System.out.println("El jugador "+listaviajeros.get(i).Viajero+" esta en la posicion "+listaviajeros.get(i).Paradas.peek());
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("El jugador "+listaviajeros.get(i).Viajero+" saco en los dados "+dados+" pero EN ESA POSICION SE ENCUENTRA EL JUGADOR "+listaviajeros.get(GUIA.GuiaCompleta[listaviajeros.get(i).Paradas.peek()+dados]-1).Viajero+" no avanza el jugador ");
                        System.out.println();
                    }

                }
                else
                {
                    System.out.println("Se repite el lanzamiento de dados porque se paso de la posicion");
                    System.out.println();
                }
                
            }
            
        }while(GUIA.GuiaCompleta[42]==0);
        
        recorrido(listaviajeros);
        // TODO code application logic here
    }
    public static void recorrido(ArrayList<Viajero> listaviajeros) 
    { 
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
           
        for(int i=0;i<listaviajeros.size();i++)
        {
            if(listaviajeros.get(i).Paradas.peek()==42)
            {
                 System.out.println("El jugador que termino el viaje es: "+listaviajeros.get(i).Viajero);
                System.out.println("Paso por las paradas siguientes : ");

                 
                for(int j=1;j<listaviajeros.get(i).Paradas.size();j++)
                {
                    System.out.println("el lugar numero "+j+" fue: "+listaviajeros.get(i).Paradas.get(j));
                }

            }
                    
            
            
        }
               
                    
       
    }

}
