package presentacion;

import aplicacion.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


/**
 *Panel en el  cual se puede establecer un fondo dado una imagen especifica
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 24/04/2020
 * */

public class VentanaConfiguracion extends PanelFondo{
	private static final long serialVersionUID = -9061270043019360839L;
	private static int ANCHO= 683;
	private static int ALTO=384;

	
	private POOng game;
	private String[][] rutasPersonajes = new String[][]{{"resources/gokuUp.png","resources/gokuD.png"},{"resources/jason 2.png","resources/jasonEspalda.PNG"},{"resources/marioUp.PNG","resources/marioDo.PNG"}}; 
	
	/*Ventana Jugadores*/
	public JPanel panelBotones, panelVolverJugar;
	public PantallaJuego pantallaJ ;
	public JButton unJug, dosJug ,cpuVsCpu,volverIni,volverConfi,guardarConfi,jugar; 
	
	/*Componentes compartidos por Paneles*/
	public JComboBox comboBoxPersonaje1,comboBoxPersonaje2,comboBoxCp1,comboBoxCp2,opcionesPelotas;
	public String[] categorias = new String[] {"SuperHeroe","Terror","Videojuego"};
	public String[] pelotas = new String[] {"lenta","rapida","incremental"};
	public JLabel textoNombre1,textoNombre2,texto1,texto2,textoPelota;
	public JLabel jugador1,jugador2;
	private JTextField nombre1,nombre2;
	
	private final double[] xMax = new double[] {130,530};
	
	/*Ventana two Player*/
	public JPanel twoPlayer;
	
	
	
	/*Seleccion de personaje */
	
	public VentanaConfiguracion( String ruta){
		super(ruta);
		prepareElementos();
		prepareAcciones();
	}
	
	public void prepareElementos(){
		this.setLayout(null );
		prepareModoJugadores();
		prepareTwoPlayer();
	}
	public void prepareModoJugadores(){
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setOpaque(false);
		panelBotones.setLocation(7*(ANCHO/16),400);
		panelBotones.setSize(100,300);
		
		unJug = new JButton("One Player");
		dosJug = new JButton("Two Players");
		cpuVsCpu =  new JButton("CPY VS CPU");
		volverIni = new JButton("Back");

		panelBotones.add( unJug );
		panelBotones.add( dosJug );
		panelBotones.add( cpuVsCpu );
		panelBotones.add( volverIni );
		
		panelVolverJugar = new JPanel();
		panelVolverJugar.setLayout( new FlowLayout());

		panelVolverJugar.setBounds(23*ANCHO/64, ALTO+200, 200, 50);
		panelVolverJugar.setOpaque(false);
		
		jugar = new JButton("Play");
		guardarConfi = new JButton("Save");
		volverConfi = new JButton("Back");
		
		
		panelVolverJugar.add(volverConfi);
		panelVolverJugar.add(guardarConfi);
		panelVolverJugar.add(jugar);
		
		this.add(panelBotones);
		
		jugador1= new JLabel();
		jugador2= new JLabel();
		textoNombre1 = new JLabel("Nombre:");
		textoNombre2 = new JLabel("Nombre:");
		texto1= new JLabel("Personajes:");
		texto2= new JLabel("Personajes:");
		textoPelota= new JLabel("Pelota");


	}
	
