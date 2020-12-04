/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Familia Rambay
 */
import javax.swing.JOptionPane;
public class Debase10tocualquierBASE 
{
        public static void main(String[] args) 
        {         
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el número a transformar"));
        int base = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la base"));
        Base bases = new Base(numero,base); 
        bases.transformar();
        JOptionPane.showMessageDialog(null,"El número "+numero+" en base "+base+"es:\n"+bases.getResultado());
        }
}
 
class Base 
{
String equivalente;  
int posicion;  
int numero;  
int base;  
int[] digitos;
int[] auxiliar;
 
        public Base(int n, int b) 
        {
                posicion = 0;
                equivalente = "";
                base = b;
                numero = n;
                digitos = new int [1];
        }
 
        public void transformar() 
        {
                if(numero<base)
                {
                        digitos[posicion]=numero;
                        tamaño(); 
                }
                else
                {
                        digitos[posicion]=numero%base;
                        tamaño();
                        posicion++;
                        numero = numero/base;
                        transformar(); 
                 }
        }
 
        public String getResultado()
        {
                for (int j = digitos.length-2; j >= 0; j--) 
                {
                        equivalente+=Character.toUpperCase(Character.forDigit(digitos[j], 16));
                }
                return equivalente;
        }
 
        private void tamaño() 
        {
                 auxiliar = digitos;
                 digitos = new int [auxiliar.length+1];
                 System.arraycopy(auxiliar, 0, digitos, 0, auxiliar.length);
        }
}