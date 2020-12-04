import java.util.Iterator;
import java.util.Scanner;
import net.datastructures.*;
public class Amigos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String vertices[]= { "Lupe,Espol","Maria,Espol","Byron,Ucse",
            "Gabriel,Espol","Julio,Ucse","Pedro,Espol","Jose,Ucse",
            "Luis,Espol","Carlos,Ucse","Manuel,Espol"};
        String arcos[]= {"Lupe,Gabriel","Lupe,Julio","Lupe,Maria",
            "Maria,Pedro","Maria,Byron","Byron,Jose","Byron,Manuel",
            "Gabriel,Julio","Gabriel,Luis","Julio,Pedro","Julio,Luis",
            "Pedro,Jose","Jose,Manuel","Luis,Carlos","Carlos,Manuel"};
        Vertex <Parada>  V,V1 = null,V2 = null;
        Parada dato;

        AdjacencyListGraph G= new AdjacencyListGraph();
        for (int i =0; i< vertices.length;i++){
            String str[];
            dato=new Parada();
            str=vertices[i].split(",");
            dato.nombre=str[0];
            dato.univeridad=str[1];
            dato.visitado=false;
            V=G.insertVertex(dato);
        }
        for (int i =0; i< arcos.length;i++){
            //obtengo los vertices
            String str2[];
            str2=arcos[i].split(",");
            String v1_id=str2[0];
            String v2_id=str2[1];
            V1=BuscarVertice(G,v1_id);
            V2=BuscarVertice(G,v2_id);
            if (V1!=null & V2!=null){
                G.insertEdge(V1,V2, 1);
                G.insertEdge(V2,V1, 1);
            }
        }
        //Muestro en pantalla los datos
        Iterator<Parada> listv= G.vertices().iterator();
        Iterator liste= G.edges().iterator();
        while (listv.hasNext()){
            V=(Vertex)listv.next();
            System.out.println(V.element().nombre);
        }
        Scanner scn=new Scanner(System.in);
        String busca;
        int tipo;
        System.out.print("Ingrese el nombre al cual se le buscara los amigos: ");
        busca=scn.next();
        System.out.print("Ingrese el tipo de busqueda(1,2,3): ");
        tipo=scn.nextInt();
        busqueda_de_amigos(G,busca,tipo);
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
public static void busqueda_de_amigos(AdjacencyListGraph G,String Data,int tipo){
    Queue <Vertex> cola = new NodeQueue();
   Vertex<Parada> V,V_adj=null;
   Iterator<Edge> liste;
   Edge E;
   Vertex<Parada> Ends[];

   V=BuscarVertice(G,Data);
   cola.enqueue(V);
   int cont=1;
   
        V=cola.dequeue(); //saco el frente de la cola
        V.element().visitado=true;
        liste= G.incidentEdges(V).iterator(); //reviso si hay arcos
 
         System.out.print("Compañeros de primer grado: ");
       
         

        while (liste.hasNext()){
            E=liste.next();
            Ends=G.endVertices(E);

            if (Ends[1].element().nombre.compareTo(V.element().nombre)!=0){
                V_adj=G.opposite(V, E);
                if (V_adj.element().visitado==false){
                    V_adj.element().visitado=true;
                    cola.enqueue(V_adj);
                 if(tipo==3){
                    System.out.print(V_adj.element().nombre+" ");
                    }
                 else if(tipo==1){
                     if(V_adj.element().univeridad.equals("Espol"))
                         System.out.print(V_adj.element().nombre+" ");
                 }
                 else if(tipo==2){
                     if(V_adj.element().univeridad.equals("Ucse"))
                         System.out.print(V_adj.element().nombre+" ");
                }
            }
        }
         
   }
         System.out.print("\nCompañeros de segundo grado: ");
         cont=cola.size();
         for(int i=0; i<cont;i++){
              V=cola.dequeue(); //saco el frente de la cola
              liste= G.incidentEdges(V).iterator(); //reviso si hay arcos
             while (liste.hasNext()){
            E=liste.next();
            Ends=G.endVertices(E);

            if (Ends[1].element().nombre.compareTo(V.element().nombre)!=0){
                V_adj=G.opposite(V, E);
                if (V_adj.element().visitado==false){
                 V_adj.element().visitado=true;
                 cola.enqueue(V_adj);
                 if(tipo==3){
                    System.out.print(V_adj.element().nombre+" ");
                    }
                 else if(tipo==1){
                     if(V_adj.element().univeridad.equals("Espol"))
                         System.out.print(V_adj.element().nombre+" ");
                 }
                 else if(tipo==2){
                     if(V_adj.element().univeridad.equals("Ucse"))
                         System.out.print(V_adj.element().nombre+" ");
                }
            }
        }

   }
         }
}
static class Parada{
    String nombre;
    String univeridad;
    boolean visitado=false;
    }

}