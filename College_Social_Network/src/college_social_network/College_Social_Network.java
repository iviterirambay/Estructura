/*
 *Nombres:Alfonso Guevara Gallardo
 *        Irwin Viteri
 * Profesor: Ing Ángel López
 * Fecha de entrega: 28/08/2011
 */
package college_social_network;

//LIBRERIAS
import net.datastructures.*;
import java.util.*;
import java.util.Stack;
import net.datastructures.Vertex;
import java.io.*;

import javax.swing.JOptionPane;
import net.datastructures.Dijkstra;

public class College_Social_Network {
//  VARIABLE GLOBAL

    final static int W = 100, INF = 1000000;

    public static void main(String[] args) throws IOException {
        //VARIABLES PROGRAMA PRINCIPAL 
        Estudiante datos = new Estudiante();
        Dijkstra camino = new Dijkstra();
        int opcion = 0;//Variable para la eleccion de menu
        BufferedReader estudiantes, amigos, preferencias, cursos;//La utilizaremos para leer archivos
        String strRead, strRead1, strRead2;//variables con la que leemos palabras de archivos
        AdjacencyListGraph Grafo = new AdjacencyListGraph();//Grafo principal de todo el preyecto
        Vertex<Estudiante> V;//vertice temporal en el que se añadira un estudiante en cada lazo

        //PROCEDEMOS A CARGAR EL GRAFO CON LOS RESPECTIVOS ARCHIVOS    
        try {
            //ABRIMOS LOS ARCHIVOS
            estudiantes = new BufferedReader(new FileReader("Estudiantes.dat"));
            preferencias = new BufferedReader(new FileReader("Preferencias.dat"));
            cursos = new BufferedReader(new FileReader("Cursos.dat"));
            amigos = new BufferedReader(new FileReader("Amigos.dat"));
            //MIENTRAS LOS ARCHIVOS CONTENGAN INFORMAACION
            while ((strRead = estudiantes.readLine()) != null) {
                datos = new Estudiante();
                String splitarray1[] = strRead.split(";");
                datos.nombre = splitarray1[0];
                datos.ciudad = splitarray1[1];
                datos.universidad = splitarray1[2];
                //V=Grafo.insertVertex(datos);
                strRead1 = preferencias.readLine();
                String splitarray2[] = strRead1.split(",");
                for (int i = 0; i < splitarray2.length; i++) {
                    datos.itr_pref.add(splitarray2[i]);
                }
                strRead2 = cursos.readLine();
                String splitarray3[] = strRead2.split(",");
                for (int i = 0; i < splitarray3.length; i++) {
                    datos.itr_curso.add(splitarray3[i]);
                }
                V = Grafo.insertVertex(datos);//Se añaden vertices al grafo hasta que sea final de archivo

            }//AQUI SE TERMINA DE CARGAR EL GRAFO

            //LLAMAMOS AL PROCEDIMIENTO QUE ENLAZA LO AMIGOS ENVIANDOLE EL GRAFO Y EL ARCHIVO
            //DONDE SE ENCUENTRAN LOS AMIGOS
            ConstruirAmigos(Grafo, amigos);
            //CERRAMOS TODOS LOS ARCHIVOS
            estudiantes.close();
            cursos.close();
            preferencias.close();
            amigos.close();

        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }

        do {//LAZO QUE SE REPITE MIENTRAS LA OPCION NO SEA SALIR
            do {//LAZO QUE SE REPITE SI LA OPCION INGRESADA NO ES CORRECTA
                opcion = MENU(opcion);//Llamamos a la funcion menu que muestra el menu y retorna un entero
                if (opcion < 1 || opcion > 8) {
                    System.out.println("Message:  Error la opcion elegida no esta entre las opciones ");
                }

            } while (opcion < 1 || opcion > 8);

            switch (opcion) {
                case 1: {
                    Consultar_Persona(Grafo);//LLAMAMOS AL PRCEDIMIENTO QUE CONSULTA A UNA PERSONA
                }
                break;
                case 2: {
                    Nueva_persona(Grafo);//LLAMAMOS AL PROCEDIMIENTO QUE AGREGA A UNA PERSONA

                }
                break;

                case 3: {
                    Agregar_Amigo(Grafo);//LLAMAMOS AL PROCEDIMIENTO QUE AGREGA A UN AMIGO

                }
                break;

                case 4: {
                    Eliminar_Persona(Grafo);//LLAMAMOS AL PREOCEDIMIENTO QUE ELIMINA A UNA PERSONA
                }
                break;
                case 5: {
                    Eliminar_Amigo(Grafo);//LLAMAMOS AL PROCEDIMIENTO QUE ELIMINA A UN AMIGO

                }
                break;
                case 6: {
                    Listar_Amigos(Grafo);//LLAAMOS AL PROCEDIMIENTO QUE LISTA TODOS LOS AMIGOS DE UN PERSONA
                }
                break;
                case 7: {
                    SujerirAmigos(Grafo);

                }
                break;
                case 8: {
                    //MUESTRA UN CUADRO DE TEXTO DE DESPEDIDA
                    JOptionPane.showMessageDialog(null, "Gracias por visitar college social network");
                }
                break;
            }

        } while (opcion != 8);
    }
//TDA ESTUDIANTE

