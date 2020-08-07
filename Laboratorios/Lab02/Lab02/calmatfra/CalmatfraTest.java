
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalmatfraTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalmatfraTest
{
    /**
     * Default constructor for test class CalmatfraTest
     */
    @Test
    public void deberiaOperarUnarios(){
        HashMap<String, Matriz> variables = new  HashMap();
        Matriz matriz1 = new Matriz(new int[][][]{ { {1,7}, {5,6} }, { {31,2},{21,8}}  } );
        variables.put("X" , matriz1  );
        Matriz matrizR = variables.get("X").transpuesta();
        variables.put("Y",matrizR);
        assertEquals( new Matriz( new int[][][]{ { {1,7}, {31,2} }, { {5,6},{21,8}}  } ),variables.get("Y"));
        
        Matriz matriz2 = new Matriz(new int[][][]{ { {1,7}, {11,9} }, { {1,2},{1,8}}  });
        variables.put("A",matriz2);
        Fraccionario frac = new Fraccionario(1,7) ;
        Matriz var = variables.get("A").multiplicacionEscalar(frac);
        variables.put("B", var);
        assertEquals( new Matriz( new int[][][]{ { {1,49}, {5,42} }, { {31,14},{21,56} }  } ),variables.get("B"));
      
        
    }
    @Test
    public void deberiaOperarBinariosSuma(){
        HashMap<String, Matriz> variables = new  HashMap();
        Matriz matriz1 = new Matriz(new int[][][]{ { {1,7}, {5,6} }, { {31,2},{21,8}}  } );
        Matriz matriz2 = new Matriz(new int[][][]{ { {1,7}, {11,9} }, { {1,2},{1,8}}  });
        Matriz matriz3 = new Matriz(new int[][][]{ { {1,8}, {11,9} }, { {1,2},{1,8}}  });
        Matriz matriz4 = new Matriz(  new int[][][] {  { {1,2 },{2,3}} , { {4,5}, {6,7}} } );
        variables.put("I",matriz1);
        variables.put("J",matriz2);
        variables.put("Ñ",matriz3);
        variables.put("L",matriz4);
        variables.put("K",variables.get("I").sume(variables.get("J"),0));
        assertEquals( new Matriz( new int[][][]{ { {2,7}, {37,18} }, { {16,1},{11,4} }  } ),variables.get("K"));
        variables.put("Q",variables.get("I").sume(variables.get("Ñ"),1));
        assertEquals( new Matriz( new int[][][]{ { {1,56}, {-7,18} }, { {15,1},{5,2}} }  ),variables.get("Q"));
        variables.put("E",variables.get("L").multiplique(variables.get("L")));
        assertEquals( new Matriz( new int[][][]{ { {17,60}, {19,21} } , { {38,35}, {932,755} }}  ),variables.get("E"));
        
    
    }
    
    

    
}
