package presentacion; 
 
// en awt se encuentran los Grid y border layout

import aplicacion.*;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Toolkit;

/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 */
public class MarbelGameGUI  extends JFrame{
	
	/*JFrame MarbelGame*/
    private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ALTO = DIMENSION.height;
    private static final int ANCHO = DIMENSION.width;
	private static final String INICIO ="inicio";
	private static final String CONFIG = "Configuracion";
	
	/*JMenuBar Archivo*/
	private JMenuBar menuBar;
	
	private JMenu archivo;
	
	private JMenuItem nuevo;
	private JMenuItem abrir;
	private JMenuItem salvar;
	private JMenuItem salvarComo;
	private JMenuItem salir;
	/*JMenuBar Configuracion*/
	private JMenu configuracionColor;
	private JMenuItem botonColorTablero, botonColorBarrera;
	
	/**JFileChooser Abrir/Guardar)*/
	private JFileChooser fileChooser;
	/**JColorChooser para seleccionar los colores*/
	private JColorChooser colorChooser;

	/*Elementos ventanaPrincipal*/
	private JPanel	panelBoton, ventanas,inicio;
	private VentanaConfig configuracion;
	private JButton botonEmpezar;
	
	/*Variables para La variable marbelGame*/
	private Color colorCasilla = Color.WHITE, colorBarrera = Color.BLACK;
	private int numeroBarreras= 1,numeroHuecos = 3, size = 4;
	
	private PantallaJuego pantallaJuego;
	private MarbelGame juego ;
	
	
    private MarbelGameGUI(){
		juego = null;
        prepareElementos();
		prepareAcciones();
    }

	/*
	 *Metodo que prepara todos los componentes del Jframe  ( MarbelGameGUI )
	 */
    private void prepareElementos(){
        this.setTitle("Marbel Game");
		this.setSize( ANCHO/2,ALTO/2);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
				
		ventanas = new JPanel();
		ventanas.setLayout( new CardLayout (0,0));
		setDefaultCloseOperation ( DO_NOTHING_ON_CLOSE  );

		prepareElementosMenu();
		prepareElementosChooser();
		prepareElementosConfiguracion();
    }
	
	/**
	  *Metodo que prepara los elementos de los cuales son  posible hacer una configuracion en el menu 
	  */
	private void prepareElementosMenu(){	
		menuBar = new JMenuBar();
		
		/**Elementos del JMenuBar */
		archivo= new JMenu("Archivo");
		configuracionColor = new JMenu("Colores");
		
		/**Añadieno elementos al JMenu Archivo*/
		nuevo= new JMenuItem("Nuevo");
		abrir= new JMenuItem("Abrir");
		salir= new JMenuItem("Salir");
		
		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(salir);
		
		/**Añadieno elementos al JMenu Configuracion Color*/
		botonColorTablero = new JMenuItem("Color Tablero");
		botonColorBarrera = new JMenuItem("Color Barrera");
		
		configuracionColor.add(botonColorTablero);
		configuracionColor.add(botonColorBarrera);
		
		/**Anadiendo elementos al JMenuBar*/
		menuBar.add(archivo);
		menuBar.add(configuracionColor);
	
		setJMenuBar(menuBar);
	}
	
	/**
	  *Crea los Jframe ya predeterminados JColorChooser y JFileChooser
	 */
	private void prepareElementosChooser(){
		colorChooser = new JColorChooser();
		fileChooser = new JFileChooser();
	}
	
	/** 
	  *Prepara todos los elementos que realizar una modificacion al programa
	*/
	private void prepareElementosConfiguracion(){
		/*Contenedor de botones con GridLayout
		GRIDLAYOUT: filas, columnas, espacioLibre filas,espacioLibre columnas
		BODERLAYOUT:*/
		
		inicio = new PanelFondo("imagenes/fondoInicio.png");
		configuracion = new VentanaConfig("imagenes/fondoConfig.png");
		
		inicio.setLayout( null);
		
		panelBoton = new JPanel();
		panelBoton.setLayout( new GridLayout(1,1) );
		
		//Agregando el boton a el panel panel boton
		botonEmpezar = new JButton("Start");
		establecerIcono( botonEmpezar,"imagenes/start.png");
		
		panelBoton.add( botonEmpezar );
		panelBoton.setSize(ANCHO/8,ALTO/8);
		panelBoton.setLocation((ANCHO/5)-(ANCHO/64),19*(ALTO/64));
		
		inicio.add( panelBoton);
	
		ventanas.add(inicio,INICIO);
		ventanas.add(configuracion,CONFIG);
		
		this.add( ventanas);

	}
	
