import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class CalculadoraOperaCadena {

    public static void main(String[] args) {
        frm nuevo = new frm();
        nuevo.setLocationRelativeTo(null);
        nuevo.setVisible(true);
    }
    public static  class ALexico {
    private int tipo,error;

    public ALexico(){
        error = 0;
        tipo = -1;
    }
    public void add(char x){
        tipo = getType(x);
        if(tipo == -1)
            error++;
    }
    public int getTipo(char c){
        return getType(c);
    }
    public int getError(){
        return error;
    }
    public  int getType(char x){
        int i = -1;
        switch(x){
            case '0': i = 0; break;
            case '1': i = 0; break;
            case '2': i = 0; break;
            case '3': i = 0; break;
            case '4': i = 0; break;
            case '5': i = 0; break;
            case '6': i = 0; break;
            case '7': i = 0; break;
            case '8': i = 0; break;
            case '9': i = 0; break;
            case '.': i = 1; break;
            case '(': i = 2; break;
            case ')': i = 3; break; //cierra
            case '[': i = 2; break;
            case ']': i = 3; break; //cierra
            case '{': i = 2; break;
            case '}': i = 3; break; //cierra
            case '+': i = 4; break;
            case '-': i = 4; break;
            case '*': i = 4; break;
            case '/': i = 4; break;
            case '^': i = 4; break;
            case '&': i = 4; break;
            case '!': i = 5; break;
        }
        return i;
    }
}
public static class ASintactico {
    private int tipo,error;
    private myStack2 pila;

    public  ASintactico(int tamano){
        tipo = -1;
        error = 0;
        pila = new myStack2(tamano);
    }
    
    public void add(char c){ //Falta optimizar
        String s = String.valueOf(c);
        char pi;
        if(c == '(' || c == '[' || c == '{'){ //Se verifica los parÃ©ntesis
            pila.push(s);
        }else{
            if(c == ')' || c == ']' || c == '}'){
               if(pila.isEmpty())
                    error++;
               else{
                   pi = pila.pop().charAt(0);
                   if( ((pi == '(' && c == ')') || (pi == '[' && c == ']') || ( pi == '{' && c == '}')) == false )
                       error++;
               }
            }
        }
    }
    public boolean isEmpty(){
        return pila.isEmpty();
    }
    public void setType(int xtipo){
        if(tipo == 1){
            if(xtipo != 0)
                error++; //Si luego de "." no sigue un nÃºmero
        }
         if(tipo == 4)   {
             if(xtipo == 4)
                 error++; //Si agrega dos operadores seguidos
         }
        tipo = xtipo;
    }
    public int getError(){
        return error;
    }
}

/**
 * @author Edmond
 * Comentarios a edmond.duke.developer@gmail.com
 * http://softcx.blogspot.com
 */
public static class frm extends javax.swing.JFrame implements ActionListener {
    private String[] stringInfija;
    private int error = 0;
    private float resp = 0;
    private UIManager.LookAndFeelInfo apariencias[]; //Obtiene las apariencias instaladas

    public frm() {
        initComponents();
        apariencias = UIManager.getInstalledLookAndFeels(); //Obtiene los temas instalados
        cambioUI(1); //Cambia de apariencia (tema)
        this.setSize(306,400);
    }

    private void cambioUI(int i){ //Metodo para cambiar de apariencia (tema)
        try{
            UIManager.setLookAndFeel(apariencias[i].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void botonClic(JButton boton){ //Agrega el String de la propiedad Name del JButton al JTexfield principal (txt)
        int posIniCursor = txt.getSelectionStart();
        int posFinCursor = txt.getSelectionEnd();

        String strAntes,strDespues,cadena;
        if(posIniCursor == posFinCursor)
            cadena = txt.getText();
        else
            cadena = txt.getText().substring(0,posIniCursor) + txt.getText().substring(posFinCursor,txt.getText().length());

        strAntes = cadena.substring(0,posIniCursor);
        strDespues = cadena.substring(posIniCursor);
        txt.setText(strAntes + boton.getName() +  strDespues);

        if(boton == jbSigno){
            txt.setSelectionStart(posIniCursor+2);
            txt.setSelectionEnd(posIniCursor+2);
        }else{
            txt.setSelectionStart(posIniCursor+1);
            txt.setSelectionEnd(posIniCursor+1);
        }
    }

    private void getStrInfija(String str){ //Crea la cadena infija del txt
        Vector<String> cadenaInfija = new Vector();

        ASintactico asin = new ASintactico(str.length());
        ALexico alex = new ALexico();
        
        error = 0;
        //if(str.length() == 0)
            //error++;

        String num = "";
        int i,j,tipo,atipo,nOperadores,nNumeros;
        char c;
        atipo = -5;
        i = 0;
        j = 1;
        nOperadores = 0;
        nNumeros = 0;
        while( i < str.length() && error == 0){
            c = str.charAt(i);
            if( c != ' '){
                j = 1;
                tipo = alex.getTipo(c); //Tipo es un entero que con el que indica un caracter
                if( (atipo == 0 && tipo == 2) || (atipo == 3 && tipo == 0) || (atipo == 3 && tipo == 2)){
                    c = '*';  //Agrega operador '*' en expresiones
                    j = 0;    ///   x * (..      )*[        (...)*999
                    tipo = 4;
                }
                if( (atipo == -5 && c == '-') || (atipo == 2 && c == '-')){ //Agrega el 0 en expresiones (-9) Ã³ -5 -> (0-9), 0-5
                    c = '0';
                    j = 0;
                    tipo = 0;
                }
                alex.add(c);
                asin.setType(tipo);
                asin.add(c);

                error = error + alex.getError() + asin.getError();

                if(error == 0){
                    if( tipo < 2){ //Es nÃºmero
                            num = num + String.valueOf(c);
                    }else{ //Es operador o signo de agrupaciÃ³n
                        if(num.equals("") == false){
                            try{
                                float x = Float.valueOf(num).floatValue();
                                cadenaInfija.add(num);
                                nNumeros++;
                            }catch(NumberFormatException e){
                                error++;
                            }
                        }
                        num = "";
                        if(tipo == 4) //Es operador
                            nOperadores++;
                        cadenaInfija.add(String.valueOf(c));
                    }
                }
                atipo = tipo;
            }
            i = i + j;
        }
        if(asin.isEmpty() == false)
            error++; //Si no estÃ¡n bien emperajados los parÃ©ntesis
        if(num.equals("") == false){ // Si la cadena "num" tiene un nÃºmero
            try{
                float x = Float.valueOf(num).floatValue();
                nNumeros++;
                cadenaInfija.add(num);
            }catch(NumberFormatException e){
                error++;
            }
        }
        if(nOperadores + 1 != nNumeros)
            error++; //Si nÃºmero de operadores no le corresponde al nÃºmero de operandos
        
        if(error == 0){
            int tamanio = cadenaInfija.size();
            stringInfija = new String[tamanio];
            for(i = 0; i < tamanio; i++){ //Pasa el vector a una arreglo de String
                stringInfija[i] = cadenaInfija.get(i);
            }
        }
    }

    private void Operar(){ //Opera la cadena infija si no hubiese errores
        getStrInfija(txt.getText());
        if(error > 0){
            txtR.setText("Error en sintaxis =");
        }else{
            MakePostFijo2 post = new MakePostFijo2(stringInfija); //Clase que convierte cadena infija           
            resp = post.getValor();
            txtR.setText(resp + " =");
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneText = new javax.swing.JPanel();
        txt = new javax.swing.JTextField();
        txtR = new javax.swing.JTextField();
        panelBotones = new javax.swing.JPanel();
        JButtonC = new javax.swing.JButton();
        JBAns = new javax.swing.JButton();
        jbParentesis1 = new javax.swing.JButton();
        jbParentesis2 = new javax.swing.JButton();
        JBPotencia = new javax.swing.JButton();
        jbRaiz = new javax.swing.JButton();
        jbFactorial = new javax.swing.JButton();
        jbSuma = new javax.swing.JButton();
        jb1 = new javax.swing.JButton();
        jb2 = new javax.swing.JButton();
        jb3 = new javax.swing.JButton();
        jbResta = new javax.swing.JButton();
        jb4 = new javax.swing.JButton();
        jb5 = new javax.swing.JButton();
        jb6 = new javax.swing.JButton();
        jbMultiplica = new javax.swing.JButton();
        jb7 = new javax.swing.JButton();
        jb8 = new javax.swing.JButton();
        jb9 = new javax.swing.JButton();
        jbDivide = new javax.swing.JButton();
        jbSigno = new javax.swing.JButton();
        jb0 = new javax.swing.JButton();
        jbDot = new javax.swing.JButton();
        jbOpera = new javax.swing.JButton();
        barMenu = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuISalir = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();
        mnuIAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setMinimumSize(new java.awt.Dimension(10, 10));

        paneText.setMinimumSize(new java.awt.Dimension(330, 150));
        paneText.setLayout(new java.awt.BorderLayout());

        txt.setFont(new java.awt.Font("Tahoma", 0, 24));
        txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt.setText("0");
        txt.setBorder(null);
        txt.setMinimumSize(new java.awt.Dimension(6, 60));
        txt.setPreferredSize(new java.awt.Dimension(19, 70));
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKeyTyped(evt);
            }
        });
        paneText.add(txt, java.awt.BorderLayout.CENTER);

        txtR.setFont(new java.awt.Font("Tahoma", 1, 15));
        txtR.setForeground(new java.awt.Color(0, 0, 153));
        txtR.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtR.setText("=");
        txtR.setBorder(null);
        paneText.add(txtR, java.awt.BorderLayout.SOUTH);

        getContentPane().add(paneText, java.awt.BorderLayout.CENTER);

        panelBotones.setPreferredSize(new java.awt.Dimension(315, 250));
        panelBotones.setLayout(new java.awt.GridLayout(6, 4, 10, 10));

        JButtonC.setBackground(new java.awt.Color(204, 51, 0));
        JButtonC.setFont(new java.awt.Font("Tahoma", 0, 18));
        JButtonC.setForeground(new java.awt.Color(255, 255, 255));
        JButtonC.setText("C");
        JButtonC.setFocusable(false);
        JButtonC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonCActionPerformed(evt);
            }
        });
        panelBotones.add(JButtonC);

        JBAns.setFont(new java.awt.Font("Tahoma", 0, 18));
        JBAns.setText("ANS");
        JBAns.setFocusable(false);
        JBAns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAnsActionPerformed(evt);
            }
        });
        panelBotones.add(JBAns);

        jbParentesis1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbParentesis1.setText("(");
        jbParentesis1.setFocusable(false);
        jbParentesis1.setName("("); // NOI18N
        panelBotones.add(jbParentesis1);
        jbParentesis1.addActionListener(this);

        jbParentesis2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbParentesis2.setText(")");
        jbParentesis2.setFocusable(false);
        jbParentesis2.setName(")"); // NOI18N
        panelBotones.add(jbParentesis2);
        jbParentesis2.addActionListener(this);

        JBPotencia.setFont(new java.awt.Font("Tahoma", 0, 18));
        JBPotencia.setText("x^y");
        JBPotencia.setToolTipText("\"x\" elevado a la \"y\"");
        JBPotencia.setFocusable(false);
        JBPotencia.setName("^"); // NOI18N
        panelBotones.add(JBPotencia);
        JBPotencia.addActionListener(this);

        jbRaiz.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbRaiz.setText("xâˆšy");
        jbRaiz.setToolTipText("RaÃ­z x-sima de y");
        jbRaiz.setFocusable(false);
        jbRaiz.setName("âˆš"); // NOI18N
        panelBotones.add(jbRaiz);
        jbRaiz.addActionListener(this);

        jbFactorial.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbFactorial.setText("!");
        jbFactorial.setFocusable(false);
        jbFactorial.setName("!"); // NOI18N
        panelBotones.add(jbFactorial);
        jbFactorial.addActionListener(this);

        jbSuma.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbSuma.setText("+");
        jbSuma.setFocusable(false);
        jbSuma.setName("+"); // NOI18N
        panelBotones.add(jbSuma);
        jbSuma.addActionListener(this);

        jb1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb1.setText("1");
        jb1.setFocusable(false);
        jb1.setName("1"); // NOI18N
        panelBotones.add(jb1);
        jb1.addActionListener(this);

        jb2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb2.setText("2");
        jb2.setFocusable(false);
        jb2.setName("2"); // NOI18N
        panelBotones.add(jb2);
        jb2.addActionListener(this);

        jb3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb3.setText("3");
        jb3.setFocusable(false);
        jb3.setName("3"); // NOI18N
        panelBotones.add(jb3);
        jb3.addActionListener(this);

        jbResta.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbResta.setText("-");
        jbResta.setFocusable(false);
        jbResta.setName("-"); // NOI18N
        panelBotones.add(jbResta);
        jbResta.addActionListener(this);

        jb4.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb4.setText("4");
        jb4.setFocusable(false);
        jb4.setName("4"); // NOI18N
        panelBotones.add(jb4);
        jb4.addActionListener(this);

        jb5.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb5.setText("5");
        jb5.setFocusable(false);
        jb5.setName("5"); // NOI18N
        panelBotones.add(jb5);
        jb5.addActionListener(this);

        jb6.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb6.setText("6");
        jb6.setFocusable(false);
        jb6.setName("6"); // NOI18N
        panelBotones.add(jb6);
        jb6.addActionListener(this);

        jbMultiplica.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbMultiplica.setText("*");
        jbMultiplica.setFocusable(false);
        jbMultiplica.setName("*"); // NOI18N
        panelBotones.add(jbMultiplica);
        jbMultiplica.addActionListener(this);

        jb7.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb7.setText("7");
        jb7.setFocusable(false);
        jb7.setName("7"); // NOI18N
        panelBotones.add(jb7);
        jb7.addActionListener(this);

        jb8.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb8.setText("8");
        jb8.setFocusable(false);
        jb8.setName("8"); // NOI18N
        panelBotones.add(jb8);
        jb8.addActionListener(this);

        jb9.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb9.setText("9");
        jb9.setFocusable(false);
        jb9.setName("9"); // NOI18N
        panelBotones.add(jb9);
        jb9.addActionListener(this);

        jbDivide.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbDivide.setText("/");
        jbDivide.setFocusable(false);
        jbDivide.setName("/"); // NOI18N
        panelBotones.add(jbDivide);
        jbDivide.addActionListener(this);

        jbSigno.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbSigno.setText("+/-");
        jbSigno.setFocusable(false);
        jbSigno.setName("(-"); // NOI18N
        panelBotones.add(jbSigno);
        jbSigno.addActionListener(this);

        jb0.setFont(new java.awt.Font("Tahoma", 0, 18));
        jb0.setText("0");
        jb0.setFocusable(false);
        jb0.setName("0"); // NOI18N
        panelBotones.add(jb0);
        jb0.addActionListener(this);

        jbDot.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbDot.setText(".");
        jbDot.setFocusable(false);
        jbDot.setName("."); // NOI18N
        panelBotones.add(jbDot);
        jbDot.addActionListener(this);

        jbOpera.setBackground(new java.awt.Color(0, 153, 0));
        jbOpera.setFont(new java.awt.Font("Tahoma", 0, 18));
        jbOpera.setForeground(new java.awt.Color(255, 255, 255));
        jbOpera.setText("=");
        jbOpera.setFocusable(false);
        jbOpera.setPreferredSize(new java.awt.Dimension(45, 62));
        jbOpera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOperaActionPerformed(evt);
            }
        });
        panelBotones.add(jbOpera);

        getContentPane().add(panelBotones, java.awt.BorderLayout.SOUTH);

        mnuArchivo.setText("Archivo");

        mnuISalir.setText("Salir");
        mnuISalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuISalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuISalir);

        barMenu.add(mnuArchivo);

        mnuAyuda.setText("Ayuda");

        mnuIAcercaDe.setText("Acerca de ...");
        mnuIAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIAcercaDeActionPerformed(evt);
            }
        });
        mnuAyuda.add(mnuIAcercaDe);

        barMenu.add(mnuAyuda);

        setJMenuBar(barMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuISalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuISalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_mnuISalirActionPerformed

    private void jbOperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOperaActionPerformed
        Operar();
    }//GEN-LAST:event_jbOperaActionPerformed

    private void txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyTyped
        if(evt.getKeyChar() == '\n')
            Operar();
    }//GEN-LAST:event_txtKeyTyped

    private void JBAnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAnsActionPerformed
        txt.setText(txt.getText() + resp);
    }//GEN-LAST:event_JBAnsActionPerformed

    private void JButtonCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonCActionPerformed
        txt.setText("");
    }//GEN-LAST:event_JButtonCActionPerformed

    private void mnuIAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIAcercaDeActionPerformed
        frmAcercaDe frm = new frmAcercaDe(this,true);
        frm.setLocationRelativeTo(this);
        frm.setVisible(true);
    }//GEN-LAST:event_mnuIAcercaDeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAns;
    private javax.swing.JButton JBPotencia;
    private javax.swing.JButton JButtonC;
    private javax.swing.JMenuBar barMenu;
    private javax.swing.JButton jb0;
    private javax.swing.JButton jb1;
    private javax.swing.JButton jb2;
    private javax.swing.JButton jb3;
    private javax.swing.JButton jb4;
    private javax.swing.JButton jb5;
    private javax.swing.JButton jb6;
    private javax.swing.JButton jb7;
    private javax.swing.JButton jb8;
    private javax.swing.JButton jb9;
    private javax.swing.JButton jbDivide;
    private javax.swing.JButton jbDot;
    private javax.swing.JButton jbFactorial;
    private javax.swing.JButton jbMultiplica;
    private javax.swing.JButton jbOpera;
    private javax.swing.JButton jbParentesis1;
    private javax.swing.JButton jbParentesis2;
    private javax.swing.JButton jbRaiz;
    private javax.swing.JButton jbResta;
    private javax.swing.JButton jbSigno;
    private javax.swing.JButton jbSuma;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenuItem mnuIAcercaDe;
    private javax.swing.JMenuItem mnuISalir;
    private javax.swing.JPanel paneText;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JTextField txt;
    private javax.swing.JTextField txtR;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        botonClic((JButton)(e.getSource()));
    }
}public static class frmAcercaDe extends javax.swing.JDialog {


    public frmAcercaDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de JPedro Responde");
        getContentPane().setLayout(new java.awt.GridLayout(6, 0));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Nombre");
        getContentPane().add(lblTitulo);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel2.setText("  Calcudora");
        getContentPane().add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("  VersiÃ³n: 0.1 (14 de febrero de 2010)");
        getContentPane().add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setText("  Desarrollador: Edmond Duke");
        getContentPane().add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("  Comunicar errores: edmond.duke.developer@gmail.com");
        getContentPane().add(jLabel5);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton1.setText("Salir");
        jButton1.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-401)/2, (screenSize.height-191)/2, 401, 191);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

}
/**
 * @author Edmond
 * Comentarios a edmond.duke.developer@gmail.com
 * http://softcx.blogspot.com
 * Basado en el algoritmo shunting yard
 */
