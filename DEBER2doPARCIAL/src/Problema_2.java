/*******************************************************************************
 * Escriba un programa en java que permita el ingreso de un conjunto de vertices
 * y genere el arbol binario de busqueda. Además deberá permitir:
 * (a) Consultar un Valor  
 * (b) Agregar un vértice 
 * (c) Remover un vértice
 ******************************************************************************/


//*******************************LIBRERIAS**************************************
import java.util.Scanner;
import net.datastructures.*;


public class Problema_2{
    public static void main(String[] args) {
        //************************VARIABLES A USAR******************************
        Scanner lector = new Scanner(System.in);
        int i, num_v, vert;
        Nodo_busqueda A,R;
        Position<Nodo_busqueda> B; 
        LinkedBinaryTree<Nodo_busqueda> Arbol_ABB = new LinkedBinaryTree<Nodo_busqueda>();//Arbol Binario de Busqueda
        Nodo_busqueda X= new Nodo_busqueda();//Nodo temporal que almacenar el contenido al Arbol
        
        
        
        System.out.print("\n\t\t\t\t\t¿Cuántos vértices desea ingresar?\n\t\t\t\t\t\t");
        num_v = lector.nextInt();
        
        System.out.println("Escriba sus vertices");
        
        for(i=0; i<num_v; i++)
        {
            System.out.print("***********************************\nVertice " +(i+1) +": ");
            vert = lector.nextInt();
            if(i==0)
            {
                X.valor=vert;
                InsertNode(Arbol_ABB,X,null);//Solo al 1er valor lo colocara como raiz
            }
            else
            {
                X= new Nodo_busqueda();
                X.valor=vert;
                InsertNode(Arbol_ABB,X,Arbol_ABB.root());
            }    
        }
        do{
            MENU();
           do{
            System.out.print("Escriba su opcion: ");
            i = lector.nextInt();
           }while(i<1 || i>3);
           int valor;
            switch(i)
            {
                case 1:
                    //CONSULTA LOS VALORES EN EL ARBOL-----------------------
                    System.out.print("Escriba el vertice a buscar:-----> ");
                    valor = lector.nextInt();
                    B = Consultar_nodo(Arbol_ABB,valor, Arbol_ABB.root());
                    if(B!=null)
                        System.out.println("El vertice es:--> " +B.element().valor);
                    else
                        System.out.println("El valor no existe");
                    break;
                    //AGREGA UN NUEVO VERTICE AL ARBOL-----------------------
                case 2:
                    A = new Nodo_busqueda();
                    System.out.print("Escriba su nodo a agregar:----> ");
                    valor = lector.nextInt();
                    A.valor = valor;
                    InsertNode(Arbol_ABB, A, Arbol_ABB.root());
                    break;
                    //ELIMINA UN NODO DADO POR EL USUARIO AL ARBOL
                case 3:
                    A = new Nodo_busqueda();
                    R = new Nodo_busqueda();
                    System.out.print("Escriba su nodo a remover:-----> ");
                    valor = lector.nextInt();
                    A.valor = valor;
                    R = Remover_nodo(Arbol_ABB, A,Arbol_ABB.root());
                    if(R!=null)
                        System.out.println("El nodo [" +R.valor +"] fue removido con exito!!!");
                    else
                        System.out.println("No existe ese nodo");           
                    break;
            }
        }while(true);
        
    }
    
 //*********************************METODOS*************************************   
    
