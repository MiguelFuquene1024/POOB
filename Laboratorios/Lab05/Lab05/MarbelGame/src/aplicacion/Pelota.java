package aplicacion;

import java.awt.Color;
/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 *@version 1.0
 *@version 2.0
 *@version 3.0
 */
public class Pelota implements Elemento{
	
	private MarbelGame tablero;
	private int fila, columna;
	private Color color;
	/**
	 * Constructor de la clase pelota
	 * @param fila, representa la fila donde ira ubicada la pelota
	 * @param columna,  representa la  columna donde ira ubicada la pelota
	 * @param color, representa el color de la pelota
	 */
	public Pelota( int fila, int columna , Color color ){
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.color = color;		
		
	}
	/**
	 * Constructor de la clase pelota
	 * @param tablero, es el tablero donde se va a ubicar la pelota
	 * @param fila, representa la fila donde ira ubicada la pelota
	 * @param columna,representa la  columna donde ira ubicada la pelota
	 * @param color,representa el color de la pelota
	 */
	public Pelota( MarbelGame tablero, int fila, int columna , Color color ){
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.color = color;		
		this.tablero.setElemento( (Elemento)this, fila, columna);
		
	}
	/**
	 * Metodo que se encarga de mover un apelota a otra posicion
	 * @param fila, es la fila destino donde se movera la pelota
	 * @param columna, es la columna destino donde se movera la pelota
	 */
	public void move(int fila, int columna  ){
		tablero.setElemento( null, this.fila,this.columna);

		this.fila = fila;
		this.columna = columna;
		this.tablero.setElemento( (Elemento)this, fila, columna);
	}
	/**
	 * metodo que cambia el color de una pelota
	 * @param newColor, es el nuevo color que va a tomar la pelota
	 */
	public void setColor( Color newColor ){
		this.color = newColor;
		
	}
	/**
	 * metodo para obtener el color de una pelota
	 * @return Color, retorna el color de la pelota actualmente
	 */
	public Color getColor(){
		return color;
	}
	
}