package aplicacion;
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
	protected int ancho;
	protected int alto;
	protected double xPosition;
	protected double yPosition;
	protected ImageIcon image;
	
	
	/**
	 * Constructor de la clase Elemento
	 * @param xPos, double que representa la posicion en x del elemento
	 * @param yPos, double que representa la posicion en y del elemento
	 * @param ancho, int que representa el ancho que tomara en el juego
	 * @param alto, int  que representa el largo que tomara en el  juego
	 * */
	public Elemento(double xPos, double yPos,int ancho,int alto){
		this.ancho= ancho;
		this.alto = alto;
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
	 * metodo que devuelve la posicion el ancho asignado al elemento
	 * @return int, el ancho asignado al elemento
	 * */
	public int getAncho(){
		return ancho;	
	}
	
	/**
	 * metodo que devuelve la posicion el alto asignado al elemento
	 * @return int, el alto asignado al elemento
	 * */
	public int getAlto(){
		return alto;	
	}
	
	
	/**
	 * metodo que se encarga de establecer la imagen que tomara el Elemento
	 * @param rute, la ruta de la imagen del elemento que desea tomar
	 * */
	
	public void setImage( String rute ){
		image = new ImageIcon(rute );	
	}
	
	/**
	 * metodo que se encarga de establecer la imagen que tomara el Elemento
	 * @param ImageIcon, la nueva imagen Rescalada segun se necesite
	 * */
	
	public void setImage( ImageIcon i ){
		image = i;	
	}
	
	
	/**
	 * metodo que se encarga de deolver la imagen establecida para el Elemento
	 * */
	public ImageIcon getImage(){
		return image;
	}

}
