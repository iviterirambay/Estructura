/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author victor
 */
import  java.awt.*;
import javax.swing.*;
public class Panel2 extends JPanel {
   public Panel2()
    {
    setVisible(true);
    setSize(170,115);
    }
public void paint(Graphics g)
{
    super.paint(g);
g.drawImage(Imagenes.cargaImagen("b.gif"),0,0,this);
}
public static void main(String aa[])
{ Panel2 a=new Panel2();
}
}