    static class Estudiante {

        String nombre;
        String universidad;
        String ciudad;
        LinkedList<String> preferencias = new <String>LinkedList();
        LinkedList<String> cursos = new <String>LinkedList();
        ListIterator itr_pref = preferencias.listIterator();
        ListIterator itr_curso = cursos.listIterator();
        int distancia;
    }
//FUNCION QUE MUESTRA EL MENU Y RETORNA UN ENTERO

    public static int MENU(int opcion) {
        String numero;
        numero = JOptionPane.showInputDialog("\t\tCOLLEGE SOCIAL NETWORK\n1.- Consultar\n2.- Agregar Personas\n3.- Agregar Amigos\n4.- Eliminar Persona\n5.- Eliminar Amigo\n6.- Listar Amigos\n7.- Sujerencias de amigos\n8.-Salir");
        // Convierte el número de tipo String a tipo int
        opcion = Integer.parseInt(numero);
        return opcion;

    }
            
    /****PROCEDIMIENTO QUE DADO UN GRAFO Y UN ARCHIVO CONSTRUYE LOS AMIGOS RESPECTIVOS*****/
    public static void ConstruirAmigos(AdjacencyListGraph Graph, BufferedReader File) {
        String read;
        Vertex<Estudiante> V1 = null;
        Vertex<Estudiante> V2 = null;
        try {
            while ((read = File.readLine()) != null) {
                String array[] = read.split(",");
                String v1temp = array[0];
                String v2temp = array[1];
                V1 = BuscarVertice(Graph, v1temp);
                V2 = BuscarVertice(Graph, v2temp);
                if (V1 != null && V2 != null) {
                    int temp = Compatibilidad(V1, V2);
                    Graph.insertEdge(V1, V2, temp);
                }
            }

        } catch (IOException e) {
            System.out.println("error " + e.getMessage());

        }

    }

    /*******FUNCION QUE RETORNA EL VERTICE QUE QUEREMOS BUSCAR DADO UN GRAFO Y EL NOMBRE DE LA PERSONA QUE CONTIENE EL VERTICE*********/
    public static Vertex BuscarVertice(AdjacencyListGraph G, String Data) {
        Vertex<Estudiante> V;
        Iterator listv = G.vertices().iterator();

        while (listv.hasNext()) {
            V = (Vertex) listv.next();
            Estudiante Vdata = V.element();
            if (Vdata.nombre.compareToIgnoreCase(Data) == 0) {
                return (V);
            }
        }
        return (null);
    }

    /*****FUNCION QUE RETORNA UN ARCO DATO UN GRAFO DOS VERTICES Y EL PESO DEL MISMO******/
    public static Edge<Estudiante> GRAPH_buscarEdge(AdjacencyListGraph grafo, Vertex<Estudiante> inicio, Vertex<Estudiante> fin, Integer peso) {//Busca un arco
        Edge resultado = null;
        Iterator listv = grafo.edges().iterator();
        Edge Arco;

        while (listv.hasNext()) {
            Arco = (Edge) listv.next();
            if ((grafo.endVertices(Arco)[0].equals(inicio) && grafo.endVertices(Arco)[1].equals(fin) && Arco.element().equals(peso))
                    || (grafo.endVertices(Arco)[1].equals(inicio) && grafo.endVertices(Arco)[0].equals(fin) && Arco.element().equals(peso))) {
                resultado = Arco;
            }
        }
        return resultado;
    }
    /*FUNCION QUE RETORNA UN ENTERO DEACUERDO AL CRITERIO DE COMPATIBILIDAD*/

