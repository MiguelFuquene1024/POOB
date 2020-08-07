package aplicacion;

import java.awt.Color;
/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 *@version 1.0
 *@version 2.0
 *@version 3.0
 */
public class Hueco implements Elemento{
	private int fila, columna;
	private Pelota pelota;
	private MarbelGame tablero;
	private Color color;
	/**
	 * Constructor de la clase hueco
	 * @param fila, es la fila donde se ubicara el hueco
	 * @param columna, es la columna donde se ubicara el hueco 
	 * @param color, es el color con el que se pintara el hueco
	 */
	public Hueco( int fila, int columna, Color color ){
		this.color= color;
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.color = color;		
	}
	/**
	 * Constructor de la clase hueco
	 * @para tablero, es el tablero al cual se le añadira el hueco
	 * @param fila, es la fila donde se ubicara el hueco
	 * @param columna, es la columna donde se ubicara el hueco 
	 * @param color, es el color con el que se pintara el hueco
	 */
	public Hueco( MarbelGame tablero, int fila, int columna, Color color ){
		this.color= color;
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.color = color;		
		this.tablero.setElemento( (Elemento)this, fila, columna);
	}
	/**
	 * metodo que obtiene el color actual de un hueco
	 * @return Color, retorna el color actual de un hueco
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * metodo que obtiene el color de una pelota si esta se encuentra dentro de un hueco, de lo 
	 * contrario retorna blanco que representa que el hueco esta vacio
	 * @return Color, retorna el color de la pelota dentro del hueco
	 */
	public Color getColorPelota(){
		return ( pelota != null )?pelota.getColor():Color.WHITE;
	}
	/**
	 * Metodo que evalua si un hueco esta vacio o no
	 * @return Boolean, retorna un booleano indicando si el hueco esta vacio o no
	 */
	public boolean isEmpty(){
		return ( pelota == null );
	}
	/**
	 * Metodo que se usa para añadir una pelota al hueco
	 * @param p, representa la pelota que se le va a añadir al hueco
	 */
	public void llenarHueco( Pelota p ){
		pelota = p;
	}
	/**
	 * metodo que evalua si una pelota esta bien ubicada en un hueco de acuerdo a su color
	 * @return Boolean, retorna un booleano indicando si el color del hueco corresponde al de la pelota
	 */
	public boolean bienUbicado( ){
		return ( color.equals(pelota.getColor()) );
		
	}

}