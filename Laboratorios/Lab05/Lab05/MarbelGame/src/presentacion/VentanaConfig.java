package presentacion;

import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 */
public class VentanaConfig extends PanelFondo{
	
	private JPanel botones;
	public JButton play,volver,guardar;
	
	public  JTextField tamanoTablero,  numeroHuecos, numeroBarreras;
	
	/**
	 * Constructor, constructor de la clase que extiende la Clase PanelFondo
	 *@param ruta, String que representa la ruta en la que se encuentra la imagen a poner de fondo
	*/
	public VentanaConfig( String ruta ){
		super( ruta );
		
		prepareElementos();
	}
	
	/*
	 *Metodo que prepara todos los componentes del JPanel
	 */
	public void prepareElementos(){
		tamanoTablero = new JTextField();
        numeroHuecos = new JTextField();
        numeroBarreras = new JTextField();
		
		preparePanelBotones();
		prepareComboBox();
	}
	
	/*
	 *Prepara los botones que tiene este panel
	 */
	public void preparePanelBotones(){
		this.setLayout( new BorderLayout() );
		
		botones = new JPanel();
		botones.setBackground(Color.BLACK);
		botones.setLayout( new FlowLayout() );
		
		play = new JButton("Jugar");
		play.setForeground(Color.WHITE);
		play.setBackground(new Color(87,3,3));
		
		
		guardar = new JButton("Guardar");
		guardar.setForeground(Color.WHITE);
		guardar.setBackground(new Color(87,3,3));
		
		volver = new JButton("Volver");
		volver.setForeground(Color.WHITE);
		volver.setBackground(new Color(87,3,3));
		
		botones.add(volver);
		botones.add(guardar);
		botones.add(play);
	
		this.add(botones, BorderLayout.SOUTH);
	}
	
	
	/*
	 *Prepara los JTextField los input's con el cual el usuario interactuara 
	 */
	public void prepareComboBox(){
		Box boxTamanoTablero = Box.createHorizontalBox();
		
		JLabel tamano = new JLabel("Tamano Tablero", JLabel.LEFT);
		tamano.setForeground( Color.WHITE);
		
        boxTamanoTablero.add(tamano);
        boxTamanoTablero.add(Box.createGlue());
		Box tamanoOriginal = Box.createVerticalBox();
        tamanoOriginal.add(boxTamanoTablero);
        tamanoOriginal.add(tamanoTablero);
		
		
		Box boxNumeroHuecos = Box.createHorizontalBox();
		
		JLabel numeroH = new JLabel("Numero Huecos", JLabel.LEFT);
		numeroH.setForeground(new Color(255,255,255 ));
		
        boxNumeroHuecos.add(numeroH);
        boxNumeroHuecos.add(Box.createGlue());
		
		Box numHuecoOriginal = Box.createVerticalBox();
        numHuecoOriginal.add(boxNumeroHuecos);
        numHuecoOriginal.add(numeroHuecos);
		
		
		Box boxNumeroBarreras = Box.createHorizontalBox();
		
		JLabel numeroB = new JLabel("Numero Barreras", JLabel.LEFT);
		numeroB.setForeground(new Color(255,255,255 ));
		
        boxNumeroBarreras.add(numeroB);
        boxNumeroBarreras.add(Box.createGlue());
		Box numBarrerasOriginal = Box.createVerticalBox();
        numBarrerasOriginal.add(boxNumeroBarreras);
        numBarrerasOriginal.add(numeroBarreras);
		
		
		Box singleLineFields = Box.createVerticalBox();
        singleLineFields.add(tamanoOriginal);
		singleLineFields.add(numHuecoOriginal);
		singleLineFields.add(numBarrerasOriginal);
	
		this.add(singleLineFields, BorderLayout.NORTH);
	}
	

	
	
	
}