
import net.datastructures.*;

public class Problema1 {
    
    static class vertice {
    
        String Nombre;
        boolean visitado = false;
        
        public vertice (String Nombre){
            this.Nombre = Nombre;
            
        }
    }

   
    public static void main(String[] args) { 
        
         //creacion del primer grafo
        AdjacencyListGraph<vertice,Integer> Grafo = new AdjacencyListGraph<vertice, Integer>();
        Vertex<vertice> Vfirst= null, Vlast=null;
        
        
        for(int i=0;i<=7;i++){
            Grafo.insertVertex(new vertice("º"+i+"º"));
           
        }
        Grafo.insertVertex(new vertice("º9º"));
        
        Vfirst= SearchVertex(Grafo, "º2º");
        Vlast = SearchVertex(Grafo, "º1º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º2º");
        Vlast = SearchVertex(Grafo, "º3º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º2º");
        Vlast = SearchVertex(Grafo, "º6º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º2º");
        Vlast = SearchVertex(Grafo, "º3º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º1º");
        Vlast = SearchVertex(Grafo, "º6º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º1º");
        Vlast = SearchVertex(Grafo, "º4º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º3º");
        Vlast = SearchVertex(Grafo, "º5º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º6º");
        Vlast = SearchVertex(Grafo, "º5º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º6º");
        Vlast = SearchVertex(Grafo, "º7º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º5º");
        Vlast= SearchVertex(Grafo, "º9º");
        Grafo.insertEdge(Vfirst, Vlast, 0);
        Grafo.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º5º");
        Vlast= SearchVertex(Grafo, "º0º");
        Grafo.insertEdge(Vfirst, Vlast, 0); 
        Grafo.insertEdge(Vlast, Vfirst,0);
        
        Vfirst= SearchVertex (Grafo, "º4º");
        Vlast= SearchVertex (Grafo, "º7º");
        Grafo.insertEdge (Vfirst, Vlast, 0);
        Grafo.insertEdge (Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º7º");
        Vlast= SearchVertex (Grafo, "º9º");
        Grafo.insertEdge (Vfirst, Vlast, 0);
        Grafo.insertEdge (Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo, "º0º");
        Vlast= SearchVertex(Grafo, "º9º");
        Grafo.insertEdge (Vfirst, Vlast, 0);
        Grafo.insertEdge (Vlast, Vfirst, 0);
        
         //creacion del segundo grafo
        AdjacencyListGraph<vertice, Integer> Grafo2 = new  AdjacencyListGraph<vertice, Integer>();
        
        for(int i=0;i<=7;i++){
            
            Grafo2.insertVertex(new vertice(""+i));
        }
        Grafo2.insertVertex(new vertice(""+9));
        
        Vfirst= SearchVertex(Grafo2, "1");
        Vlast = SearchVertex(Grafo2, "2");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "1");
        Vlast = SearchVertex(Grafo2, "4");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "1");
        Vlast= SearchVertex(Grafo2, "6");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "2");
        Vlast = SearchVertex(Grafo2, "3");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "2");
        Vlast = SearchVertex(Grafo2, "6");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "3");
        Vlast = SearchVertex(Grafo2, "5");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast,Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "4");
        Vlast = SearchVertex(Grafo2, "7");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "4");
        Vlast = SearchVertex(Grafo2, "2");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "5");
        Vlast = SearchVertex(Grafo2, "6");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "5");
        Vlast = SearchVertex(Grafo2, "9");
        Grafo2.insertEdge(Vfirst, Vlast, 0);
        Grafo2.insertEdge(Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "5");
        Vlast= SearchVertex(Grafo2, "0");
        Grafo2.insertEdge(Vfirst, Vlast, 0); 
        Grafo2.insertEdge(Vlast, Vfirst,0);
        
        Vfirst= SearchVertex (Grafo2, "6");
        Vlast= SearchVertex (Grafo2, "7");
        Grafo2.insertEdge (Vfirst, Vlast, 0);
        Grafo2.insertEdge (Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "7");
        Vlast= SearchVertex (Grafo2, "9");
        Grafo2.insertEdge (Vfirst, Vlast, 0);
        Grafo2.insertEdge (Vlast, Vfirst, 0);
        
        Vfirst= SearchVertex(Grafo2, "9");
        Vlast= SearchVertex(Grafo2, "0");
        Grafo2.insertEdge (Vfirst, Vlast, 0);
        Grafo2.insertEdge (Vlast, Vfirst, 0);
        
        
        
        
        System.out.println("\t"+" GRAFOS NO DIRIGIDOS ");
        
        System.out.println("GRAFO A: ");
        ShowGrafos(Grafo);
        System.out.println(" ");
        System.out.println("GRAFO B: ");
        ShowGrafos(Grafo2);
        
        System.out.println("EL GRAFO 'A' Y EL GRAFO 'B' SON EQUIVALENTES?   "+ AreEquivalent(Grafo,Grafo2));

    }
    
    public static Vertex<vertice> SearchVertex(AdjacencyListGraph<vertice,Integer> grafo,String nombre){
        Vertex<vertice> resultado = null;
        for(Vertex<vertice> vertice: grafo.vertices()){
            if(vertice.element().Nombre.equals(nombre))
                resultado= vertice;
        }
        return resultado;
    }
    
    public static void ShowGrafos(AdjacencyListGraph<vertice,Integer> grafo){
        
        for(Vertex<vertice> vertice: grafo.vertices()){
            System.out.println("Vertice: "+vertice.element().Nombre);
            
            for(Edge<Integer> arco: grafo.edges()){
                if(grafo.endVertices(arco)[0].equals(vertice)){
                    System.out.println("\t"+"**  "+grafo.endVertices(arco)[0].element().Nombre+"--"+arco.element()+"->"+grafo.endVertices(arco)[1].element().Nombre+"  **");
                }
            }
        }
        
        
    }

     public static boolean AreEquivalent( AdjacencyListGraph<vertice,Integer> grafoV, AdjacencyListGraph<vertice,Integer> grafoU){
         if(grafoV.numVertices()!=grafoU.numVertices() || grafoV.numEdges()!=grafoU.numEdges())
            return false;
         int b;
        for(Vertex<vertice> vertexV:grafoV.vertices()){
            b=0;
            for(Vertex<vertice> vertexU:grafoU.vertices()){
                if(grafoV.degree(vertexV)==grafoU.degree(vertexU) && vertexU.element().visitado==false && b==0){
                    vertexU.element().visitado=true;
                    b=1;
                }
            }
        }
        //revisando que todos los vertices del grafoU hayan sido visitados
        b=0;
        for(Vertex<vertice> vertexU:grafoU.vertices()){
            if(vertexU.element().visitado==false)
                b=1;          //existe al menos un vertice sin visitar, por lo tanto no es isomorfo
        }

        
        
        //ponemos todos los visitados en falso ya que no debemos alterar los grafos
        for(Vertex<vertice> vertexU:grafoU.vertices()){
            vertexU.element().visitado=false;
        }

        
        if(b==0)
            return true;
        else
            return false;
     }
}