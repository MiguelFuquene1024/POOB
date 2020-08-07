package aplicacion;

public class SINAPExcepcion extends Exception{
	public static final String EMPTY_INERNATIONAL_NAME ="el area tiene un nombre internacional vacio";
	public static final String DUPLICATED_AREA ="el area ya se encuentra registrada";
	public static final String EMPTY_PARAMETERS ="todos los campos son obligatorios";
	
	public SINAPExcepcion(String message){
		super(message);
	}
	
}