
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class PruebaTabla {
  
  private JTable t;
  private DefaultTableModel m;
  private int i=0;
  private int a=0;
  
  public PruebaTabla(){
    //* Inicializar variables *//
    JFrame v = new JFrame();
    m = new DefaultTableModel();
    t = new JTable(m);
    JScrollPane s = new JScrollPane(t);
   
    //* Se agregarn tres columnas inicialmente //*
    m.addColumn(i++);
    m.addColumn(i++);
    m.addColumn(i++);

    /** Se agregan diez columnas inicialmente con el valor del contador **/
    for(int i=0;i<10;i++)
      m.addRow(new Object[]{a++,"",""});
    
    /** Se agregan el scroll a la ventna **/
    v.getContentPane().add(s,BorderLayout.CENTER);
    
    JPanel pN=new JPanel();
    
    //* Inicializan botonnes y se dan acciones *//
    JButton aF = new JButton("Agregar Fila");
    pN.add(aF);
    aF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        agregar(2);
      }  
    });
    
    JButton bF = new JButton("Borrar Fila");
    pN.add(bF);
    bF.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        eliminar(2);
      }  
    });
    
    JButton aC = new JButton("Agregar Columna");
    pN.add(aC);
    aC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        agregar(1);
      }  
    });
    
    JButton bC = new JButton("Borrar Columna");
    pN.add(bC);
    bC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        eliminar(1);
      }  
    });
    
    /** Agregan y se crea ventana **/
    v.getContentPane().add(pN,BorderLayout.NORTH);
    v.pack();
    v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    v.setVisible(true);
  }
  
  /* Se encarga de eliminar
   * si el valor es 1 elimina la columna
   * si el valor es 2 elimina la fila */
  private void eliminar(int caso){
    try{
    switch(caso){
      case 1:
        t.removeColumn(t.getColumnModel().getColumn(t.getSelectedColumn()));//Elimna la columna selecionada por el usuario
        break;
      case 2:
        m.removeRow(t.getSelectedRow());//Elimna la fila selecionada por el usuario
        break;
    }
    }catch(ArrayIndexOutOfBoundsException aIE){}
  }
  
  /* Se encarga de agregar
   * si el valor es 1 agrega una columna
   * si el valor es 2 agrega una fila */
  private void agregar(int caso){
    try{
    switch(caso){
      case 1:
        m.addColumn(i++);//Agrega la columna con su respectivo nombre, en este caso el valor del contador
        break;
      case 2:
        m.addRow(new Object[]{a++,"",""});//Agrega un fila con el valor del contador y los demas en blanco
        break;
    }  
    }catch(ArrayIndexOutOfBoundsException aIE){}
  }
  public static void main(String [] args){
    new PruebaTabla();//Se corre la clase
  }
}