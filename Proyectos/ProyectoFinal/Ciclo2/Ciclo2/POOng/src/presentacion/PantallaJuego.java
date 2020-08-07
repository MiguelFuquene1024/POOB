package presentacion;

import aplicacion.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



/**
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/05/2020
 * 
 * clase que representa la clase Pantalla del juego:
 * */
public class PantallaJuego extends JFrame{
	private static final long serialVersionUID = -7600810760106043005L;
	private static final int ANCHO = 683;
	private static final int ALTO = 384;
	
	private POOng game = null;
	public Hilo hilo;
	
	private JMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem save,export,exit;
	
	private PanelJuego fondo;
	private JPanel panelSuperior;
	private JPanel panelInferior;
	private JLabel player1;
	private JLabel score1;
	private JLabel score2;
	private JLabel strength1;
	private JLabel player2;
	private JLabel strength2;
	private JLabel bestScore;
	
	
	private JFileChooser fileChooser;
	
	public PantallaJuego( POOng game){
		this.game = game;
		prepareElementos();
		prepareAcciones();
		
	}
	
	public void prepareElementos(){
		fileChooser = new JFileChooser();
		this.setSize(ANCHO,ANCHO);
		this.setLocationRelativeTo(null);
		this.setLayout( new BorderLayout() );
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("resources/icon.png").getImage());
		
		prepareContadores();
		this.add(panelSuperior,BorderLayout.NORTH);
		this.add(panelInferior,BorderLayout.SOUTH);
		
		
		fondo = new PanelJuego(game, "resources/fondoJuego.png",this);
		hilo = new Hilo(fondo);
		game.setHilo(hilo);
		hilo.start();
		this.add(fondo,BorderLayout.CENTER);
		
		
		prepareElementosMenu();
		
	}
	
	public void prepareContadores(){
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new GridLayout( 1,5,0,0 ) );
		
		player1 = new JLabel("Player1: "+game.getJugador(0).getNombre());
		score1 = new JLabel("Score: "+game.getJugador(0).getScore());
		strength1 = new JLabel("strength: "+game.getPersonaje(0).getStrenght()+"%");
		
		panelSuperior.add( player1 );
		panelSuperior.add(strength1);
		panelSuperior.add( score1 );
		
		
		panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout( 1,5,0,0 ) );
		
		player2 = new JLabel("Player2: "+game.getJugador(1).getNombre());
		score2 = new JLabel("Score: "+game.getJugador(1).getScore());
		strength2 = new JLabel("strength: "+game.getPersonaje(0).getStrenght()+"%");
		
		//bestScore = new JLabel("Best Score: ");
		panelInferior.add( player2 );
		panelInferior.add(strength2);
		panelInferior.add( score2 );
		//panelInfo.add(bestScore);
	
	}
	
	
	/**
	 *metodo encargado de actualizar todas las acciones que haga el programa
	*/
	public void refresqueContadores(){
		score1.setText("Score: "+game.getJugador(0).getScore());
		strength1.setText("strength: "+game.getPersonaje(0).getStrenght()+"%");
		
		score2.setText("Score: "+game.getJugador(1).getScore());
		strength2.setText("strength: "+game.getPersonaje(1).getStrenght()+"%");
	
	}
	
	public void prepareElementosMenu(){
		menuBar = new JMenuBar();	
		archivo = new JMenu("Archivo");
		save = new JMenuItem("Save");
		export = new JMenuItem("Export");
		exit =  new JMenuItem("Exit");
		
		archivo.add(save);
		archivo.add(export);
		archivo.add(exit);
		
		menuBar.add(archivo);
		setJMenuBar(menuBar);	
	}

	public void prepareAcciones(){
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
            	accionSalir();
            }
        });
		
		
		
		save.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				try{
					accionSalvar();
				}
				catch( Exception e){
				}
			}
		});
		
		exit.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionSalir();
			}
		});
		
		

	}
	
	public void accionSalvar() throws PoongException{
		if( !game.isPaused()){game.pauseAll();}
		fileChooser.setVisible(true);
		fileChooser.setFileFilter( new FileNameExtensionFilter("extension archivo .dat","dat") );
		
		int answ = fileChooser.showSaveDialog(this);
		if ( answ == fileChooser.APPROVE_OPTION){
			game.salvar( fileChooser.getSelectedFile());
		}
	
	}
	
	public void accionSalir(){
		if( !game.isPaused()){game.pauseAll();}
    	int answ = JOptionPane.showConfirmDialog( null,"¿Esta seguro que desea salir?",
                "Error:", JOptionPane.ERROR_MESSAGE );
    	
    	if ( answ == JOptionPane.YES_OPTION){
    		this.dispose();
    	}
    	game.pauseAll();
	} 
}

