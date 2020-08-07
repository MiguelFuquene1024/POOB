/** Calculadora.java
 * Representa una calculadora de matrices de fraccionarios
 * @author ESCUELA 2018-01
 */

import java.util.HashMap;
public class Calmatfra{

    private HashMap<String, Matriz> variables = new  HashMap();
    private boolean ok;
    //Consultar en el API Java la clase HashMap

    public Calmatfra(){
        ok = true;
    }
    
    /**
       @param variable, cadena de texto el cual representara la llave que se asignara a matriz en la hash map
       @param matriz, cubo de enteros que representa una matriz de numeradores y denominadores.
       */
    public void asigne(String variable, int [][][] matriz){
        variables.put(variable, new Matriz( matriz  ) );
        
    }
    
    
    /**
       @param respuesta, cadena de texto el cual representara la llave que se asignara al resultado de la operacion de matriz1 
       @param matriz1, cadena de texto el cual representara la llave que se asigno a matriz1
       @param f, Fraccionario a multiplicar en dado caso que se desee hacer producto punto ( Puede ser null )
       @param operacion, operacion a realizar a matriz 1 (Transpuesta, multiplicacion de un escalar a una matriz)
       */
    //operadores unarios: para una matriz : T ( transpuesta de la matriz ), E ( multiplicacion de un escalar a una matriz)
    public void opere( String respuesta, String matriz1, Fraccionario f , char operacion ){
        if (  ( f != null   && operacion == 'E' ||  operacion == 'T' ) && variables.containsKey( matriz1 ) ){
            ok = true;
            Matriz matriz=  variables.get( matriz1 );
            
            switch ( operacion ){
            case 'T':
                variables.put( respuesta , matriz.transpuesta() );
                break;
            default :
                variables.put( respuesta , matriz.multiplicacionEscalar( f ) );
                break;
        }
        
        
        }
        else { ok = false;}
    }
    
    
    /**
       @param respuesta, cadena de texto el cual representara la llave que se asignara al resultado de la operacion de operando1 y operando2
       @param operando1, cadena de texto el cual representara la llave que se asigno a operando1 en la hashMap
       @param operacion, operacion a realizar entre las matrices ( + , -, . , *)
       @param operando2, cadena de texto el cual representara la llave que se asigno a operando2 en la hashMap
       */
    // Los operadores binarios : + (suma), - (resta), . (multiplique elemento a elemento), * (multiplique matricial)
    public void opere(String respuesta, String operando1, char operacion, String operando2){
        //Validar que existan ambos operandos
        if ( variables.containsKey( operando1 )  && variables.containsKey( operando2 ) ){
            Matriz matriz1, matriz2;
            
            matriz1=  variables.get( operando1 );
            matriz2=  variables.get( operando2 );
            
            
            switch ( operacion ){
            case '+':
                
                variables.put( respuesta, matriz1.sume(  matriz2,0 ) );
                break;
            case '-':
                variables.put( respuesta,matriz1.sume(  matriz2,1 ) );
                break;
            case '.':
                variables.put( respuesta, matriz1.multipliqueElemento(  matriz2 ) );
                break;

            default : 
                variables.put( respuesta, variables.get( operando1 ).multiplique(  matriz2 ) );
                break;

            }
        }
        else {this.ok = false;}
        
    }

    
    /**
       
       @param variable, cadena de texto el cual representara la llave que se consultar
       */
    public String consulta(String variable){ 
        if ( variables.containsKey( variable ) ){
            String msg = variables.get(variable).toString();
            System.out.println( msg );
    
            return   msg.replace("\n", " ");
        }
        this.ok = false;
        return null;
    }

    public boolean ok(){
        return this.ok;
    }
}


