package presentacion;


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
public class PantallaJuego extends JFrame{
	/*JFrame PantallaJuego*/
    private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ALTO = DIMENSION.height;
    private static final int ANCHO = DIMENSION.width;
	private JFileChooser fileChooser;
	
	private  PanelTablero panelTablero;
	private MarbelGame tablero;
	private PanelFondo fondo;
	
	/*JMenuBar Guardar*/
	private JMenuBar menuBar;
	private JMenu menuSalvar, configuracion;
	private JMenuItem salvar;
	private JMenuItem salvarComo;
	private JMenuItem  reset;
	
	
	/*Panel texto*/
	private JPanel panelTexto;
	private JLabel movements, badMovements,goodMovements;
	
	/*Botones Juego*/
	private JPanel panelBotones;
	
	private JButton botonNorte,botonSur,botonEste,botonOeste;
	
	public PantallaJuego( MarbelGame t ){
		tablero = t; 
		prepareElementos();	
		prepareAcciones();
	}
	
	/*
	 *Metodo que prepara todos los componentes del Jframe  (PantallaJuego )
	 */
	public void prepareElementos(){
		fileChooser = new JFileChooser();
		fondo = new PanelFondo("imagenes/fondoJuego.jpg");
		panelTablero = new PanelTablero( tablero );
		
		
		fondo.setLayout(null);
		fondo.add( panelTablero );
		panelTablero.setBounds(3*ANCHO/16,0,40*tablero.getSize(),40*tablero.getSize());
		panelTablero.repaint();
		this.setTitle("Juego");
		this.setSize( ANCHO/2,ALTO/2);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setLayout( new BorderLayout() );
		this.add( fondo, BorderLayout.CENTER);
		
		prepareElementosGuardar();
		prepareElementosBotones();
		prepareElementosTexto();
	}		
	
	
	/**
	  *Prepara los Menus necesarios  con opciones que puede realizar el usuario, (Salvar,SalvarComo,Configuracion Inicial)
	*/
	public void prepareElementosGuardar(){
		menuBar = new JMenuBar();
		
		menuSalvar = new JMenu("Opciones");
		configuracion = new JMenu("Configuracion");
		
		salvar= new JMenuItem("Salvar");
		salvarComo= new JMenuItem("Salvar Como");
		
		reset= new JMenuItem("Configuracion Inicial");
		
		menuSalvar.add(salvar);
		menuSalvar.add(salvarComo);
		configuracion.add(reset);
		
		menuBar.add( menuSalvar);
		menuBar.add( configuracion);
		
		this.setJMenuBar( menuBar );
		
		
	}
	
	/**
	  *Prepara los  botones con los que el usuario va a interactuar en el programa
	*/
	public void prepareElementosBotones(){
		panelBotones = new JPanel();
		panelBotones.setLayout( new GridLayout(2,2));
		
		botonNorte = new JButton("Arriba");
		botonNorte.setForeground(Color.WHITE);
		botonNorte.setBackground(new Color(87,3,3));
		
		botonSur = new JButton("Abajo");
		botonSur.setForeground(Color.WHITE);
		botonSur.setBackground(new Color(87,3,3));
		
		botonOeste = new JButton("Izquierda");
		botonOeste.setForeground(Color.WHITE);
		botonOeste.setBackground(new Color(87,3,3));
		
		botonEste = new JButton("Derecha");
		botonEste.setForeground(Color.WHITE);
		botonEste.setBackground(new Color(87,3,3));
		
		panelBotones.add( botonNorte);
		panelBotones.add( botonSur);
		panelBotones.add( botonOeste);
		panelBotones.add( botonEste);
		
		this.add( panelBotones,BorderLayout.SOUTH);
	}
	
	/**
	  * prepara los indicadores de avance del juego ( numero de movimientos, numero de malos movimientos , numero de buenos movimientos )
	*/
	public void prepareElementosTexto(){
		panelTexto = new JPanel();
		panelTexto.setBackground(Color.BLACK);
		panelTexto.setBounds(3*ANCHO/16,195,150,80);
		panelTexto.setLayout( new FlowLayout() );
		
		movements= new JLabel("Numero Movimientos: "+""+0); 
		movements.setForeground(Color.WHITE);
		
		badMovements= new JLabel("Canicas Mal ubicadas: "+""+0); 
		badMovements.setForeground( Color.WHITE);
		
		goodMovements=new JLabel("Canicas Bien ubicadas: "+""+0); 
		goodMovements.setForeground( Color.WHITE);
		
		panelTexto.add(movements);
		panelTexto.add(badMovements);
		panelTexto.add(goodMovements);
		
		fondo.add(panelTexto);
	}

	
	
