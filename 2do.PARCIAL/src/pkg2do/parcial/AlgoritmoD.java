/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2do.parcial;


import java.awt.*;
//-------------------------------------------------------------------------/
//  AlgoritmoD.java
//  
//  Applet para ejecutar el algoritmo de Dijkstra en un grafo dirigido
// 	y encontrar el camino mas corto a todos los nodos.
//-------------------------------------------------------------------------/

public class AlgoritmoD extends java.applet.Applet {
    GraphCanvas grafocanvas = new GraphCanvas(this);
    Options options = new Options(this);   
    Documentacion documentacion = new Documentacion();
    public void init() {
	setLayout(new BorderLayout(10, 10));
	add("Center", grafocanvas);
	add("North", documentacion);
	add("East", options);
    }
    public Insets insets() {
	return new Insets(10, 10, 10, 10);
    }
    public void lock() {
	grafocanvas.lock();
	options.lock();
    } 
    public void unlock() {
	grafocanvas.unlock();
	options.unlock();
    } 
}

class Options extends Panel {
// opciones a un lado de la pantalla
    Button b1 = new Button("limpiar");
    Button b2 = new Button("ejecutar");
    Button b3 = new Button("paso");
    Button b4 = new Button("inicializar");
    Button b5 = new Button("ejemplo");
    Button b6 = new Button("salir");
    AlgoritmoD parent;   
    boolean Locked=false;
  
    Options(AlgoritmoD myparent) {
	parent = myparent;
	setLayout(new GridLayout(6, 1, 0, 10));
	add(b1);
	add(b2);
	add(b3);
	add(b4);
	add(b5); 
	add(b6);
    }
    public boolean action(Event evt, Object arg) {
	if (evt.target instanceof Button) {
	  if (((String)arg).equals("paso")) {
	     if (!Locked) {
	        b3.setLabel("siguiente paso");
	        parent.grafocanvas.stepalg();
	     }
	     else parent.documentacion.doctext.showline("cerrado");
	  }
	  if (((String)arg).equals("siguiente paso")) 
	     parent.grafocanvas.nextstep();
	  if (((String)arg).equals("inicializar")) { 
	     parent.grafocanvas.inicializar();
	     b3.setLabel("paso");
	     parent.documentacion.doctext.showline("referencia");
	  }
	  if (((String)arg).equals("limpiar")) { 
	     parent.grafocanvas.limpiar();
	     b3.setLabel("paso");
	     parent.documentacion.doctext.showline("referencia");
	  }
	  if (((String)arg).equals("ejecutar")) {
	     if (!Locked)  
	        parent.grafocanvas.runalg();
	     else parent.documentacion.doctext.showline("cerrado");
	  }
	  if (((String)arg).equals("ejemplo")) {
	     if (!Locked)   
	        parent.grafocanvas.showejemplo();
	     else parent.documentacion.doctext.showline("cerrado");
	  } 
	  if (((String)arg).equals("salir")) { 
	     System.exit(0);
	  } 
	}                   
	return true; 
    }
    
    public void lock() {
	Locked=true;
    }
    public void unlock() {
	Locked=false;
	b3.setLabel("paso"); 
    } 
}    

class Documentacion extends Panel {
// Documentacion arriba de la pantalla
    DocOptions docopt = new DocOptions(this);
    DocText doctext = new DocText();
    Documentacion() {
	setLayout(new BorderLayout(10, 10));
	add("West", docopt);
	add("Center", doctext);
    }
}

class DocOptions extends Panel {
    Choice doc = new Choice();
    Documentacion parent;   
   
    DocOptions(Documentacion myparent) {
	setLayout(new GridLayout(2, 1, 5, 0));
	parent = myparent;
	add(new Label("DOCUMENTACION:"));
	doc.addItem("dibujar nodos");
	doc.addItem("remover nodos"); 
	doc.addItem("mover nodos");
	doc.addItem("el nodo_inicial");
	doc.addItem("dibujar aristas"); 
	doc.addItem("cambiar pesos");
	doc.addItem("remover aristas");
	doc.addItem("limpiar / inicializar"); 
	doc.addItem("ejecutar algoritmo");
	doc.addItem("pasar");
	doc.addItem("ejemplo"); 
	doc.addItem("salir"); 
	doc.addItem("referencia");
	add(doc);
    }
  
    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Choice) {
	    String str=new String(doc.getSelectedItem());
	    parent.doctext.showline(str);  
        }             
       	return true;
    }
}

