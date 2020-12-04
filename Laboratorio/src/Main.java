/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Scanner;
import net.datastructures.*;


/**
 *
 * @author cltsmalc
 */
public class Main {


    public static void main(String[] args) {
        
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\nIngrese una cadena de numeros separados con coma(,).\n\t\tEjemplo:1,2,3");
        Scanner scanner = new Scanner(System.in);
        String cadena = scanner.next();
        LinkedBinaryTree<Integer> SBT = new LinkedBinaryTree<Integer>();
        SBT = ArmarTree(cadena);
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\nIngrese el numero que desea BUSCAR");
        Integer num = scanner.nextInt();
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.");
        Position ref = SBT.root();
        SearchNodEsbt(SBT, num, ref);
       
      
    }

     public static Position<Integer> SearchNodEsbt(LinkedBinaryTree<Integer> SBT,Integer clave,Position<Integer> nodoB){
       int i = 0;
       
         if(!SBT.hasRight(nodoB) && clave>nodoB.element())
                return null;
            else{
                if(!SBT.hasLeft(nodoB) && clave<nodoB.element())
                    return null;
                else{
                    if(clave==nodoB.element())
                        return nodoB;
                    else{
                        if(clave>nodoB.element()){
                            i++;
                            System.out.println("------>\tIzquierda"+"  "+i );
                            return SearchNodEsbt(SBT,clave,SBT.right(nodoB));}
                        
                        else
                            i++;
                            System.out.println("------>\tDerecha"+"  "+  i );
                            return SearchNodEsbt(SBT,clave,SBT.left(nodoB));
                    }
                }
            }
    }
     
public static LinkedBinaryTree<Integer> ArmarTree(String cadena){
   LinkedBinaryTree<Integer> SBT = new LinkedBinaryTree<Integer>();
   String splitarray[] = cadena.split(",");
   Integer nume = null;
   nume = Integer.parseInt(splitarray[0]);
   SBT.addRoot(nume);
   Position ref= SBT.root();
   for(int i = 1; i < splitarray.length ; i++){
       nume = Integer.parseInt(splitarray[i]);    
         Insert(SBT, nume, ref);
      
      }
     
     return SBT;
}
     
      public static void Insert(LinkedBinaryTree<Integer> SBT,Integer X,Position<Integer> nodoB){
        if(SBT.isEmpty())
            SBT.addRoot(X);
        else{
            if(!SBT.hasRight(nodoB) && X>nodoB.element())
                SBT.insertRight(nodoB, X);
            else{
                if(!SBT.hasLeft(nodoB) && X<=nodoB.element())
                    SBT.insertLeft(nodoB, X);
                else{
                    if(X>nodoB.element())
                        Insert(SBT,X,SBT.right(nodoB));
                    else
                        Insert(SBT,X,SBT.left(nodoB));
                }
            }
        }

    }
}