    static int Compatibilidad(Vertex<Estudiante> V1, Vertex<Estudiante> V2) {
        int pref_similar = 0, curso_similar = 0, K = 0, temp_al_cuadrado;
        int igual_universidad = 1;
        String pref_temp1, pref_temp2, curso_temp1, curso_temp2;

        V1.element().itr_pref = V1.element().preferencias.listIterator();//Apuntamos al inicio de las listas
        V2.element().itr_pref = V2.element().preferencias.listIterator();
        V1.element().itr_curso = V1.element().cursos.listIterator();
        V2.element().itr_curso = V2.element().cursos.listIterator();


        if (V1.element().universidad.equals(V2.element().universidad)) {
            igual_universidad = 0;
        }

        while (V1.element().itr_pref.hasNext()) {
            pref_temp1 = (String) V1.element().itr_pref.next();

            V2.element().itr_pref = V2.element().preferencias.listIterator();//Volvemos al inicio de la lista de preferencias
            while (V2.element().itr_pref.hasNext()) {
                pref_temp2 = (String) V2.element().itr_pref.next();
                if (pref_temp1.equals(pref_temp2)) {
                    pref_similar++;
                }
            }
        }

        while (V1.element().itr_curso.hasNext()) {
            curso_temp1 = (String) V1.element().itr_curso.next();

            V2.element().itr_curso = V2.element().cursos.listIterator();//Volvemos al inicio de la lista de cursos
            while (V2.element().itr_curso.hasNext()) {
                curso_temp2 = (String) V2.element().itr_curso.next();
                if (curso_temp1.equals(curso_temp2)) {
                    curso_similar++;
                }
            }
        }
        temp_al_cuadrado = (int) Math.pow(curso_similar, 2);
        K = W / (1 + pref_similar + temp_al_cuadrado + (2 * (igual_universidad)));
        return K;
    }

    /*******PROCEDIMIENTO PARA CONSULTAR DATOS DE UNA PERSONA DADO UN GRAFO*******/
    public static void Consultar_Persona(AdjacencyListGraph G) throws IOException {

        Vertex<Estudiante> dato = null;
        InputStreamReader scantext = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(scantext);
        System.out.println("Ingrese el nombre completo de la persona a consultar:");
        String nombre = br.readLine();

        if (BuscarVertice(G, nombre) != null) {
            dato = BuscarVertice(G, nombre);
            dato.element().itr_pref = dato.element().preferencias.listIterator();
            dato.element().itr_curso = dato.element().cursos.listIterator();
            System.out.println("NOMBRE COMPLETO:" + dato.element().nombre + "\nUNIVERSIDAD:" + dato.element().universidad
                    + "\nCIUDAD:" + dato.element().ciudad);

            System.out.println("++++++++++++PREFERENCIAS++++++++++");
            while (dato.element().itr_pref.hasNext()) {

                System.out.println(dato.element().itr_pref.next().toString());
            }
            System.out.println("++++++++++++++CURSOS++++++++++++++");
            while (dato.element().itr_curso.hasNext()) {

                System.out.println(dato.element().itr_curso.next().toString());
            }
        } else {
            System.out.println("NO EXISTE LA PERSONA EN LA RED SOCIAL");
        }
    }
    /*PROCEDIMIENTO PARA AGREGAR UNA NUEVA PERSONA DADO UN GRAFO*/

    public static void Nueva_persona(AdjacencyListGraph G) throws IOException {
        Estudiante nuevo = new Estudiante();

        InputStreamReader scantext = new InputStreamReader(System.in);
        BufferedReader nueva_persona = new BufferedReader(scantext);

        System.out.println("Ingrese el nombre de la persona nueva:");
        nuevo.nombre = nueva_persona.readLine();

        System.out.println("Ingrese la ciudad:");
        nuevo.ciudad = nueva_persona.readLine();
        System.out.println("Ingrese la universidad:");
        nuevo.universidad = nueva_persona.readLine();
        System.out.println("Ingrese la preferencias separadas por espacio:");
        String arraypref[] = nueva_persona.readLine().split(" ");
        nuevo.preferencias.addAll(Arrays.asList(arraypref));
        System.out.println("Ingrese los cursos separados por espacio:");
        String arraycurso[] = nueva_persona.readLine().split(" ");
        nuevo.cursos.addAll(Arrays.asList(arraycurso));
        G.insertVertex(nuevo);


    }
    /*PROCEDIMIENTO PARA AGEGAR UN AMIGO DADO UN GRAFO*/

