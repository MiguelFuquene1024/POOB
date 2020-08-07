package aplicacion;
import java.awt.Color;

/**
 * Write a description of class Izquierdosa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Izquierdosa extends Celula
{

    

    /**
     * Constructor for objects of class Izquierdosa
     */
    public Izquierdosa(AutomataCelular ac,int fila, int columna)
    {
        super(ac,fila,columna);
        changeColor(Color.red);
    }
	
	
	
	
    @Override
    public void decida( ){
        
        int numVecinos  = automata.vecinOeste( getFila(), getColumma() );
        if( (numVecinos >1 || numVecinos==0 ) && isVivo() ){
            estadoSiguiente = VIVA;  
        }
        
        
        else if( (numVecinos == 1 || numVecinos >3) ) {
            estadoSiguiente = MUERTA;
        }
        
           
    }

}
