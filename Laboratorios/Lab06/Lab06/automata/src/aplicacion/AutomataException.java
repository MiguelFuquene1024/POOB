package aplicacion;


public class  AutomataException extends Exception{
	public static final String SE_ENCUENTRA_EN_CONSTRUCCION ="Opcion  en construccion";
	public static final String EXTENSION_ARCHIVO_NO_VALIDO ="La extension del archivo no es  .dat";
	public static final String EXTENSION_ARCHIVO_NO_ES_TXT ="La extension del archivo no es  .txt";
	public static final String INDICE_FUERA_DE_RANGO ="La posicion no debe superar a 20";
	public static final String TIPO_INCORRECTO_POSICION ="La posicion no es un numero";
	public static final String TIPO_NO_EXISTENTE_ELEMENTO ="El elemento ingresado no existe";
	public  AutomataException(  String message ){
		super(message);
	}
}