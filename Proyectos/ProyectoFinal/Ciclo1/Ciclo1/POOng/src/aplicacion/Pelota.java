package aplicacion;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.*;



/**
 * clase que representa la pelota de tenis que tiene el juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 22/04/2020
 * @version 3.0 24/04/2020     
 * */

public class Pelota implements Serializable{
	protected final int SIZE = 15;
	protected double xPosition,yPosition;
	protected double dx =0.4,dy=0.4;
	protected int sali;
	protected Ellipse2D.Double shape;
	protected boolean paused;
	
	/**
	 * Constructor de la clase pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * */
	public Pelota( double xPosition, double yPosition ){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.paused = false;
		shape = new Ellipse2D.Double(xPosition,yPosition,SIZE,SIZE );

	}
	
	/**
	 * Constructor de la clase pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * @param speed, double que representa la velocidad de la velocidad de la pelota
	 * */
	public Pelota( double xPosition, double yPosition, double speed){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.paused = false; 
		shape = new Ellipse2D.Double(xPosition,yPosition,SIZE,SIZE );
		dx =speed;
		dy =speed;
		
	}
	
	/**
	 * este metodo hace que la pelota reaparezca cuando sale de los limites
	 * */
	public void reaparecer(){
		dy = ( yPosition > 520 ) ?-0.3:0.3;
		this.xPosition = 200;
		this.yPosition = 200;
		
		dx =0.4;
		
		shape = new Ellipse2D.Double(xPosition,yPosition,SIZE,SIZE );

	}
	
	/**
	 *metodo encargado de pausar o quitar el pause una pelota
	 */
	public void pause(){
		paused = !paused;
	}
	
	
	/**
	 * Metodo que que se encarga de incrementar la velocidad de la pelota
	 * */
	public void increaseSpeed(){
		dx= ( dx < 0 )? -1:1;
		dy= ( dy < 0) ? -1:1;
	}
	
	
	/**
	 * Metodo que que se encarga de disminuir la velocidad de la pelota
	 * */
	public void decreaseSpeed(){
		if( Math.abs(dx) != 1  ){
			dx= ( dx < 0 )? 1:-1;
			dy= ( dy < 0) ? -1:1;
		}
	}
	
	
	/**
	 * Metodo que establece las posiciones de la pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota 
	 * */
	public void setPosition( int newXpos, int newYpos){
		this.xPosition = newXpos;
		this.yPosition = newYpos;
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
	 * metodo encargado de retornar la figura asociada a la pelota
	 * @return Ellipse2D.Double, figura asociada a la pelota de POOng
	 * */
	public Ellipse2D.Double getShape(){
		return shape;
	
	}
	
	/**
	 * metodo que se encarga  de cambiar la posicion de la pelota, ( mover la pelota)
	 * */
	public void move(){
		if( !( paused) ){
			rebotar();
			xPosition += dx;
			yPosition += dy;
			shape = new Ellipse2D.Double(xPosition,yPosition,SIZE,SIZE);
		}
	}
	
	/**
	 * metodo que se encarga de cambiar la direccion a la cual se dirige la pelota
	 * */
	public void reverseD(){
		Random r = new Random();
		dx = ( r.nextInt(10) > 5) ?-dx:dx;
		dy = -dy;
	}
	
	
	/**
	 * metodo que se encarga de decir si una pelota choco con un personaje
	 * @param shape, la figura asociada al personaje que deseamos saber si lo golpeo
	 * @return boolean, que dice si choco o no con un personaje
	 * */
	public boolean rebotaPersonaje( Rectangle2D.Double shape ){
		
		boolean valid = ((int)shape.getY() == (int)yPosition) ;
		if (valid && this.shape.getBounds2D().intersects(shape.getBounds2D()) ){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Metodo encargado de hacer rebotar la pelota con los bordes de la cancha
	 * */
	public void rebotar(){
		sali = -1;
		if ( xPosition < 130 || xPosition > 500){
			dx=-dx;
		}
		if( yPosition < 15 || yPosition > 520){
			sali = (yPosition < 15)?0:1;
		}
		
	}
	
	
	/**
	 * metodo que dice si la pelota se salio en la cancha en la posicion y
	 * @return int,si la pelota se salio en la cancha -1 si no se salio, 0 si se salio para el jugador 1 , 1 si se salio para el jugador 2 
	 * */
	public int getSali(){
		return sali;
	}
}
