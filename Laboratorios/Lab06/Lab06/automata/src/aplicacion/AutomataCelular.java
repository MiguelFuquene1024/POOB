package aplicacion;

import java.util.*;
import java.io.*;
import persistencia.*;

public class AutomataCelular implements Serializable {
	/**
	  *el serivalVersioUID lo dio eclipse
	*/
	
    static protected int LONGITUD=20;
    private  Elemento[][] automata;
	public static transient AutomataIO automataIo;
	private static final long serialVersionUID = 3370254807680571940L;
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
	
	public Elemento[][] getElementos(){
		return this.automata;
	}	
	

    public int  getLongitud(){
        return LONGITUD;
    }

    public Elemento getElemento(int f,int c){
        return automata[f][c];
    }

    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    public void algunosElementos(){
		/**
       Elemento  indiana = new Celula( this,1,1  ) ;
       Elemento OO7 = new Celula( this,2,2  ) ;
   
       Elemento marx = new Izquierdosa( this, 3,4 );
       Elemento hegel = new Izquierdosa( this,3,5 );

       Elemento suroeste = new Barrera( this, 0,0 );
       Elemento noreste = new Barrera( this, 7,7 );
       
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( this, 3 + elem[0],3 +elem[1] );
       }
       */
       Elemento Camilo = new Familia( this, 3,3 );
       
       for( int[] elem : MOVEMENTS){
            Elemento w = new Celula( this, 6 + elem[0], 6 + elem[1] );
       }
       /*
       Elemento Fuquene = new Familia( this, 6,6 );
		*/
    }
    
    
    public boolean checkPos( int f, int c ){
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
    
    public int vecinOeste(int f,int c){
        int col = c + 1;
        if(checkPos(f,col) && getElemento(f,col).isVivo() ){
            return 1;
        }
        return 0;
    
    }
   
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

    
    public void ticTac(){
       recorrer( true );
       recorrer( false );

    }
	
	public  void cambiarMatriz( Elemento[][] m){
		for( int f=0 ; f < m.length;f++){
			for( int c=0 ; c < m.length;c++){
				Elemento elemento =m[f][c];
	
				if(elemento instanceof Barrera ){
					this.setElemento(f, c, (Elemento) new Barrera(this,f,c) );
				}
				else if (elemento instanceof Conway){
					this.setElemento(f, c, (Elemento) new Conway(this,f,c) );
				}
				else if (elemento instanceof Familia){
					this.setElemento(f, c, (Elemento) new Familia(this,f,c) );
				}
				else if(elemento instanceof Izquierdosa){
					this.setElemento(f, c, (Elemento) new Izquierdosa(this,f,c) );
				}
				else if(elemento instanceof Respawn){
					this.setElemento(f, c, (Elemento) new Respawn(this,f,c) );
				}
				else if ( elemento instanceof Celula){
					this.setElemento(f, c, (Elemento) new Celula(this,f,c) );
				}
				else{
					this.setElemento(f, c, null );
				}
			}
		}
	}
	
	public void salvar( File f ) throws AutomataException{
		automataIo.salvarO1(f ,this);
	}
	
	
	public AutomataCelular abrir( File f ) throws AutomataException{
		return automataIo.abrirO1(f);
	}
	
	public void exportar( File f) throws AutomataException{
		automataIo.exportarO1(f ,this);
	}
	
	public AutomataCelular importar( File f) throws AutomataException{
		return automataIo.importarO3(f);
	}
	
	public void setAutomata( AutomataCelular a ){
		cambiarMatriz( a.getElementos());
	}
}

