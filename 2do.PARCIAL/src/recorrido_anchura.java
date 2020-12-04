/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import net.datastructures.*;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * @author daniel
 */
public class recorrido_anchura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String vertices[]= { "A","B","C","D","H","R","T"};
        String arcos[]= {"D,B","D,C","C,R","B,H","R,H","H,A","H,T","H,D"};
        Vertex <Parada>  V,V1 = null,V2 = null;
        Parada dato;

        AdjacencyListGraph G= new AdjacencyListGraph();
        for (int i =0; i< vertices.length;i++){
            dato=new Parada();
            dato.nombre=vertices[i];
            dato.visitado=false;
            V=G.insertVertex(dato);
        }
        for (int i =0; i< arcos.length;i++){
            //obtengo los vertices
            String v1_id=arcos[i].substring(0,1);
            String v2_id=arcos[i].substring(2,3);
            V1=BuscarVertice(G,v1_id);
            V2=BuscarVertice(G,v2_id);
            if (V1!=null & V2!=null){
                G.insertEdge(V1,V2, 1);
            }
        }
        //Muestro en pantalla los datos
        Iterator<Parada> listv= G.vertices().iterator();
        Iterator liste= G.edges().iterator();
        while (listv.hasNext()){
            V=(Vertex)listv.next();
            System.out.println(V.element().nombre);
        }
        while (liste.hasNext()){
              Edge E=(Edge)liste.next();
            System.out.println(E.element());
        }
        System.out.println("recorrido en anchura");
        RecorroEnAnchura(G,"C");

        listv= G.vertices().iterator();
        while (listv.hasNext()){
            V=(Vertex)listv.next();
            V.element().visitado=false;
        }
        System.out.println("recorrido en profundidad");
        RecorroEnProfundidad(G,"C");
    }

 public static Vertex BuscarVertice(AdjacencyListGraph G,String Data){
   Vertex<Parada> V;
   Iterator listv= G.vertices().iterator();

   while (listv.hasNext())
        {
            V=(Vertex)listv.next();
            Parada Vdata=V.element();
            if (Vdata.nombre.compareTo(Data)==0)
                return(V);
            }
    return(null);
}


public static void RecorroEnAnchura(AdjacencyListGraph G,String Data){
   
   Queue <Vertex> cola = new NodeQueue();
   Vertex<Parada> V,V_adj=null;
   Iterator<Edge> liste;
   Edge E;
   Vertex<Parada> Ends[];

   V=BuscarVertice(G,Data);
   cola.enqueue(V);

   while (!cola.isEmpty()){
        V=cola.dequeue(); //saco el frente de la cola
        V.element().visitado=true;

        System.out.println(V.element().nombre);
        

        liste= G.incidentEdges(V).iterator(); //reviso si hay arcos
        while (liste.hasNext()){
            E=liste.next();
            Ends=G.endVertices(E);

            if (Ends[1].element().nombre.compareTo(V.element().nombre)!=0){
                V_adj=G.opposite(V, E);
                if (V_adj.element().visitado==false){
                 cola.enqueue(V_adj);
                }
            }
        }
   }
 }

public static void RecorroEnProfundidad(AdjacencyListGraph G,String Data){

   Stack <Vertex> pila = new NodeStack();
   Vertex<Parada> V,V_adj=null;
   Iterator<Edge> liste;
   Edge E;
   Vertex<Parada> Ends[];

   V=BuscarVertice(G,Data);
   pila.push(V);

   while (!pila.isEmpty()){
        V=pila.pop(); //saco el frente de la cola
        V.element().visitado=true;

        System.out.println(V.element().nombre);

        liste= G.incidentEdges(V).iterator(); //reviso si hay arcos
        while (liste.hasNext()){
            E=liste.next();
            Ends=G.endVertices(E);

            if (Ends[1].element().nombre.compareTo(V.element().nombre)!=0){
                V_adj=G.opposite(V, E);
                if (V_adj.element().visitado==false){
                 pila.push(V_adj);
                }
            }
        }
   }
 }


}
class Parada{
    String nombre;
    boolean visitado=false;
    }
