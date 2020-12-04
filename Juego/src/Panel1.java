/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
import java.awt.*;
import javax.swing.*;
public class Panel1 extends JPanel {

    public Panel1()
    {
    setVisible(true);
    
    }
    public void paint(Graphics g)
    { super.paint(g);
   /* g.drawImage(Imagenes.cargaImagen("ayuda.jpg"),0,0,this);
    g.setColor(new Color(100,0,255));
     setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
    g.drawString("Paso 1:Iniciar los valores con los que comenzara el metodo  ", 10, 90);
    g.drawString("el programa por defecto comenzara con X=0, Y=0, Z=0", 10,110);
    g.drawString("Paso 2: Inserta Es como error aproximado a alcanzar", 10, 170);
       g.drawString("Paso 3: Inserta la ecuacion de la forma AX+BY+CZ=D", 10, 230);
          g.drawString("Paso 4: Click en el icono de la calculadora y listo", 10, 290);
              g.drawString("resultado en la tabla", 10, 310);
*/g.drawImage(Imagenes.cargaImagen("VM1.jpg"),0,0,this);
         // NOI18N
               g.setColor(Color.RED);
                setFont(new java.awt.Font("Comic Sans MS", 0, 18));
                g.drawString("Aplicacion java 1.6", 130, 90);
                 g.drawString("Por Victor A.M.L", 10,120);
                 g.drawString("Juego de Ahorcado", 130,150);



    }
 public static void main(String aa[])
{ Panel1 a=new Panel1();
}
}
