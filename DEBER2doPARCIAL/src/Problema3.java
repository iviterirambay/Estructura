import java.util.*;
import net.datastructures.*;

public class Problema3 {

    static class Vertice{
        String nombre="";
        boolean visitado= false;
    }

    static class VertexDist{
        Vertex<Vertice> vertex;
        int distancia;
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Vertice,Integer> grafo= new AdjacencyListGraph<Vertice,Integer>();
        Vertex<Vertice> vinicio=null,vfin=null ;

        Vertice v;

        for(int i=1;i<=6;i++){
            v=new Vertice();
            v.nombre=v.nombre.concat("V"+i);
            grafo.insertVertex(v);
        }

        vinicio=buscarVertice(grafo, "V1");
        vfin=buscarVertice(grafo, "V2");
        grafo.insertEdge(vinicio, vfin, 3);

        vinicio=buscarVertice(grafo, "V1");
        vfin=buscarVertice(grafo, "V3");
        grafo.insertEdge(vinicio, vfin, 4);

        vinicio=buscarVertice(grafo, "V1");
        vfin=buscarVertice(grafo, "V5");
        grafo.insertEdge(vinicio, vfin, 8);

        vinicio=buscarVertice(grafo, "V2");
        vfin=buscarVertice(grafo, "V5");
        grafo.insertEdge(vinicio, vfin, 5);

        vinicio=buscarVertice(grafo, "V3");
        vfin=buscarVertice(grafo, "V5");
        grafo.insertEdge(vinicio, vfin, 3);

        vinicio=buscarVertice(grafo, "V5");
        vfin=buscarVertice(grafo, "V4");
        grafo.insertEdge(vinicio, vfin, 7);

        vinicio=buscarVertice(grafo, "V5");
        vfin=buscarVertice(grafo, "V6");
        grafo.insertEdge(vinicio, vfin, 3);

        vinicio=buscarVertice(grafo, "V6");
        vfin=buscarVertice(grafo, "V4");
        grafo.insertEdge(vinicio, vfin, 2);
                
        LinkedList<VertexDist> lstMejorRuta= new LinkedList<VertexDist>();
        lstMejorRuta=Dijkstra(grafo,buscarVertice(grafo, "V1"),buscarVertice(grafo, "V4"));

        for(VertexDist VD: lstMejorRuta){
            System.out.println(VD.vertex.element().nombre+"="+VD.distancia);
        }
    }

    public static LinkedList<VertexDist> Dijkstra(AdjacencyListGraph<Vertice,Integer> grafo, Vertex<Vertice> vertexInit,Vertex<Vertice> vertexFin){
        if(grafo==null || grafo.numVertices()==0 || grafo.numEdges()==0 || vertexInit==null || vertexFin==null)
            return null;
        final int INFINITO=Integer.MAX_VALUE;//creo una constante INFINITO con el mayor entero que puede manejar java
        VertexDist vertexDist,vertexDistMenor,vertexDistDestino,vertexDistInit=null;
        LinkedList<VertexDist> lstCola= new LinkedList<VertexDist>();
        LinkedList<VertexDist> lstMejorRuta= new LinkedList<VertexDist>();
        LinkedList<VertexDist> lstVertexDist= new LinkedList<VertexDist>();

        //me aseguro que todos los vÃ©rtices no estan marcados
        for(Vertex<Vertice> vertex: grafo.vertices()){
            vertex.element().visitado=false;
        }

        for(Vertex<Vertice> vertex: grafo.vertices()){
            vertexDist=new VertexDist();
            vertexDist.vertex=vertex;
            if(vertex.equals(vertexInit)){
                vertexDist.distancia=0;
                vertexDistInit=vertexDist;
            }
            else{
                vertexDist.distancia=INFINITO;
            }
            lstVertexDist.add(vertexDist);
        }

        //inserto el vertexDistInit en la lstCola
        lstCola.add(vertexDistInit);
        vertexDistInit.vertex.element().visitado=true;
        while(!lstCola.isEmpty()){
            //saco el vertexDist con la menor distancia acumulada
            vertexDistMenor=ObtenerVertexDistMenor(lstCola);
            for(Edge<Integer> arco: grafo.incidentEdges(vertexDistMenor.vertex)){
                if(grafo.endVertices(arco)[0].equals(vertexDistMenor.vertex)){
                    vertexDistDestino=SearchDistVertex(lstVertexDist,grafo.endVertices(arco)[1]);
                    if(vertexDistMenor.distancia+arco.element()<vertexDistDestino.distancia)
                        vertexDistDestino.distancia=vertexDistMenor.distancia+arco.element();
                    if(vertexDistDestino.vertex.element().visitado==false){//si el vertice no existe n la lstCola
                        lstCola.add(vertexDistDestino);
                        vertexDistMenor.vertex.element().visitado=true;
                    }
                }
            }
        }
        
        lstMejorRuta=TheBestRoad(grafo,lstVertexDist,vertexInit,vertexFin);        
        return lstMejorRuta;
    }


