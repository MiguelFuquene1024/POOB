package presentacion;


import aplicacion.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;


/**
 *Panel en el  cual se pintan todos los componentes graficos del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 24/04/2020
 * @version 3.0 05/04/2020
 * */
public class PanelJuego extends PanelFondo {
	private transient Image temporal;
	private ImageIcon image;
	private PanelFondo pause;
	private transient Graphics2D g2;
	private POOng game;
	private PantallaJuego parent ;
	private int wait = 2,stop =997 ; 
	
	
	public PanelJuego( POOng game ,String rute, PantallaJuego parent ){
		super(rute);
		this.parent=parent;
		this.setLayout(null);
		this.addKeyListener(new EventoTeclado() );
		this.setFocusable(true);
		this.game = game;
		pause = new PanelFondo("resources/pause.png");
		pause.setBounds(240,520, 200, 100);
		pause.setBackground(Color.BLACK);
		pause.setVisible(false);
		this.add(pause);
		prepareImagenes();
	}
	
	
	public void prepareImagenes(){
		preparePersonajes();
		prepareSorpresas();
		prepareBloque();
		
	}
	public void preparePersonajes(){
		for( int x= 0 ; x < game.getAmountPersonajes();x++){
			Personaje p = game.getPersonaje(x);
			image = p.getImage();
			temporal = image.getImage().getScaledInstance(p.getAncho(), p.getAlto(), Image.SCALE_SMOOTH);
			p.setImage(new ImageIcon(temporal));
		}
	}
	
	public void prepareSorpresas(){
		for( int x= 0 ; x < game.getAmountSorpresas();x++){
			Sorpresa s = game.getSorpresa(x);
			image = s.getImage();
			temporal = image.getImage().getScaledInstance(s.getAncho(), s.getAlto(), Image.SCALE_SMOOTH);
			s.setImage(new ImageIcon(temporal));
		}
	}
	
	public void prepareBloque(){
		Bloque b= game.getBloque();
		image = b.getImage();
		temporal = image.getImage().getScaledInstance(b.getAncho(), b.getAlto(), Image.SCALE_SMOOTH);
		b.setImage(new ImageIcon(temporal));
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		dibujar(g);
		actualizar();
		
		
	}	
	
	public void dibujar(Graphics  g){
		g2.setColor(Color.WHITE);
		for ( int x =0; x < game.getAmountPersonajes() ;x++){
			Rectangle2D.Double personaje =game.getPersonaje(x).getshape(); 
			
			temporal = game.getPersonaje(x).getImage().getImage();
			g.drawImage(temporal, (int)personaje.getX(), (int)(personaje.getY()), this);
		}
		if (game.getCurrentSorpresa()!=null){
			Rectangle2D.Double s =game.getCurrentSorpresa().getShape(); 
			temporal  = game.getCurrentSorpresa().getImage().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);			
		}
		if(game.getBloque().isActive() ){
			Rectangle2D.Double s =game.getBloque().getShape(); 
			temporal  = game.getBloque().getImage().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);
		}
				
		
		g2.setColor(new Color(100,221,23));
		g2.fill(game.getShapePelota(0));
	
	
	}
	
	public void actualizar(){
		if( EventoTeclado.pause ){
			game.pauseAll();
			pause.setVisible(game.isPaused());
		}
		game.jugar();
		moverPlataformas();
		actualizarContadores();
		 	
	}
	
	public void moverPlataformas(){
		for ( int x=0 ; x < 2; x++){
			quitarPoder(x);
			game.moverPersonaje(x);
		}
		
		
		
		
		
	}
	
	public void actualizarContadores(){
		parent.refresqueContadores();
	}
	
	public void quitarPoder(int index ){
		if(!game.isPaused()&& game.getPersonaje(index).isPaused()){
			if( wait%stop  == 0){
				game.getPersonaje(index).pause();
				wait = 2;
			}
			else{wait=( wait < stop )?wait +1:wait + 0;}
		}
		if(game.getCurrentSorpresa()==null && game.getLastSorpresa() == 4 && game.getPersonaje(index).isSlow()) {
			if( wait%stop  == 0){
				game.getPersonaje(index).restablecerVelocidad();
				wait = 2;
			}
			else{wait=( wait < stop )?wait +1:wait + 0;}	
		}
	}
	
}