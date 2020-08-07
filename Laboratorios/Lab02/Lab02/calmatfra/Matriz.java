/**
 * @author ECI, 2019
 *
 */
public class Matriz{

    private Fraccionario [][] matriz;

    public static boolean esMatriz (int [][][] elementos){
        return false;
    }

    /**
     * @param elementos, cubo de enteros que representa una matriz de numeradores y denominadores.
     * Retorna una matriz dados sus elementos. Los fraccionarios se representan como {numerador, denominador}
     */

    public Matriz (int [][][] elementos) {
        int x, y ;

        matriz = new Fraccionario[ elementos.length ][ elementos[0].length ];
        for ( x = 0; x < elementos.length; x++ ){
            for ( y = 0 ; y <  elementos[0].length ; y++){
                int[] current = new int[2];

                current = elementos[x][y];

                Fraccionario fraccionario = new Fraccionario( current[0], current[1]);
                matriz[ x ][ y ] =  fraccionario;
            }

        }

    }

    /**
     * @param elementos, la matriz de fraccionarios que se usara para actualizar a matriz .
     * Retorna una matriz dados sus elementos. 
     */
    public Matriz (Fraccionario[][] elementos) {
        matriz = elementos;

    }

    /**
     * @param d, lista de fraccionario que representan la diagonal de una matriz
     * 
     * Retorna una matriz dada su diagonal. 
     */    
    public Matriz (Fraccionario [] d ){
        int i, j , length ;
        length = d.length;

        matriz = new Fraccionario[  length ][ length ];
        Fraccionario empty = new Fraccionario( 0,1 );
        for ( i = 0; i < length; i++ ){
            for ( j = 0; j < length; j++ ){
                matriz[ i ][ j ] = ( i == j ) ?   d[ i ]:empty;

            }

        }

    }    
    /**
     * @param e,  fraccionario a colocar en toda la matriz
     * @param f, entero que representa el numero de filas de la matriz
     * @param c, entero que representa el numero de columnas de la matriz
     * Retorna una matriz de un numero repetido dada su dimension. 
     */
    public Matriz (Fraccionario e, int f, int c) {
        int i, j;
        matriz = new Fraccionario [ f ][ c ];
        for ( i = 0; i < f; i++ ){
            for ( j = 0 ; j < c ; j++ ){
                matriz[ i ][ j ] = e;
            }

        }

    }
    /**
     * @param n, entero que representa la dimension de la matriz
     * Retorna una matriz identidad dada su dimension. 
     */
    public Matriz (int n) {
        int i;
        Fraccionario [] listaDiagonal = new Fraccionario[n];

        for ( i = 0; i < n ; i++ ){
            listaDiagonal[ i ] = new Fraccionario( 1 , 1 );
        }

        matriz = new Matriz( listaDiagonal ).matriz ;
    }

    public Matriz dimension(){
        return null;
    }
    
    
    /**
       @param f, entero que representa una posicion en la matriz
       @param c, entero que representa una posicion en la matriz
       */
    public Fraccionario get(int f, int c){
        return matriz[ f ][ c ];
    }

    /**
     * @param otra, matriz con la que se va a comparar
     * @return booleano, mencionando si las dos matrices son iguales
     * Compara esta matriz con otra
     */
    public boolean equals (Matriz otra) {
        return matriz.equals( otra.matriz);
    }

    /** 
     * @param otra, onjeto con el que se va a comparar
     * @return booleano, mencionando si las dos matrices son iguales
     * Compara esta matriz con otra
     */
    @Override
    public boolean equals(Object otra) {

        if (   ( otra == null ) ||  ( getClass() != otra.getClass() ) ){
            return false;
        }
        return true ;
    }


    /** 
     * @return cadena, cadena con los datos de la matriz alineado a derecha por columna
     * Retorna una cadena con los datos de la matriz alineado a derecha por columna
     * 
     */
    @Override
    public String toString(){
        String msj = "";
        for(int i=0;i<matriz.length;i++){
            msj += "\n";

            for(int j=0;j<matriz[0].length;j++){
                if ( j == matriz[0].length - 1 ){
                    msj += matriz[i][j].toString();
                }

                else {
                    msj += matriz[i][j].toString()+ ", ";
                }

            }
        }

        return msj;
    }
    
