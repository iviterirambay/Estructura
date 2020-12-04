package dijkstra;

import net.datastructures.AdjacencyListGraph;
import net.datastructures.Vertex;
import net.datastructures.Edge;
import java.util.Iterator;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        //String vertices[] = {"V1", "V2", "V3", "V4", "V5", "V6"};
        //String arcos[] = {"V2,V5", "V1,V2", "V1,V3", "V1,V5", "V3,V5","V5,V6", "V6,V4", "V5,V4"};
        //int pesos[] = {5,3, 4, 8,3,3, 2, 7};

        String vertices[] = {"a","b","c","d","e","f","g","h","i","j","z",};
        String arcos[] = {"a,b","a,e","a,h","b,c","b,f","b,e","c,f","c,g","c,d","e,f","e,h","h,f","h,i","i,f","i,j","j,g","j,z","d,z","d,g","b,a","e,a","h,a","c,b","f,b","e,b","f,c","g,c","d,c","f,e","h,e","f,h","i,h","f,i","j,i","g,j","z,j","z,d","g,d"};
        int pesos [] = {3,5,4,2,7,5,2,6,3,4,7,5,2,4,6,4,5,2,7,3,5,4,2,7,5,2,6,3,4,7,5,2,4,6,4,5,2,7};


        Stack<Vertex> camino = new <Vertex>Stack();
        Vertex <DataVertex> V, V1 = null, V2 = null;
        DataVertex Datos;


        //agrego los vertices
        AdjacencyListGraph G = new AdjacencyListGraph();
        for (int i = 0; i < vertices.length; i++) {
            Datos = new DataVertex();
            Datos.id = vertices[i];
            Datos.visitado = false;
            Datos.previo = null;
            Datos.distancia = 1000;
            V = G.insertVertex(Datos);
        }
        //agrego los pesos
        for (int i = 0; i < arcos.length; i++) {
            String v1_id = arcos[i].substring(0, 1);
            String v2_id = arcos[i].substring(2, 3);
            V1 = BuscarVertice(G, v1_id);
            V2 = BuscarVertice(G, v2_id);
            if (V1 != null & V2 != null) {
                G.insertEdge(V1, V2, pesos[i]);
            }
        }
        V = BuscarVertice(G, "a");
        //calculo los caminos minimos con el vertice inicial V1
        Caminos_dijkstra(G, V);
	//Extraigo el camino minimo a un vertice dado
        camino = GeneraCamino(G, "z");
          //agrego los pesos


	//Saco de la pila el camino
        while (!camino.isEmpty()) {
            V1 = camino.pop();
            System.out.println(V1.element().id +" - "+V1.element().distancia);
        }

    }

    public static Vertex BuscarVertice(AdjacencyListGraph G, String id) {
        Vertex<DataVertex> V;
        Iterator listv = G.vertices().iterator();

        while (listv.hasNext()) {
            V = (Vertex) listv.next();
            if (V.element().id.compareTo(id) == 0) {
                return (V);
            }
        }
        return (null);
    }

    public static void Caminos_dijkstra(AdjacencyListGraph G, Vertex V0) {
	//cola de prioridad de vertices
        PriorityQueue<Vertex> ColaPrioridad = new PriorityQueue<Vertex>(20,
                new Comparator<Vertex>() {
                    public int compare(Vertex i, Vertex j) {
                        Vertex<DataVertex> Vj = j;
                        Vertex<DataVertex> Vi = i;
                        return (Vj.element().distancia - Vi.element().distancia);
                    }
                }
        );

        Vertex<DataVertex> Ends[];
        Vertex<DataVertex> V_adj, U;
        Iterator<Edge> liste;
        Edge<Integer> E;
        int distancia_por_U = 0;

	//coloco el primer el vertice
        U=V0;
        U.element().distancia=0;
        ColaPrioridad.offer(U);

        Iterator<Vertex> listv;
        while (!ColaPrioridad.isEmpty()) {
            listv=G.vertices().iterator();
            listv.next();
            while (listv.hasNext())
            {
              Vertex<DataVertex> x=listv.next();
              if (x.element().previo==null)
              {
                 System.out.print("-Inf ");
              }
              else
              {
                  DataVertex z=(DataVertex)x.element().previo.element();
                  System.out.print(z.id +"->"+x.element().distancia + " ");
              }
            }
            System.out.println(" ");

            U = ColaPrioridad.poll();//saco el vertice con menor distancia
            System.out.print(U.element().id+ "-> ");
            liste = G.incidentEdges(U).iterator(); //Saco sus arcos
            while (liste.hasNext()) {
                E = liste.next();
                Ends = G.endVertices(E); 
                if (Ends[0] == U) { //reviso si hay un camino menor
                    V_adj = Ends[1];
                    distancia_por_U = U.element().distancia + E.element();
                    if (distancia_por_U < V_adj.element().distancia) { 
                        V_adj.element().distancia = distancia_por_U;
                        V_adj.element().previo = U; 
                        ColaPrioridad.offer(V_adj);//actualizao la distancia y encolo el nuevo vertice
                    }
                }
            }
        }
    }

    public static Stack<Vertex> GeneraCamino(AdjacencyListGraph G, String id) {
        Vertex<DataVertex> V, P;
        Stack<Vertex> camino = new <Vertex>Stack();

        V = BuscarVertice(G, id);
        while (V != null) { //coloco en la pila los vertices previous hasta el inicio
            camino.push(V);
            V = V.element().previo;
        }
        return (camino);
    }

    public static class DataVertex {

        String id;
        Boolean visitado;
        Vertex previo;
        int distancia;
    }
}