    public static LinkedList<VertexDist> TheBestRoad(AdjacencyListGraph<Vertice,Integer> grafo, LinkedList<VertexDist> lstVertexDist,Vertex<Vertice> vertexInit,Vertex<Vertice> vertexFin){
        final int INFINITO=Integer.MAX_VALUE;
        VertexDist vertexDistInit=SearchDistVertex(lstVertexDist,vertexInit);
        VertexDist vertexDistFin=SearchDistVertex(lstVertexDist,vertexFin);

        if(vertexDistInit==null || vertexDistInit.distancia==INFINITO || vertexDistFin==null || vertexDistFin.distancia==INFINITO){
            return null;
        }
        LinkedList<VertexDist> lstMejorRuta= new LinkedList<VertexDist>();
        if(vertexInit.equals(vertexFin)){
            lstMejorRuta.add(vertexDistInit);
            return lstMejorRuta;
        }
        int mejorCosto=vertexDistFin.distancia;
        int pesoAc=0,nArcosOut,cont,selectArco;
        VertexDist vertexDist=vertexDistInit;
        lstMejorRuta.add(vertexDist);
        do{
            nArcosOut=0;
            for(Edge<Integer> arco: grafo.incidentEdges(vertexDist.vertex)){
                if(grafo.endVertices(arco)[0].equals(vertexDist.vertex)){
                    nArcosOut++;
                }
            }
           
            if((vertexDist.vertex.equals(vertexFin) && pesoAc!=mejorCosto) || nArcosOut==0){
                lstMejorRuta.removeAll(lstMejorRuta);
                vertexDist=vertexDistInit;
                lstMejorRuta.add(vertexDist);
                pesoAc=0;
                for(Edge<Integer> arco: grafo.incidentEdges(vertexDist.vertex)){
                   if(grafo.endVertices(arco)[0].equals(vertexDist.vertex)){
                        nArcosOut++;
                   }
                }
            }

            cont=0;
            selectArco=1+(int)(Math.random()*nArcosOut);
            
            for(Edge<Integer> arco: grafo.incidentEdges(vertexDist.vertex)){
                if(grafo.endVertices(arco)[0].equals(vertexDist.vertex)){
                    cont++;
                    if(cont==selectArco){
                        pesoAc+=arco.element();
                        vertexDist=SearchDistVertex(lstVertexDist,grafo.endVertices(arco)[1]);
                        lstMejorRuta.add(vertexDist);
                    }
                }
            }

        }while(!(vertexDist.vertex.equals(vertexFin)&&pesoAc==mejorCosto));
        return lstMejorRuta;
    }



    public static VertexDist SearchDistVertex(LinkedList<VertexDist> lstVertexDist, Vertex<Vertice> vertex){
        for(VertexDist vertexDist:lstVertexDist){
            if(vertexDist.vertex.equals(vertex))
                return vertexDist;
        }
        return null;
    }
    public static VertexDist ObtenerVertexDistMenor(LinkedList<VertexDist> lstVertexDist){
        VertexDist vertexDistMenor=lstVertexDist.getFirst();
        for(VertexDist vertexDist: lstVertexDist){
            if(vertexDist.distancia<vertexDistMenor.distancia){
                vertexDistMenor=vertexDist;
            }
        }
        lstVertexDist.remove(vertexDistMenor);
        return vertexDistMenor;
    }

    /**
     * Busca dentro de un AdjacencyListGraph<Vertice,Integer> un Vertex<Vertice>
     * en funcion del nombre del vÃ©rtice que es un string y lo devuelve, sino
     * retorna null.
     *
     * @param grafo grafo en donde se va a buscar el vÃ©rtice
     * @param nombre nombre del vÃ©rtice a buscar
     * @return
     *      El vÃ©rtice encontrado a partir del nombre, caso contrario retorna null
     */
    public static Vertex<Vertice> buscarVertice(AdjacencyListGraph<Vertice,Integer> grafo,String nombre){
        Vertex<Vertice> resultado = null;
        for(Vertex<Vertice> vertice: grafo.vertices()){
            if(vertice.element().nombre.equals(nombre))
                resultado= vertice;
        }
        return resultado;
    }
}

