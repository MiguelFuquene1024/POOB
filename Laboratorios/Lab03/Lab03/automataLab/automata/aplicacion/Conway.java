package aplicacion;
import java.awt.Color;

import java.awt.Color;

/**Informacion sobre una célula<br>
<b>(COLOR,automata,fila,columna,estadoActual,estadoSigiente)</b><br>
Las celulas conocen su color, el automata en la que viven, la posición en la que están en ese autómata,su estado actual y el estado que van a tomar en el siguiente instante.<br>
Todas las células son de color azul<br>
Los posibles estados de una célula son tres: viva, muerta o latente<br>
 */
public class Conway extends Celula{

    /**Crea una célula, viva o latente, en la posición (<b>fila,columna</b>) del autómta <b>ac</b>.Toda nueva célula va a estar viva en el estado siguiente.
    @param ac automata celular en el que se va a ubicar la nueva célula
    @param fila fila en el automata celular
    @param columna columna en el automata celula
     */
    public Conway(AutomataCelular ac,int fila, int columna){
        super(ac,fila,columna);
        super.changeColor( Color.blue );
    }

    /**
     * Metodo que decide el comportamiento de la celula conway
     *(Reglas del juego de la vida).
     */
    
    @Override
     public void decida( ){
        
        int numVecinos  = automata.vecinos( getFila(), getColumma() );
        
        
        
        if( ( (numVecinos == 2 ) && isVivo() ) || numVecinos == 3  ){
            estadoSiguiente = VIVA;  
        }
        
        
        else if( (numVecinos == 1 || numVecinos >3) ) {
            estadoSiguiente = MUERTA;
        }
        
           
    }


}
