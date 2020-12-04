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
public class Panel extends JPanel {
    public Panel()
    {
    setVisible(true);
    setSize(550,480);
    }

public void paint(Graphics g)
    {
    super.paint(g);
    g.drawImage(Imagenes.cargaImagen("ayuda.jpg"),0,0,this);
    g.setColor(new Color(100,0,255));
     setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
    g.drawString("Paso 1:Click en el boton iniciar para generar la palabra  ", 10, 90);
    g.drawString("se puede agregar palabras en la pestaña archivo", 10,110);
    g.drawString("ingresar la letra que se crea y dar enter", 10, 170);
       g.drawString("Paso 3: si la letra es corecta aparecera donde corresponde", 10, 230);
          g.drawString("Paso 4: si es erronea aparecera parte del el ahorcado", 10, 290);
              g.drawString("", 10, 310);

               g.setColor(Color.RED);
                g.drawString("se tienes tres oportunidades para ganar", 10, 390);
                 g.drawString("si quiere una nueva palabra click de nuevo en inicar", 10, 410);
                 g.drawString("las lineas del INICIO son la cantidad de letras", 10,430);



    }
public static void main(String aa[])
{ Panel a=new Panel();
}
}