    public static void Agregar_Amigo(AdjacencyListGraph G) throws IOException {
        Vertex<Estudiante> V1 = null;
        Vertex<Estudiante> V2 = null;
        InputStreamReader scantext1 = new InputStreamReader(System.in);
        BufferedReader persona = new BufferedReader(scantext1);
        InputStreamReader scantext2 = new InputStreamReader(System.in);
        BufferedReader amigo_a_agregar = new BufferedReader(scantext2);
        System.out.println("Ingrese el nombre de la persona a agregarle el amigo:");
        String persona_con_amigo = persona.readLine();
        System.out.println("Ingrese el amigo a agregar:");
        String amigo_agregado = amigo_a_agregar.readLine();
        V1 = BuscarVertice(G, persona_con_amigo);
        V2 = BuscarVertice(G, amigo_agregado);
        if (V1 != null && V2 != null) {
            G.insertEdge(V2, V1, Compatibilidad(V2, V1));
            System.out.println(V1.element().nombre + " y " + V2.element().nombre + " ahora son amigos");
        } else {
            System.out.println("**************************************************************************");
            System.out.println("No se puede agregar puesto que los dos o uno de los dos no existe en la Red");
            System.out.println("**************************************************************************");
        }
    }
    /*PROCEDIMIENTO PARA ELIMINAR UNA PERSONA DADO UN GRAFO */

    public static void Eliminar_Persona(AdjacencyListGraph G) throws IOException {
        Vertex<Estudiante> V1 = null;
        InputStreamReader scantext = new InputStreamReader(System.in);
        BufferedReader nombre = new BufferedReader(scantext);
        System.out.println("Ingrese el nombre que desea eliminar:");
        String nombre_a_eliminar = nombre.readLine();
        if (BuscarVertice(G, nombre_a_eliminar) != null) {
            V1 = BuscarVertice(G, nombre_a_eliminar);
            G.removeVertex(V1);
            System.out.println(V1.element().nombre + " " + "Ha sido eliminado de College Social Network");
        } else {
            System.out.println("**************************************************");
            System.out.println("No se encontro el nombre en College Social Network ");
            System.out.println("**************************************************");
        }
    }
    /*PROCEDIMIENTO PARA ELIMINAR UN AMGO DADO UN GRAFO*/

    public static void Eliminar_Amigo(AdjacencyListGraph G) throws IOException {
        Vertex<Estudiante> V1 = null;
        Vertex<Estudiante> V2 = null;
        InputStreamReader scantext1 = new InputStreamReader(System.in);
        BufferedReader persona = new BufferedReader(scantext1);
        InputStreamReader scantext2 = new InputStreamReader(System.in);
        BufferedReader amigo_a_eliminar = new BufferedReader(scantext2);
        System.out.println("Ingrese el nombre de la persona:");
        String persona_con_amigo = persona.readLine();
        System.out.println("Ingrese el amigo de la persona a elimnar:");
        String amigo_eliminado = amigo_a_eliminar.readLine();
        V1 = BuscarVertice(G, persona_con_amigo);
        V2 = BuscarVertice(G, amigo_eliminado);
        if (V1 != null && V2 != null && G.areAdjacent(V2, V1)) {
            Edge enemigo = GRAPH_buscarEdge(G, V1, V2, Compatibilidad(V1, V2));
            G.removeEdge(enemigo);
            System.out.println(V1.element().nombre + " y " + V2.element().nombre + " han dejado de ser amigos");

        } else {
            System.out.println("*********************************************");
            System.out.println("No se puede eliminar puesto que no son amigos");
            System.out.println("*********************************************");
        }
    }
    /*PROCEDIMIENTO PARA LISTAR AMIGOS DADO UN GRAFO*********/

