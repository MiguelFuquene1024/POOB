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
 * @version 2.0 12/05/2020
 * */

public class VentanaConfiguracion extends PanelFondo{
	private static final long serialVersionUID = -2942077290252968122L;
	private static int ANCHO= 683;
	private static int ALTO=384;
	private POOng game;
	private transient Image temporal;
	/*Ventana Jugadores*/
	public JPanel panelBotones, panelVolverJugar,panelVolverJugar1,panelVolverJugar3;
	public PantallaJuego pantallaJ ;
	public JButton unJug, dosJug ,cpuVsCpu,volverIni; 
	public JButton volverC1,guardarConfi1,jugar1;
	public JButton volverC2,guardarConfi2,jugar2;
	public JButton volverC3,guardarConfi3,jugar3;
	/*Componentes compartidos por Paneles*/
	private JComboBox comboBoxPersonaje1;
	private JComboBox comboBoxPersonaje2;
	
	private JComboBox comboBoxCp11;
	private JComboBox comboBoxCp12;
	
	private JComboBox comboBoxCp31;
	private JComboBox comboBoxCp32;
	
	private JComboBox opcionesPelotas;
	private JComboBox opcionesPelotas1;
	private JComboBox opcionesPelotas3;

	private JLabel textoNombre1,textoNombre2,texto1,texto2,textoPelota;
	private JLabel jugador1,jugador2;
	private JTextField nombre1,nombre2,nomOp11,nomOp12,nomOp31,nomOp32;	
	private final double[] xMax = new double[] {130,530};
	private String[] categorias = new String[] {"SuperHeroe","Terror","Videojuego"};
	private String[] pelotas = new String[] {"lenta","rapida","incremental"};
	private String[] cpus = new String[] {"Extreme","Sniper","Greedy","Lazy",};
	private String[][] rutasPersonajes = new String[][]{{"resources/gokuUp.png","resources/gokuD.png"},{"resources/jason 2.png","resources/jasonEspalda.PNG"},{"resources/marioUp.PNG","resources/marioDo.PNG"}};
	
	/*Ventanas */
	private JPanel onePlayer;
	private JPanel twoPlayer;
	private JPanel cpuPlayer;
	
	
	
	/*Seleccion de personaje */
	
	public VentanaConfiguracion( String ruta){
		super(ruta);
		prepareElementos();
		prepareAcciones();
	}
	
	public void prepareElementos(){
		this.setLayout(null );
		prepareModoJugadores();
		
		prepareOnePlayer();
		prepareTwoPlayer();
		prepareCpu();
		
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
		
		jugar1 = new JButton("Play");
		guardarConfi1 = new JButton("Save");
		volverC1 = new JButton("Back");
		
		jugar2 = new JButton("Play");
		guardarConfi2 = new JButton("Save");
		volverC2 = new JButton("Back");
		
		jugar3 = new JButton("Play");
		guardarConfi3 = new JButton("Save");
		volverC3 = new JButton("Back");
		
		
		this.add(panelBotones);
		

	}
	
	public void crearPanelVolverJugar(JPanel p,JPanel parent,JButton b1,JButton b2,JButton b3){
		p.setLayout( new FlowLayout());
		p.setBounds(23*ANCHO/64, ALTO+200, 200, 50);
		p.setOpaque(false);
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		parent.add(p);
	}
	
	public void plantilla(JPanel panel,JComboBox c1,JComboBox c2,JComboBox c3,JTextField n1,JTextField n2,String text1,String text2 ){
		jugador1= new JLabel("Player1");
		jugador2= new JLabel("Player2");
		textoNombre1 = new JLabel("Nombre:");
		textoNombre2 = new JLabel("Nombre:");
		texto1= new JLabel(text1);
		texto2= new JLabel(text2);	
		textoPelota= new JLabel("Pelota"); 
		
		panel.setBackground( new Color (76,175,80) );
		panel.setLayout(null);
		

		textoNombre1.setBounds(25,5*ALTO/8  ,100,20); 
		textoNombre1.setForeground(Color.WHITE);
		n1.setBounds(25+70,5*ALTO/8  ,100,20);
		
		textoNombre2.setBounds(80+9*ANCHO/16,5*ALTO/8 ,100,20);
		textoNombre2.setForeground(Color.WHITE);
		n2.setBounds(150+9*ANCHO/16,5*ALTO/8  ,100,20);
		
		texto1.setForeground(Color.WHITE);
		texto2.setForeground(Color.WHITE);
				
		textoPelota.setForeground(Color.WHITE);
		textoPelota.setBounds(3*ANCHO/8, 3*ALTO/4,400,20);
	
		jugador1 = setImage(jugador1,"resources/playerOne.png",25,ALTO/4);
		jugador2 = setImage(jugador2,"resources/playerTwo.png",9*ANCHO/16,ALTO/4);
		
		
		texto1.setBounds(0+25, 3*ALTO/4,100,20);
		c1.setBounds(25+70,3*ALTO/4  ,100,20);
		
		
		texto2.setBounds(150+29*ANCHO/64, 3*ALTO/4,100,20);
		c2.setBounds(150+9*ANCHO/16,3*ALTO/4,100,20);	
		
		c3.setBounds(7*ANCHO/16, 3*ALTO/4,100,20);
		
		panel.add(n1);
		panel.add(n2);
		panel.add(c3);
		panel.add(textoPelota);
		panel.add(jugador1);
		panel.add(texto1);
		panel.add(jugador2);
		panel.add(texto2);
		panel.add(c1);
		panel.add(c2);
		panel.add(textoNombre1);
		panel.add(textoNombre2);	
	}
	