	public void prepareTwoPlayer(){ 
		nombre1 = new JTextField("Player1");
		nombre2 = new JTextField("Player2");
		
		twoPlayer = new PanelFondo("resources/fondoJugadores.png");
		twoPlayer.setBackground( new Color (76,175,80) );
		twoPlayer.setLayout(null);
		
		textoNombre1.setBounds(25,5*ALTO/8  ,100,20); 
		textoNombre1.setForeground(Color.WHITE);
		nombre1.setBounds(25+70,5*ALTO/8  ,100,20);
		
		textoNombre2.setBounds(80+9*ANCHO/16,5*ALTO/8 ,100,20);
		textoNombre2.setForeground(Color.WHITE);
		nombre2.setBounds(150+9*ANCHO/16,5*ALTO/8  ,100,20);
		
		texto1.setForeground(Color.WHITE);
		texto2.setForeground(Color.WHITE);
				
		textoPelota.setForeground(Color.WHITE);
		textoPelota.setBounds(3*ANCHO/8, 3*ALTO/4,400,20);
	
		jugador1 = setImage(jugador1,"resources/playerOne.png",25,ALTO/4);
		jugador2 = setImage(jugador2,"resources/playerTwo.png",9*ANCHO/16,ALTO/4);
		
		comboBoxPersonaje1 = new JComboBox(categorias);
		texto1.setBounds(0+25, 3*ALTO/4,100,20);
		comboBoxPersonaje1.setBounds(25+70,3*ALTO/4  ,100,20);
		
		comboBoxPersonaje2 = new JComboBox(categorias);
		texto2.setBounds(150+29*ANCHO/64, 3*ALTO/4,100,20);
		comboBoxPersonaje2.setBounds(150+9*ANCHO/16,3*ALTO/4,100,20);	
		
		opcionesPelotas = new JComboBox(pelotas);
		opcionesPelotas.setBounds(7*ANCHO/16, 3*ALTO/4,100,20);
		
		twoPlayer.add(nombre1);
		twoPlayer.add(nombre2);
		twoPlayer.add(opcionesPelotas);
		twoPlayer.add(textoPelota);
		twoPlayer.add(jugador1);
		twoPlayer.add(texto1);
		twoPlayer.add(jugador2);
		twoPlayer.add(texto2);
		twoPlayer.add(comboBoxPersonaje1);
		twoPlayer.add(comboBoxPersonaje2);
		twoPlayer.add(panelVolverJugar);
		twoPlayer.add(textoNombre1);
		twoPlayer.add(textoNombre2);
	}
	
	public JLabel setImage( JLabel b ,String rute,int xPos, int yPos ){
		ImageIcon icono = new ImageIcon(rute);
		Image temporal = icono.getImage().getScaledInstance(300,100, Image.SCALE_SMOOTH);
		b.setIcon(new ImageIcon(temporal));
		b.setBounds(xPos, yPos, 300, 100);
		return b;
		
	}
	
	public  JPanel getTwoPlayer(){
		return twoPlayer;
	
	}
	

	
	public  void getPlayervsCPU(){
		
	}
	
	public  void getCPUvsCPU(){
		
	}
	
	public void abrir( File f ){
		try{
			game = new POOng();
			game = game.abrir(f);
			pantallaJ = new PantallaJuego(game);
			
		}
		catch( Exception e){
			JOptionPane.showMessageDialog( null,e.getMessage(),
	                "Error:", JOptionPane.ERROR_MESSAGE );
		}
		
	}
	
	public void prepareAcciones(){
		jugar.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig();
					accionCambiarAJuego();
					
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		
		guardarConfi.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig();
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
	}
	
	public void accionCambiarAJuego() throws PoongException{
		String input = JOptionPane.showInputDialog(this,"¿Cuantas rondas quiere jugar?",10);
		if( input != null ){
			try{
				game.setMaxScore(Integer.valueOf(input));
				pantallaJ = new PantallaJuego(game);
			}
			catch(NumberFormatException e ){
				throw new PoongException(PoongException.VALOR_NO_NUMERICO);
			}
		}
	}
	
	public void  accionGuardarConfig() throws PoongException{
		game = new POOng();
		int answ1 = comboBoxPersonaje1.getSelectedIndex();
		int answ2 = comboBoxPersonaje2.getSelectedIndex();
		if ( answ1 == answ2 ){
			throw new PoongException(PoongException.PERSONAJES_IGUALES );			
		}
		game.addJugador(new Jugador(nombre1.getText(), new Personaje(200,70,xMax) ));
		game.addJugador(new Jugador(nombre2.getText(), new Personaje(200,450,xMax)));
		game.establezcaPersonajes();
		for ( int x = 0; x < game.getPersonajes();x++){
			int i = ( x == 0)?answ1 : answ2;
			int j = ( x == 0)?0 : 1;
			game.setImagePersonaje(x,rutasPersonajes[i][j]);
		}
	}
}

