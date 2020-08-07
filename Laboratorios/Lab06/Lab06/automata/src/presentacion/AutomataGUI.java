package presentacion;
import aplicacion.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AutomataGUI extends JFrame{    

    private JButton botonReloj;
	private JButton botonIniciar;
	private JPanel panelBotones;
    private JLabel lFila;
    private JLabel lColumna;
    private JTextField tFila;
    private JTextField tColumna;
    private JPanel panelControl;
    private JPanel panelNueva;
    private JPanel panelBNueva;
    private JButton botonViva;
    private JButton botonLatente;
    
    private JFileChooser fileChooser;
    private FotoAutomata foto;
    private AutomataCelular automata=null,automataInicial=null;
	
    private JMenuBar menuBar;
    private JMenu archivo, configuracion;
    
    private JMenuItem salve ;
    private JMenuItem abra;
    private JMenuItem exporte;
    private JMenuItem importe;
	private JMenuItem reiniciar;
	private JMenuItem salir;
    
	private boolean iniciar;
    public AutomataGUI(AutomataCelular ac ) {
        super("Automata celular");
		iniciar = false;
        String sDirectorioTrabajo = System.getProperty("user.dir");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        automata=ac;
		automataInicial = new AutomataCelular();
		automataInicial.setAutomata( ac );
        fileChooser = new JFileChooser();
        foto=new FotoAutomata(automata);
        setSize(new Dimension( 600 , 600 )); 
		this.setLocationRelativeTo(null);
        prepareElementos();
        prepareAcciones();

    }

    private void prepareElementos() {
        setResizable(false);
		panelBotones = new JPanel();
		panelBotones.setLayout( new GridLayout(1,3,0,0));

        botonReloj=new JButton("Tic-tac");
		botonIniciar = new JButton("Iniciar");
		
		panelBotones.add(botonIniciar);
		panelBotones.add(botonReloj);
		
        foto.setVisible(false);
        getContentPane().setLayout(new BorderLayout());
		getContentPane().add(foto,BorderLayout.NORTH);
        getContentPane().add(panelBotones,BorderLayout.SOUTH);
		foto.repaint();
        prepareELementosMenu();
    }
    
    public void prepareELementosMenu(){
    	menuBar = new JMenuBar();
    	archivo = new JMenu("Archivo");
    	configuracion = new JMenu("Configuracion");
		
    	salve = new JMenuItem("Salvar") ;
        abra =  new JMenuItem("Abrir") ;
        exporte = new JMenuItem("Exportar");
        importe = new JMenuItem("Importar");
		reiniciar =  new JMenuItem("Reiniciar");
		salir = new JMenuItem("Salir");
    	
    	archivo.add(salve);
    	archivo.add(abra);
    	archivo.add(exporte);
    	archivo.add(importe);
		archivo.add(salir);
		
		configuracion.add(reiniciar);
		
    	
    	menuBar.add(archivo);
		menuBar.add(configuracion);
		setJMenuBar(menuBar);
    	
    	
    
    }

    private void prepareAcciones(){

        botonReloj.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    botonRelojAccion();
                }
            });
			
		botonIniciar.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e) {
	                	accionIniciar();
	                }
	    });
		prepareAccionesMenu();
	}
	
	public void prepareAccionesMenu(){
		
		salir.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e) {
	                	accionCerrar();
	                }
	    });
		
		salve.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						accionSalvar();
					}
					catch( AutomataException a){
						JOptionPane.showMessageDialog(null, a.getMessage(),"Error:",JOptionPane.ERROR_MESSAGE);
					}

                }
            });
			
			
		abra.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						accionAbrir();
					}
					catch( AutomataException a){
						JOptionPane.showMessageDialog(null, a.getMessage(),"Error:",JOptionPane.ERROR_MESSAGE);
					}

                }
            });
			
		exporte.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						accionExportar();
					}
					catch( AutomataException a){
						JOptionPane.showMessageDialog(null, a.getMessage(),"Error:",JOptionPane.ERROR_MESSAGE);
					}
                  
                }
            });
			
		importe.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						accionImportar();
					}
					catch( AutomataException a){
						JOptionPane.showMessageDialog(null, a.getMessage(),"Error:",JOptionPane.ERROR_MESSAGE);
					}
                }
            });
			
		reiniciar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try{
						accionReiniciar();
					}
					catch( AutomataException a){
						JOptionPane.showMessageDialog(null, a.getMessage(),"Error:",JOptionPane.ERROR_MESSAGE);
					}
                  
                }
            });
		
		
    }

    private void botonRelojAccion( ) {
        automata.ticTac(  );
        foto.repaint();
    }
		
	public void accionSalvar() throws AutomataException{
		fileChooser.setVisible(true );
		fileChooser.setFileFilter( new FileNameExtensionFilter("Extension archivo .dat","dat"));
		int answ = fileChooser.showSaveDialog(this);
		
		if( answ == fileChooser.APPROVE_OPTION){
			if ( iniciar ){
				automata.salvar(fileChooser.getSelectedFile()) ;
			}
			else{
				throw new AutomataException("Primero se debe iniciar la automata");
			}
		}
		
	}

	public void accionAbrir() throws AutomataException{
		fileChooser.setVisible(true );
		fileChooser.setFileFilter( new FileNameExtensionFilter("Extension archivo .dat","dat"));
		int answ = fileChooser.showOpenDialog(this);
		
		if( answ == fileChooser.APPROVE_OPTION){
			automata.setAutomata( automata.abrir(fileChooser.getSelectedFile()) );
			automataInicial.setAutomata( automata);
			if ( !iniciar ){accionIniciar();}
			foto.setAutomata( automata);
			foto.repaint();
		}
	}

	public void accionExportar() throws AutomataException{
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Exportar");
		fileChooser.setFileFilter( new FileNameExtensionFilter("Extension archivo.txt","txt"));
		
		int answ = fileChooser.showSaveDialog(this);
		if ( answ == fileChooser.APPROVE_OPTION ){
			automata.exportar( fileChooser.getSelectedFile());
		}
	}

	public void accionImportar() throws AutomataException{
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Importar");
		fileChooser.setFileFilter( new FileNameExtensionFilter("Extension archivo.txt","txt"));
		int answ = fileChooser.showOpenDialog( this);
		if ( fileChooser.APPROVE_OPTION == answ ){
			automata.setAutomata( automata.importar( fileChooser.getSelectedFile()) );
			automataInicial.setAutomata( automata);
			if ( !iniciar ){accionIniciar();}
			foto.setAutomata( automata);
			foto.repaint();
		}
		
	}

	public void accionReiniciar() throws AutomataException{
		automata.setAutomata( automataInicial );
		foto.setAutomata( automata);
		foto.repaint();
	}
	
	public void accionCerrar() {
		System.exit(0);
	}
	
	public void accionIniciar(){
		iniciar = true;
		foto.setVisible(true);
	}


    public static void main(String[] args) {
        AutomataCelular ac=new AutomataCelular();
        AutomataGUI ca=new AutomataGUI(ac);
        ca.setVisible(true); 
    } 

}





