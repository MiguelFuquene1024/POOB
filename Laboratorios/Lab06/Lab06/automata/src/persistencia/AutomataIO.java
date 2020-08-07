package persistencia;

import java.io.*;
import aplicacion.*;


public abstract class AutomataIO {
	public static void salvar( File file, AutomataCelular a) throws AutomataException {
		try{
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( file ) );
			out.writeObject(a);
			out.close();
			
		}
		catch( Exception e){
			throw new AutomataException("Ocurrio un error al salvar "+file.getName());
		}		
	}
	
	public static void salvarO1( File file, AutomataCelular a) throws AutomataException {
		if ( file.getName().endsWith(".dat")){
			salvar(file,a);
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_VALIDO);
		}
		
		
	}
	
	public static AutomataCelular abrir( File file ) throws AutomataException {
		try{
			ObjectInputStream in = new ObjectInputStream( new FileInputStream(file));
			AutomataCelular a = (AutomataCelular)in.readObject();
			in.close();
			return a;
			
			
		}
		catch( Exception e){
			System.out.println(e);
			throw new AutomataException("Ocurrio un error al Abrir "+file.getName());
		}		
		
	}
	
	
	public static AutomataCelular abrirO1( File file ) throws AutomataException {
		if ( file.getName().endsWith(".dat")){
			return abrir( file );
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_VALIDO);
		}
		
		
	}
	
	
	public static void exportar ( File f,AutomataCelular a ) throws AutomataException{
		try{
			PrintWriter out = new PrintWriter( new FileOutputStream( f) );
			
			
			for ( Elemento[]  elementos : a.getElementos()){
					for (Elemento e: elementos ){
						if ( e != null ){
							out.println( e.getInfo().replace("aplicacion.",""));
						}
						
					}
			}
			out.close();
		
		}
		catch( Exception e){
			System.out.println(e);
			throw new AutomataException("Ocurrio un error al intentar exportar el archivo "+ f.getName());
		}
		
		
	}
	
	public static void exportarO1 ( File f,AutomataCelular a ) throws AutomataException{
		if( f.getName().endsWith(".txt") ){
			exportar(f,a);
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_ES_TXT);
		}
		
	}
	
	
	
	public static AutomataCelular importar ( File fi ) throws AutomataException{
		AutomataCelular a = new AutomataCelular();
		if( fi.getName().endsWith(".txt") ){
			try{
				BufferedReader in = new BufferedReader( new FileReader( fi ) );
				String  currentInstruction = in.readLine();
				while ( currentInstruction != null ){
					String[] lista =currentInstruction.split(" "); 
					int f = Integer.parseInt(lista[1].trim()),c =Integer.parseInt(lista[2].trim()); 
					if ( "Celula".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Celula(a,f,c) );
					}
					else if("Barrera".equals(lista[0]) ){
						a.setElemento(f, c, (Elemento) new Barrera(a,f,c) );
					}
					else if ("Conway".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Conway(a,f,c) );
					}
					else if ("Familia".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Familia(a,f,c) );
					}
					else if( "Izquierdosam".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Izquierdosa(a,f,c) );
					}
					else if( "Respawn".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Respawn(a,f,c) );
					}
					currentInstruction = in.readLine();
				}
				in.close();
				return a;
			}			
			catch( Exception e){
				System.out.println(e);
				throw new AutomataException("Ocurrio un error al intentar importar el archivo "+ fi.getName());
			}
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_ES_TXT);
		}
		
	}
	
	
	public static AutomataCelular importarO1 ( File fi ) throws AutomataException{
		if( fi.getName().endsWith(".txt") ){
			return importar(fi);
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_ES_TXT);
		}
		
	}
	
	public static AutomataCelular importarO2 ( File fi ) throws AutomataException{
		AutomataCelular a = new AutomataCelular();
		int cont = 1;
		if( fi.getName().endsWith(".txt") ){
			try{
				BufferedReader in = new BufferedReader( new FileReader( fi ) );
				String  currentInstruction = in.readLine();
				while ( currentInstruction != null ){
					String[] lista =currentInstruction.split(" "); 
					int f = Integer.parseInt(lista[1].trim()),c =Integer.parseInt(lista[2].trim()); 
					if ( "Celula".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Celula(a,f,c) );
					}
					else if("Barrera".equals(lista[0]) ){
						a.setElemento(f, c, (Elemento) new Barrera(a,f,c) );
					}
					else if ("Conway".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Conway(a,f,c) );
					}
					else if ("Familia".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Familia(a,f,c) );
					}
					else if( "Izquierdosam".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Izquierdosa(a,f,c) );
					}
					else if( "Respawn".equals(lista[0])){
						a.setElemento(f, c, (Elemento) new Respawn(a,f,c) );
					}
					else{
						throw new AutomataException(AutomataException.TIPO_NO_EXISTENTE_ELEMENTO);
					}
					
					currentInstruction = in.readLine();
					cont++;
				}
				in.close();
				return a;
			}
			catch ( AutomataException e){
				throw new AutomataException("Nombre del error:"+e.getMessage()+" en la linea "+cont+", revisar los elementos a ingresar");
			}
			
			catch (  IndexOutOfBoundsException e){
				throw new AutomataException("Nombre del error:"+AutomataException.INDICE_FUERA_DE_RANGO+" en la linea "+cont+"la posicion debe ser menor a 20");
			}
			
			catch( Exception e){
				throw new AutomataException("Ocurrio un error al intentar importar el archivo "+ fi.getName());
			}
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_ES_TXT);
		}
		
	}
	
	
	public static AutomataCelular importarO3 ( File fi ) throws AutomataException{
		AutomataCelular a = new AutomataCelular();
		int cont = 1;
		if( fi.getName().endsWith(".txt") ){
			try{
				BufferedReader in = new BufferedReader( new FileReader( fi ) );
				String  currentInstruction = in.readLine();
				while ( currentInstruction != null ){
					String[] lista =currentInstruction.split(" "); 
					int f = Integer.parseInt(lista[1].trim()),c =Integer.parseInt(lista[2].trim()); 
					
					Class clase = Class.forName("aplicacion."+lista[0]);
					Object object = clase.getDeclaredConstructor(AutomataCelular.class , int.class , int.class).newInstance(a,f,c);

					
					currentInstruction = in.readLine();
					cont++;
				}
				in.close();
				return a;
			}
			
			catch( ClassNotFoundException e ){
				throw new AutomataException("Nombre del error:"+AutomataException.TIPO_NO_EXISTENTE_ELEMENTO+" en la linea "+cont+", revisar los elementos a ingresar");
			}
			catch( NumberFormatException e){
				throw new AutomataException("Nombre del error:"+AutomataException.TIPO_INCORRECTO_POSICION+", en la linea "+cont+" la posicion del elemento no es un numero");
				
				
			}
			
			catch (  IndexOutOfBoundsException e){
				throw new AutomataException("Nombre del error:"+AutomataException.INDICE_FUERA_DE_RANGO+" en la linea "+cont+"la posicion debe ser menor a 20");
			}
			
			catch( Exception e){
				throw new AutomataException("Ocurrio un error al intentar importar el archivo "+ fi.getName());
			}
		}
		else{
			throw new AutomataException(AutomataException.EXTENSION_ARCHIVO_NO_ES_TXT);
		}
		
	}

}
