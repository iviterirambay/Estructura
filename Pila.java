

import java.io.*;
public class Pila {

    public static void main(String[] args)throws IOException{
        int validarpop = 0;
        int validarpush=0;
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String sIngreso;

        PilaStack ps = new PilaStack();

        System.out.println("Ingresa cadena: ");
        sIngreso= buff.readLine();

        for(int iCont=0; iCont<sIngreso.length(); iCont++){

            System.out.println(sIngreso.charAt(iCont));
            if(sIngreso.charAt(iCont)=='{'){
                ps.push();
                validarpush++;
            }
            if(sIngreso.charAt(iCont)=='['){
                ps.push();
                validarpush++;
            }
            
            if(sIngreso.charAt(iCont)=='('){
                ps.push();
                validarpush++;
            }
            if(sIngreso.charAt(iCont)==')'){
                ps.pop();
                validarpop++;
            }
            if(sIngreso.charAt(iCont)==']'){
                ps.pop();
                validarpop++;
            }
            if(sIngreso.charAt(iCont)=='}'){
                ps.pop();
                validarpop++;
            }
            
        }
        if(ps.vacia()==true && validarpop == validarpush){
            System.out.println("Parentesis balanceados");
        }
        
        else{
                System.out.println("Corrija por favor");
        }



    }
public  static class PilaStack {
    int iTope=-1;
    char caracteres[];

    PilaStack(){
        int max=5;
        caracteres=new char[max];
    }
    public boolean llena(){
        if(iTope==caracteres.length-1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean vacia(){
        if(iTope==-1){
            return true;
        }
        else{
            return false;
        }
    }
    public void push(){
        char dato = 0;
        if(llena()==true){
            System.out.print("Overflow");
        }
        else{
            iTope++;
            caracteres[iTope]=dato;
        }
    }
    public char pop(){
        char aux = 0;
        if(vacia()==true){
            System.out.println("Empty");
        }
        else{
            aux = caracteres[iTope];
            iTope--;
        }
        return aux;
    }
}

}


