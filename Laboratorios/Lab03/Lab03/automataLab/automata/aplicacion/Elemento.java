package aplicacion;
import java.awt.Color;


/**
 * Clase que describe los comportamientos de un Elemento 
 *
 * @author Ivan Camilo Rincon Saavedra
 * @author Miguel Angel Fuquene 
 * @version1.0 12/03/2020
 */
public interface Elemento{
  final static int REDONDA = 1;
  final static int CUADRADA = 2;
  
  /**
     el decidir un estado de un elemento por defecto es no hacer nada
     */
  default void decida(){
  };
   
  /**
     el cambiar un estado de un elemento por defecto es no hacer nada
     */
  default void cambie(){
  };
  
  /**
     metodo que dice si la forma del elemento es redonda o cuadrada
     @int, 1 si es el elemento es redonda o 2 si el elemnto es cuadrado
     */
  default int getForma(){
      return (this instanceof  Celula  ) ? REDONDA: CUADRADA;
  }
  
  /**
     metodo abstracto el cual devuelve el color del elemento
     @return Color, que representa el color del elemento seleciconado
     */
  Color getColor();
  
  /**
     metodo que dice si el elemento se encuentra viva o no 
     @return boolean, que representa el estado del elemento
     */
  default boolean isVivo(){
      return false;
  }
  
}
