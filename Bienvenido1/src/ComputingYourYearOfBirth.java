
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author irabvite
 */
public class ComputingYourYearOfBirth {
    public static void main (String[]args) throws IOException{
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);
        System.out.print("Enter your age: ");
        String text = input.readLine();
        int age = new Integer(text).intValue();
        System.out.println("You are " +age +" years old, now," );
        int year= 2011-age;
        System.out.println("so you were probably born in "+year);
    }
}
