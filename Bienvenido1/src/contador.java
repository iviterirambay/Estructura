import java.util.*;
import java.io.*;
/**
 * Clase que cuenta las palabras que aparecen en un texto.
 * 
 */
public class contador
{
    // Mapa que utilizamos para almacenar la frecuencia de las palabras
    // Usando TreeMap nos almacena las palabras ordenadas de acuerdo a 
    // la clave que se usa, en este caso por orden alfabetico.
    private Map<String, Integer> elMapa;
    
    
    /**
     * Constructor que deja el mapa vacio
     */
    public contador()
    {
        // Creamos el mapa vacio
        elMapa = new TreeMap<String, Integer>();
    }
    
    /**
     * Lee un texto del fichero cuyo nombre se especifica y mete en el mapa
     * las palabras, con su frecuencia de aparicion
     */
    public void LeeTexto(String nombreFichero) throws FileNotFoundException
    {
        String linea;
        int posEspacio;
        int valor;
        //Abrimos el fichero, creando el stream necesario para leer de fichero
        
        FileReader fich = new FileReader(nombreFichero);
        BufferedReader ent = new BufferedReader(fich);
       
        try {
            try {
                do {    // Leo una linea
                    linea = ent.readLine();
                    if (linea!=null) {
                        linea = linea.toLowerCase();
                        linea = linea.trim();
                        // Separo el texto en palabras
                        String[] palabras=linea.split(" ");
                        // recorro todas las palabras
                        for (String palabra: palabras) {
                            // trato solo las palabras de tamano mayor que cero
                            // para evitar problemas con varios espacios seguidos
                            if (palabra.length()>0) {
                                if (elMapa.containsKey(palabra)) 
                                    {// aumento en uno el valor
                                        valor = elMapa.get(palabra);
                                        elMapa.put(palabra, valor+1);
                                     } else { //anado nueva palabra
                                         elMapa.put(palabra,1);  
                                     }
                            } // if
                        } // for
                    } // if linea no nula
                }while (linea!=null);
            } finally {
                ent.close();
                fich.close();
            }
        } catch (IOException e) {
            System.out.println("Error "+e);
        }                 
    }

    /**
     * Retorna el numero de apariciones de una palabra
     */
    public int aparicionesPalabra(String palabra) {
        if (elMapa.containsKey(palabra)){
            return elMapa.get(palabra);}
        else {
            return 0;
        }
                
    }
    
    /**
     * Muestra el mapa en orden alfabetico
     */
    public void ordenAlfabetico () {
        // Obtengo el conjunto de Map.Entry con todas las parejas palabra,
        // frecuencia
        Set<Map.Entry<String, Integer>> elConjunto = elMapa.entrySet();
        
        for (Map.Entry<String,Integer> elElem:elConjunto) {
            System.out.println("Palabra : "+elElem.getKey()+ 
                               "   Frecuencia: "+elElem.getValue());
        }
    }
    

    
    // Clase interna que empleamos para almacenar y ordenar los datos 
    // correspondientes a una palabra; debe implementar el metodo compareTo
    private static class DatosPalabra implements Comparable<DatosPalabra> {
        private int frecuencia;
        private String palabra;
        
        DatosPalabra(String pal, int frec){
            this.frecuencia = frec;
            this.palabra = pal;
        }
        
        @Override
        public String toString(){
            return "Frecuencia : "+this.frecuencia+"   Palabra: "+this.palabra;
        }
        
        public int compareTo(DatosPalabra otro){
           
            if (this.frecuencia < otro.frecuencia) {
                return -1;
            } else if (this.frecuencia > otro.frecuencia) {
                return 1;
            }else {
                return 0;
            }
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof DatosPalabra) {
                return this.frecuencia==((DatosPalabra)o).frecuencia;
            } else {
                return false;   
            }
        }
    }

    /**
     * Muestra el mapa en orden inverso de frecuencia
     * Uso una priority queue para ordenar
     */
    public void ordenFrecuencia() {
       
        PriorityQueue<DatosPalabra> conjuntoOrdenado = 
            new PriorityQueue<DatosPalabra>();
        Set<Map.Entry<String, Integer>> elConjunto = elMapa.entrySet();
        
        // Recorro el conjunto creando un objeto DatosPalabra
        // por cada palabra y metiendolo en el conjunto ordenado
        for (Map.Entry<String,Integer> elElem:elConjunto) {
            DatosPalabra nuevoDato = 
                new DatosPalabra( elElem.getKey(), elElem.getValue());
            conjuntoOrdenado.add(nuevoDato);
        }
        while (!conjuntoOrdenado.isEmpty()){
            DatosPalabra nuevoDato = conjuntoOrdenado.remove();
            System.out.println(nuevoDato);
        }
    }    
    
    /**
     * Muestra el mapa en orden inverso de frecuencia
     * Uso una lista y un metodo de ordenar
     */
    public void ordenFrecuenciaOtro() {
       
        List<DatosPalabra> lista = 
            new LinkedList<DatosPalabra>();
        Set<Map.Entry<String, Integer>> elConjunto = elMapa.entrySet();
        
        // Recorro el conjunto creando un objeto DatosPalabra
        // por cada palabra y metiendolo en la lista
        for (Map.Entry<String,Integer> elElem:elConjunto) {
            DatosPalabra nuevoDato = 
                new DatosPalabra(elElem.getKey(), elElem.getValue());
            lista.add(nuevoDato);
        }
        // ordenar la lista
        Collections.sort(lista);
        for (DatosPalabra dato:lista){
            System.out.println(dato);
        }
    }
        
        
}
