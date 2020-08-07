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
     * Constructor for objects of class Respawn
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

    /**Retorna el color del Elemento Respawn
    @return Color, color que representa el color actual de Respawn
     */
    public final Color getColor(){
        return color;
    }
    
    /**
     * Metodo que decide el comportamiento del elemento Respawn
     *(El elemnto solo vive una vez y al desaparecer genera celulas Conway en sus 8 posiciones vecinas ).
     */
    
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
