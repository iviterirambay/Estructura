import java.lang.String;
import javax.swing.JOptionPane;

public class TextFunc
{
     public static void main(String[] args)
          {
               String textString;
                              
               textString = JOptionPane.showInputDialog("Enter a string:");
               
                              
               totalString(textString);
               convLowerCase(textString);
               replaceLetters(textString);
               convUpperCase(textString);
               
               System.out.println();
               System.exit( 0 );     
          }  
          
          public static void totalString(String text)
          {
               System.out.println("The length of the string " + text + " is " 
               + text.length());
          }
          
          public static void convLowerCase(String text)
          {
               System.out.println("Here it is in lower case " 
               + text.toLowerCase());
          }
          
          public static void replaceLetters(String text)
          {
               System.out.println("Here it is with the a and z swapped " 
               + text.replace('a','z'));
          }
          
          public static void convUpperCase(String text)
          {
               System.out.println("Here it is in upper case " 
               + text.toUpperCase());
          }
}
