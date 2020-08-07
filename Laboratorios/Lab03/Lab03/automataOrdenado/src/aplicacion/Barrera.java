package aplicacion;
import java.awt.Color;

/**
 * 
 *
 * @author ivan camilo rincon saavedra
 * @author miguel angel fuquene 
 */
public class Barrera implements Elemento
{
    private AutomataCelular automata;
    private int fila,columna;
    protected Color color;
    /**
     * Constructor for objects of class Barrera
     */
    public Barrera( AutomataCelular ac , int fila, int columna )
    {
        automata=ac;
        this.fila=fila;
        this.columna=columna;
        automata.setElemento(fila,columna,(Elemento)this);  
        color=Color.black;
       
    }

    /**Retorna el color de  la célula
    @return 
     */
    public final Color getColor(){
        return color;
    }
}
