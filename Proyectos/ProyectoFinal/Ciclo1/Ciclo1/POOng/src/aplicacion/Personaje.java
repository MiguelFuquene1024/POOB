package aplicacion;

import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

import presentacion.EventoTeclado;

/**
 * clase que representa los personajes del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 23/04/2020
 * @version 3.0 24/04/2020       
 * */

public class Personaje  implements Serializable {
	private double xPosition,yPosition;
	private final int ALTO =80,ANCHO=50;
	private ImageIcon image;
	private double strength;
	private double dx = 5;
	private double[] xMax;
	private boolean paused;
	private Rectangle2D.Double shape;
	
	
	/**
	 * constructor los personajes del juego POOng
	 * 
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public Personaje( double xPosition, double yPosition, double[] xMax){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		strength = 100;
		this.paused = false;
		dx = 1;
		this.xMax=xMax;
		shape = new Rectangle2D.Double(xPosition,yPosition,ANCHO,ALTO);
	}
	
	/**
	 * metodo que devuelve la posicion en x de la pelota
	 * @return double, la posicion en x de la pelota
	 * */
	public double getXposition(){
		return xPosition;
	}
	
	/**
	 * metodo que devuelve la posicion en y de la pelota
	 * @return double, la posicion en y de la pelota
	 * */
	public double getYposition(){
		return yPosition;	
	}
	

	/**
	 * metodo que se encarga de establecer la imagen que tomara el personaje
	 * @param rute, la ruta de la imagen del personaje que desea tomar
	 * */
	public void setImage( String rute){
		image = new ImageIcon( rute );
		Image temporal = image.getImage().getScaledInstance(ANCHO, ALTO, Image.SCALE_SMOOTH);
		image = new ImageIcon(temporal);
	}
	
	
	public Image getImage() {
		
		return image.getImage();
	}
	
	/**
	 *metodo encargado de pausar o quitar el pause a un personaje
	 */
	public void pause(){
		paused = !paused;
	}
	
	/**
	 * metodo que retorna la figura asociada al personaje
	 * @return Rectangle2D.Double , figura asociada al personaje 
	 * */
	public Rectangle2D.Double  getshape(){
		return shape;
	}
	
	/**
	 * metodo que se encarga de mover al personaje 1
	 * */
	public void moverPersonaje1(){
		if( EventoTeclado.a ){
			moveLeft();
		}
		
		if( EventoTeclado.d ){
			moveRight();
		}
	}	
	
	/**
	 * metodo que se encarga de mover al personaje 2
	 * */
	public void moverPersonaje2(){
		if( EventoTeclado.left ){
			moveLeft();
		}
		
		if( EventoTeclado.right){
			moveRight();
		}
	}
	
	
	/**
	 * metodo encargado de desplazar el personaje a la izquierda
	 * */
	public void moveLeft() {
		if ( !paused && xPosition-dx > xMax[0] ){
			xPosition-=dx;
			shape = new Rectangle2D.Double(xPosition,yPosition,ANCHO,ALTO);
			this.dicreaseStrenght();
		}
	}
	
	/**
	 * metodo encargado de desplazar el personaje a la derecha
	 * */
	public void moveRight(){
		if ( !paused && xPosition+shape.width+dx < xMax[1] ){
			xPosition+=dx;
			shape = new Rectangle2D.Double(xPosition,yPosition,ANCHO,ALTO);
			this.dicreaseStrenght();
			
		}
	}
	
	/**
	 * metodo encargado de disminuir la fotaleza al personaje
	 * */
	private void dicreaseStrenght(){
		strength-=0.3;	
	}
	
	/**
	 * metodo que dice si el personaje es capaz de seguir en el juego
	 * @return boolean, que nos dice si la fortaleza de este es menor al 50%
	 * */
	public boolean iGiveUp(){
		return  ( strength < 0.5 );
	
	}
	
	/**
	 * metodo que retorna la fortaleza que tiene el personaje
	 * @return int,returna el porcentaje que le queda al jugador 
	 * */
	public int getStrenght(){
		
		return (int)Math.ceil(strength *100);
	}

}