class DocText extends TextArea {
    final String drawnodos = new String("DIBUJAR NODOS:\n"+
	  "Dibuje un nodo haciendo click en el mouse.\n\n");
    final String rmvnodos = new String("REMOVER NODOS:\n"+
	  "Para remover un nodo presione <ctrl> y haga click en el nodo.\n"+
	  "No se puede remover el nodo_inicial.\n"+
	  "Seleccione otro nodo_inicial, asi podra remover el nodo.\n\n");
    final String mvnodos = new String("MOVER NODOS\n"+
	  "Para mover un nodo presione <Shift>, haga click en el nodo,\ny arrastrelo a"+
	  " su nueva posicion.\n\n");
    final String nodo_inicial = new String("NODO INICIAL:\n"+
	  "El nodo_inicial es azul, los otros nodos son grises.\n"+ 
	  "El primer nodo que usted dibuja en la pantalla sera el nodo_inicial.\n"+
	  "Para seleccionar otro nodo_inicial presione <ctrl>, haga click en el nodo_inicial,\n"+
	  "y arrastre el mouse a otro nodo.\n"+
	  "Para borrar el nodo_inicial, primero seleccione otro nodo_inicial, y despues"+
	  "\nremueva el nodo normalmente.\n\n"); 
    final String drawaristas = new String("DIBUJAR ARISTAS:\n"+
	  "Para dibujar una arista haga click al mouse en un nodo,"+
	  "y arrastrelo a otro nodo.\n\n");
    final String peso = new String("CAMBIAR PESOS:\n"+
	  "Para cambiar el peso de una arista, haga click en la flecha y \n"+
	  "arrastrela sobre la arista.\n\n");
    final String rmvaristas = new String("REMOVER ARISTAS:\n"+
	  "Para remover una arista, cambiar su peso a 0.\n\n");
    final String clrreset = new String("BOTON DE<LIMPIAR>: "+
	  "Remover el grafo de la pantalla.\n"+
	  "BOTON DE<INICIALIZAR>: "+
	  "Remover los resultados del algoritmo en el grafo,\n"+
	  " y abrir la pantalla.\n\n");
    final String runalg = new String("BOTON DE <EJECUTAR>: "+
	  "Ejecuta el algoritmo en el grafo, habra un tiempo\n" +
	  "de retraso de +/- 1 segundos entre cada paso.\n"+
	  "Para ejecutar el algoritmo mas lento, use <PASO>.\n");
    final String paso = new String("BOTON DE <PASO>: " +
	  "Recorrer el algoritmo paso a paso.\n");
    final String ejemplo = new String("BOTON DE <EJEMPLO>: "+
	  "Despliega un grafo en la pantalla.\n"+
	  "Usted puede usar <PASO> or <EJECUTAR>\n");
    final String exitbutton = new String("BOTON DE <SALIR>: " +
	  "Solo funciona si el applet es ejecutado en appletviewer.\n");
    final String toclose = new String("ERROR: "+
	  "Esta posicion es para cerrar a otro nodo/arista.\n");
    final String hecho = new String("El lgoritmo ha terminado, " +
	  "siga las aristas naranjas del nodo_inicial a cualquier nodo "+
	  "para obtener\nel mas corto camino al " +
	  "nodo. La longitud del camino se escribe en el nodo.\n" +
          "presione <INICIALIZAR> para inicializar el grafo, y liberar la pantalla.");
    final String alguno = new String("El algoritmo ha terminado, " +
	  "siga las aristas naranjas del nodo_inicial a cualquier nodo "+
	  "para obtener\nel mas corto camino al " +
	  "nodo. La longitud del camino se escribe en el nodo.\n" +
          "No hay caminos del nodo_inicial a ningun otro nodo.\n" +
          "presione <INICIALIZAR> para inicializar el grafo, y liberar la pantalla.");
    final String ninguno = new String("El algoritmo ha terminado, " +
	  "no hay nodos alcanzables desde el nodo inicial.\n"+
	  "presione <INICIALIZAR> para inicializar el grafo, y liberar la pantalla.");
  
    final String maxnodos = new String("ERROR: "+ 
	  "Maximo numero de nodos alcanzado!\n\n");
    final String info = new String("DOCUMENTACION:\n"+
	  "Usted puede ver toda la documentacion u obtener documentacion\n"+
	  "de algo especifico "+
	  "seleccionando el item a la izquierda.\nSeleccionar <Referencia> "+
	  "lo regresa "+
	  " al texto.\n\n"); 
    final String cerrado = new String("ERROR: "+
	  "Teclado/mouse cerrado para esta accion.\n"+
	  "Presione <SIGUIENTE PASO> o <INICIALIZAR>.\n"); 
    final String doc = info + drawnodos + rmvnodos + mvnodos + 
		       nodo_inicial + drawaristas + peso + rmvaristas + 
		       clrreset + runalg + paso + ejemplo + exitbutton;
    DocText() {
	super(5, 2);
	setText(doc);
    }
    
    public void showline(String str) {
	if (str.equals("dibujar nodos"))              setText(drawnodos);
	else if (str.equals("remover nodos"))       setText(rmvnodos);
	else if (str.equals("mover nodos"))         setText(mvnodos);
	else if (str.equals("el nodo_inicial"))      setText(nodo_inicial);
	else if (str.equals("dibujar aristas"))        setText(drawaristas);
	else if (str.equals("cambiar pesos"))     setText(peso);
	else if (str.equals("remover aristas"))      setText(rmvaristas);
	else if (str.equals("limpiar / inicializar"))      setText(clrreset);
	else if (str.equals("ejecutar algoritmo"))      setText(runalg);
	else if (str.equals("pasar"))       setText(paso);
	else if (str.equals("ejemplo"))            setText(ejemplo); 
        else if (str.equals("salir"))               setText(exitbutton);
	else if (str.equals("referencia"))          setText(doc);
	else if (str.equals("toclose"))            setText(toclose); 
	else if (str.equals("hecho"))               setText(hecho);   
	else if (str.equals("cerrado"))             setText(cerrado);
	else if (str.equals("maxnodos"))           setText(maxnodos);       
        else if (str.equals("ninguno"))               setText(ninguno);   
        else if (str.equals("alguno"))               setText(alguno);   
	else setText(str);
    }
}

