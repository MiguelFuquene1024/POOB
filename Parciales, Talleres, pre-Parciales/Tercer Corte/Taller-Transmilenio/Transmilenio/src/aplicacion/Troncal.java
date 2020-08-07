package aplicacion;
import java.util.HashMap;
import java.io.*;

public class Troncal implements Serializable  {
	private String nombre;
	private int promedio;
	private HashMap<String,Estacion> estaciones;
	
	public Troncal( String nombre, int promedio){
		this.nombre = nombre;
		this.promedio = promedio;
		estaciones= new HashMap<String,Estacion>();
		
		
	}
	
	/**
	 **añade las estaciones de una troncal de transmilenio
	 *@param e, estacion que pertenece al troncal de transmilenio
	*/
	public void addEstaciones( Estacion e){
		estaciones.put(e.getNombre(),e);
	}
	
	/**
	  *@return String, que representa el nombre de la  troncal 
	*/
	public String getNombre(){
		return nombre;
	}
	
	/**
	  *@param promedio, que representa el promedio de recorrido de la troncal actualizado
	*/
	public void setPromedio( int promedio){
		this.promedio= promedio;
	}
	
	
	/**
	 *@param nombre,nombre de la estacion a  buscar
	 *@param nivelOcupacion, el nuevo nivel de ocupacion de la estacion
	*/
	public void ocupacionEstacion( String nombre,String  nivelOcupacion){
		estaciones.get( nombre ).setOcupacion(nivelOcupacion);
	}
	
	
	
}