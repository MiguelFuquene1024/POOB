package aplicacion;
import java.util.Comparator;

public class OrdenarPorFuncion4 implements Comparator<Ruta> {
    @Override
    public int compare(Ruta o1, Ruta o2) {
		
		String a = o1.getNumeroEstaciones()+""+o1.getNombre();
        String b = o2.getNumeroEstaciones()+""+o2.getNombre();
		return a.compareTo(b);
    }
}