package aplicacion;
import java.awt.Color;

public interface Elemento{
  final static int REDONDA = 1;
  final static int CUADRADA = 2;
  
  default void decida(){
  };
   
  default void cambie(){
  };
  
  default int getForma(){
      return (this instanceof  Celula  ) ? REDONDA: CUADRADA;
  }
  
  Color getColor();
  
  default boolean isVivo(){
      return false;
  }
  
}
