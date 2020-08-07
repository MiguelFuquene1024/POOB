package persistencia ;

import java.io.*;
import aplicacion.*;
import java.util.ArrayList;

public abstract class Persistencia{
	
	public static void salvar( File file,Troncal troncal ){
		if( file.getName().contains(".txt")){
			try{	
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(file));
				salida.writeObject(troncal);
				
				salida.close();
			}
			catch( Exception e){
				System.out.println("el error es "+e.getMessage());
			}
		}	
		
	}
	
	public static ArrayList<String>  importar( File file){
		ArrayList<String> instrucciones = new ArrayList<String>();
		
		try{
			BufferedReader leer = new BufferedReader( new FileReader( file ) );
			
			String  linea = leer.readLine();
			while( linea!= null ){
				
				linea = leer.readLine();
				if (linea != null ){
					instrucciones.add(linea);
				}
				
			}
		}
		catch( Exception e ){
		}
		
		return instrucciones;
		
	}

	
	
	
	
}