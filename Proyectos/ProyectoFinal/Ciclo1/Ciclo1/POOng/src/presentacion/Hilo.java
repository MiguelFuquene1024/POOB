package presentacion;

/**
 * clase que realiza el movimiento del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * */
public class Hilo extends Thread {
	boolean ejecute; 
	PanelJuego p;
	
	
	/**
	 * Constructor de la clase Hilo
	 * @param p, ingresa el panel en el cual se dibujara el juego 
	 *  
	 * */
	public Hilo( PanelJuego p){
		ejecute = true;
		this.p = p;
		
	}
	
	@Override
	public void run(){
		while( ejecute ){
			p.repaint();
		}
				
	}
		
	public void pareEjecucion(){
		ejecute = false;
	}
	

}