class GraphCanvas extends Canvas implements Runnable {
// area de dibujo del grafo
    
    final int MAXNODOS = 20;
    final int MAX = MAXNODOS+1;
    final int NODOSIZE = 26;
    final int NODORADIX = 13;
    final int DIJKSTRA = 1;
 
    // informacion basica del grafo
    Point nodo[] = new Point[MAX];          // nodo
    int peso[][] = new int[MAX][MAX];     // peso de arista
    Point arista[][] = new Point[MAX][MAX];  // posicion actual de la flecha
    Point startp[][] = new Point[MAX][MAX]; // punto inicial
    Point endp[][] = new Point[MAX][MAX];   // y final de arista
    float dir_x[][] = new float[MAX][MAX];  // direccion de arista
    float dir_y[][] = new float[MAX][MAX];  // direccion de arista
   
    // informacion del grafo al ejecutar el algoritmo
    boolean algedge[][] = new boolean[MAX][MAX];
    int dist[] = new int[MAX];
    int finaldist[] = new int[MAX];
    Color colornodo[] = new Color[MAX];
    boolean changed[] = new boolean[MAX];   // indica cambio de distancia durante el algoritmo   
    int numchanged =0; 
    int neighbours=0;
    
    int paso=0;
    
    // informacion usada por el algoritmo para encontrar el siguiente 
    // nodo con minima distancia
    int mindist, minnodo, minstart, minend;
    int numnodos=0;      // numero ode nodos
    int emptyspots=0;    // lugares vacios en el arreglo nodo[] (por borrado de nodos)
    int startgrafo=0;    // comienzo de grafo
    int hitnodo;         // click del mouse en o cerca del nodo
    int nodo1, nodo2;    // numero de nodos envueltos in la accion
    Point thispoint=new Point(0,0); // posicion actual del mouse
    Point oldpoint=new Point(0, 0); // posicion previa del nodo
    // accion actual
    boolean newarista = false;
    boolean movearista = false;
    boolean movestart = false;
    boolean deletenodo = false;
    boolean movenodo = false;
    boolean performalg = false;
    boolean clicked = false;
    // fonts
    Font roman= new Font("TimesRoman", Font.BOLD, 12);
    Font helvetica= new Font("Helvetica", Font.BOLD, 15);
    FontMetrics fmetrics = getFontMetrics(roman);
    int h = (int)fmetrics.getHeight()/3;
    // buffer doble
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Dimension offScreenSize;

