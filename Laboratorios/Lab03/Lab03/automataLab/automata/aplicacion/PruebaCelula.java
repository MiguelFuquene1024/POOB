package aplicacion;


import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The test class PruebaCelula.
 *
 * @author Ivan Camilo Rincon Saavedra
 * @author Miguel Angel Fuquene 
 * @version1.0 12/03/2020
 */
public class PruebaCelula
{
    /**
     * Default constructor for test class PruebaCelula
     */
    public PruebaCelula()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void deberiaHacerTicTac()
    {
        aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
        aplicacion.Celula celula1 = new aplicacion.Celula(automata1, 4, 4);
        aplicacion.Celula celula2 = new aplicacion.Celula(automata1, 5, 4);
        
        assertFalse ( automata1.getElemento(4,4).isVivo() );
        automata1.ticTac();
        assertTrue ( automata1.getElemento(4,4).isVivo() );
        automata1.ticTac();
        assertTrue ( automata1.getElemento(4,4).isVivo() );
        automata1.ticTac();
        assertTrue ( !automata1.getElemento(4,4).isVivo() );
    }

    @Test
    public void deberiaSerIzquierodosa()
    {
        aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
        aplicacion.Izquierdosa marx = new aplicacion.Izquierdosa(automata1, 3, 4);
        aplicacion.Izquierdosa hegel = new aplicacion.Izquierdosa(automata1, 3, 5);
        automata1.ticTac();
        Color color = Color.red;
        assertEquals(automata1.getElemento(3,4).getColor(), color );
        automata1.ticTac();
        assertFalse(automata1.getElemento(3,4).isVivo() );
        automata1.ticTac();
        assertTrue(automata1.getElemento(3,5).isVivo() );
    }
    
    @Test
    public void deberiaSerFamilia()
    {   
     
       aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
       int[][] MOVEMENTS = new int[][] { {0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
       
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( automata1, 3 + elem[0],3 +elem[1] );
       }
       
       Elemento Camilo = new Familia( automata1, 3,3 );
       
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( automata1, 6 + elem[0], 6 + elem[1] );
       }
       
       Elemento Fuquene = new Familia( automata1, 6,6 );
       automata1.ticTac();
       Color color = Color.green;
       automata1.ticTac();
       assertEquals(automata1.getElemento(3,3).getColor(), color );
       assertEquals(automata1.vecinos(3,3), 8 );
       automata1.ticTac();
       assertTrue(!automata1.getElemento(3,5).isVivo() );
    }
    
    @Test
    public void deberiaConway()
    {   
     
       aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
       int[][] pos = new int[][]{{0,1},{1,1},{2,1}};
       for( int[] p : pos){
            Elemento w = new Conway( automata1, p[0],p[1] );
       }
       
       
       

    }
    
    
    @Test
    public void deberiaQuedarEstatico()
    {   
       
       aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
       int[][] pos = new int[][]{{0,1},{0,0},{1,1},{1,0} };
       for( int[] p : pos){
            Elemento w = new Conway( automata1, p[0],p[1] );
       }
       
       automata1.ticTac();
       for( int[] p : pos){
            assertTrue( automata1.getElemento( p[0],p[1]).isVivo() );
       }
       

    }
    
    
    @Test
    public void deberiaParpadear()
    {   
     
       aplicacion.AutomataCelular automata1 = new aplicacion.AutomataCelular();
       int[][] pos = new int[][]{{0,1},{1,1},{2,1}};
       for( int[] p : pos){
            Elemento w = new Conway( automata1, p[0],p[1] );
       }
       
       automata1.ticTac();
       assertTrue(automata1.getElemento(0,1).isVivo() );
       assertTrue(automata1.getElemento(1,1).isVivo() );
       assertTrue(automata1.getElemento(2,1).isVivo() );
       automata1.ticTac();

       assertTrue(automata1.getElemento(1,0).isVivo() );
       assertTrue(automata1.getElemento(1,1).isVivo() );
       assertTrue(automata1.getElemento(1,2).isVivo() );
    }
}


