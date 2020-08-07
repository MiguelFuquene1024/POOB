package aplicacion;

import java.awt.Color;
/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 *@version 1.0
 *@version 2.0
 *@version 3.0
 */
public class Barrera implements Elemento{
	private MarbelGame tablero;
    private int fila,columna;
    protected Color color;
    /**
     * Constructor de la clase Barrera
     * @param fila, representa la posicion de la fila donde estara ubicada la barrera
     * @param columna, representa la posicion de la columna donde estara ubicada la barrera
     * @param color, representa el colo de cual se pintara la barrera
     */
	 
	 public Barrera( int fila, int columna, Color color )
    {
        this.tablero=tablero;
        this.fila=fila;
        this.columna=columna;
        this.color= color;
       
    }
    /**
	 * Constructor de la clase Barrera
	 * @param tablero, es el tablero en donde se va a pintar la barrera
	 * @param fila, representa la posicion de la fila donde estara ubicada la barrera
	 * @param columna, representa la posicion de la columna donde estara ubicada la barrera
	 * @param color, representa el colo de cual se pintara la barrera
	 */	 
    public Barrera( MarbelGame tablero , int fila, int columna, Color color )
    {
        this.tablero=tablero;
        this.fila=fila;
        this.columna=columna;
        this.tablero.setElemento((Elemento)this,fila,columna);  
        this.color= color;
       
    }

    /**Retorna el color de  la barrera
    @return Color, color de la barrera
     */
    public  Color getColor(){
        return color;
    }
	
	
	
}