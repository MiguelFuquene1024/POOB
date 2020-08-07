package aplicacion;

import java.util.*;


/**
 * Clase que construye una automata celular
 *
 * @author Ivan Camilo Rincon Saavedra
 * @author Miguel Angel Fuquene 
 * @version1.0 12/03/2020
 */
public class AutomataCelular{
    static protected int LONGITUD=20;
    private final Elemento[][] automata;
    public static final int[][] MOVEMENTS = new int[][] { {0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
    
    
    
    private Elemento[][] llenarMatriz(Elemento[][] m){
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                m[f][c]=null;
            }
        }
        return m;
    }
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        llenarMatriz( automata );
        algunosElementos();
    }
    
    
    /**
       @return int, entero que representa la longitud de la automata
       */
    public int  getLongitud(){
        return LONGITUD;
    }

    /**
     * @param f, entero que representa una fila en una matriz 
       @param c, entero que representa una columna en una matriz
       @return Elemento, el cual es al que pertenece a la f, c establecida en la automata
       */
    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }
    
    /**
       @param f, entero que representa una fila en una matriz 
       @param c, entero que representa una columna en una matriz
       @param nueva , tipo Elemnto el cual se agregara a la automata
       */
    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }
    
    /**
       metodo que añade elementos predefinidos a la automata Celular
       */
    public void algunosElementos(){
       /**
       //1)Celula
       Elemento  indiana = new Celula( this,1,1  ) ;
       Elemento OO7 = new Celula( this,2,2  ) ;
       
       //2)Izquierodosa
       Elemento marx = new Izquierdosa( this, 3,4 );
       Elemento hegel = new Izquierdosa( this,3,5 );
       
       //3)Barrera
       Elemento suroeste = new Barrera( this, 0,0 );
       Elemento noreste = new Barrera( this, 7,7 );
       
       
       //4)Famila
       Elemento Camilo = new Familia( this, 3,3 );
       Elemento Fuquene = new Familia( this, 6,6 );
       
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( this, 3 + elem[0],3 +elem[1] );
       }
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( this, 6 + elem[0], 6 + elem[1] );
       }
       //Respawn
       
       //6)Conway
       Elemento john = new Conway(  this , 5,1);
       Elemento horton = new Conway(  this , 5,2);
       
       //7)Estatico
       int[][] pos = new int[][]{{0,1},{0,0},{1,1},{1,0} };
       
       //8)Parpadeador
       int[][] pos = new int[][]{{3,3},{3,2},{3,1} };
       for( int[] p : pos){
            Elemento w = new Conway( this, p[0],p[1] );
       }
       int[][] pos = new int[][]{{3,3},{3,2},{3,1} };
       for( int[] p : pos){
            Elemento w = new Conway( this, p[0],p[1] );
       }*/
       Elemento respawn = new Respawn(this, 6,6);
    }
    
    /**
     * @param f, entero que representa una fila en una matriz 
     * @param c, entero que representa una columna en una matriz
     * @return boolean , que dice si la fila y columna es valida en la matriz
     * 
       */
    private boolean checkPos( int f, int c ){
        boolean ok = true; 
        if ( f < 0 ||f >= LONGITUD ){
            ok = false;
        }
        else if (  c < 0 ||c >= LONGITUD ){
            ok = false;
        }
        else if (getElemento(f,c)  == null ){
            ok = false;
        }
        return ok;
    }
    
    /**
     * @param f, entero que representa una fila en una matriz 
     * @param c, entero que representa una columna en una matriz
     * @return int ,  dice la cantidad de celulas vecinas vivas que tiene una celula en la posicion f,c
     * 
       */
    public int vecinos( int f, int c ){
        int cont = 0;
        for ( int[] move: MOVEMENTS){
            int filaTemp = f + move[0], coluTemp = c+ move[1];
            
            if ( checkPos(filaTemp, coluTemp) && getElemento(filaTemp,coluTemp).isVivo() ){
                cont++;
            }
        }
        return cont;
    }
    
    /**
     * @param f, entero que representa una fila en una matriz 
     * @param c, entero que representa una columna en una matriz
     * @return int ,  el cual retorna 1 si tiene un vecino a su derecha, de lo contrario devuelve 0
     * 
       */
    public int vecinOeste(int f,int c){
        int col = c + 1;
        if(checkPos(f,col) && getElemento(f,col).isVivo() ){
            return 1;
        }
        return 0;
    
    }
    
    /**
       metodo que recorre toda la automata, para que sus celulas decidan su estado o cambien de estado
       @param decidir, booleano que dice si las celulas deciden o cambian
       */
    private void recorrer( boolean decidir ){
        for ( int i = 0; i < LONGITUD; i++){
            for ( int j = 0; j < LONGITUD; j++){
                if (  decidir ){
                    if ( checkPos(  i,  j )  ){
                        automata[i][j].decida(); 
                    }
                    else if (  vecinos(i,j) == 3 ){
                        setElemento(i,j, new Conway( this, i, j) );
                    }
                }
                else if ( !decidir ){
                    if ( checkPos(  i,  j )  ){
                          automata[i][j].cambie();
                    }
                }
            }

        }

    }

    /**
     * metodo que marca el paso del tiempo
       */
    public void ticTac(){
       recorrer( true );
       recorrer( false );

    }
}