	public void prepareOnePlayer( ){
		onePlayer = new PanelFondo("resources/fondoJugadores.png");
		nomOp11 = new JTextField("Player1");
		nomOp12 = new JTextField("Player2");
		
		comboBoxCp11 = new JComboBox(categorias);
		comboBoxCp12 = new JComboBox(cpus);
		opcionesPelotas1 = new JComboBox(pelotas);
		panelVolverJugar1 = new JPanel();
		crearPanelVolverJugar(panelVolverJugar1,onePlayer,volverC1,guardarConfi1,jugar1);
		plantilla(onePlayer,comboBoxCp11 ,comboBoxCp12,opcionesPelotas1,nomOp11,nomOp12,"Personajes:","Cpu:");
	}
	
	public void prepareTwoPlayer(){ 
		twoPlayer = new PanelFondo("resources/fondoJugadores.png");
		nombre1 = new JTextField("Player1");
		nombre2 = new JTextField("Player2");
		
		comboBoxPersonaje1 = new JComboBox(categorias);
		comboBoxPersonaje2 = new JComboBox(categorias);
		opcionesPelotas = new JComboBox(pelotas);
		
		panelVolverJugar = new JPanel();
		crearPanelVolverJugar(panelVolverJugar, twoPlayer,volverC2,guardarConfi2,jugar2);
		plantilla(twoPlayer,comboBoxPersonaje1 ,comboBoxPersonaje2,opcionesPelotas ,nombre1,nombre2,"Personajes:","Personajes:");
	}
	public void prepareCpu(){
		cpuPlayer = new PanelFondo("resources/fondoJugadores.png");
		nomOp31 = new JTextField("Player1");
		nomOp32 = new JTextField("Player2");
		
		comboBoxCp31 = new JComboBox(cpus);
		comboBoxCp32 = new JComboBox(cpus);
		opcionesPelotas3 = new JComboBox(pelotas);
		
		panelVolverJugar3 = new JPanel();
		crearPanelVolverJugar(panelVolverJugar3, cpuPlayer,volverC3,guardarConfi3,jugar3);
		plantilla(cpuPlayer,comboBoxCp31 ,comboBoxCp32,opcionesPelotas3 ,nomOp31,nomOp32,"Cpu:","Cpu:");
		
	}

	
	public JLabel setImage( JLabel b ,String rute,int xPos, int yPos ){
		ImageIcon icono = new ImageIcon(rute);
		temporal = icono.getImage().getScaledInstance(300,100, Image.SCALE_SMOOTH);
		b.setIcon(new ImageIcon(temporal));
		b.setBounds(xPos, yPos, 300, 100);
		return b;
		
	}
	
	public JPanel getOnePlayer(){
		return onePlayer;
	}
	
	
	public  JPanel getTwoPlayer(){
		return twoPlayer;
	}
	
	public JPanel getCpu(){
		return cpuPlayer;
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
		
		/**opcion1*/
		jugar1.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
					accionCambiarAJuego();
					
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		
		guardarConfi1.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		/**opcion 2*/
		jugar2.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
					accionCambiarAJuego();
					
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		
		guardarConfi2.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		
		
		/**opcion 3*/
		jugar3.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
					accionCambiarAJuego();
					
				}
				catch( Exception e){
					JOptionPane.showMessageDialog( null,e.getMessage(),
			                "Error:", JOptionPane.ERROR_MESSAGE );
				}
				
			}
		});
		
		guardarConfi3.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				try{
					accionGuardarConfig2();
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
	
	public void  accionGuardarConfig2() throws PoongException{
		game = new POOng();
		int answ1 = comboBoxPersonaje1.getSelectedIndex();
		int answ2 = comboBoxPersonaje2.getSelectedIndex();
		if ( answ1 == answ2 ){
			throw new PoongException(PoongException.PERSONAJES_IGUALES );			
		}
		game.addJugador(new Jugador(nombre1.getText(), new Personaje(200,70,xMax) ));
		game.addJugador(new Jugador(nombre2.getText(), new Personaje(200,450,xMax)));
		game.establezcaPersonajes();
		for ( int x = 0; x < game.getAmountPersonajes();x++){
			int i = ( x == 0)?answ1 : answ2;
			int j = ( x == 0)?0 : 1;
			game.setImagePersonaje(x,rutasPersonajes[i][j]);
		}
		game.addPelota(getPelota(opcionesPelotas.getSelectedIndex()));
	}
	
	public Pelota getPelota(int index){
		PelotaNormal p = null;
		switch(index){ 
			case 0:
				p = new PelotaNormal( 200,200,0.2);
				break;
			case 1:
				p = new PelotaNormal( 200,200,0.5);
				break;
			default :
				PelotaIncremental pe =new PelotaIncremental( 200,200,0.2);
				return pe;
		}
		return p;
	}
	
}