	public void prepareAcciones(){
		
		/*Cerrar ventana del JFrame*/
		this.addWindowListener( new WindowAdapter(){
			public void windowClosing( WindowEvent event){
				accionCerrar();
			}
		});
		
		/*salvarComo un archivo*/
		salvarComo.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionSalvar();
			}
		});
		
		/*Salvar un archivo*/
		salvar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionSalvar();
			}
		});
		
		/*Desliza el tablero hacia arriba*/
		botonNorte.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionMoverNorte();
			}
		});
		
		/*Desliza el tablero hacia abajo*/
		botonSur.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionMoverSur();
			}
		});
		
		/*Desliza el tablero hacia la derecha*/
		botonEste.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionMoverEste();
			}
		});
		
		/*Desliza el tablero hacia hacia la izquierda*/
		botonOeste.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionMoverOeste();
			}
		});
		
		/*Reinicia a la configuracino inicial del programa */
		reset.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionResetear();
			}
		});
		
	}
	
	/**
	  *metodo encargado de mover todos los elementos del tablero  hacia arriba
	*/
	public void accionMoverNorte(){
		tablero.deslizarNorte();
		refresque();
		
	}
	
	
	/**
	  *metodo encargado de mover todos los elementos del tablero  hacia abajo
	*/
	public void accionMoverSur(){
		tablero.deslizarSur();
		refresque();
		
	}
	
	/**
	  *metodo encargado de mover todos los elementos del tablero  hacia abajo
	*/
	public void accionMoverEste(){
		tablero.deslizarEste();
		refresque();
		
	}
	
	/**
	  *metodo encargado de mover todos los elementos del tablero hacia la izquierda
	*/
	public void accionMoverOeste(){
		tablero.deslizarOeste();
		refresque();
		
	}
	
	/**
	  *metodo encargado de mover todos los elementos del tablero hacia la derecha
	*/
	public void accionResetear(){
		tablero.setTableroInicial();
		refresque();
	}
	
	
	/**
	  *metodo encargado de guardar el juego actual
	*/
	public void accionSalvar(){
		fileChooser.setVisible(true);
		int accion = fileChooser.showSaveDialog(salvar);
		if ( JFileChooser.APPROVE_OPTION == accion ){
			File archivo = fileChooser.getSelectedFile();
			
			JOptionPane.showMessageDialog(salvar,"El archivo de nombre "+archivo.getName()+" que se trata de guardar en la ruta "+archivo+"\n NO se pudo almacenar ya que esta la funcion se encuentra en construccion.");
		}
		fileChooser.setVisible(false);
	}
	
	/**
	  *metodo encargado de cerrar el frame
	*/
	public void accionCerrar(){
		int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de salir?");

		if (resp == JOptionPane.YES_OPTION){
			setDefaultCloseOperation( HIDE_ON_CLOSE  );
        }
		else{
			setDefaultCloseOperation ( DO_NOTHING_ON_CLOSE  );
		}
	}
	
	
	/**
	 *metodo encargado de actualizar todas las acciones que haga el programa
	*/
	public void refresque(){
		int[] m = tablero.getMovements(); 
		
		movements.setText("Numero Movimientos: "+""+m[0]); 
		badMovements.setText("Canicas Mal ubicadas: "+""+m[1]); 
		goodMovements.setText("Canicas Bien ubicadas: "+""+m[2]); 

		panelTablero.repaint();
	}
	
	

public static void main (String [ ] args) {
	Elemento[][] t = new Elemento[][]{{null,(Elemento) new Pelota(0,1,Color.ORANGE),null,null},{(Elemento) new Pelota(1,0,Color.BLUE),(Elemento) new Barrera(1,1,Color.BLACK),null,null},{(Elemento) new Hueco(2,0,Color.BLUE),null,null,(Elemento) new Hueco(2,3,Color.ORANGE)},{null,(Elemento) new Hueco(3,1,Color.GREEN),(Elemento) new Pelota(3,2,Color.GREEN),null}};
	
	MarbelGame m = new MarbelGame( t );
	
	
	PantallaJuego p = new PantallaJuego( m );
	p.setVisible(true);
    }

	
}
