


import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Imagenes {
	public  static Imagenes singleton=new Imagenes();
	public static Image cargaImagen(String fichero){
			URL url = singleton.getClass().getResource(fichero);
		return Toolkit.getDefaultToolkit().getImage(url);
}
	public static Image Rosquilla(String n){
		URL url = singleton.getClass().getResource(n);
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
	public static Image Disparar(String n){
		URL url = singleton.getClass().getResource(n);
		return Toolkit.getDefaultToolkit().getImage(url);
	}
	
}