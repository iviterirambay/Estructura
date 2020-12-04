// Promedio_10.java
/* Programa que lee 10 números enteros o hasta que se increse
 * un número negativo, el cual muestra el promedio de los 
 * números introducidos (sin contar número negativo).
 */
import javax.swing.JOptionPane;
 public class Promedio_10{
  public static void main ( String args[]){
   final int Tam = 10;

   int[] num = new int[Tam];

   int divi=0,negativo=0;
    for(int i=0;i<Tam;i++){
     String numString = JOptionPane.showInputDialog(
     "Escriba un número:");

     //Combierte los números de tipo String a int
     num[i] = Integer.parseInt(numString);
     if(num[i]<0){
     i=Tam;
     negativo = 1;
     }
     divi++;
    }
   promedio(num,divi-negativo);
  }
 public static void promedio(int [] list,int dividir){
 int suma=0;
 double Promedio;
 String output;
 if(dividir==0){
 output = "\nNo hay promedio que calcular, pues ingreso un número negativo";
 JOptionPane.showMessageDialog(null, output);
 }
for(int i=0;i<dividir;i++){

suma = suma + list[i];
}
Promedio = (double)(suma/dividir);
output = "\nEl promedio de los números que ingreso es "+Promedio;
JOptionPane.showMessageDialog(null, output);
}
 }
