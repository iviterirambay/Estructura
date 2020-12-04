import javax.swing.JOptionPane;
public class Suma {
    public static void main( String args[] )
    {
        String FirstNum;
        String SecondNum;
        
        int First,Second,Suma;
        FirstNum = JOptionPane.showInputDialog("Escriba el primer entero");
        SecondNum = JOptionPane.showInputDialog("Escriba el segundo entero");
        
        //CONVERTIR LOS NUMEROS DE TIPO String a int
        First = Integer.parseInt(FirstNum);
        Second = Integer.parseInt(SecondNum);
        
        Suma = First + Second;
        
        //Mostrar el resultado
        JOptionPane.showMessageDialog(null, "La suma es: " +Suma, "Resultado",JOptionPane.PLAIN_MESSAGE);
    }
}
