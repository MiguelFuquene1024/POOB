package aplicacion;
import java.util.Comparator;

public class OrdenarParadasPorOrden implements Comparator<Estacion> {
    @Override
    public int compare(Estacion o1, Estacion o2) {
		Integer orden1= new Integer(o1.getOrden());
		Integer orden2= new Integer(o2.getOrden());
		return orden1.compareTo(orden2);
    }
}