    // opcion de ejecutar
    Thread algrthm;
    // algoritmo actual, (se pueden aniadir mas)
    int algoritmo;
    // informacion del algoritmo para ser desplegado en la documentacion
    String showstring = new String("");
    boolean stepthrough=false;
    // cerrar la pantalla mientras se ejecuta el algoritmo
    boolean Locked = false;
    AlgoritmoD parent;
    GraphCanvas(AlgoritmoD myparent) {
	parent = myparent;
	init();
	algoritmo=DIJKSTRA;
	setBackground(Color.white);
    }
    public void lock() {
    // cerrar la pantalla mientras se ejecuta el algoritmo
	Locked=true;
    }
    public void unlock() {
	Locked=false;
    }
    public void start() {
	if (algrthm != null) algrthm.resume();
    }
    public void init() {
	for (int i=0;i<MAXNODOS;i++) {
	  colornodo[i]=Color.gray;
	  for (int j=0; j<MAXNODOS;j++)
	      algedge[i][j]=false;
	}
	colornodo[startgrafo]=Color.blue;
	performalg = false;
    }
    public void limpiar() {
    // remueve grafo de la pantalla
	startgrafo=0;
	numnodos=0;
	emptyspots=0;
	init();
	for(int i=0; i<MAXNODOS; i++) {
	  nodo[i]=new Point(0, 0);
	  for (int j=0; j<MAXNODOS;j++)
	      peso[i][j]=0;
	}
	if (algrthm != null) algrthm.stop();
	parent.unlock();
	repaint();
    }
    public void inicializar() {
    // inicializa aun grafo despues de ejecutar un algoritmo
	init();
	if (algrthm != null) algrthm.stop();
	parent.unlock();
	repaint();
    }
    public void runalg() {
    // anima el algoritmo
	parent.lock();
	initalg();
	performalg = true;
	algrthm = new Thread(this);
	algrthm.start();
    }
    public void stepalg() {
    // le permite pasar por el algoritmo
	parent.lock();
	initalg();
	performalg = true;
	nextstep();
    }
    public void initalg() {
	init();
	for(int i=0; i<MAXNODOS; i++) {
	  dist[i]=-1;
	  finaldist[i]=-1;
	  for (int j=0; j<MAXNODOS;j++)
              algedge[i][j]=false;
	}
        dist[startgrafo]=0;
	finaldist[startgrafo]=0;
	paso=0;
    }
    public void nextstep() {
    // calcula un paso en el algoritmo (encuentra un mas corto 
    // camino al siguiente nodo).
        finaldist[minend]=mindist;
	algedge[minstart][minend]=true;
	colornodo[minend]=Color.orange;
        // mas informacion para la documentacion
	paso++;
	repaint();
    }
    public void stop() {
	if (algrthm != null) algrthm.suspend();
    }
    public void run() {
	for(int i=0; i<(numnodos-emptyspots); i++){
	  nextstep();
	  try { algrthm.sleep(2000); }
	  catch (InterruptedException e) {}
	}
	algrthm = null;
    }
    public void showejemplo() {
    // dibuja un grafo en la pantalla
	int w, h;
	limpiar();
	init();
	numnodos=10;
	emptyspots=0;
	for(int i=0; i<MAXNODOS; i++) {
	  nodo[i]=new Point(0, 0);
	  for (int j=0; j<MAXNODOS;j++)
	      peso[i][j]=0;
	}
	w=this.size().width/8;
	h=this.size().height/8;
	nodo[0]=new Point(w, h);     nodo[1]=new Point(3*w, h);   
	nodo[2]=new Point(5*w, h);   nodo[3]=new Point(w, 4*h); 
	nodo[4]=new Point(3*w, 4*h); nodo[5]=new Point(5*w, 4*h);
	nodo[6]=new Point(w, 7*h);   nodo[7]=new Point(3*w, 7*h); 
	nodo[8]=new Point(5*w, 7*h); nodo[9]=new Point(7*w, 4*h);
	peso[0][1]=4;  peso[0][3]=85;
	peso[1][0]=74; peso[1][2]=18; peso[1][4]=12;
	peso[2][5]=74; peso[2][1]=12; peso[2][9]=12;
	peso[3][4]=32; peso[3][6]=38;
	peso[4][3]=66; peso[4][5]=76; peso[4][7]=33;
	peso[5][8]=11; peso[5][9]=21;
	peso[6][7]=10; peso[6][3]=12;
	peso[7][6]=2;  peso[7][8]=72;
	peso[8][5]=31; peso[8][9]=78; peso[8][7]=18;
	peso[9][5]=8;
	for (int i=0;i<numnodos;i++)
	   for (int j=0;j<numnodos;j++)
	      if (peso[i][j]>0)
	         aristaupdate(i, j, peso[i][j]);
	repaint();
    }
    public boolean mouseDown(Event evt, int x, int y) {
	
	if (Locked)
	    parent.documentacion.doctext.showline("cerrado");
	else {
	  clicked = true;
	  if (evt.shiftDown()) {
	  // mover un nodo
	     if (nodohit(x, y, NODOSIZE)) {
	        oldpoint = nodo[hitnodo];
	        nodo1 = hitnodo;
	        movenodo=true;
	     }
	  }
	  else if (evt.controlDown()) {
	  // borrar un nodo
	     if (nodohit(x, y, NODOSIZE)) {
	        nodo1 = hitnodo;
	        if (startgrafo==nodo1) {
	           movestart=true;
	           thispoint = new Point(x,y);
                   colornodo[startgrafo]=Color.gray;
	        }
	        else
	           deletenodo= true;
	     }
	  }
	  else if (aristahit(x, y, 5)) {
	  // cambiar peso de una arista
	     movearista = true;
	     repaint();
	  }
	  else if (nodohit(x, y, NODOSIZE)) {
	  // dibuja una nueva arista
	     if (!newarista) {
	        newarista = true;
	        thispoint = new Point(x, y);
	        nodo1 = hitnodo;
	     }
	   }
	   else if ( !nodohit(x, y, 50) && !aristahit(x, y, 50) )  {
	   // dibuja nuevo nodo
	      if (emptyspots==0) {
	      // toma el siguiente punto disponible en el arreglo
	         if (numnodos < MAXNODOS)
	            nodo[numnodos++]=new Point(x, y);
	         else  parent.documentacion.doctext.showline("maxnodos");
	      }
	      else {
	      // tomar un punto vacio en el array (de algun nodo borrado previamente)
	         int i;
	         for (i=0;i<numnodos;i++)
	            if (nodo[i].x==-100) break;
	         nodo[i]=new Point(x, y);
	         emptyspots--;
	      }
	   }
	   else
	   // mouseclick para cerrar a un point r flecha, probablemente un error
	      parent.documentacion.doctext.showline("toclose");
	   repaint();
	}
	return true;
    }
    public boolean mouseDrag(Event evt, int x, int y) {
	if ( (!Locked) && clicked ) {
	   if (movenodo) {
	   // mover nodo y ajustar aristas entrando/saliendo del nodo
	      nodo[nodo1]=new Point(x, y);
	      for (int i=0;i<numnodos;i++) {
	         if (peso[i][nodo1]>0) {
	            aristaupdate(i, nodo1, peso[i][nodo1]);
	         }
	         if (peso[nodo1][i]>0) {
	            aristaupdate(nodo1, i, peso[nodo1][i]);
	         }
	      }
	      repaint();
	   }
	   else if (movestart || newarista) {
	      thispoint = new Point(x, y);
	      repaint();
	   }
	   else if (movearista) {
	      changepeso(x, y);
	      repaint();
	   }
	}
	return true;
    }

