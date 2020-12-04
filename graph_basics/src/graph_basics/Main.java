/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph_basics;
import net.datastructures.*;
import java.util.*;
/**
 *
 * @author 
 */
public class Main {

    public static void main(String[] args) {
    AdjacencyListGraph G= new AdjacencyListGraph();
    Vertex v1=G.insertVertex(10);
    Vertex v2=G.insertVertex(20);
    Vertex v3=G.insertVertex(30);
    Vertex v4=G.insertVertex(40);
    Vertex v5=G.insertVertex(50);
    Vertex v6=G.insertVertex(60);

    G.insertEdge(v2, v1, 5);
    G.insertEdge(v1, v4, 2);
    G.insertEdge(v3, v4, 5);
    G.insertEdge(v3, v5, 1);
    G.insertEdge(v5, v6, 10);
    G.insertEdge(v5, v4, 3);
    G.insertEdge(v2, v3, 2);
    G.insertEdge(v2, v6, 2);
    G.insertEdge(v6, v3, 7);

    Iterator listv= G.vertices().iterator();
    Iterator liste= G.edges().iterator();
    while (listv.hasNext()){
         Vertex V=(Vertex)listv.next();
        System.out.println(V.element());
        //System.out.println(listv.next());
    }
    while (liste.hasNext()){
      // Edge E=(Edge)liste.next();
        //System.out.println(E.element());
        System.out.println(liste.next());
    }
    
    }

}
