package practica3;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Calculadora extends javax.swing.JFrame {

    private int MAX_LENGTH_DISPLAY = 22;
    
    String actual = "";
    String anterior = "0";
    String operacio = "";
    boolean borrarPantalla = false;

    public Calculadora() {
        setSize(268,293);
        setTitle("Calculadora");
        centrarFinestra(this);
        initComponents();
        setPantalla("0");
    }

    private void centrarFinestra(Calculadora aThis)
    {
        //Tomo el tamaÃ±o de la pantalla
        Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
        //El ancho de la pantalla lo divido en 2 y le resto la mitad del ancho de mi ventana, con eso queda centrada en el eje X, para el eje Y es lo mismo pero con el alto:
        aThis.setLocation((pantallaTamano.width/2)-(aThis.getWidth()/2), (pantallaTamano.height/2)-(aThis.getHeight()/2));
    }

    private void setPantalla(String text)
    {
        txtDisplay.setText(text);
    }
    private String getPantalla()
    {
        return txtDisplay.getText();
    }
    private void borrarTot()
    {
        setPantalla("0");
        anterior = "0";
        actual = "";
        operacio = "";
        borrarPantalla = false;
    }
    private void borrarActual()
    {
        double aux = Double.parseDouble(getPantalla());
        if(aux != 0)
        {
            String aux2 = Double.toString(aux);
            int borrar = aux2.length()-1;
            int trobat = aux2.indexOf(".");
            if(trobat != -1)
            {
                String sub = aux2.substring(trobat+1, trobat+2);
                if ((sub.compareTo("0") == 0)&&(aux2.length() == trobat+2))
                    borrar = borrar -2;
            }
            anterior = aux2.substring(0, borrar);
            if(anterior.compareTo("") == 0)
                anterior = "0";
            setPantalla(anterior);
            actual = anterior;
            anterior = "0";
            operacio = "";
            borrarPantalla = false;
        }
        operacio = "";
    }
    private void errorByZero()
    {
        setPantalla("Error");
        anterior = "0";
        actual = "";
        operacio = "";
        borrarPantalla = true;
    }

    private void esperarSeguent(String entrat)
    {
        int opcio = 0;
        int operacio_anterior = 0;
        opcio = operacioSeguent(entrat);
        operacio_anterior = operacioSeguent(operacio);
        if((actual.compareTo("") == 0)&&(opcio != -1)&&(operacio_anterior != -1))
        {
            operacio_anterior = opcio;
            operacio = entrat;
            opcio = 0;
            entrat = "";
        }
        if(opcio == -1)
        {
            if(entrat.compareTo("") != 0)
            {
                if((actual.lastIndexOf(".") == -1)||(entrat.compareTo(".") != 0))
                {
                    if(entrat.compareTo("signe") != 0)
                    {
                        if(borrarPantalla)
                        {
                            setPantalla("0");
                        }
                        else
                        {
                            actual = getPantalla();
                        }
                        if(actual.length() < MAX_LENGTH_DISPLAY)
                        {
                            if((actual.indexOf("0") == 0)&&(entrat.compareTo(".") != 0)&&(actual.lastIndexOf(".") == -1))
                            {
                                actual = actual.substring(1);
                            }
                            actual += entrat;
                        }
                        setPantalla(actual);
                    }
                }
            }
        }
        else
        {
            if((operacio.compareTo("") == 0)&&(entrat.compareTo("=") != 0))
            {
                operacio = entrat;
                borrarPantalla = true;
                anterior = getPantalla();
                actual = "";
            }
            else
            {
                double aux = 0, aux2 = 0;
                switch (operacio_anterior)
                {
                    case 1:
                        aux2 = Double.parseDouble(actual);
                        if(entrat.compareTo("%") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            aux2 = (Double.parseDouble(anterior) * aux2) /100;
                        }
                        else if(entrat.compareTo("1/x") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            if(aux2 != 0)
                                aux2 = 1/aux2;
                        }
                        aux = Double.parseDouble(anterior)+aux2;
                        anterior = Double.toString(aux);
                        actual = "";
                        borrarPantalla = true;
                        if((entrat.compareTo("=") == 0)||(entrat.compareTo("%") == 0)||(entrat.compareTo("1/x") == 0))
                            operacio = "";
                        else
                            operacio = entrat;
                        setPantalla(anterior);
                        break;
                    case 2:
                        aux2 = Double.parseDouble(actual);
                        if(entrat.compareTo("%") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            aux2 = (Double.parseDouble(anterior) * aux2) /100;
                        }
                        else if(entrat.compareTo("1/x") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            if(aux2 != 0)
                                aux2 = 1/aux2;
                        }
                        aux = Double.parseDouble(anterior)-aux2;
                        anterior = Double.toString(aux);
                        actual = "";
                        borrarPantalla = true;
                        if((entrat.compareTo("=") == 0)||(entrat.compareTo("%") == 0)||(entrat.compareTo("1/x") == 0))
                            operacio = "";
                        else
                            operacio = entrat;
                        setPantalla(anterior);
                        break;
                    case 3:
                        aux2 = Double.parseDouble(actual);
                        if(entrat.compareTo("%") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            aux2 = (Double.parseDouble(anterior) * aux2) /100;
                        }
                        else if(entrat.compareTo("1/x") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            if(aux2 != 0)
                                aux2 = 1/aux2;
                        }
                        aux = Double.parseDouble(anterior)*aux2;
                        anterior = Double.toString(aux);
                        actual = "";
                        borrarPantalla = true;
                        if((entrat.compareTo("=") == 0)||(entrat.compareTo("%") == 0)||(entrat.compareTo("1/x") == 0))
                            operacio = "";
                        else
                            operacio = entrat;
                        setPantalla(anterior);
                        break;
                    case 4:
                        aux2 = Double.parseDouble(actual);
                        if(entrat.compareTo("%") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            aux2 = (Double.parseDouble(anterior) * aux2) /100;
                        }
                        else if(entrat.compareTo("1/x") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            if(aux2 != 0)
                                aux2 = 1/aux2;
                        }
                        double aux_actual = aux2;
                        if(aux_actual != 0)
                        {
                            aux = Double.parseDouble(anterior)/aux_actual;
                            anterior = Double.toString(aux);
                            actual = "";
                            borrarPantalla = true;
                            if((entrat.compareTo("=") == 0)||(entrat.compareTo("%") == 0)||(entrat.compareTo("1/x") == 0))
                                operacio = "";
                            else
                                operacio = entrat;
                            setPantalla(anterior);
                        }
                        else
                        {
                            errorByZero();
                        }
                        break;
                    case 5:
                        if(actual.compareTo("") == 0)
                            actual = anterior;
                        aux2 = Double.parseDouble(actual);
                        if(entrat.compareTo("%") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            aux2 = (Double.parseDouble(anterior) * aux2) /100;
                        }
                        else if(entrat.compareTo("1/x") == 0)
                        {
                            operacio = "";
                            actual = "";
                            borrarPantalla = true;
                            if(aux2 != 0)
                                aux2 = 1/aux2;
                        }
                        aux = Math.sqrt(aux2);
                        anterior = Double.toString(aux);
                        actual = "";
                        borrarPantalla = true;
                        operacio = "";
                        setPantalla(anterior);
                        break;
                    case 6:
                        if(actual.compareTo("") == 0)
                            actual = anterior;
                        aux = (Double.parseDouble(actual))/100;
                        anterior = Double.toString(aux);
                        actual = "";
                        borrarPantalla = true;
                        operacio = "";
                        setPantalla(anterior);
                        break;
                    case 7:
                        if(actual.compareTo("") == 0)
                            actual = anterior;
                        aux = Double.parseDouble(actual);
                        if(aux != 0)
                        {
                            anterior = Double.toString(1/aux);
                            actual = "";
                            borrarPantalla = true;
                            operacio = "";
                            setPantalla(anterior);
                        }
                        else
                        {
                            errorByZero();
                        }
                        break;
                    case 8:
                        break;
                    case 9:
                        borrarTot();
                        break;
                    case 10:
                        borrarActual();
                        break;
                }
            }
        }
    }

    private int operacioSeguent(String entrat)
    {
        if(entrat.compareTo("+") == 0)
            return 1;
        else if(entrat.compareTo("-") == 0)
            return 2;
        else if(entrat.compareTo("*") == 0)
            return 3;
        else if(entrat.compareTo("/") == 0)
            return 4;
        else if(entrat.compareTo("sqrt") == 0)
        {
            operacio = "sqrt";
            return 5;
        }
        else if(entrat.compareTo("%") == 0)
        {
            if (operacio.compareTo("") == 0)
                operacio = "%";
            return 6;
        }
        else if(entrat.compareTo("1/x") == 0)
        {
            if (operacio.compareTo("") == 0)
                operacio = "1/x";
            return 7;
        }
        else if(entrat.compareTo("=") == 0)
            return 8;
        else if(entrat.compareTo("CE") == 0)
        {
            operacio = "CE";
            return 9;
        }
        else if(entrat.compareTo("BACK") == 0)
        {
            operacio = "BACK";
            return 10;
        }
        else if(entrat.compareTo("signe") == 0)
        {
            double aux = 0;
            if(actual.compareTo("") == 0)
                actual = anterior;
            if(actual.compareTo("") != 0)
                aux = Double.parseDouble(actual);
            if(aux != 0)
            {
                aux = -aux;
                actual = Double.toString(aux);
                setPantalla(actual);
            }
            return -1;
        }
        else
            return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDisplay = new javax.swing.JTextField();
        jbnBorrar = new javax.swing.JButton();
        jbnBack = new javax.swing.JButton();
        jbnArrel = new javax.swing.JButton();
        jbnDividir = new javax.swing.JButton();
        jbnPercent = new javax.swing.JButton();
        jbnInvertir = new javax.swing.JButton();
        jbnIgualar = new javax.swing.JButton();
        jbnMultiplicar = new javax.swing.JButton();
        jbnRestar = new javax.swing.JButton();
        jbnSumar = new javax.swing.JButton();
        jbnNum9 = new javax.swing.JButton();
        jbnNum8 = new javax.swing.JButton();
        jbnNum7 = new javax.swing.JButton();
        jbnNum6 = new javax.swing.JButton();
        jbnNum5 = new javax.swing.JButton();
        jbnNum3 = new javax.swing.JButton();
        jbnNum2 = new javax.swing.JButton();
        jbnNum4 = new javax.swing.JButton();
        jbnNum1 = new javax.swing.JButton();
        jbnPunt = new javax.swing.JButton();
        jbnSigne = new javax.swing.JButton();
        jbnNum0 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setName("calc_central"); // NOI18N
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        txtDisplay.setBackground(new java.awt.Color(255, 255, 255));
        txtDisplay.setEditable(false);
        txtDisplay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplay.setText("0");
        txtDisplay.setName("txtPrincipal"); // NOI18N
        txtDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisplayActionPerformed(evt);
            }
        });
        txtDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDisplayKeyPressed(evt);
            }
        });

        jbnBorrar.setText("CE");
        jbnBorrar.setFocusable(false);
        jbnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnBorrarActionPerformed(evt);
            }
        });

        jbnBack.setText("Back");
        jbnBack.setDefaultCapable(false);
        jbnBack.setFocusable(false);
        jbnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnBackActionPerformed(evt);
            }
        });

        jbnArrel.setText("sqrt");
        jbnArrel.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnArrel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnArrelActionPerformed(evt);
            }
        });

        jbnDividir.setText("/");
        jbnDividir.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnDividirActionPerformed(evt);
            }
        });

        jbnPercent.setText("%");
        jbnPercent.setFocusable(false);
        jbnPercent.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnPercentActionPerformed(evt);
            }
        });

        jbnInvertir.setText("1/x");
        jbnInvertir.setFocusable(false);
        jbnInvertir.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnInvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnInvertirActionPerformed(evt);
            }
        });

        jbnIgualar.setText("=");
        jbnIgualar.setFocusable(false);
        jbnIgualar.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnIgualar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnIgualarActionPerformed(evt);
            }
        });

        jbnMultiplicar.setText("*");
        jbnMultiplicar.setFocusable(false);
        jbnMultiplicar.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnMultiplicarActionPerformed(evt);
            }
        });

        jbnRestar.setText("-");
        jbnRestar.setFocusable(false);
        jbnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnRestarActionPerformed(evt);
            }
        });

        jbnSumar.setText("+");
        jbnSumar.setFocusable(false);
        jbnSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnSumarActionPerformed(evt);
            }
        });

        jbnNum9.setText("9");
        jbnNum9.setFocusable(false);
        jbnNum9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum9ActionPerformed(evt);
            }
        });

        jbnNum8.setText("8");
        jbnNum8.setFocusable(false);
        jbnNum8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum8ActionPerformed(evt);
            }
        });

        jbnNum7.setText("7");
        jbnNum7.setFocusable(false);
        jbnNum7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum7ActionPerformed(evt);
            }
        });
        jbnNum7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jbnNum7KeyPressed(evt);
            }
        });

        jbnNum6.setText("6");
        jbnNum6.setFocusable(false);
        jbnNum6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum6ActionPerformed(evt);
            }
        });

        jbnNum5.setText("5");
        jbnNum5.setFocusable(false);
        jbnNum5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum5ActionPerformed(evt);
            }
        });

        jbnNum3.setText("3");
        jbnNum3.setFocusable(false);
        jbnNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum3ActionPerformed(evt);
            }
        });

        jbnNum2.setText("2");
        jbnNum2.setFocusable(false);
        jbnNum2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum2ActionPerformed(evt);
            }
        });

        jbnNum4.setText("4");
        jbnNum4.setFocusable(false);
        jbnNum4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum4ActionPerformed(evt);
            }
        });

        jbnNum1.setText("1");
        jbnNum1.setFocusable(false);
        jbnNum1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum1ActionPerformed(evt);
            }
        });

        jbnPunt.setText(".");
        jbnPunt.setFocusable(false);
        jbnPunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnPuntActionPerformed(evt);
            }
        });

        jbnSigne.setText("+/-");
        jbnSigne.setFocusable(false);
        jbnSigne.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbnSigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnSigneActionPerformed(evt);
            }
        });

        jbnNum0.setText("0");
        jbnNum0.setFocusable(false);
        jbnNum0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnNum0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbnNum0, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jbnNum1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jbnNum4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jbnNum7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbnNum5, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                    .addComponent(jbnNum8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbnNum2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                    .addComponent(jbnSigne, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbnNum6, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jbnNum9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(jbnNum3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jbnPunt, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbnDividir, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(jbnSumar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(jbnRestar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(jbnMultiplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbnIgualar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbnInvertir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbnPercent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbnArrel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbnArrel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbnDividir, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jbnNum9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbnNum7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbnNum8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbnNum4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jbnNum5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jbnNum6, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jbnMultiplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jbnPercent, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbnRestar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                .addComponent(jbnInvertir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbnNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jbnNum1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbnNum2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addGap(11, 11, 11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbnNum0, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jbnSigne, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jbnPunt, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jbnSumar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jbnIgualar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jbnNum0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum0ActionPerformed
        esperarSeguent("0");
    }//GEN-LAST:event_jbnNum0ActionPerformed
    private void txtDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisplayActionPerformed
    private void jbnNum1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum1ActionPerformed
        esperarSeguent("1");
    }//GEN-LAST:event_jbnNum1ActionPerformed
    private void jbnNum2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum2ActionPerformed
        esperarSeguent("2");
    }//GEN-LAST:event_jbnNum2ActionPerformed
    private void jbnNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum3ActionPerformed
        esperarSeguent("3");
    }//GEN-LAST:event_jbnNum3ActionPerformed
    private void jbnNum4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum4ActionPerformed
        esperarSeguent("4");
    }//GEN-LAST:event_jbnNum4ActionPerformed
    private void jbnNum5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum5ActionPerformed
        esperarSeguent("5");
    }//GEN-LAST:event_jbnNum5ActionPerformed
    private void jbnNum6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum6ActionPerformed
        esperarSeguent("6");
    }//GEN-LAST:event_jbnNum6ActionPerformed
    private void jbnNum7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum7ActionPerformed
        esperarSeguent("7");
    }//GEN-LAST:event_jbnNum7ActionPerformed
    private void jbnNum8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum8ActionPerformed
        esperarSeguent("8");
    }//GEN-LAST:event_jbnNum8ActionPerformed
    private void jbnNum9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnNum9ActionPerformed
        esperarSeguent("9");
    }//GEN-LAST:event_jbnNum9ActionPerformed
    private void jbnPuntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnPuntActionPerformed
        esperarSeguent(".");
    }//GEN-LAST:event_jbnPuntActionPerformed
    private void jbnSigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnSigneActionPerformed
        esperarSeguent("signe");
    }//GEN-LAST:event_jbnSigneActionPerformed
    private void jbnSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnSumarActionPerformed
        esperarSeguent("+");
    }//GEN-LAST:event_jbnSumarActionPerformed
    private void jbnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnRestarActionPerformed
        esperarSeguent("-");
    }//GEN-LAST:event_jbnRestarActionPerformed
    private void jbnMultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnMultiplicarActionPerformed
        esperarSeguent("*");
    }//GEN-LAST:event_jbnMultiplicarActionPerformed
    private void jbnDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnDividirActionPerformed
        esperarSeguent("/");
    }//GEN-LAST:event_jbnDividirActionPerformed
    private void jbnArrelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnArrelActionPerformed
        esperarSeguent("sqrt");
    }//GEN-LAST:event_jbnArrelActionPerformed
    private void jbnPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnPercentActionPerformed
        esperarSeguent("%");
    }//GEN-LAST:event_jbnPercentActionPerformed
    private void jbnInvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnInvertirActionPerformed
        esperarSeguent("1/x");
    }//GEN-LAST:event_jbnInvertirActionPerformed
    private void jbnIgualarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnIgualarActionPerformed
        esperarSeguent("=");
    }//GEN-LAST:event_jbnIgualarActionPerformed
    private void jbnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnBackActionPerformed
        esperarSeguent("BACK");
    }//GEN-LAST:event_jbnBackActionPerformed
    private void jbnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnBorrarActionPerformed
        esperarSeguent("CE");
    }//GEN-LAST:event_jbnBorrarActionPerformed

    private void jbnNum7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jbnNum7KeyPressed

    }//GEN-LAST:event_jbnNum7KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed

    private void txtDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDisplayKeyPressed
            try {
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_0:
                    esperarSeguent("0");
                    break;
                case KeyEvent.VK_1:
                    esperarSeguent("1");
                    break;
                case KeyEvent.VK_2:
                    esperarSeguent("2");
                    break;
                case KeyEvent.VK_3:
                    esperarSeguent("3");
                    break;
                case KeyEvent.VK_4:
                    esperarSeguent("4");
                    break;
                case KeyEvent.VK_5:
                    esperarSeguent("5");
                    break;
                case KeyEvent.VK_6:
                    esperarSeguent("6");
                    break;
                case KeyEvent.VK_7:
                    esperarSeguent("7");
                    break;
                case KeyEvent.VK_8:
                    esperarSeguent("8");
                    break;
                case KeyEvent.VK_9:
                    esperarSeguent("9");
                    break;
                case KeyEvent.VK_ENTER:
                    esperarSeguent("=");
                    break;
                case KeyEvent.VK_MINUS:
                    esperarSeguent("-");
                    break;
                case KeyEvent.VK_ASTERISK:
                    esperarSeguent("*");
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    esperarSeguent("BACK");
                    break;
                case KeyEvent.VK_DELETE:
                    esperarSeguent("CE");
                    break;
                case KeyEvent.VK_PLUS:
                    esperarSeguent("+");
                    break;
                case KeyEvent.VK_PERIOD:
                    esperarSeguent(".");
                    break;
                case KeyEvent.VK_DIVIDE:
                    esperarSeguent("/");
                    break;
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDisplayKeyPressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbnArrel;
    private javax.swing.JButton jbnBack;
    private javax.swing.JButton jbnBorrar;
    private javax.swing.JButton jbnDividir;
    private javax.swing.JButton jbnIgualar;
    private javax.swing.JButton jbnInvertir;
    private javax.swing.JButton jbnMultiplicar;
    private javax.swing.JButton jbnNum0;
    private javax.swing.JButton jbnNum1;
    private javax.swing.JButton jbnNum2;
    private javax.swing.JButton jbnNum3;
    private javax.swing.JButton jbnNum4;
    private javax.swing.JButton jbnNum5;
    private javax.swing.JButton jbnNum6;
    private javax.swing.JButton jbnNum7;
    private javax.swing.JButton jbnNum8;
    private javax.swing.JButton jbnNum9;
    private javax.swing.JButton jbnPercent;
    private javax.swing.JButton jbnPunt;
    private javax.swing.JButton jbnRestar;
    private javax.swing.JButton jbnSigne;
    private javax.swing.JButton jbnSumar;
    private javax.swing.JTextField txtDisplay;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent e) {
        esperarSeguent("7");
    }

    public void keyTyped(KeyEvent e) {
        esperarSeguent("7");
    }

    public void keyPressed(KeyEvent e) {
        esperarSeguent("7");
    }

    public void keyReleased(KeyEvent e) {
        esperarSeguent("7");
    }

}