    //INSERTA UN NODO EN EL ARBOL DE ACUERDO AL VALOR DE LA RAIZ DEL ARBOL Y DE CADA SUBARBOL
    public static void InsertNode(LinkedBinaryTree<Nodo_busqueda> SBT,Nodo_busqueda X,Position<Nodo_busqueda> nodoB){
        if(SBT.isEmpty())//Si el ARBOL esta vacio agrego a la RAIZ
            SBT.addRoot(X);
        else{
            if(!SBT.hasRight(nodoB) && X.valor>nodoB.element().valor)//Si el valor de la RAIZ es menor que el valor a agregar, si es que no hay vertice a su derecha
                SBT.insertRight(nodoB, X);
            else{
                if(!SBT.hasLeft(nodoB) && X.valor<=nodoB.element().valor)//Si el valor de la RAIZ es MAYOR o IGUAL que el valor a agregar, si es que no hay vertice a su derecha
                    SBT.insertLeft(nodoB, X);
                else{
                    if(X.valor>nodoB.element().valor)
                        InsertNode(SBT,X,SBT.right(nodoB));
                    else
                        InsertNode(SBT,X,SBT.left(nodoB));
                }
            }
        }

    }
    static class Nodo_busqueda{//Tipo de dato para crear la clave
        int valor;//Valor que contiene el vertice
    }
    
    //BUSCA EN EL ARBOL EL VALOR PEDIDO POR EL USUARIO Y RETORNA SU POSICION
    public static Position<Nodo_busqueda> Consultar_nodo(LinkedBinaryTree<Nodo_busqueda> SBT, int V, Position<Nodo_busqueda> A)
    {
        if(!SBT.hasLeft(A) && !SBT.hasRight(A))//Si llega una hoja
        {
            if(A.element().valor == V)//Si es igual el valor que estoy buscando 
                return (A);//Retorna dicho valor
            else
                return null;//Retorna un vertice nulo
        }
        else
        {
            if(V == A.element().valor)//Si da el caso de buscar un valor que este como raiz
                return A;
            else if(V < A.element().valor)// Si el valor es menor que la RAIZ
            {
                if(SBT.hasLeft(A))//Si tiene un vertice izquierdo, entonces siga buscando
                    return(Consultar_nodo(SBT,V,SBT.left(A)));
                else
                    return null;//Retorna un vertice nulo
            }
            else
            {
                if(SBT.hasRight(A))//Si tiene un vertice derecho, entonces siga buscando
                    return(Consultar_nodo(SBT,V,SBT.right(A)));
                else
                    return null;//Retorna un vertice nulo
            }
        }
    }
    