public static class MakePostFijo2 {
    private Vector<String> rpn;
    private String[] cadena;


    public MakePostFijo2(String[] cadenaInfija){
        cadena = cadenaInfija;
        rpn = new Vector();
        creaPostFijo();
    }
    private void creaPostFijo(){ //La cadena infija debe ser correcta
        myStack2 pila = new myStack2(cadena.length);
        int i,sim;
        i = 0;
        while(i < cadena.length){
            String s= cadena[i];
            sim = getPriori(s);
            if(sim == 0){ //Es nÃºmero
                rpn.add(s);
            }else{ //Es operador
                while(pila.isEmpty() == false && Precedencia(pila.StackTop() ,s) && sim > 0) // Falta âˆš y ^ de derecha a izquierda
                    rpn.add(pila.pop());
                if(sim == -2){ //Si encuentra signo agrupacion que cierra
                    while(getPriori(pila.StackTop()) != -1) //Hasta que encuentre signo agrupaciÃ³n que abre
                        rpn.add(pila.pop());
                    pila.pop(); // quita "(" de la pila
                }else
                    pila.push(s);
            }
            i++;
        }
        while(pila.isEmpty() == false)
            rpn.add(pila.pop());
    }
    public String get(int index){
        return rpn.get(index);
    }
    public int Size(){
        return rpn.size();
    }
    public boolean Precedencia(String strPila,String str){ //Corregir al trabajar con ^
        int p1 = getPriori(strPila);                          // es asociativo por la derecha
        int p2 = getPriori(str);
        return p1 >= p2;  
    }
    public  int getPriori(String str){
        int i=0;
        if(str.equals("-"))
            i = 1;
        if(str.equals("+"))
            i = 1;
        if(str.equals("*"))
            i = 2;
        if(str.equals("/"))
            i = 2;
        if(str.equals("^"))
            i = 3;
        if(str.equals("&"))
            i = 3;
        if(str.equals("!"))
            i = 4;
        if(str.equals("("))
            i = -1;
        if(str.equals("["))
            i = -1;
        if(str.equals("{"))
            i = -1;
        if(str.equals(")"))
            i = -2;
        if(str.equals("]"))
            i = -2;
        if(str.equals("}"))
            i = -2;
        return i;
    }
    public float getValor(){
        myStack2 pila = new myStack2(cadena.length);
        String s,valor;
        float op1,op2;
        int i,simbol;
        //simbol = 0;
        for(i = 0; i < rpn.size(); i++){
            s = rpn.get(i);
            simbol = getPriori(s);
            if(getPriori(s) == 0){ //es nÃºmero
                pila.push(s);
            }else{
                //try{
                if(simbol == 4){  //Es factorial{
                    op1 = Float.valueOf(pila.pop());
                    //if(getDecimal(op1) == 0){
                        valor = String.valueOf(factorial((int)op1));
                        pila.push(valor);
                    //else
                        //error++;
                }else{
                    op2 = Float.valueOf(pila.pop());
                    op1 = Float.valueOf(pila.pop());
                    valor = operar(s,op1,op2);
                    pila.push(valor);
                }

            }
        }
        return Float.valueOf(pila.pop());
    }
    public  String operar(String str, float op1, float op2){
        float valor = 0;
        if(str.equals("+"))
            valor = op1 + op2;
        if(str.equals("-"))
            valor = op1 - op2;
        if(str.equals("*"))
            valor = op1*op2;
        if(str.equals("/")){
            //if(op2 != 0)
                valor = op1 / op2;
            //else
                //xerror++;
        }
        if(str.equals("^"))
            valor = (float) Math.pow(op1,op2);
        if(str.equals("&"))
            valor = (float) Math.pow(op2, 1/op1);
        return String.valueOf(valor);
    }

    public  float factorial(int num){
          float x = 1;
          int i;
          for (i = 1; i < num; i++)
            x = x * (i+1);
          return x;
    }
}
public static class myStack2 {
    private String[] pila;
    private int tope;

    public myStack2(int capacidad){
        pila = new String[capacidad];
        tope =  -1;
    }
    public boolean isEmpty(){
        return tope == -1;
    }
    public void push(String str){
        if(tope + 1 < pila.length)
            pila[++tope] = str;
    }
    public String pop(){
        if(isEmpty())
                return "";
        return pila[tope--];
    }
    public String StackTop(){
        return pila[tope];
    }
}

}
