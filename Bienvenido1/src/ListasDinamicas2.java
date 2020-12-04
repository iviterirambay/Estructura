package listas_dimamicas2;
import java.util.ListIterator;
import java.util.LinkedList;
/**
 *
 * @author angelous
 */
public class ListasDinamicas2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     /**
     * @param args the command line arguments
     */
        LinkedList lList = new LinkedList();
        ListIterator itr = lList.listIterator();
        System.out.println("agrego elementos a la lista");

        for (int i = 0; i < 10; i++) {
            estudiante elemento = new estudiante();
            elemento.nombre = "alumno" + i;
            elemento.num_mat = i;
            itr.add(elemento);
        }


        itr = lList.listIterator();// apunto a al comienzo de la lista
        while (itr.hasNext())//imprimo la lista
        {
            estudiante temp = (estudiante) itr.next();
            System.out.print(temp.nombre + " ");
        }
        System.out.print("\n");

        itr = lList.listIterator();

        itr.next();
        itr.remove();//remuevo el primero
        itr = lList.listIterator();// apunto a al comienzo de la lista

        while (itr.hasNext())//imprimo la lista
        {
            estudiante temp = (estudiante) itr.next();
            System.out.print(temp.nombre + " ");
        }
        System.out.print("\n");

        itr = lList.listIterator();// apunto a al comienzo de la lista
        while (itr.hasNext())//me muevo al ultimo
        {
            itr.next();
        }
        itr.remove();//remuevo el ultimo
        itr = lList.listIterator();// apunto a al comienzo de la lista

        while (itr.hasNext())//imprimo la lista
        {
            estudiante temp = (estudiante) itr.next();
            System.out.print(temp.nombre + " ");
        }
        System.out.print("\n");

        itr = lList.listIterator(); // apunto a al comienzo de la lista
        for (int i = 0; i < 2; i++) //me muevo 2 posiciones hacia adelante y borro
        {
            itr.next();
        }
        itr.remove();

        for (int i = 0; i < 3; i++) //me muevo 3 posiciones mas y borro
        {
            itr.next();
        }

        itr.remove();

//Agrego un elemento donde borre
        estudiante elemento = new estudiante();
        elemento.nombre = "alumno" + 45;
        elemento.num_mat = 45;

        itr.add(elemento);
//itr.next();

        estudiante temp = (estudiante) itr.next();
        temp.nombre = "alumno" + 100;
        temp.num_mat = 100;
//itr.next();
        itr.set(temp);

        temp = (estudiante) itr.next();
        temp.nombre = "alumno" + 150;
        temp.num_mat = 150;


        itr = lList.listIterator();// apunto a al comienzo de la lista
        while (itr.hasNext())//imprimo la lista
        {
            temp = (estudiante) itr.next();
            System.out.print(temp.nombre + " ");
        }
        System.out.print("\n");


        while (itr.hasPrevious()) {
            temp = (estudiante) itr.previous();
            System.out.print(temp.nombre + " ");
        }
        System.out.print("\n");
     
        System.out.println(lList.size());
        lList.clear();
        System.out.println(lList.size());
    }

}

class estudiante {

    public int num_mat;
    public String nombre;
}