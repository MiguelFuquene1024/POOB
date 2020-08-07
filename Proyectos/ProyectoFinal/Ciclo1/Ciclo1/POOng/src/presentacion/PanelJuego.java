package presentacion;


import aplicacion.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;


/**
 *Panel en el  cual se pintan todos los componentes graficos del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 24/04/2020
 * */
public class PanelJuego extends PanelFondo {
	private Graphics2D g2;
	private POOng game;
	private boolean on = false;
	
	
	public PanelJuego( POOng game ,String rute ){
		super(rute);
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
			
			Image imagen = game.getPersonaje(x).getImage();
			g.drawImage(imagen, (int)personaje.getX(), (int)(personaje.getY()), this);
			//g2.fill(personaje);
		}
		
		
		g2.setColor(Color.RED);
		g2.fill(game.getShapePelota(0));
	
	
	}
	
	public void actualizar(){
		if( EventoTeclado.pause ){game.pauseAll();}
		game.jugar();
		moverPlataformas();
		 	
	}
	
	public void moverPlataformas(){
		game.moverPersonaje(0);
		game.moverPersonaje(1);
		
	}
	
}