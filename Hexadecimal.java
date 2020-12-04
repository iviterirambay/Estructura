//Hexadecimal.java
//Este programe convierte un número de base 10 a uno de base 16

/**
 *
 * @uthor:Viteri Rambay Irwin Alberto
 */
import java.io.*;
import java.lang.*;

public class Hexadecimal{
  public static void main(String[] args) throws IOException{  
  BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
  System.out.println("Ingrese el número decimal para convertirla en hexadecimal:");
  String hex = bf.readLine();
  int i = Integer.parseInt(hex);
  String hex1 = Integer.toHexString(i);
  System.out.println("Hexa decimal: " + hex1);
  }
}