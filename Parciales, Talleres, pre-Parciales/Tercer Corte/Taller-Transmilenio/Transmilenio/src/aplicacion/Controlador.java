package aplicacion;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;



public class Controlador{
	/* Clase que controla a las demas unicamente para mostrar los casos solicitados en el taller **/
	public Controlador(){
	}
	
	/**
	*Prueba el COMPORTAMIENTO del servicio 1
	*/
	public static void probarCaso1(){
		Ruta g12 = new Ruta("G12");
		
		g12.addParadas(new Estacion("Portal Sur",1, "alto",10  ) );
		g12.addParadas(new Estacion("Santa Isabel",4, "alto",10  ) );
		g12.addParadas(new Estacion("SENA",3, "alto",10  ) );
		g12.addParadas(new Estacion("Alqueria",2, "alto",10  ) );
		
		System.out.println(g12.tiempoEstacion("Alqueria"));
	}
	
	/**
	 *Prueba el COMPORTAMIENTO del servicio 4
	 *@param estacionInicio String, estacion en la cual iniciara la prueba
	 *@param estacionFin String,  estacion en la cual finalizara la prueba
	*/
	public static void  probarCaso4(String estacionInicio,String estacionFin ){
		HashMap rutas = new HashMap<String, ArrayList<Ruta> >();
		ArrayList<Ruta > noTransbordos = new ArrayList<Ruta>();
		
		Ruta g12 = new Ruta("G12");
		Ruta f16 = new Ruta("F16");
		
		g12.addParadas(new Estacion("Alqueria",2, "alto",10  ) );
		g12.addParadas(new Estacion("Portal Sur",1, "alto",10  ) );
		g12.addParadas(new Estacion("SENA",3, "alto",10  ) );
		g12.addParadas(new Estacion("Santa Isabel",4, "alto",10  ) );
		
		f16.addParadas(new Estacion("Alqueria",2, "alto",10  ) );
		f16.addParadas(new Estacion("Portal Sur",1, "alto",10  ) );
		f16.addParadas(new Estacion("Santa Isabel",3, "alto",10  ) );
		
		if ( g12.isRutaSinTransbordo(estacionInicio,estacionFin) &&   f16.isRutaSinTransbordo(estacionInicio,estacionFin) ){
			
			noTransbordos.add(g12);
			noTransbordos.add(f16);
			rutas.put(estacionInicio+"-"+estacionFin,noTransbordos);
			
			Collections.sort(noTransbordos, new OrdenarPorFuncion4());
			
			for( Ruta r :noTransbordos ){
				System.out.println(r.getNombre());
			}
			
		}
	}
	
	/**
	 *Prueba el COMPORTAMIENTO del servicio 6
	*/
	public static void probarCaso6(){
		Transmilenio transmilenio= new Transmilenio();
		
		Ruta b10 = new Ruta("B10");
		Ruta a10 = new Ruta("A10");
		
		b10.addParadas(new Estacion("Cardio Infantil",1, "alto",2  ) );
		b10.addParadas(new Estacion("Calle 146",2, "alto",3  ) );
		b10.addParadas(new Estacion("Alcala",3, "alto",4  ) );
		b10.addParadas(new Estacion("Calle 100",4, "alto",5  ) );
		
		
		a10.addParadas(new Estacion("Calle 76",1, "alto",7  ) );
		a10.addParadas(new Estacion("Calle 26",3, "alto",6  ) );
		a10.addParadas(new Estacion("Calle 57",2, "alto",4  ) );
		a10.addParadas(new Estacion("Av cali",4, "alto",4  ) );
		
		String[][] planRuta = new String[][] {{"Cardio Infantil","B10"},{"Calle 76","A10"},{"Av cali",null}};
		
		transmilenio.addRutas(b10);
		transmilenio.addRutas(a10);
		
		System.out.println(transmilenio.planDeRuta(planRuta));
		
	}
	
	/**
	 *Prueba que se puede guardar una troncal en un archivo txt
	*/
	public static void deberiaGuardarTroncal(){
		Transmilenio transmilenio = new Transmilenio();
		
		Troncal nqsSur = new Troncal("NQS Sur", 7 );
		
		nqsSur.addEstaciones(new Estacion("Alqueria",2, "alto",10  ) );
		nqsSur.addEstaciones(new Estacion("Portal Sur",1, "alto",10  ) );
		nqsSur.addEstaciones(new Estacion("SENA",3, "alto",10  ) );
		nqsSur.addEstaciones(new Estacion("Santa Isabel",4, "alto",10 ));
		
		transmilenio.addTroncales(nqsSur);
		
		nqsSur = transmilenio.getTroncal("NQS Sur");
		nqsSur.setPromedio(6);
		
		transmilenio.addTroncales(nqsSur);
		
		transmilenio.salvar(new File("testSalvar.txt"),"NQS Sur");		
	}
	
	/**
	 *Prueba que se puede importar algo de un archivo plano
	*/
	public static void deberiaImportar(){
		Transmilenio transmilenio = new Transmilenio();
		File fichero = new File("testImportar.txt");
		
		for( String elemento : transmilenio.importar( fichero )){
			System.out.println(elemento);
		}
		
	}
	
	public static void main(String[] args){
		probarCaso1();
		System.out.println("\n");
		probarCaso4("Portal Sur","Santa Isabel");
		System.out.println("\n");
		probarCaso6();
		System.out.println("\n");
		deberiaGuardarTroncal();
		deberiaImportar();
		
		
	}
}