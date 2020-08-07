package presentacion;


import aplicacion.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;


/**
 *Panel en el  cual se pintan todos los componentes graficos del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 24/04/2020
 * */
public class PanelJuego extends PanelFondo implements Serializable{
	private static final long serialVersionUID = 8383695398574127221L;
	private transient Image temporal;
	private Graphics2D g2;
	private POOng game;
	private PantallaJuego parent ;
	private int wait = 2,stop =997 ; 
	
	
	public PanelJuego( POOng game ,String rute, PantallaJuego parent ){
		super(rute);
		this.parent=parent;
		this.addKeyListener(new EventoTeclado() );
		this.setFocusable(true);
		this.game = game;

	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		dibujar(g);
		actualizar();
		
		
	}	
	
	public void dibujar(Graphics  g){
		g2.setColor(Color.WHITE);
		for ( int x =0; x < game.getPersonajes() ;x++){
			Rectangle2D.Double personaje =game.getPersonaje(x).getshape(); 
			
			temporal = game.getPersonaje(x).getImage();
			g.drawImage(temporal, (int)personaje.getX(), (int)(personaje.getY()), this);
		}
		if (game.getCurrentSorpresa()!=null){
			Rectangle2D.Double s =game.getCurrentSorpresa().getShape(); 
			temporal  = game.getCurrentSorpresa().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);			
		}
		if(game.getBloque()!=null ){
			Rectangle2D.Double s =game.getBloque().getShape(); 
			temporal  = game.getBloque().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);
		}
				
		
		g2.setColor(new Color(100,221,23));
		g2.fill(game.getShapePelota(0));
	
	
	}
	
	public void actualizar(){
		if( EventoTeclado.pause ){game.pauseAll();}
		game.jugar();
		moverPlataformas();
		actualizarContadores();
		 	
	}
	
	public void moverPlataformas(){
		for ( int x=0 ; x < 2; x++){
			quitarPoderFreeze(x);
			game.moverPersonaje(x);
		}
		
		
		
		
		
	}
	
	public void actualizarContadores(){
		parent.refresqueContadores();
	}
	
	public void quitarPoderFreeze(int index ){
		if(!game.isPaused()&& game.getPersonaje(index).isPaused()){
			if( wait%stop  == 0){
				game.getPersonaje(index).pause();
				wait = 2;
			}
			else{wait=( wait < stop )?wait +1:wait + 0;}
		}		
	}
	
}