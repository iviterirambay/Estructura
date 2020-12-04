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
public class graph_recorridos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String vertices[]= { "A","B","C","D","H","R","T"};
        String arcos[]= {"D,B","D,C","C,R","B,H","R,H","H,A","H,T","H,D"};
        Vertex <String>  V,V1 = null,V2 = null;

        AdjacencyListGraph G= new AdjacencyListGraph();
        for (int i =0; i< vertices.length;i++){
            V=G.insertVertex(vertices[i]);
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
    Iterator listv= G.vertices().iterator();
    Iterator liste= G.edges().iterator();
    while (listv.hasNext()){
        System.out.println(listv.next());
    }
    while (liste.hasNext()){
        System.out.println(liste.next());
    }
    


    }

 public static Vertex BuscarVertice(AdjacencyListGraph G,String Data){
   Vertex<String> V1;
   Iterator listv= G.vertices().iterator();

   while (listv.hasNext())
        {
            V1=(Vertex)listv.next();
            if (V1.element().compareTo(Data)==0)
                return(V1);
            }

    return(null);
}

}

