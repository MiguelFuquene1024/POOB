package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class POOngGUI extends JFrame {
	private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ANCHO = DIMENSION.width/2;
	private static final int ALTO = DIMENSION.height/2;
	private PanelFondo fondo;
	
	private static String inicio="INICIO";
	private static String configuracion ="CONFIG";
	private static String twoPlayer ="twoPlayer";

	/*Ventanas*/
	private JPanel ventanas, panelBotones;
	private VentanaConfiguracion ventanConfig;
	private PantallaJuego pantallaJ;
	private JPanel ventanTwoPlayer;
	
	/*Botones*/
	private JButton empezar,instrucciones;
	
	/*Menu Bar*/
	private JMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem importar, exportar;
	
	
	
	
	public POOngGUI(){
		
		prepareElementos();
		prepareAcciones();
	}
	

public void prepareElementos(){
	this.setTitle("POOng");
	this.setResizable(false);
	this.setSize(ANCHO,ANCHO);
	this.setBackground(new Color(5,36,55));
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation( EXIT_ON_CLOSE );
	EventoTeclado p = new EventoTeclado();
	this.addKeyListener( p );
	prepareVentanas();
    prepareMenuBar();
	
	
	
}

public void prepareVentanas(){
	ventanas = new JPanel();
	ventanas.setLayout( new CardLayout( 0, 0) );
	
	fondo = new PanelFondo("resources/fondoInicio.png");
	ventanConfig = new VentanaConfiguracion("resources/fondoConfig.png");
	ventanTwoPlayer = ventanConfig.getTwoPlayer();
	fondo.setLayout(null);
	panelBotones = new JPanel();
	panelBotones.setOpaque(false);
	panelBotones.setLayout( new GridLayout(2,1,5,5));
	
	instrucciones = new JButton("Rules");
	empezar = new JButton("Start");
	
	panelBotones.add(instrucciones);
	panelBotones.add(empezar);
	panelBotones.setSize(100, 50);
	panelBotones.setLocation(7*ANCHO/16,13*ANCHO/16);
	fondo.add(panelBotones);
	
	
	ventanas.add(fondo,inicio);
	ventanas.add(ventanConfig,configuracion);
	ventanas.add( ventanTwoPlayer,twoPlayer);
	
	this.add(ventanas);

}

public void prepareMenuBar(){
	menuBar = new JMenuBar();
	archivo = new JMenu("Archivo");
	importar = new JMenuItem("Importar");
	exportar = new JMenuItem("Exportar");
	
	archivo.add(importar);
	archivo.add(exportar);
	
	menuBar.add(archivo);
	
	this.setJMenuBar(menuBar);
}




public void prepareAcciones(){
	empezar.addActionListener( new ActionListener(){
		
		public void actionPerformed(ActionEvent event ){
			accionCambiarAJugadores();
		}


	});
	
	
	/* Ventana Configuracion */
	ventanConfig.volverIni.addActionListener( new ActionListener(){
		
		public void actionPerformed(ActionEvent event ){
			accionCambiarAinicio();
		}


	});
	
	ventanConfig.dosJug.addActionListener( new ActionListener(){
		
		public void actionPerformed(ActionEvent event ){
			accionCambiarUnJugador();
		}


	});
	
	ventanConfig.volverConfi.addActionListener( new ActionListener(){
		
		public void actionPerformed(ActionEvent event ){
			accionCambiarAConfig();
		}


	});
}


public void accionCambiarAJugadores(){
	CardLayout cLayout=(CardLayout)(ventanas.getLayout());
	cLayout.show( ventanas, configuracion);
}

public void accionCambiarAinicio(){
	CardLayout cLayout=(CardLayout)(ventanas.getLayout());
	cLayout.show( ventanas, inicio);
}


public void accionCambiarUnJugador(){
	CardLayout cLayout=(CardLayout)(ventanas.getLayout());
	cLayout.show( ventanas, twoPlayer);
}

public void accionCambiarAConfig(){
	
	CardLayout cLayout=(CardLayout)(ventanas.getLayout());
	cLayout.show( ventanas, configuracion);
}



public static void main(String[] args){
POOngGUI gui = new POOngGUI();
gui.setVisible(true);
}

}

