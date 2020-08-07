package aplicacion;

import java.awt.geom.Rectangle2D;
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
 * @version 4.0 05/05/2020       
 * */
public class Personaje extends Elemento{
	private static final long serialVersionUID = 6112243834308839425L;
	private static final double INITIALSPEED=0.8; 
	private double strength;
	private double dx;
	private double[] xMax;
	private boolean paused;
	private Rectangle2D.Double shape;
	
	
	/**
	 * constructor del personaje del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public Personaje( double xPosition, double yPosition, double[] xMax){
		super(xPosition,yPosition,50,80);
		strength = 1;
		dx=INITIALSPEED;
		this.paused = false;
		this.xMax=xMax;
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
	}
	
	/**
	 * metodo que se encarga de devolver la figura asociada a el personaje
	 * */
	public Rectangle2D.Double getShape(){
		return shape;
	}
	
	
	/**
	 * metodo que nos dice si el jugar se puede mover
	 * @boolean,nos dice si el jugar se puede mover
	 * */
	public boolean isPaused(){
		return paused;
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
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
			this.dicreaseStrenght();
		}
	}
	
	/**
	 * metodo encargado de desplazar el personaje a la derecha
	 * */
	public void moveRight(){
		if ( !paused && xPosition+shape.width+dx < xMax[1] ){
			xPosition+=dx;
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
			this.dicreaseStrenght();
			
		}
	}
	
	/**
	 * metodo encargado de disminuir la fotaleza al personaje
	 * */
	private void dicreaseStrenght(){
		strength-=0.00001;
		//strength-=0.01;
	}
	
	/**
	 * metodo que encarga de actualizar la fotaleza del personaje
	 * @param newStrenght, la nueva fortaleza que tomara el personaje
	 */
	public void setStrength( double newStrenght){
		strength= ( newStrenght > 1 )?1:newStrenght;
		
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
	public double getStrenght(){
		
		return Math.ceil(strength *100);
	}
	
	
	/**
	 * metodo que le quita fortaleza a un personaje cuando es golpeado por un bloque
	 * */
	public void chocarConBloque(){
		strength-=0.5;
	}
	
	
	/**
	 * metodo que restablece la velocidad inicial del personaje
	 * */
	public void restablecerVelocidad(){
		dx=INITIALSPEED;
	}
	
	/**
	 * metodo encargado de disminuir la velocidad del personaje
	 * */
	public void disminuirVelocidad(){
		dx=0.4;
	}
	
	/**
	 * metodo que dice si la velocidad del personaje se disminuyo
	 * @return boolean, que dice si a el personaje se le bajo la velocidad
	 * */
	public boolean isSlow(){
		return dx==0.4;
	}
	
}