class FotoAutomata extends JPanel{
    public static int TAMANO=40;
    private AutomataCelular automata;

    public FotoAutomata(AutomataCelular ac) {
        setBackground(Color.white);
        automata=ac;
                                                            
        setPreferredSize(new Dimension(800,800));       

    }

    public void setAutomata(AutomataCelular automata){
        this.automata=automata;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int f=0;f<=automata.getLongitud();f++){
            g.drawLine(f*TAMANO,0,f*TAMANO,automata.getLongitud()*TAMANO);
        }
        for (int c=0;c<=automata.getLongitud();c++){
            g.drawLine(0,c*TAMANO,automata.getLongitud()*TAMANO,c*TAMANO);
        }       
        for (int f=0;f<automata.getLongitud();f++){
            for(int c=0;c<automata.getLongitud();c++){
                if (automata.getElemento(f,c)!=null){
                    g.setColor(automata.getElemento(f,c).getColor());
                    if (automata.getElemento(f,c).getForma()==Elemento.CUADRADA){                  
                        if (automata.getElemento(f,c).isVivo()){
                            g.drawRoundRect(TAMANO*c+3,TAMANO*f+3,35,35,5,5);
                        }else{
                            g.fillRoundRect(TAMANO*c+3,TAMANO*f+3,35,35,5,5);    

                        }
                    }else {
                        if (automata.getElemento(f,c).isVivo()){
                            g.fillOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        } else {
                            g.drawOval(TAMANO*c+10,TAMANO*f+10,20,20);
                        }
                    }
                }
            }
        }
    }
}