    public static void Listar_Amigos(AdjacencyListGraph G) throws IOException {
        Vertex<Estudiante> V1 = null;
        Vertex<Estudiante> V2 = null;
        Iterator listV2 = G.vertices().iterator();
        InputStreamReader scantxt = new InputStreamReader(System.in);
        BufferedReader persona_con_amigos_a_consultar = new BufferedReader(scantxt);
        System.out.println("Ingrese el nombre de la persona a consultarle los amigos que tiene:");
        String consulta_amigos = persona_con_amigos_a_consultar.readLine();
        if (BuscarVertice(G, consulta_amigos) != null) {
            V1 = BuscarVertice(G, consulta_amigos);
            System.out.println("Los amigos de" + " " + V1.element().nombre + " " + "son:");
            while (listV2.hasNext()) {
                V2 = (Vertex) listV2.next();
                if (G.areAdjacent(V2, V1)) {
                    System.out.println(V2.element().nombre);
                }
            }
        } else {
            System.out.println("************************************");
            System.out.println("La persona no se encuentra en la red");
            System.out.println("************************************");
        }
    }
    /*PROCEDIMIENTO QUE BUSCA EL CAMINO MAS CORTO ENTRE DOS VERTICES*/
public static void Dijkstra(AdjacencyListGraph G, Vertex<Estudiante> Vin, Vertex<Estudiante> Vfin) {
        Vertex<Estudiante> vert, vertice_minimo, Vtemp;
        LinkedList<Vertex> ConjuntoVertices = new <Vertex> LinkedList();
        ListIterator itr_vertice = ConjuntoVertices.listIterator();
        Edge Arco;
        int peso = 0;
        Vin.element().distancia = 0;
        Iterator vertices = G.vertices().iterator();
        while (vertices.hasNext()) {
            vert = (Vertex) vertices.next();
            if (vert != Vin) {
                vert.element().distancia = INF;
            }
            itr_vertice.add(vert);
        }
        while (EstaEnLista(ConjuntoVertices, Vfin)) {
            vertice_minimo = VerticeMinDistancia(ConjuntoVertices);
            ConjuntoVertices.remove(vertice_minimo);
            Iterator vert_adj = G.vertices().iterator();
            while (vert_adj.hasNext()) {
                Vtemp = (Vertex) vert_adj.next();
                if ((G.areAdjacent(vertice_minimo, Vtemp)) && (EstaEnLista(ConjuntoVertices, Vtemp))) {
                    Arco = GRAPH_buscarEdge(G, vertice_minimo, Vtemp, Compatibilidad(vertice_minimo, Vtemp));
                    peso = Integer.parseInt(Arco.element().toString());

                    Vtemp.element().distancia = Math.min((Vtemp.element().distancia), (vertice_minimo.element().distancia + peso));
                }
            }
        }
    }
/*FUNCION PARA VERIFICAR SI UN VERTICE ESTA EN UNA LISTA UTILIZADA EN DIJKSTRA*/
    public static boolean EstaEnLista(LinkedList<Vertex> Lista, Vertex<Estudiante> Vfin) {
        ListIterator verifica = Lista.listIterator();
        Vertex<Estudiante> Vtemp;
        while (verifica.hasNext()) {
            Vtemp = (Vertex) verifica.next();
            if (Vtemp.equals(Vfin)) {
                return true;
            }
        }
        return false;
    }
/*FUNCION QUE RETORNA UN VERTICE CON LA MENOR DISTANCIA O COSTO DADA UNA LISTA DE VERTICES*/
    public static Vertex VerticeMinDistancia(LinkedList<Vertex> Lista) {
        ListIterator Vminimo = Lista.listIterator();
        Vertex<Estudiante> Vmin, Vtemp;
        Vmin = (Vertex) Vminimo.next();
        while (Vminimo.hasNext()) {
            Vtemp = (Vertex) Vminimo.next();
            if (Vmin.element().distancia > Vtemp.element().distancia) {
                Vmin = Vtemp;
            }
        }
        return Vmin;
    }
/*PROCEDIMIENTO PARA SUJERIR AMIGOS UTILIZANDO PROCEDIMEINTO DIJKSTRA*/
    public static void SujerirAmigos(AdjacencyListGraph G) throws IOException {

        Vertex<Estudiante> Vin = null, Vtemp = null;
        Iterator vertice = G.vertices().iterator();
        InputStreamReader scantxt = new InputStreamReader(System.in);
        BufferedReader sujerido = new BufferedReader(scantxt);
        System.out.println("Ingrese el nombre de una persona para sujerirle amistad:");
        String Data = sujerido.readLine();

        Vin = BuscarVertice(G, Data);
        if (Vin != null) {
            System.out.println("Sujerencias de amigos: ");

            while (vertice.hasNext()) {
                Vtemp = (Vertex) vertice.next();
                Dijkstra(G, Vin, Vtemp);
                if (Vtemp.element().distancia < W && !(G.areAdjacent(Vin, Vtemp)) && Vtemp != Vin) {
                    System.out.println(Vtemp.element().nombre);
                }

            }
        } else {
            System.out.println("Message: No se puede sujerir amistad debido a que la persna no se encuentra en la red");
        }
    }
}