    public static Nodo_busqueda Remover_nodo(LinkedBinaryTree<Nodo_busqueda> SBT, Nodo_busqueda clave, Position<Nodo_busqueda> A)
    {
        Position<Nodo_busqueda> nodoX = Consultar_nodo(SBT,clave.valor,A);
        if(nodoX==null)
            return null;
        if(SBT.isExternal(nodoX))
            return SBT.remove(nodoX);
        Position<Nodo_busqueda> padreA;
        Nodo_busqueda contenidoRem = nodoX.element(),raiz;
        LinkedBinaryTree<Nodo_busqueda> nuevoSubArbolIzq=new LinkedBinaryTree<Nodo_busqueda>(),nuevoSubArbolDer=new LinkedBinaryTree<Nodo_busqueda>();
        if(!SBT.hasRight(nodoX)){
            raiz=SBT.left(nodoX).element();
            if(SBT.hasLeft(SBT.left(nodoX)))
                nuevoSubArbolIzq=DuplicarArbolBinario(SBT,SBT.left(SBT.left(nodoX)));
            if(SBT.hasRight(SBT.left(nodoX)))
                nuevoSubArbolDer=DuplicarArbolBinario(SBT,SBT.right(SBT.left(nodoX)));
            RemoverSubArbolBinario(SBT,SBT.left(nodoX));
            SBT.attach(nodoX,nuevoSubArbolIzq ,nuevoSubArbolDer);
            SBT.replace(nodoX, raiz);
        }
        else{
            if(!SBT.hasLeft(nodoX)){
                raiz=SBT.right(nodoX).element();
                if(SBT.hasLeft(SBT.right(nodoX)))
                    nuevoSubArbolIzq=DuplicarArbolBinario(SBT,SBT.left(SBT.right(nodoX)));
                if(SBT.hasRight(SBT.right(nodoX)))
                    nuevoSubArbolDer=DuplicarArbolBinario(SBT,SBT.right(SBT.right(nodoX)));
                RemoverSubArbolBinario(SBT,SBT.right(nodoX));
                SBT.attach(nodoX,nuevoSubArbolIzq ,nuevoSubArbolDer);
                SBT.replace(nodoX, raiz);
            }
            else{
                A=SBT.left(nodoX);
                while(SBT.hasRight(A))
                    A=SBT.right(A);
                raiz=A.element();
                if(SBT.hasLeft(A)){
                    padreA=SBT.parent(A);
                    if(SBT.hasLeft(padreA)){
                        nuevoSubArbolIzq=DuplicarArbolBinario(SBT,SBT.left(padreA));
                        RemoverSubArbolBinario(SBT,SBT.left(padreA));
                    }
                    nuevoSubArbolDer=DuplicarArbolBinario(SBT,SBT.left(A));
                    RemoverSubArbolBinario(SBT,A);
                    SBT.attach(padreA,nuevoSubArbolIzq,nuevoSubArbolDer);
                }
                else{
                    RemoverSubArbolBinario(SBT,A);
                }
                SBT.replace(nodoX,raiz);
            } 
        }
        return contenidoRem;
    }
    public static LinkedBinaryTree DuplicarArbolBinario(LinkedBinaryTree arbolOriginal,Position nodoBO){

        LinkedBinaryTree subArbolIzq=new LinkedBinaryTree();//Almacenara el sub-arbol izquierdo de una raiz
        LinkedBinaryTree subArbolDer=new LinkedBinaryTree();//Almacenara el sub-arbol derechi de una raiz
        Position raiz;
        LinkedBinaryTree arbolNuevo=new LinkedBinaryTree();//Almacena el nuevo arbol que sera retornado
        if(arbolOriginal.isExternal(nodoBO)){
            raiz=arbolNuevo.addRoot(nodoBO.element());//Almacena la posicion de la RAIZ principal del nuevo ARBOl
            return arbolNuevo;
        }
        else{
           if(arbolOriginal.hasLeft(nodoBO))
                subArbolIzq=DuplicarArbolBinario(arbolOriginal,arbolOriginal.left(nodoBO));
            if(arbolOriginal.hasRight(nodoBO))
                subArbolDer=DuplicarArbolBinario(arbolOriginal,arbolOriginal.right(nodoBO));
            raiz=arbolNuevo.addRoot(nodoBO.element());

            arbolNuevo.attach(raiz, subArbolIzq, subArbolDer);
            return arbolNuevo;
        }
    }
    public static void RemoverSubArbolBinario(LinkedBinaryTree arbol,Position<Nodo_busqueda> nodoB){
        if(arbol.isEmpty())//Si el arbol esta vacio
            return;//Detiene la recursividad
        if(arbol.isExternal(nodoB))//Verficia si el nodo no pertenece a esta parte del sub-arbol
            arbol.remove(nodoB);//Remueve el nodo en dicha posicion
        else{
            if(arbol.hasLeft(nodoB))
                RemoverSubArbolBinario(arbol,arbol.left(nodoB));
            if(arbol.hasRight(nodoB))
                RemoverSubArbolBinario(arbol,arbol.right(nodoB));
            RemoverSubArbolBinario(arbol,nodoB);
        }
    }
    public static void MENU(){
        System.out.println("***\t***\t***\t***\t***\t***\t***\t***\t***\t***\n\t\t\t\tOperaciones que se pueden realizar");
        System.out.println("\t\t\t1.- Consultar valor\n"+
                               "\t\t2.- Agregar vertice\n"+
                               "\t3.- Remover vertice\n***\t***\t***\t***\t***\t***\t***\t***\t***\t***");
    }
    

}
