

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MatrizTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MatrizTest
{


    

    @Test
    public void deberiaSumarMatrices()
    {
        Matriz matriz1 = new Matriz(new int[][][]{ { {1,7}, {5,6} }, { {31,2},{21,8}}  } );
        Matriz matriz2 = new Matriz(new int[][][]{ { {1,7}, {11,9} }, { {1,2},{1,8}}  });
        
        assertEquals( new Matriz( new int[][][]{ { {2,7}, {37,18} }, { {16,1},{11,4}}  } ), matriz1.sume(matriz2, 0));
        
        Matriz matriz3 = new Matriz(new int[][][]{{{1,2}},{{1,2}}});
        Matriz matriz4 = new Matriz(new int[][][]{{{3,4}},{{3,4}}});
        assertEquals(new Matriz(new int[][][]{{{5,4}}, {{5,4}}} ), matriz3.sume(matriz4, 0));
    }
    
    @Test
    public void deberiaRestarMatrices()
    {
        Matriz matriz1 = new Matriz(new int[][][]{ { {1,7}, {5,6} }, { {31,2},{21,8}}  } );
        Matriz matriz2 = new Matriz(new int[][][]{ { {1,8}, {11,9} }, { {1,2},{1,8}}  });
        
        assertEquals( new Matriz( new int[][][]{ { {1,56}, {-7,18} }, { {15,1},{5,2}}  }), matriz1.sume(matriz2, 1));
        
        Matriz matriz3 = new Matriz(new int[][][]{{{1,2}},{{1,2}}});
        Matriz matriz4 = new Matriz(new int[][][]{{{3,4}},{{3,4}}});
        assertEquals(new Matriz(new int[][][]{{{5,4}}, {{5,4}}} ), matriz3.sume(matriz4, 0));
    }
    
    @Test
    public void deberiaMultiplicarMatrices()
    {
        Matriz matriz1 = new Matriz(  new int[][][] {  { {1,2 },{2,3}} , { {4,5}, {6,7}} } );
        assertEquals(new Matriz( new int[][][]{ {{17,60}, {19,21} } , { {38,35}, {932,755}  }} ), matriz1.multiplique(matriz1));
    }
    
    @Test
    public void deberiaMultiplicarElemento()
    {
        
        Matriz matriz1 = new Matriz(  new int[][][] {  { {1,2 },{2,3}} , { {4,5}, {6,7}} } );
        assertEquals(new Matriz( new int[][][]{ {{1,4}, {4,9} } , { {16,25}, {36,49}  }} ), matriz1.multipliqueElemento(matriz1));
    }
    
    @Test
    public void deberiaHacerTranspuesta()
    {
        
        Matriz matriz1 = new Matriz(  new int[][][] {  { {1,2 },{2,3}} , { {4,5}, {6,7}} } );
        assertEquals(new Matriz( new int[][][] {  { {1,2 },{4,5}} , { {2,3}, {6,7}} } ), matriz1.transpuesta());
    }
    
    @Test
    public void deberiaMultiplicarPorUnEscalar()
    {
        
        Matriz matriz1 = new Matriz(  new int[][][] {  { {1,2 },{2,3}} , { {4,5}, {6,7}} } );
        assertEquals(new Matriz( new int[][][]{ {{1,4}, {2,6} } , { {2,5}, {3,7}  }} ), matriz1.multiplicacionEscalar( new Fraccionario(1,2)));
    }
}




