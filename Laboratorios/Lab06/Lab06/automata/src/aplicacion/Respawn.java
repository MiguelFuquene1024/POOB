package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Spawner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Respawn implements Elemento
{
private AutomataCelular automata;
    private int f,c, size;
    private int times = 0;
    protected Color color;
    
    /**
     * Constructor for objects of class Barrera
     */
    
    public Respawn( AutomataCelular ac , int fila, int columna )
    {
        automata=ac;
        f = fila;
        c =  columna;
        automata.setElemento(fila,columna,(Elemento)this);  
        color=Color.red;
        size = automata.LONGITUD;
    }

    /**Retorna el color de  la célula
    @return 
     */
    public final Color getColor(){
        return color;
    }
    
    
	/**
	  * metodo encargado de retornar la informacion de la celula en formato de exportar establecido
	  *@return String, informacion de la celula en formato de exportar establecido
	*/
	public String getInfo(){
		return getClass().getName()+" "+f+" "+c; 
	}
	
	
    public void decida( ){

        if ( times == 0 ){
            for ( int[] move : automata.MOVEMENTS){
                int filaTemp = f + move[0], coluTemp = c+ move[1];
                if ( ( filaTemp >= 0 && filaTemp < size  ) && ( coluTemp>=0 && coluTemp < size)   ){
                    automata.setElemento(filaTemp,coluTemp, new Conway( automata,filaTemp,coluTemp)); 
                    
                    
                }
            }
            automata.setElemento(f,c,null);
            
        }
        times++;
        
        

       
        
           
    }
}