	private void prepareAcciones(){

		/*Cerrar ventana del JFrame*/
		this.addWindowListener( new WindowAdapter(){
			public void windowClosing( WindowEvent event){
				accionCerrar();
			}
		});
		/*Cerrar ventana en el  Menu  Configuracion del menuItem salir*/
		salir.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event ){
				accionCerrar();
			}
		});
		
		/*Abrir un archivo*/
		abrir.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent event ){
				accionAbrir();
			}
		});
			
		/*abrir la ventana de juego*/
		botonEmpezar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAconfiguracion();
			}
		});
		
		
		/*volver al menu inicio desde la ventana configuracion*/
		configuracion.volver.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAInicio();
			}
		});
		
		/*guardaLa configuracion ingresada por input en los JTextField*/
		configuracion.guardar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionGuardarConfig();
			}
		});
		
		
		/*abre un nuevoFrame el cual pertenece a la pantalla de juego */
		configuracion.play.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionAbrirPantallaJuego();
			}
		});
		
		/*para seleccionar el color de las casillas */
		botonColorTablero.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionSeleccionarColor( Color.WHITE, false,"Seleccione un color para el tablero");
			}
		});
		
		/*para seleccionar el color de las barreras */
		botonColorBarrera.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionSeleccionarColor( Color.BLACK, true,"Seleccione un color para la barrera");
			}
		});
		
		
	
		
	}

	/**
	   *Metodo encargado de cerrar la aplicacion
	   */
	public void accionCerrar(){
		int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de salir?");

		if (resp == JOptionPane.YES_OPTION){
            System.exit(0);
        }
	}
	
	/**
	   *Metodo encargado de abrir la aplicacion
	   */
	public void accionAbrir(){
		fileChooser.setVisible(true);
		int accion = fileChooser.showOpenDialog(abrir);
		if( accion == fileChooser.APPROVE_OPTION ){
			File archivo = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(abrir,"El archivo de nombre "+archivo.getName()+" que se trata de abrir en la ruta "+archivo+"\n NO se pudo abrir ya que esta la funcion se encuentra en construccion.");
			
		}
		
		fileChooser.setVisible(false);
	}
	
	
	/**
	   *Metodo encargado de cerrar la aplicacion
	   */
	public void accionSeleccionarColor( Color defaultColor, boolean barrera,String titulo){
		colorChooser.setVisible(true);
		Color color = JColorChooser.showDialog(null, titulo , defaultColor);
		
		if ( color!= null ){
			if( barrera ){
				colorBarrera = color;
			}
			else{
				colorCasilla = color;
			}
		}
		
		if ( colorCasilla.equals( colorBarrera ) ){
			colorCasilla = Color.WHITE;
			colorBarrera = Color.BLACK;
			//error
		}
		colorChooser.setVisible(false);
	}
	
	/**
	   *Metodo el cual actualiza la configuracion predeterminada del juego
	   */
	public void accionCambiarAconfiguracion(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, CONFIG);
	}
	
	
	/**
	   *Metodo el cual vuelve a la ventana de inicio
	   */
	public void accionCambiarAInicio(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, INICIO);
	}
	
	/**
	   *Metodo el cual abre la pantalla del juego
	   */
	public void accionAbrirPantallaJuego(){
		juego = new MarbelGame( size , colorCasilla );
		juego.setElementosAleatorios(numeroBarreras,numeroHuecos, colorBarrera);
		pantallaJuego = new PantallaJuego( juego );
		pantallaJuego.setVisible(true);
	}
	
	/**
	   *Metodo encargado de guardar 
	   */
	public void accionGuardarConfig(){
		try {
			size=Integer.parseInt(configuracion.tamanoTablero.getText());
			numeroHuecos=Integer.parseInt(configuracion.numeroHuecos.getText());
			numeroBarreras=Integer.parseInt(configuracion.numeroBarreras.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Ingresar valores numericos" ,"Error" , JOptionPane.WARNING_MESSAGE);
			colorCasilla = Color.WHITE;
			colorBarrera = Color.BLACK;
		}
		
		
	}
	
	/**
	   *Metodo encargado de colocar imagenes a los componentes  graficos
	   *@param componente, componente grafico el cual se le coloca la imagen
	   *@param rutaImagen, String la ruta en la cual se encuentra la ruta
	   */
	public void establecerIcono(JComponent componente, String rutaImagen ){
		try{
			MarbelGameIcono fondo = new MarbelGameIcono(ImageIO.read(new File(rutaImagen)));
			componente.setBorder(fondo);
		}
		catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	
	}
	
	
	public static void main(String args[]){
		MarbelGameGUI gui =new  MarbelGameGUI();
		gui.setVisible(true);
	}    
}
