package aplicacion;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;
import persistencia.*;

public class Transmilenio implements Serializable  {
	private HashMap<String,Ruta> rutas;
	private static transient Persistencia persitencia;
	private HashMap<String,Troncal> troncales;
	
	public Transmilenio(){
		rutas= new HashMap<String,Ruta>();
		troncales= new HashMap<String,Troncal>();
		
	}
	
	/**
	 **añade las rutas de transmilenio
	 *@param r, ruta que pertenece al sistema de transmilenio
	*/
	public void addRutas( Ruta r){
		rutas.put(r.getNombre(),r);
	}
	
	/**
	  *añade las troncales de transmilenio
	 *@param t, troncal  que tiene el sistema de transmilenio
	*/
	public void addTroncales( Troncal t){
		troncales.put(t.getNombre(),t);
	}
	
	/**
	 *@return retorna las  rutas que tiene el sistema 
	*/
	public HashMap<String,Ruta> getRutas(){
		return rutas;
		
		
	}
	
	/*
	 *@param planRuta ingresa una matriz de cadenas la cual la primera posicion de la lista es el nombre de la estacion  y la segunda posicion es el nombre de la Ruta
	 @return int que es el tiempo promedio de recorrer las rutas
	 */
	public int planDeRuta( String[][] planRuta ){
		int tiempo = 0;
		String estacionFinal = planRuta[planRuta.length - 1][0] ;
		for( String[] r : planRuta  ){
			tiempo+= ( (  r[1] != null )? rutas.get( r[1] ).find( r[0],estacionFinal ):0 );
		}
		return tiempo;
		
		
	}
	
	/**
	 *@param nombreTroncal,  el nombre de la troncal a devolver de tipo String
	 *@return Troncal, la troncal solicitada
	*/
	public Troncal getTroncal( String nombreTroncal){
		return troncales.get( nombreTroncal ); 
	}
	
	
	/**
	 *@param file archivo donde se guardara la Troncal
	 *@param nombreTroncal,  el nombre de la troncal a guardar de tipo String
	*/
	public void salvar( File file, String nombreTroncal ){
		persitencia.salvar( file, getTroncal(nombreTroncal ));
	}
	
	/**
	 *@param file archivo donde se importara  la informacion
	*/
	public ArrayList<String> importar( File file){
		return persitencia.importar( file );
	}
	
}