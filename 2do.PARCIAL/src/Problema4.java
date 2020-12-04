/******************************************************************************
 * Escriba un programa en java que permita el ingreso de una expresión postfix,
 * con la cual genere el arbol de expresiones y evalué la expresión mostrando 
 * el resultado.
 *****************************************************************************/

//*******************************LIBRERIAS************************************
import java.util.Scanner;
import java.util.Stack;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;
/**
 *
 * @author irabvite
 */
public class Problema4 {
    public static void main (String[] args){
       //***************************VARIABLES A USAR****************************
        Stack<LinkedBinaryTree> stack = new <LinkedBinaryTree> Stack();
        Position referencia;
        LinkedBinaryTree expresion1,expresion2,expresiones;
        Scanner scanner = new Scanner (System.in);
        String string;
        char caracter;
        int indice_expresion;
        //***********************CUERPO DEL PROBLEMA****************************
        System.out.println("Escriba la expresión en postfix a ser evaluada: ");
        string = scanner.next();
        System.out.println(".-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.\n");
        for(indice_expresion=0;indice_expresion<string.length();indice_expresion++){
            caracter=string.charAt(indice_expresion);
        if((caracter=='+')|(caracter=='-')|(caracter=='*')|(caracter=='/')){
            expresion2=stack.pop();
            expresion1=stack.pop();
            expresiones=new LinkedBinaryTree();
            referencia=expresiones.addRoot(caracter);
            expresiones.attach(referencia, expresion1, expresion2);
            stack.push(expresiones);
            System.out.println("");
            }
                
        else{
            expresiones=new LinkedBinaryTree();
            expresiones.addRoot(caracter);
            stack.push(expresiones);
            }
        }
        expresiones=stack.pop();
        EvaluarArbolExpresion(expresiones,expresiones.root());
        System.out.println("\n");                
}
    //******************************METODOS*************************************
    public static void Recorrer_pos_orden(LinkedBinaryTree AB,Position ref){
            if (AB.hasLeft(ref))
                Recorrer_pos_orden(AB,AB.left(ref));
            if (AB.hasRight(ref))
                 Recorrer_pos_orden(AB,AB.right(ref));
            String data= ref.element().toString();
            System.out.print(data);
            return;
    }
    public static int EvaluarArbolExpresion(LinkedBinaryTree<String> ArbolB,Position<String> nodoB){
        if(ArbolB.isExternal(nodoB))
            return Integer.parseInt(nodoB.element());
        else{
            if(nodoB.element().equals("+"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB)) + EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB));
            if(nodoB.element().equals("-"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB)) - EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB));
            if(nodoB.element().equals("*"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB)) * EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB));
            return EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB)) / EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB));
        }
    }
 
}
//AB*CD*-H+