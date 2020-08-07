package aplicacion;
import java.util.HashMap;
import java.io.*;
public class Estacion implements Serializable {
    private HashMap<String,Integer> tramos;
	private String nombre;
	private String nivelOcupacion;
	private int orden;
	private int tiempoEspera;
	

    /**
     * constructor de la clase Estacion
	 * @param nombre String nombre de la estacion a crear
	 * @param orden  int numero del orden el cual esta la estacion respecto a la ruta
	 * @param nivelOcupacion String nivel de ocupacion de la estacion (alto,medio,bajo)
	 * @param tiempoEspera int tiempo de espera promedio de la estacion
	 *
     */
    public Estacion(String nombre,int orden, String nivelOcupacion,int tiempoEspera   ){
		tramos = new HashMap<String,Integer>();
		this.nombre = nombre;
		this.orden = orden;
		this.nivelOcupacion = nivelOcupacion;
		this.tiempoEspera = tiempoEspera;

    }
	
	/**
	  *@param llave String, la cual sera la concatenacion de los dos nombres de las estaciones que componene el tramo
	  *@param distancia int, que representa la distancia del tramo ingresado
	*/
	public void addTramos( String llave, int distancia ){
		tramos.put(llave, new Integer(distancia) );
	}
	
	/**
	  *@return int, que representa el orden de una estacion de una ruta
	*/
	public int getOrden(){
		return orden;
	}
	
	/**
	  *@return String, que representa el nombre de la  estacion 
	*/
	public String getNombre(){
		return nombre;
	}
	
	/**
	  *@return int, que representa el tiempo de espera  de la  estacion 
	*/
	public int getTiempoEspera(){
		return tiempoEspera;
	}
	
	/**
	  *actualiza el nivel de oupacion de una estacion 
	  *@param nivelOcupacion,  variable de tipo String que representa el nuevo nivelOcupacion de la estacion
	*/
	public void setOcupacion(String nivelOcupacion){
		this.nivelOcupacion = nivelOcupacion;
	}

   
}
