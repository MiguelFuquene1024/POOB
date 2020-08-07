


public static void salvar( File file, Tablero tablero ){
	
	try{
		PrintWriter pw= new PrintWriter( new FileOutputStream(file) );
		
		ArrayList<Flotas> flotas = tablero.getFlotas(); 	
		pw.println("Numero de Flotas totales es:"+""+flotas.size()+"\n");
	
		for( int x =0 ;x< flotas.size();x++){
			ArrayList<Maquinas>  maquinas = flotas.get(x).getMaquinas(); 
			pw.println("Nombre de la flota numero"+""+x+"es"+flotas.get(x).getName(),"y tiene"+""+maquinas.size()+"Maquinas");			
		}
	}
	
	catch( IOException ioE ){
		System.out.println("El error fue"+ioE.getMessage());
	}
	
}

public static void ArrayList<String > importar( File file ){
	ArraysList<String> nombresFlotas = new ArrayList<String>();
	
	
	try{
		BufferedReader leer = new BufferedReader( new FileReader( file ) );
		String linea = leer.readline()
		while ( linea != null ){
			if ( linea.length() == 0){
				continue;
			}
			nombresFlotas.add( linea.trim() );
			linea = leer.readline()
		leer.close();
		return nombresFLotas;
		}
		
		
	}
	catch( IOException ioE ){
		System.out.println("El error fue"+ioE.getMessage());
	}

}




