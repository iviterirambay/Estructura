// Factorial.java
// Programa que calcula el factorial de un número.
import javax.swing.JOptionPane;
public class Factorial {
        public static void main(String args[])
        {
            String numero;
            long fac;
            int num;
            numero = JOptionPane.showInputDialog("Escriba el número");
            
            // Convierte el número de tipo String a tipo int
            num = Integer.parseInt(numero);
            
            fac = factorial(num);
            JOptionPane.showMessageDialog(null,"El factorial es: "+fac,"CALCULO DE FACTORIAL",JOptionPane.PLAIN_MESSAGE);
        } 
   
    // recursive declaration of method factorial
   public static long factorial( int number )
   {                  
      // base case
      if ( number <= 1 )  
         return 1;

      // recursive step
      else                
         return number * factorial( number - 1 );

   } // end method factorial

} // end class FactorialTest

