package aplicacion;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PruebaElementos.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PruebaElementos
{


    @Test
    public void deberiaSerBarrera()
    {
        aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
        aplicacion.Barrera suroeste = new aplicacion.Barrera(automata1, 0,0);
        aplicacion.Barrera noreste = new aplicacion.Barrera(automata1, 7, 7);
        assertFalse( automata1.getElemento(0,0) == null );
        automata1.ticTac();
        assertFalse( automata1.getElemento(0,0) == null );
        automata1.ticTac();
        assertFalse( automata1.getElemento(7,7) == null );
    }
    
    @Test
    public void deberiaSerRespawn()
    {
        aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
        aplicacion.Elemento nuevo = new aplicacion.Respawn(automata1, 6,6);
       
        assertTrue( automata1.getElemento(6,6).getForma() == 2);
        automata1.ticTac();
        assertEquals(automata1.vecinos(6,6), 8 );
        automata1.ticTac();
        assertTrue( automata1.getElemento(6,6) == null );
    }
}

