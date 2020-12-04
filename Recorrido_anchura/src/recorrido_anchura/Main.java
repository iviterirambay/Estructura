/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package recorrido_anchura;
import net.datastructures.*;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * @author daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String vertices[]= { "LUPE,ESPOL","GABRIEL,ESPOL",
                            "JULIO,UCSG","LUIS,ESPOL",
                            "MARIA,ESPOL","PEDRO,ESPOL",
                            "CARLOS,UCSG","BYRON,UCSG",
                            "JOSE,UCSG","MANUEL,ESPOL"};
        String arcos[]= {"GABRIEL,LUPE","GABRIEL,JULIO",
                         "GABRIEL,LUIS","LUPE,MARIA",
                         "LUPE,JULIO","JULIO,LUIS",
                         "MARIA,PEDRO","MARIA,BYRON",
                         "LUIS,CARLOS","CARLOS,MANUEL",
                         "MANUEL,JOSE","MANUEL,BYRON",
                         "PEDRO,JOSE"};
        Vertex <Parada>  V,V1 = null,V2 = null;
        Parada dato;

        AdjacencyListGraph G= new AdjacencyListGraph();
        for (int i =0; i< vertices.length;i++){
            dato=new Parada();
            String [] temp = vertices[i].split(",");
            dato.nombre= temp[0];
            dato.u = temp[1];
            dato.visitado=false;
            V=G.insertVertex(dato);
        }
        for (int i =0; i< arcos.length;i++){
            //obtengo los vertices
            String [] tmp = arcos[i].split(",");
            String v1_id=tmp[0];
            String v2_id=tmp[1];
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
            System.out.println( "Vertice: "+V.element().nombre + " " + V.element().u );
        }
        while (liste.hasNext()){
              Edge E=(Edge)liste.next();
            System.out.println( "Arcos: "+ E.element());
        }
        System.out.println("recorrido en anchura");
        RecorroEnAnchura(G,"GABRIEL");
/*
        listv= G.vertices().iterator();
        while (listv.hasNext()){
            V=(Vertex)listv.next();
            V.element().visitado=false;
        }*/
       // System.out.println("recorrido en profundidad");
        //RecorroEnProfundidad(G,"C");
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
        
        System.out.println(V.size());

        liste= G.incidentEdges(V).iterator(); //reviso si hay arcos
        int i =0;
        while (liste.hasNext()){
            E=liste.next();
            Ends=G.endVertices(E);

            
            if (Ends[1].element().nombre.compareTo(V.element().nombre)!=0){
                V_adj=G.opposite(V, E);
                //System.out.println(V.element().nombre +  " el opuesto es: " + V_adj.element().nombre + " "+V_adj.element().visitado  );
                if (V_adj.element().visitado==false){
                  //  System.out.println("Encolar: " + V_adj.element().nombre );
                    V_adj.element().visitado = true;
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
    String u;
    boolean visitado=false;
    }
