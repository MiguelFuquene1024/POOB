package aplicacion;
import java.awt.Color;

public interface Elemento{
	public static int REDONDO =1;
	public static int CUADRADO=2;
	
	Color getColor();
	
	default int getForma(){
		return ( this instanceof Pelota || this instanceof Hueco )? REDONDO:CUADRADO;
	}
	
}