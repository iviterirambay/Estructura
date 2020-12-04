package listas_dinamicas;

import java.util.ListIterator;
import java.util.LinkedList;
/**
 *
 * @author angelous
 */
public class ListasDinamicas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList lList = new LinkedList();
        ListIterator itr = lList.listIterator();
        System.out.println("agrego elementos a la lista");
        itr.add(0);
        itr.add(1);
        itr.add(2);
        itr.add(3);
        itr.add(4);
        itr.add(5);
        itr.add(6);
        itr.add(7);
        itr.add(8);
        itr.add(9);

        itr = lList.listIterator();// apunto a al comienzo de la lista

        while (itr.hasNext())//recorro la lista
        {
            System.out.print(itr.next() + " ");
        }

        System.out.println("\nBorro elementos de la lista");

        itr = lList.listIterator();
//itr.remove(); //error

        itr.next(); //remuevo el primero
        itr.remove();
        System.out.println(lList);

        itr = lList.listIterator();// apunto a al comienzo de la lista
        while (itr.hasNext())//remuevo el ultimo
        {
            itr.next(); //remuevo el ultimo
        }
        itr.remove();
        System.out.println(lList);

        itr = lList.listIterator();// apunto a al comienzo de la lista
        while (itr.hasNext())//remuevo el ultimo
        {
            itr.next(); //remuevo el ultimo
        }
        while (itr.hasPrevious())//remuevo el ultimo
        {
            System.out.print(itr.previous()+ " "); //remuevo el ultimo

        }
        System.out.println(lList);



        itr = lList.listIterator(); // apunto a al comienzo de la lista
        for (int i = 0; i < 2; i++) //me muevo 2 posiciones hacia adelante y borro
        {
            itr.next();
        }
        itr.remove();
        System.out.println(lList);

        for (int i = 0; i < 3; i++) //me muevo 3 posiciones mas y borro
        {
            itr.next();
        }
        itr.remove();
        System.out.println(lList);

//Agrego un elemento donde borre
        itr.add(45);
        System.out.println(lList);

        itr.next();
        itr.set(100); //cambio el valor de un elemento
        System.out.println(lList);

    }

}
