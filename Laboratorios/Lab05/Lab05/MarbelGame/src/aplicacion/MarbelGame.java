package aplicacion;

import java.awt.Color;
import java.util.*;
import java.util.Random;
/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 *@version 1.0
 *@version 2.0
 *@version 3.0
 */
public class MarbelGame {
	private final int SIZE;
	private int movements, badMovements,goodMovements,numeroHuecos;
	public Color color;
	private Elemento[][] tablero,tablero2;
	/**
	 * Constructor de la pclase MarbelGame
	 * @param t, representa una matriz de elementos que sera el tablero del marbel game
	 */
	public MarbelGame( Elemento[][] t ){
		this.SIZE = t.length;
		color = Color.WHITE;
		tablero = new Elemento[SIZE][SIZE];
		tablero2 = new Elemento[SIZE][SIZE];
		this.pasarTablero( t );
		setToCeroMovements();
	}
	
	/**
	 * Constructor de la clase MarbelGame
	 * @param SIZE, representa el tamaño del tablero que se va a crear
	 * @param c, representa el color del tablero que se va a crear
	 */
	public MarbelGame( int SIZE, Color c ){
		this.SIZE = SIZE;
		color = c;
		tablero = new Elemento[SIZE][SIZE];
		tablero2 = new Elemento[SIZE][SIZE];
		setToCeroMovements();
	}
	/**
	 * metodo que reinicia los contadores de cantidade de mov,malos mov y buenos mov.
	 */
	public void setToCeroMovements(){
		movements = 0;
		badMovements = 0;
		goodMovements = 0;
	}
	
	
	/**
	 * metodo que escoge al azar un numero de fila y columna
	 * @return int[], retorna una matriz de enteros que contienen el numero de fila y columna
	 */
	private int[] random(){
		int randF,randC ;
		
		Random rdm = new Random();
		
		randF =  rdm.nextInt(SIZE);
		randC =  rdm.nextInt(SIZE);
		
		while( tablero[randF][randC] != null ){
			randF = rdm.nextInt(SIZE);
			randC =  rdm.nextInt(SIZE);
			}
		return new int[]{randF,randC};
	}
	/**
	 * metodo que obtiene el color actual del tablero
	 * @return Color,retorna el color actual del tablero
	 */
	public Color  getColor(){
		return color;
	}
	/**
	 * metodo que reinicia el tablero de juego con pelotas,hueco y barreras en otras posiciones
	 * @param barreras,representa el numero de barreras que tendra el tablero
	 * @param huecos, representa el numero de huecos que tendra el tablero
	 * @param cBarreras, representa el numero de barreras que tendra el tablero
	 */
	public void setElementosAleatorios( int barreras, int huecos,Color  cBarreras){
		this.numeroHuecos = huecos;
		for ( int x = 0; x < barreras ; x ++ ){
			int[] indices = random();
			new Barrera(this,indices[0],indices[1],cBarreras );
			tablero2[indices[0]][indices[1]] = (Elemento)new Barrera(indices[0],indices[1],cBarreras );
		}
		
		for ( int y = 0; y < huecos ; y ++ ){
			int[] indices = random();
			Random rdm = new Random();
			
			Color c = new Color(rdm.nextInt(255),rdm.nextInt(255),rdm.nextInt(255));
			new Hueco(this,indices[0],indices[1],c );
			tablero2[indices[0]][indices[1]] = new Hueco(indices[0],indices[1],c );
			
			indices = random();
			new Pelota(this,indices[0],indices[1],c );
			tablero2[indices[0]][indices[1]] = new Pelota(indices[0],indices[1],c );
		}
		
	}	
	/**
	 * metodo que obtiene tl numero de movimientos,malos movimientos y buenos movimientos que llevan en el juego
	 * @return retorna una lista que contiene la cantidad de mov totales,move malos y mov buenos.
	 */
	public int[] getMovements(){
		return new int[]{movements,badMovements,goodMovements};
	}
	/**
	 * metodo que se usa para añadir un nuevo elemento al tablero
	 * @param newElemento, representa el nuevo elemento(pelota,hueco,barrera)que se añadira al tablero
	 * @param fila, representa la fila donde se añadira el elemento
	 * @param columna, representa la columna donde se añadira el elemento
	 */
	public void setElemento( Elemento newElemento, int fila, int columna ){
		tablero[fila][columna] = newElemento;
	}
	/**
	 * metodo que obtiene un elemento del tablero dado su fila y columna
	 * @param fila, representa la fila de donde se obtendra el elemento
	 * @param columna, representa la columna de donde se obtendra el elemento
	 * @return, retorna el elemento de dicha fila y columna
	 */
	public Elemento  getElemento( int fila , int columna ){
		return tablero[fila][columna];
	}
	/**
	 * metodo que obtiene el tamaño de un tablero
	 * @return, retorna el tamaño del tablero
	 */
	public int getSize(){
		return SIZE;
	}
	/**
	 * metodo para obtener un tablero 
	 * @return, retorna el tablero
	 */
	public Elemento[][] getTablero(){
		return tablero;
	}
	/**
	 * metodo que actualiza un tablero dado que se le ha aplicado algun movimiento
	 * @param newTablero retorna el nuevo tablero que se va a pintar
	 */
	private void pasarTablero( Elemento[][] newTablero ){
		for ( int i = 0; i < SIZE;i++ ){
			for( int j = 0; j < SIZE;j++){
				if (newTablero[i][j] instanceof Hueco){
					Hueco h = ( Hueco )newTablero[i][j];
					if ( h.isEmpty() ){
						setElemento( (Elemento)h,i,j);
						this.tablero2[i][j] =(Elemento)h;
					}
					else{
						Hueco h1 =  new Hueco(this,i,j,newTablero[i][j].getColor());
						h1.llenarHueco( new Pelota( this,i,j,h.getColorPelota()));
						
						setElemento( (Elemento)h1,i,j);
						this.tablero2[i][j] =(Elemento)h1;
					}
				}
				else if (newTablero[i][j] instanceof Barrera ){
					setElemento( (Elemento)newTablero[i][j],i,j);
					this.tablero2[i][j] = (Elemento)newTablero[i][j];
				}
				else if (newTablero[i][j] instanceof Pelota ){
					setElemento( (Elemento)new Pelota(this,i,j,newTablero[i][j].getColor()),i,j);
					this.tablero2[i][j] = (Elemento) new Pelota(i,j,newTablero[i][j].getColor());
				}
				else{
					setElemento( null,i,j);
					this.tablero2[i][j] = null;
				}
			}
		} 
		
	}
	
