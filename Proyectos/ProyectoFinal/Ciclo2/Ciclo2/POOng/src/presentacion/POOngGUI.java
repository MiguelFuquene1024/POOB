package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class POOngGUI extends JFrame {
	private static final long serialVersionUID = 8171225485810270940L;
	private static final int ANCHO = 683;
	private static final int ALTO = 384;

	private PanelFondo fondo;
	
	private static String inicio="INICIO";
	private static String configuracion ="CONFIG";
	private static String twoPlayer ="twoPlayer";
	private static String instruction ="INSTRUCTION";

	/*Ventanas*/
	private JPanel instru;
	private JPanel ventanas, panelBotones;
	private VentanaConfiguracion ventanConfig;
	private JPanel ventanTwoPlayer;
	
	/*Botones*/
	private JButton empezar,instrucciones ,volver;
	
	/*Menu Bar*/
	private JMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem importar, abrir;
	
	private JFileChooser fileChooser;
	
	
	
	
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
		this.setIconImage(new ImageIcon("resources/icon.png").getImage());
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		EventoTeclado p = new EventoTeclado();
		this.addKeyListener( p );
		fileChooser = new JFileChooser();
		prepareVentanas();
	    prepareMenuBar();
		
		
		
	}
	
	public void prepareVentanas(){
		ventanas = new JPanel();
		ventanas.setLayout( new CardLayout( 0, 0) );
		instru =new PanelFondo("resources/fondoInstrucciones.png");
		instru.setLayout( new FlowLayout());
		volver = new JButton("Back");
		instru.add(volver);
		
		
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
		ventanas.add( instru,instruction);
		
		this.add(ventanas);
	
	}
	
	public void prepareMenuBar(){
		menuBar = new JMenuBar();
		archivo = new JMenu("Archivo");
		importar = new JMenuItem("Importar");
		abrir = new JMenuItem("abrir");
		
		archivo.add(importar);
		archivo.add(abrir);
		
		menuBar.add(archivo);
		
		this.setJMenuBar(menuBar);
	}
	
	
	
	
	public void prepareAcciones(){
		empezar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionCambiarAJugadores();
			}
		});
		
		instrucciones.addActionListener( new ActionListener(){	
			public void actionPerformed(ActionEvent event ){
				accionCambiarInstrucciones();
			}
		});
		
		volver.addActionListener( new ActionListener(){	
			public void actionPerformed(ActionEvent event ){
				accionCambiarAinicio();
			}
		});
		
		prepareAccionesCongif();
		prepareAccionesIO();
	}
	
	public void prepareAccionesCongif(){
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
	
	
	public void prepareAccionesIO(){
		abrir.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionAbrir();
			}
		});
		
		
	}
	
	public void accionAbrir(){
		fileChooser.setVisible(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Extension archivo .dat","dat"));
		int answ = fileChooser.showOpenDialog(this);
		if( answ == fileChooser.APPROVE_OPTION){
			ventanConfig.abrir(fileChooser.getSelectedFile());
		}
		
		
		
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
	
	public void accionCambiarInstrucciones(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, instruction);
		
	}
	
	
	
	public static void main(String[] args){
		POOngGUI gui = new POOngGUI();
		gui.setVisible(true);
	}

}

