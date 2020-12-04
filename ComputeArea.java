import java.util.Scanner;
import javax.swing.JOptionPane;

// ComputeArea.java: Area del circulo
public class ComputeArea {
  /** Main method */
  public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
    // Assign a radius
    double radius;
    String radio;
    radio=JOptionPane.showInputDialog("Escriba el radio del circulo para calcular su area:");
    radius = Double.parseDouble(radio); 
    // Compute area
    double area = radius * radius * Math.PI; //la libreria math contiene muchas funciones matematicas

    JOptionPane.showMessageDialog(null,"El area es " + area,"CALCULO DE AREA PARA EL CIRCULO",JOptionPane.PLAIN_MESSAGE);

    
  }
}