    public boolean mouseUp(Event evt, int x, int y) {
	if ( (!Locked) && clicked ) {
	   if (movenodo) {
	   // mover el nodo si la nueva posicion no esta muy cerca a 
	   // otro nodo o fuera del panel
	      nodo[nodo1]=new Point(0, 0);
	      if ( nodohit(x, y, 50) || (x<0) || (x>this.size().width) || 
					  (y<0) || (y>this.size().height) ) {
	         nodo[nodo1]=oldpoint;
	         parent.documentacion.doctext.showline("toclose");
	      }
	      else nodo[nodo1]=new Point(x, y);
	      for (int i=0;i<numnodos;i++) {
	         if (peso[i][nodo1]>0)
	            aristaupdate(i, nodo1, peso[i][nodo1]);
	         if (peso[nodo1][i]>0) 
	            aristaupdate(nodo1, i, peso[nodo1][i]);
	      }
	      movenodo=false;
	   }
	   else if (deletenodo) {
	      nododelete();
	      deletenodo=false;
	   }
	   else if (newarista) {
	      newarista = false;
	      if (nodohit(x, y, NODOSIZE)) {
	         nodo2=hitnodo;
	         if (nodo1!=nodo2) {
	            aristaupdate(nodo1, nodo2, 50);
	            if (peso[nodo2][nodo1]>0) {
	               aristaupdate(nodo2, nodo1, peso[nodo2][nodo1]);
	            }
	            parent.documentacion.doctext.showline("cambiar pesos");
	         }
	         else System.out.println("zelfde");
	      }
	   }
	   else if (movearista) {
	      movearista = false;
	      if (peso[nodo1][nodo2]>0)
	         changepeso(x, y);
	   }
	   else if (movestart) {
	   // si la nueva posicion es un nodo, este nodo es el nodo_inicial
	      if (nodohit(x, y, NODOSIZE))
	         startgrafo=hitnodo;
	      colornodo[startgrafo]=Color.blue;
	      movestart=false;
	   }
	   repaint();
	}
	return true;
    }
    public boolean nodohit(int x, int y, int dist) {
    // checa si hay click sobre un nodo
	for (int i=0; i<numnodos; i++)
	  if ( (x-nodo[i].x)*(x-nodo[i].x) + 
				(y-nodo[i].y)*(y-nodo[i].y) < dist*dist ) {
	     hitnodo = i;
	     return true;
	  }
	return false;
    }
    public boolean aristahit(int x, int y, int dist) {
    // checa si hay click sobre una arista
	for (int i=0; i<numnodos; i++)
	  for (int j=0; j<numnodos; j++) {
	     if ( ( peso[i][j]>0 ) && 
			(Math.pow(x-arista[i][j].x, 2) + 
			 Math.pow(y-arista[i][j].y, 2) < Math.pow(dist, 2) ) ) {
	        nodo1 = i;
	        nodo2 = j;
	        return true;
	     }
	  }
	return false;
    }
    public void nododelete() {
    // borra un nodo y las aristas que entran/salen del nodo
	nodo[nodo1]=new Point(-100, -100);
	for (int j=0;j<numnodos;j++) {
	   peso[nodo1][j]=0;
	   peso[j][nodo1]=0;
	}
	emptyspots++;
    }
    public void changepeso(int x, int y) {
    // cambia el peso de una arista, cuando el usuario arrastra 
    // la flecha sobre la arista que conecta el nodo1 al nodo2.
	// direccion de la arista
	int diff_x = (int)(20*dir_x[nodo1][nodo2]);
	int diff_y = (int)(20*dir_y[nodo1][nodo2]);
	// dependiendo de la direccion de la arista , sigue el x, o el y
	// del mouse mientras la flecha se arrastra
	boolean siga_x=false;
	   if (Math.abs(endp[nodo1][nodo2].x-startp[nodo1][nodo2].x) >
		     Math.abs(endp[nodo1][nodo2].y-startp[nodo1][nodo2].y) ) {
	   siga_x = true;
	}
	// encuentra la nueva posicion de la flecha, y calcula 
	// el peso correspondiente
	if (siga_x) {
	    int hbound = Math.max(startp[nodo1][nodo2].x, 
				  endp[nodo1][nodo2].x)-Math.abs(diff_x);
	    int lbound = Math.min(startp[nodo1][nodo2].x, 
				  endp[nodo1][nodo2].x)+Math.abs(diff_x);
	    // la arista debe quedarse entre los nodos
	    if (x<lbound) { arista[nodo1][nodo2].x=lbound; }
	    else if (x>hbound) { arista[nodo1][nodo2].x=hbound; }
	    else arista[nodo1][nodo2].x=x;
	    arista[nodo1][nodo2].y=
		(arista[nodo1][nodo2].x-startp[nodo1][nodo2].x) *
		    (endp[nodo1][nodo2].y-startp[nodo1][nodo2].y)/
		        (endp[nodo1][nodo2].x-startp[nodo1][nodo2].x) + 
		startp[nodo1][nodo2].y;
	    // nuevo peso
	    peso[nodo1][nodo2]=
		Math.abs(arista[nodo1][nodo2].x-startp[nodo1][nodo2].x-diff_x)*
			100/(hbound-lbound);
	}
	// hacer lo mismo si sigue y
	else {
	    int hbound = Math.max(startp[nodo1][nodo2].y, 
			 	  endp[nodo1][nodo2].y)-Math.abs(diff_y);
	    int lbound = Math.min(startp[nodo1][nodo2].y, 
			 	  endp[nodo1][nodo2].y)+Math.abs(diff_y);
	    if (y<lbound) { arista[nodo1][nodo2].y=lbound; }
	    else if (y>hbound) { arista[nodo1][nodo2].y=hbound; }
	    else arista[nodo1][nodo2].y=y;
	    arista[nodo1][nodo2].x=
		(arista[nodo1][nodo2].y-startp[nodo1][nodo2].y) *
		    (endp[nodo1][nodo2].x-startp[nodo1][nodo2].x)/
			(endp[nodo1][nodo2].y-startp[nodo1][nodo2].y) + 
		startp[nodo1][nodo2].x;
	    peso[nodo1][nodo2]=
		Math.abs(arista[nodo1][nodo2].y-startp[nodo1][nodo2].y-diff_y)*
			100/(hbound-lbound);
	}
    }
    public void aristaupdate(int p1, int p2, int w) {
    // hacer una arista nueva del nodo p1 al p2 con peso w, o cambiar
    // el peso de la arista a w, calcular la
    // posicion resultante de la flecha
	int dx, dy;
	float l;
	peso[p1][p2]=w;
	// linea de direccion entre p1 y p2
	dx = nodo[p2].x-nodo[p1].x;
	dy = nodo[p2].y-nodo[p1].y;
	// distancia entre p1 y p2
	l = (float)( Math.sqrt((float)(dx*dx + dy*dy)));
	dir_x[p1][p2]=dx/l;
	dir_y[p1][p2]=dy/l;
	// calcular el start y endpoints de la arista,
	// ajustar startpoints si tambien hay una arista de p2 a p1
	if (peso[p2][p1]>0) {
	   startp[p1][p2] = new Point((int)(nodo[p1].x-5*dir_y[p1][p2]), 
				      (int)(nodo[p1].y+5*dir_x[p1][p2]));
	   endp[p1][p2] = new Point((int)(nodo[p2].x-5*dir_y[p1][p2]), 
				    (int)(nodo[p2].y+5*dir_x[p1][p2]));
	}
	else {
	   startp[p1][p2] = new Point(nodo[p1].x, nodo[p1].y);
	   endp[p1][p2] = new Point(nodo[p2].x, nodo[p2].y);
	}
	// la distancia de la flecha no es todo el camino a los puntos de inicio/final
	int diff_x = (int)(Math.abs(20*dir_x[p1][p2]));
	int diff_y = (int)(Math.abs(20*dir_y[p1][p2]));
	// calcular nueva posicion en x de la flecha
	if (startp[p1][p2].x>endp[p1][p2].x) {
	   arista[p1][p2] = new Point(endp[p1][p2].x + diff_x +
		(Math.abs(endp[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*
			(100-w)/100 , 0);
	}
	else {
	   arista[p1][p2] = new Point(startp[p1][p2].x + diff_x +
		(Math.abs(endp[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*
			w/100, 0);
	}
	// calcular nueva posicion en y de la flecha
	if (startp[p1][p2].y>endp[p1][p2].y) {
	   arista[p1][p2].y=endp[p1][p2].y + diff_y +
		(Math.abs(endp[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*
			(100-w)/100;
	}
	else {
	   arista[p1][p2].y=startp[p1][p2].y + diff_y +
		(Math.abs(endp[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*
			w/100;
	}
    }

    public String intToString(int i) {
	char c=(char)((int)'a'+i);
	return ""+c;
    }
    public final synchronized void update(Graphics g) {
    // preparar nueva imagen fuera de la pantalla
	Dimension d=size();
	if ((offScreenImage == null) || (d.width != offScreenSize.width) ||
			(d.height != offScreenSize.height)) {
	    offScreenImage = createImage(d.width, d.height);
	    offScreenSize = d;
	    offScreenGraphics = offScreenImage.getGraphics();
	}
	offScreenGraphics.setColor(Color.white);
	offScreenGraphics.fillRect(0, 0, d.width, d.height);
	paint(offScreenGraphics);
	g.drawImage(offScreenImage, 0, 0, null);
    }
    public void drawarista(Graphics g, int i, int j) {
    // dibuja arista entre nodo i y nodo j
	int x1, x2, x3, y1, y2, y3;
	// calcular flecha
	x1= (int)(arista[i][j].x - 3*dir_x[i][j] + 6*dir_y[i][j]);
	x2= (int)(arista[i][j].x - 3*dir_x[i][j] - 6*dir_y[i][j]);
	x3= (int)(arista[i][j].x + 6*dir_x[i][j]);
	y1= (int)(arista[i][j].y - 3*dir_y[i][j] - 6*dir_x[i][j]);
	y2= (int)(arista[i][j].y - 3*dir_y[i][j] + 6*dir_x[i][j]);
	y3= (int)(arista[i][j].y + 6*dir_y[i][j]);
	int flecha_x[] = { x1, x2, x3, x1 };
	int flecha_y[] = { y1, y2, y3, y1 };
	// si la arista ya se escogio por el algoritmo cambiar color
	if (algedge[i][j]) g.setColor(Color.orange);
	// dibuja arista
	g.drawLine(startp[i][j].x, startp[i][j].y, endp[i][j].x, endp[i][j].y);
	g.fillPolygon(flecha_x, flecha_y, 4);
	// escribe el peso de la arista en una posicion apropiada
	int dx = (int)(Math.abs(7*dir_y[i][j]));
	int dy = (int)(Math.abs(7*dir_x[i][j]));
	String str = new String("" + peso[i][j]);
	g.setColor(Color.black);
	if ((startp[i][j].x>endp[i][j].x) && (startp[i][j].y>=endp[i][j].y))
	  g.drawString( str, arista[i][j].x + dx, arista[i][j].y - dy);
	if ((startp[i][j].x>=endp[i][j].x) && (startp[i][j].y<endp[i][j].y))
	  g.drawString( str, arista[i][j].x - fmetrics.stringWidth(str) - dx , 
			     arista[i][j].y - dy);
	if ((startp[i][j].x<endp[i][j].x) && (startp[i][j].y<=endp[i][j].y))
	  g.drawString( str, arista[i][j].x - fmetrics.stringWidth(str) , 
			     arista[i][j].y + fmetrics.getHeight());
	if ((startp[i][j].x<=endp[i][j].x) && (startp[i][j].y>endp[i][j].y))
	    g.drawString( str, arista[i][j].x + dx, 
			       arista[i][j].y + fmetrics.getHeight() );
    }

    public void detailsDijkstra(Graphics g, int i, int j) {
    // checar que arista entre nodo i y nodo j esta cerca de la arista s para
    //escoger durante este paso del algoritmo
    // checar si el nodo j tiene la siguiente minima distancia al nodo_inicial
	if ( (finaldist[i]!=-1) && (finaldist[j]==-1) ) {
	  g.setColor(Color.red);
	  if ( (dist[j]==-1) || (dist[j]>=(dist[i]+peso[i][j])) ) {
             if ( (dist[i]+peso[i][j])<dist[j] ) {
                 changed[j]=true;
                 numchanged++;
             }
	     dist[j] = dist[i]+peso[i][j];
	     colornodo[j]=Color.red;             
	     if ( (mindist==0) || (dist[j]<mindist) ) {
	        mindist=dist[j];
	        minstart=i;
	        minend=j;
	     }
	  }
	}
	else g.setColor(Color.gray);
    }
    public void endstepDijkstra(Graphics g) {
    // despliega distancias parcial y total de los nodos, ajusta la distancia final
    // para el nodo que tuvo la minima distancia en este paso
    // explica el algoritmo en el panel de documentacion
        for (int i=0; i<numnodos; i++)
	  if ( (nodo[i].x>0) && (dist[i]!=-1) ) {
	     String str = new String(""+dist[i]);
	     g.drawString(str, nodo[i].x - (int)fmetrics.stringWidth(str)/2 -1, 
			       nodo[i].y + h);
	     if (finaldist[i]==-1) {
	        neighbours++;  
	        if (neighbours!=1)
	           showstring = showstring + ", ";
	        showstring = showstring + intToString(i) +"=" + dist[i];
	     } 
	  }
        showstring = showstring + ". ";
       
        if ( (paso>1) && (numchanged>0) ) {
           if (numchanged>1)
              showstring = showstring + "Note que las distancias a ";
           else showstring = showstring + "Note que la distancia a ";
           for (int i=0; i<numnodos; i++)
              if ( changed[i] )
                 showstring = showstring + intToString(i) +", ";
           if (numchanged>1)
              showstring = showstring + "han cambiado!\n";
           else showstring = showstring + "ha cambiado!\n";
        }
        else showstring = showstring + " "; 
	if (neighbours>1) { 
	// si hay otros candidatos explicar porque se tomo este 
 	  showstring = showstring + "El nodo " + intToString(minend) + 
				    " tiene la distancia minima.\n";
	  //checar sy hay otros caminos a minend. 
	  int newcaminos=0;
	  for (int i=0; i<numnodos; i++) 
	     if ( (nodo[i].x>0) && (peso[i][minend]>0) && ( finaldist[i] == -1 ) )
	        newcaminos++; 
	  if (newcaminos>0) 
	     showstring = showstring + "Cualquier otro camino a " + intToString(minend) + 
                   " visita otro nodo de la red, y sera mas largo que " +  mindist + ".\n";
          else showstring = showstring + 
			     "No hay otras aristas entrando a "+
			     intToString(minend) + ".\n";
	}
	else {
           boolean morenodos=false;
           for (int i=0; i<numnodos; i++) 
	     if ( ( nodo[i].x>0 ) && ( finaldist[i] == -1 ) && ( peso[i][minend]>0 ) )
	        morenodos=true; 
           boolean bridge=false;
           for (int i=0; i<numnodos; i++) 
	     if ( ( nodo[i].x>0 ) && ( finaldist[i] == -1 ) && ( peso[minend][i]>0 ) )
	        bridge=true; 
           if ( morenodos && bridge ) 
              showstring = showstring + "Dado que este nodo forma un 'puente' a "+
               "los nodos restantes,\ncualquier otro camino a este nodo sera mas largo.\n";     
           else if ( morenodos && (!bridge) ) 
              showstring = showstring + "Los nodos grises restantes no son alcanzables.\n";
           else showstring = showstring + "No hay otras aristas entrando a "+
	       intToString(minend) + ".\n"; 
        }
	showstring = showstring + "Node " + intToString(minend) + 
	   " sera coloreado naranja para indicar que " + mindist + 
	   " es la longitud del camino mas corto a " + intToString(minend) +"."; 
	parent.documentacion.doctext.showline(showstring);       
    }
    public void detailsalg(Graphics g, int i, int j) {
    // mas algoritmos pueden ser aniadidos
	if (algoritmo==DIJKSTRA)
	    detailsDijkstra(g, i, j);
    }
    public void endstepalg(Graphics g) {
    // mas algoritmos pueden ser aniadidos
	if (algoritmo==DIJKSTRA)
	    endstepDijkstra(g);
	if ( ( performalg ) && (mindist==0) ) {
	   if (algrthm != null) algrthm.stop();
	   int nalcanzable = 0;
	   for (int i=0; i<numnodos; i++)
	      if (finaldist[i] > 0)
		 nalcanzable++;
	   if (nalcanzable == 0)
	      parent.documentacion.doctext.showline("ninguno");
           else if (nalcanzable< (numnodos-emptyspots-1))
	      parent.documentacion.doctext.showline("alguno");
	   else
	      parent.documentacion.doctext.showline("hecho");
	} 
    }
    public void paint(Graphics g) {
        mindist=0;
	minnodo=MAXNODOS;
	minstart=MAXNODOS;
	minend=MAXNODOS;
        for(int i=0; i<MAXNODOS; i++) 
           changed[i]=false;
        numchanged=0;
        neighbours=0;
	g.setFont(roman);
	g.setColor(Color.black);
        if (paso==1)
           showstring="Algoritmo ejecutando: las aristas rojas apuntan a nodos alcanzables desde " +
                   " el nodo_inicial.\nLa distancia a: ";
        else 
           showstring="Paso " + paso + ": Las aristas rojsa apuntan a nodos alcanzables desde " +
                   "nodos que ya tienen una distancia final ." +
                   "\nLa distancia a: ";
	// dibuja una nueva arista en la posicion del mouse
	if (newarista)
	  g.drawLine(nodo[nodo1].x, nodo[nodo1].y, thispoint.x, thispoint.y);
	// dibuja todas las aristas
	for (int i=0; i<numnodos; i++)
	  for (int j=0; j<numnodos; j++)
	     if (peso [i][j]>0) {
	        // si el algoritmo se esta ejecutando entonces hacer el siguiente paso para esta arista
	        if (performalg)
	           detailsalg(g, i, j);
	        drawarista(g, i, j);
	     }
	// si la flecha ha sido arrastyrado a  0, dibujala, para que el usuario
	//tenga la opcion de hacerla positiva de nuevo
	if (movearista && peso[nodo1][nodo2]==0) {
	  drawarista(g, nodo1, nodo2);
	  g.drawLine(startp[nodo1][nodo2].x, startp[nodo1][nodo2].y, 
		     endp[nodo1][nodo2].x, endp[nodo1][nodo2].y);
	}
	// dibuja los nodos
	for (int i=0; i<numnodos; i++)
	  if (nodo[i].x>0) {
	     g.setColor(colornodo[i]);
	     g.fillOval(nodo[i].x-NODORADIX, nodo[i].y-NODORADIX, 
			NODOSIZE, NODOSIZE);
	  }
	// refleja el nodo_inicial que se mueve
	g.setColor(Color.blue);
	if (movestart)
	  g.fillOval(thispoint.x-NODORADIX, thispoint.y-NODORADIX, 
			NODOSIZE, NODOSIZE);

	g.setColor(Color.black);
	// termina este paso del algoritmo
	if (performalg) endstepalg(g);
	// dibuja circulos negros alrededor de los nodos, escribe sus nombres a la pantalla
	g.setFont(helvetica);
	for (int i=0; i<numnodos; i++)
	  if (nodo[i].x>0) {
	     g.setColor(Color.black);
	     g.drawOval(nodo[i].x-NODORADIX, nodo[i].y-NODORADIX, 
			NODOSIZE, NODOSIZE);
	     g.setColor(Color.blue);
	     g.drawString(intToString(i), nodo[i].x-14, nodo[i].y-14);
	  }
    }
}



  
    
  







 


	 

