package presentacion;

import aplicacion.*;
import java.awt.*;
import javax.swing.*;




public class PantallaJuego extends JFrame{
	private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ANCHO = DIMENSION.width/2;
	private static final int ALTO = DIMENSION.height/2;
	
	private POOng game = null;
	public Hilo hilo;
	
	private JMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem save,exit;
	
	private PanelJuego fondo;
	private JPanel panelInfo;
	private JLabel player1,score1,score2,player2,bestScore;
	
	public PantallaJuego( POOng game){
		this.game = game;
		prepareElementos();
		
	}
	
	public void prepareElementos(){
		
		this.setSize(ANCHO,ANCHO);
		this.setLocationRelativeTo(null);
		this.setLayout( new BorderLayout() );
		this.setResizable(false);
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(new EventoTeclado() );
		
		
		panelInfo = new JPanel();
		player1 = new JLabel("Player1:");
		score1 = new JLabel("Score: 0");
		player2 = new JLabel("Player2:");
		score2 = new JLabel("Score: 0");
		bestScore = new JLabel("Best Score: ");
		
		panelInfo.setLayout(new GridLayout( 1,5,0,0 ) );
		panelInfo.add( player1 );
		panelInfo.add( score1 );
		panelInfo.add( player2 );
		panelInfo.add( score2 );
		panelInfo.add(bestScore);
		this.add(panelInfo,BorderLayout.NORTH);
		
		
		fondo = new PanelJuego(game, "resources/fondoJuego.png");
		hilo = new Hilo(fondo);
		game.setHilo(hilo);
		hilo.start();
		this.add(fondo,BorderLayout.CENTER);
		
		
		prepareElementosMenu();
		
	}
	
	public void prepareElementosMenu(){
		menuBar = new JMenuBar();
	
		archivo = new JMenu("Archivo");
		save = new JMenuItem("Save");
		exit =  new JMenuItem("Exit");
		
		archivo.add(save);
		archivo.add(exit);
		
		menuBar.add(archivo);
		setJMenuBar(menuBar);	
	}


}
