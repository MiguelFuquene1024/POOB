package aplicacion;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;


/**
 * clase que representa un elemento del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 05/05/2020      
 * */
public class Elemento implements Serializable{
	private static final long serialVersionUID = -4130473059137946215L;
	protected double xPosition;
	protected double yPosition;
	protected transient Image temporal;
	protected ImageIcon image;
	
	
	/**
	 * Constructor de la clase Elemento
	 * @param xPos, double que representa la posicion en x del elemento
	 * @param yPos, double que representa la posicion en y del elemento
	 * */
	public Elemento(double xPos, double yPos){
		this.xPosition = xPos;
		this.yPosition = yPos;
	}
	
	
	/**
	 * metodo que devuelve la posicion en x del elemento
	 * @return double, la posicion en x del elemento
	 * */
	public double getXposition(){
		return xPosition;
	}
	
	/**
	 * metodo que devuelve la posicion en y del eleento
	 * @return double, la posicion en y de la pelota
	 * */
	public double getYposition(){
		return yPosition;	
	}
	
	/**
	 * metodo que se encarga de establecer la imagen que tomara el Elemento
	 * @param rute, la ruta de la imagen del elemento que desea tomar
	 * */
	
	public void setImage( String rute,int ANCHO,int ALTO ){
		image = new ImageIcon(rute );
		temporal = image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
		image = new ImageIcon(temporal);
	}
	
	
	/**
	 * metodo que se encarga de deolver la imagen establecida para el Elemento
	 * */
	public Image getImage(){
		return image.getImage();
	}

}
