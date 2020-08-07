package presentacion;


import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import aplicacion.*;
import javax.swing.JPanel;
import java.awt.Toolkit;


/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 */
public class PanelTablero extends JPanel{
	public static int SIZE = 40;
	private MarbelGame tablero;
	
	
	/**
	  *Constructor de la clase JPanel
	 *@param tablero, MarbelGame variable que representa la logica del tablero a dibujar 	
	*/
	public PanelTablero( final MarbelGame tablero ){
		this.setBackground(tablero.getColor());
		this.tablero = tablero;
		
	}
	
	/**
	 * Establece la logica del programa en el frame
	 *@param tablero, MarbelGame variable que representa la logica del tablero a dibujar 
	*/
	public void setMarbelGame( final MarbelGame tablero ){
		this.tablero = tablero;
	}
	
	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		for (int i = 0; i <= this.tablero.getSize(); ++i) {
            g.drawLine(i * PanelTablero.SIZE, 0, i * PanelTablero.SIZE, this.tablero.getSize() * PanelTablero.SIZE);
        }
		
		for (int i = 0; i <= this.tablero.getSize(); ++i) {
            g.drawLine(0, i * PanelTablero.SIZE, this.tablero.getSize()*PanelTablero.SIZE, PanelTablero.SIZE * i);
        }
		
		for (int f=0;f<tablero.getSize();f++){
            for(int c=0;c<tablero.getSize();c++){
                if (tablero.getElemento(f,c)!=null){
                    g.setColor(tablero.getElemento(f,c).getColor());
                    if (tablero.getElemento(f,c).getForma()==Elemento.REDONDO){                  
						if (tablero.getElemento(f,c) instanceof Pelota ){
                            g.fillOval(SIZE*c+10,SIZE*f+10,20,20);
                        } 
						else { 
							Hueco h = (Hueco)tablero.getElemento(f,c);
							if( h.isEmpty() ){
								g.drawOval(SIZE*c+5,SIZE*f+5,25,25);
							}
							else{
								g.drawOval(SIZE*c+5,SIZE*f+5,25,25);
								g.setColor(h.getColorPelota());
								g.fillOval(SIZE*c+10,SIZE*f+10,20,20);
							}
                            
                        }
                    }
					else {
						g.fillRoundRect(SIZE*c+3,SIZE*f+3,35,35,5,5);
                        
                    }
                }
            }
        }
		
	}
	
	
}