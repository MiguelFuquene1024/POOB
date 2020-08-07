package aplicacion;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

public class Ruta{
    private ArrayList<Estacion> paradas;
	private ArrayList<String> nombreParadas;
	private HashMap<String,Estacion> estaciones;
	
	private String nombre;
	private int numeroEstaciones = -1;

    /**
     * Inicia una ruta sin estaciones.
	 *@param nombre , nombre de tipo String que toma la ruta
     */
    public Ruta(String nombre ){
        paradas = new ArrayList<Estacion>();
		nombreParadas = new ArrayList<String>();
		estaciones = new HashMap<String,Estacion>();
		
		this.nombre = nombre;
    }
	
	/**
	 *@param  estacion  estacion la cual pertenece a una de las paradas de la ruta
	*/
	public void addParadas( Estacion estacion ){
		paradas.add( estacion );
		nombreParadas.add( estacion.getNombre() );
		estaciones.put( estacion.getNombre(),estacion);
		Collections.sort(paradas, new OrdenarParadasPorOrden());
		
	}
	
	/**
	 *@return ArrayList<Estacion> ,retorna las paradas que tiene una ruta 
	*/
	public ArrayList<Estacion> getParadas( ){
		return paradas;
		
		
	}
	
	/**
	 *metodo encargado de mostrar las paradas de una ruta
	*/
	public void printParadas(){
		for (Estacion e: paradas ){
			System.out.println( e.getNombre());
		}
	}
	
	
	/**
	 *@return retorna el nombre de la ruta
	*/
	public String getNombre(){
		return nombre;
	}
	
	/**
	 *@param estacionInicio String estacion inicio para contar cuantas paradas hay entre esta y la estacion fin
	 *@param estacionFin String estacion fin para contar cuantas paradas hay entre esta y la estacion inicio
	*/
	public void getNumeroEstaciones(String estacionInicio,String estacionFin){
		int cont = -1;
		for( Estacion e : paradas ){
			if( cont == -1){
				cont+=( estacionInicio.equals(e.getNombre()) )?1:0;
			}
			else{
				cont++;
				if (estacionFin.equals(e.getNombre()) ){
					numeroEstaciones = cont ;
					break;
				}
			}
		}
		
	}
	
	/**
	 *@return retorna el numero de paradas que existen entre dos estaciones de una ruta
	*/
	public int getNumeroEstaciones(){
		return numeroEstaciones;
	}
	
	
	/**
	  *@param estacion1 String, nombre de la estacion inicial de la ruta
	  *@param estacion2 String, nombre de la estacion inicial de la ruta
	  *@return boolean, un booleano que dice si las estaciones ingresadas se realizan sin transbordo
	*/
	public boolean isRutaSinTransbordo( String estacion1,String estacion2 ){
		boolean condition = (nombreParadas.contains(estacion1) && nombreParadas.contains(estacion2));
		if (!condition){
			// !(condition1) || !(condition2) 
			return false;
		}
		else{
			return condition;
		}
		
	}
	
	/**
	 *@param nombreEstacion String entra el nombre de la estacion el cual se desea consultar su tiempo de espera
	 *@return int, el tiempo de espera de la estacion
	*/
	public int tiempoEstacion(String nombreEstacion){
		
		return estaciones.get(nombreEstacion).getTiempoEspera();
		 
	}
	
	
	public int find( String nameEstacion, String estacionFinal){
		boolean found = false;
		int time = 0;
		for( Estacion e : paradas ){
			if ( nameEstacion.equals(e.getNombre()) ){
				found = true;
			}
			if( found && !(estacionFinal.equals(e.getNombre()) )){
				time+= e.getTiempoEspera();
			}
		
		}
		return time;
	}
	
	
   
}
