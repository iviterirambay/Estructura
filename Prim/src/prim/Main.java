package prim;

import net.datastructures.AdjacencyListGraph;
import net.datastructures.Vertex;
import net.datastructures.Edge;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String vertices[] = {"1", "2", "3", "4", "5", "6", "7"};
        String arcos[] = {"1,2", "1,4", "2,4", "2,3", "2,5", "4,5", "4,7", "5,7", "7,6", "6,3", "3,5", "5,6"};
        int pesos[] = {1, 4, 6, 2, 4, 3, 4, 7, 3, 6, 5, 8};
        AdjacencyListGraph G, Aem;
        Vertex<DataVertex> V, V1 = null, V2 = null;
        DataVertex Datos;
        Edge E;
        Vertex<DataVertex> Ends[];
        int acum = 0;
        //agrego los vertices
        G = new AdjacencyListGraph();
        for (int i = 0; i < vertices.length; i++) {
            Datos = new DataVertex();
            Datos.id = vertices[i];
            Datos.visitado = false;
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
	//genero el arbol de expansion minimo
        Aem = AEM_prim(G, "7");
        Iterator<Edge> liste = Aem.edges().iterator();
	//imprimo los arcos del Aem
        while (liste.hasNext()) {
            E = liste.next();
            acum = acum + (Integer) E.element();
            Ends = G.endVertices(E);
            System.out.println("[" + Ends[0].element().id + "," + Ends[1].element().id + "]");
        }
        System.out.println(acum);//imprimo peso del Aem
    }

    public static AdjacencyListGraph AEM_prim(AdjacencyListGraph G, String id) {
        Vertex<DataVertex> V;
        Vertex<DataVertex> Ends[];
        Vertex<DataVertex> V_adj;
        AdjacencyListGraph Aem = new AdjacencyListGraph();
        Iterator<Edge> liste;
        Edge<Integer> E;
        int count = 0; //cuenta los vertices visitados en el Aem

        PriorityQueue<Edge> ColaPrioridad = new PriorityQueue<Edge>(20,
                new Comparator<Edge>() {//cola de prioridad de arcos

                    public int compare(Edge Ei, Edge Ej) {
                        return ((Integer) Ei.element() - (Integer) Ej.element());
                    }
                });

        Iterator listv = G.vertices().iterator();
        while (listv.hasNext()) {
            V = Aem.insertVertex(listv.next()); //nuevo grafo sin arcos
        }

        V = BuscarVertice(G, id);
        do {
            if (V != null) {
                V.element().visitado = true;//marco como visitado
                liste = G.incidentEdges(V).iterator(); 

                while (liste.hasNext()) {//coloco los arcos a vertices no visitados
                    E = liste.next();
                    V_adj = G.opposite(V, E);
                    if (!V_adj.element().visitado) {
                        ColaPrioridad.offer(E);
                    }
                }
            }
            V = null;
            if (!ColaPrioridad.isEmpty()) {
                E = ColaPrioridad.poll();//saco el arco con menor fp.
                Ends = G.endVertices(E);
		// busco un arco con vertice no visitado
                if (Ends[0].element().visitado == false) {
                    V = Ends[0];
                }
                if (Ends[1].element().visitado == false) {
                    V = Ends[1];
                }
		//agrego ese arco al Aem
                if (V != null) {
                    Aem.insertEdge(Ends[0], Ends[1], E.element());
                }
            }
            count++;
        } while (count < G.numVertices());
        return (Aem);
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

    public static class DataVertex {
        String id;
        Boolean visitado;
    }
}
