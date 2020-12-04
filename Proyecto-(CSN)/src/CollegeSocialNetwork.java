/*                                                        College Social Network 
                                   DESCRIPCIÓN 
College Social Network (CSN), es una red social diseñada para promover y estimular la participación 
y colaboración entre los estudiantes de diferentes universidades que comparten características en 
común. Permite que estudiantes que tengan una misma materia establezcan contacto virtual 
(inicialmente) y que generen conocimiento en conjunto. De manera que los estudiantes refuercen y 
amplíen sus conocimientos.

                                    Información 
La información de los usuarios deberá ser cargada desde archivo(s). Con al menos el siguiente nivel 
de detalle: Nombre, Universidad, Cuidad, Preferencias (lista enlazada), Cursos Actuales (lista 
enlazada). 
Los amigos que tenga cada usuario deberán ser cargados desde archivo(s). 
 
                                 Compatibilidad (K) 
La compatibilidad es una medida que permite sugerir amigos a los usuarios de la CSN y se calcula 
usando la siguiente formula: 

                                   W 
 K =  _______________________________________________________________________
       1 + #(Preferencias Similares) + #(cursos similares)^2 + 2(!Universidad) 

W (Criterio de Afinidad) = 100 
Mientras menor sea el valor K entre dos estudiantes mayor su compatibilidad. 

                                     Implementación 
a) La información de los usuarios y sus amigos deberá ser cargada en una TDA Grafo. 
b) Los pesos de los arcos serán resultado del cálculo de compatibilidad (K). 
c) La CSN permitirá buscar a un estudiante de la red y le sugerirá a otros estudiantes con los 
   cuales muy probablemente serán buenos amigos. De acuerdo al siguiente criterio: Todos los nodos
   pertenecientes a los posibles caminos que tengan un costo menor a W.
d) Administración de los usuarios de la CSN: 
1. Consultar 
2. Agregar Personas 
3. Agregar Amigos 
4. Eliminar Persona 
5. Eliminar Amigo 
6. Listar Amigos 

                                  Consideraciones:
 * La extensión de los archivos donde está la información de los estudiantes será .dat
 * El número mínimo de vértices que deberá cargar es 20, y el número mínimo de arcos 50. 
                                                                                                    */
/* Profesor: ING. ANGEL J. LÓPEZ
 * Fecha de entrega: AGOSTO 28, 2011
 * Autores: Viteri Rambay Irwin Alberto
 *          Guevara Gallardo Alfonso
 */

//---------------------------------------------------------------------LIBRERIAS
import net.datastructures.*;
import java.util.*;
import java.io.*;

public class CollegeSocialNetwork {
    public static void main (String [] args){
        MENU();
    }
    class Estudiante{
    String nombre;
    LinkedList preferencias;
    LinkedList cursos;
    }
    public static void MENU(){
    /*Administración de los usuarios de la CSN: 
      1. Consultar 
      2. Agregar Personas 
      3. Agregar Amigos 
      4. Eliminar Persona 
      5. Eliminar Amigo 
      6. Listar Amigos*/
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n----Administración de los usuarios de la CSN:");
        System.out.println("++++1. Consultar\n----2. Agregar Personas\n++++3. Agregar Amigos\n----4. Eliminar Persona\n++++5. Eliminar Amigo\n----6. Listar Amigos\n++++++++++++++++++++++++++++++++++++++++++++");
    
   }
}