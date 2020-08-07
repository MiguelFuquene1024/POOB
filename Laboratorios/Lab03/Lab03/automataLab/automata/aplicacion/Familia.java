package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Familia here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Familia extends Celula
{
    // instance variables - replace the example below with your own
    public Familia(AutomataCelular ac,int fila, int columna)
    {
       super(ac, fila, columna);
       changeColor(Color.green);
    }
    
    @Override
    public void decida( ){
        
        int numVecinos  = automata.vecinos( getFila(), getColumma() );
        if( numVecinos == 8 ){
            estadoSiguiente = VIVA;  
        }
        else  {
            estadoSiguiente = MUERTA;
        }
        
           
    }
}
