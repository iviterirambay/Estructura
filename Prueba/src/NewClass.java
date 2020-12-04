/*
 * ESTRUCTURAS DE DATOS
 *
 * EvaluaciÃ³n de una expresiÃ³n infija representada en un Ã¡rbol binario
 * NOTA: Se asume que el arbol de expresion esta correctamente estructurado,
 *       y solo resuelve operaciones binarias, es decir no raices, cuadrados, etc.
 *      El grafico de el arbol de este ejemplo se encuentra en el archivo ARBOL EXPRESION.ppt
 *      que se encuentra dentro de la carpeta de este proyecto
 */


import net.datastructures.*;
/**
 *
 * @author Pedro Lucas B.
 */
public class NewClass {

    public static void main(String[] args) {
         LinkedBinaryTree<String> ArbolB=new LinkedBinaryTree<String>();
        ArbolB.addRoot("+");
        Position nodoB= ArbolB.root();
        nodoB=ArbolB.insertRight(nodoB,"12");
        nodoB= ArbolB.root();
        nodoB=ArbolB.insertLeft(nodoB,"-");
        nodoB=ArbolB.insertLeft(nodoB,"*");
        nodoB=ArbolB.insertLeft(nodoB,"5");
        nodoB=ArbolB.parent(nodoB);
        nodoB=ArbolB.insertRight(nodoB,"7");
        nodoB=ArbolB.parent(ArbolB.parent(nodoB));
        nodoB=ArbolB.insertRight(nodoB,"/");
        nodoB=ArbolB.insertLeft(nodoB,"8");
        nodoB=ArbolB.parent(nodoB);
        nodoB=ArbolB.insertRight(nodoB,"4");

        System.out.println("Recorrido de Arbol de Expresion en Pre Orden");
        Recorrido_PreOrden(ArbolB,ArbolB.root());
        System.out.println("\nRESULTADO:");
        System.out.println(EvaluarArbolExpresion(ArbolB,ArbolB.root()));
    }

    public static int EvaluarArbolExpresion(LinkedBinaryTree<String> ArbolB,Position<String> nodoB){
        if(ArbolB.isExternal(nodoB))
            return Integer.parseInt(nodoB.element());
        else{
            if(nodoB.element().equals("+"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB)) + EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB));
            if(nodoB.element().equals("-"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB)) - EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB));
            if(nodoB.element().equals("*"))
                return EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB)) * EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB));
            return EvaluarArbolExpresion(ArbolB,ArbolB.left(nodoB)) / EvaluarArbolExpresion(ArbolB,ArbolB.right(nodoB));
        }
    }

    
      public static void Recorrido_PreOrden(LinkedBinaryTree ArbolB,Position nodoB){

            System.out.println(nodoB.element());
            if (ArbolB.hasLeft(nodoB))
                Recorrido_PreOrden(ArbolB,ArbolB.left(nodoB));
            if (ArbolB.hasRight(nodoB))
                 Recorrido_PreOrden(ArbolB,ArbolB.right(nodoB));
    }

}