	/**
	 * metodo que reinicia una nueva partida, es decir un nuevo tablero con elementos en distintas posiciones
	 */
	public void setTableroInicial(){
		for ( int i = 0; i < SIZE;i++ ){
			for( int j = 0; j < SIZE;j++){
				if (tablero2[i][j] instanceof Hueco ){
					setElemento( (Elemento)new Hueco(this,i,j,tablero2[i][j].getColor()),i,j);
				}
				else if (tablero2[i][j] instanceof Pelota ){
					setElemento( (Elemento)new Pelota(this,i,j,tablero2[i][j].getColor()),i,j);
				}
				else if (tablero2[i][j] == null ){
					setElemento( null,i,j);
				}
			}
		}
		setToCeroMovements();
	}
	/**
	 * metodo que hace que se muevan las fichas del tablero hacia arriba
	 */
	public void deslizarNorte(){
		movements++;
		for( int j = 0; j < SIZE; j++ ){
			for ( int i = 0; i < SIZE; i++ ){
				if( getElemento( i,j) instanceof Pelota ){		
					int tempF = i - 1 ;
					Pelota p = (Pelota)getElemento( i,j);
					while( tempF  >= 0  ){
						if ((getElemento(tempF,j) instanceof Hueco) ){
							Hueco h = (Hueco)getElemento(tempF,j);
							if (h.isEmpty()){
								h.llenarHueco( p );
								if ( !(h.bienUbicado()) ){badMovements++;}
								if (  h.bienUbicado() ){goodMovements++;}
								if(tempF+1 < SIZE ){setElemento(null,tempF+1,j);}
								setElemento((Elemento)h,tempF,j);
								break;
							}
							else{
								if (tempF - 1 >=0 && getElemento(tempF-1,j)==null){
									tempF--;  p.move(tempF,j);
								}
								else{break;}
							}
						}
						else if( getElemento(tempF,j) !=null  ){break;}
						p.move(tempF,j);	
						getElemento(tempF,j);
						tempF--;
					}
				}
			}	
		}	
	}
	/**
	 * metodo que hace que se muevan las fichas del tablero hacia abajo
	 */
	public void deslizarSur(){
		movements++;
		for( int j = 0; j < SIZE; j++ ){
			for ( int i = (SIZE - 1); i >= 0; i-- ){
				if( getElemento( i,j) instanceof Pelota ){		
					int tempF = i + 1 ;
					Pelota p = (Pelota)getElemento( i,j);
					while( tempF  < SIZE  ){
						if ((getElemento(tempF,j) instanceof Hueco) ){
							Hueco h = (Hueco)getElemento(tempF,j);
							if (h.isEmpty()){
								h.llenarHueco( p );
								if ( !(h.bienUbicado()) ){badMovements++;}
								if (  h.bienUbicado() ){goodMovements++;}
								if(tempF-1 < SIZE ){setElemento(null,tempF-1,j);}
								setElemento((Elemento)h,tempF,j);
								break;
							}
							else{
								if (tempF + 1 < SIZE && getElemento(tempF+1,j) == null){
									tempF++;  p.move(tempF,j);
								}
								else{break;}
							}
						}
						else if( getElemento(tempF,j) !=null  ){break;}
						p.move(tempF,j);	
						getElemento(tempF,j);
						tempF++;
					}
				}
			}	
		}
	}
	/**
	 * metodo que hace que se muevan las fichas del tablero hacia la izquierda
	 */
	public void deslizarOeste(){
		movements++;
		for( int i = 0; i < SIZE; i++ ){
			for ( int j = 0; j < SIZE; j++ ){
				if( getElemento( i,j) instanceof Pelota ){		
					int tempC = j - 1 ;
					Pelota p = (Pelota)getElemento( i,j);
					while( tempC  >= 0  ){
						if ((getElemento(i,tempC) instanceof Hueco) ){
							Hueco h = (Hueco)getElemento(i,tempC);
							if (h.isEmpty()){
								h.llenarHueco( p );
								if ( !(h.bienUbicado()) ){badMovements++;}
								if (  h.bienUbicado() ){goodMovements++;}
								if(tempC+1 < SIZE ){setElemento(null,i,tempC+1);}
								setElemento((Elemento)h,i,tempC);
								break;
							}
							else{
								if (tempC - 1 >= 0 && getElemento(i,tempC-1) == null){
									tempC--;  p.move(i,tempC);
								}
								else{break;}
							}
						}
						else if( getElemento(i,tempC) !=null  ){
							break;
						}
						p.move(i,tempC);	
						getElemento(i,tempC);
						tempC--;
					}
				}
			}	
		}
	}
	/**
	 * metodo que hace que se muevan las fichas del tablero hacia arriba la derecha
	 */
	public void deslizarEste(){
		movements++;
		for( int i = 0; i < SIZE; i++ ){
			for ( int j = (SIZE - 1); j >= 0; j-- ){
				if( getElemento( i,j) instanceof Pelota ){		
					int tempC = j + 1 ;
					Pelota p = (Pelota)getElemento( i,j);
					while( tempC  < SIZE  ){
						if ((getElemento(i,tempC) instanceof Hueco) ){
							Hueco h = (Hueco)getElemento(i,tempC);
							if (h.isEmpty()){
								h.llenarHueco( p );
								if ( !(h.bienUbicado()) ){badMovements++;}
								if (  h.bienUbicado() ){goodMovements++;}
							    if(tempC-1 < SIZE ){setElemento(null,i,tempC-1);}
								setElemento((Elemento)h,i,tempC);
								break;
							}
							else{
								if (tempC + 1 < SIZE && getElemento(i,tempC+1) == null){
									tempC++;  p.move(i,tempC);
								}
								else{break;}
							}
						}
						else if( getElemento(i,tempC) !=null  ){
							break;
						}
						p.move(i,tempC);	
						getElemento(i,tempC);
						tempC++;
					}
				}
			}	
		}
	}
}