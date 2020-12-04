

public class TestArrayTDA_struct {
  /** Main method */
  public static void main(String[] args) {
    estudiante x,y,buen_estudiante;
       
    x =new estudiante();
    x.num_mat= 1000;
    x.nombre= "Juan Perez";
    x.nota_fund=80;
    x.nota_std=78;

    y =new estudiante();
    y.num_mat= 1001;
    y.nombre= "Mario Garcia";
    y.nota_fund=67;
    y.nota_std=98;
    

    System.out.println(x.nombre);
    System.out.println(y.nombre);

    buen_estudiante=Mejor(y, x);
    System.out.println("el mejor estudiante = "+ buen_estudiante.nombre);

  
  }

  static estudiante Mejor(estudiante estud1, estudiante estud2){
	 if (estud1.nota_std > estud2.nota_std){
             return(estud1);
         }
         if (estud1.nota_std < estud2.nota_std){
             return(estud2);
         }
         return(null);
     }


}

class estudiante {
     public int num_mat;
	 public String nombre;
	 public double nota_fund;
	 public double nota_std;
	 public boolean ayudante;
     }