    /**
       *@param m, la matriz con la que se va a sumar 
       *@param type, entero que dira si se desea hacer una suma o una resta ( 1 = resta , 0 = suma)
       *@return Matriz, retornara la matriz resultante despues de hacer la suma
       */
    //Retorna la matriz con el numero de filas o columnas
    public Matriz sume(Matriz m, int type){
        int i, j ;
        Fraccionario [][] answ; 
        answ = new Fraccionario [matriz.length][matriz[0].length];
        
        for ( i = 0 ; i < matriz.length ; i++ ){
            for ( j = 0; j < matriz[0].length; j++ ){
                answ[ i ][ j ] = get(i, j).sume( m.matriz[ i ][ j ],type );

            }
        }

        return  new Matriz( answ );
    
    }
        
    /**
       *@param m, la matriz con la que se va a restar 
       *@return Matriz, retornara la matriz resultante despues de hacer la resta
       */
    public Matriz substract( Matriz m ){
        return sume( m,1 );
    }
    
    
    /**
       *@param m, la matriz con la que se va a multiplicar 
       *@return Matriz, retornara la matriz resultante despues de hacer la multiplicacion matricial
       */
    public Matriz multiplique( Matriz m){
        int i, j,k ;
        Fraccionario [][] answ; 
        Fraccionario mult, suma;

        answ = new Fraccionario [matriz.length][m.matriz[0].length];
        for ( i = 0; i < matriz.length; i++ ){
            for ( j = 0 ; j < m.matriz[0].length; j++ ){
                suma = new Fraccionario(0,1);
                for ( k = 0; k < m.matriz.length; k++ ){
                    mult = matriz[ i ][ k ].multiplique( m.matriz[ k ][ j ] ) ;
                    suma = suma.sume( mult,0 ) ;
                }
                answ [ i ][ j ] = suma;
            }
        }

        return new Matriz( answ );
    }
    
    
    /**
       *@param m, la matriz con la que se va a multiplicar elemento a elemento
       *@return Matriz, retornara la matriz resultante despues de hacer la multiplicacion 
       */
    public Matriz multipliqueElemento( Matriz m){
        int i, j,k ;
        Fraccionario [][] answ; 
        Fraccionario mult, suma;

        answ = new Fraccionario [matriz.length][matriz.length];
        for ( i = 0; i < matriz.length; i++ ){
            for ( j = 0 ; j < matriz.length; j++ ){
                answ [ i ][ j ] =  matriz[ i ][ j ].multiplique( m.matriz[ i ][ j ] ) ;

            }
        }

        Matriz matrizRespuesta = new Matriz( answ );
        return matrizRespuesta;
    }
    
    /**
       *@param f, fraccionario el cual se multiplicara con la matriz
       *@return Matriz, retornara la matriz resultante despues de hacer la multiplicacion de el fraccionario con la matriz
       */
    public  Matriz multiplicacionEscalar( Fraccionario f){
        int i, j,row = matriz.length, column = matriz[0].length ;
        Fraccionario [][] answ; 

        answ = new Fraccionario [ row ][ column ];

        for ( i = 0 ; i < row; i++ ){
            for ( j = 0 ; j < column; j++ ){
                answ[ i ][ j ] = matriz[ i ][ j ].multiplique( f );
            }
        }

        return new Matriz( answ );
    }
    
    /**
       *
       *@return Matriz,retorna la matriz transpuesta de la matriz que llamo al metodo 
       */
    public  Matriz transpuesta( ){
        int i, j,row = matriz.length, column = matriz[0].length ;
        Fraccionario [][] answ; 

        answ = new Fraccionario [ column ][ row  ];

        for ( i = 0 ; i < row; i++ ){
            for ( j = 0 ; j < column; j++ ){
                answ[ j ][ i ] = matriz[ i ][ j ];
            }
        }

        return new Matriz( answ );
    }

}
