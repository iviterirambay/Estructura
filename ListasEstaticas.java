/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package listas_estaticas;
import java.util.*;
/**
 *
 * @author angelous
 */
public class ListasEstaticas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();

        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        System.out.println("ArrayList contiene " + arrayList.size() + " elementos");
        System.out.println(arrayList);
        Integer one = (Integer) arrayList.get(0);
        System.out.println("El valor en indice 0 es " + one);

        arrayList.set(2, one);
        System.out.println("El valor en indice 2 es " + arrayList.get(2));
        System.out.println(arrayList);
    }

}
