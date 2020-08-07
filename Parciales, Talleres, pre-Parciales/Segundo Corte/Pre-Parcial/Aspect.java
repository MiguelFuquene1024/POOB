//Aspecto
import java.util.ArrayList; 

public class Aspect{
	private String name;
	private int minutes;
	private int semester;
	
	public Aspect(){
	}
	
	public int goodReactions( s Student ) throws  ECISeriesExcepcion {
		int cont = 0;
		ArrayList<Reaction> reactions= s.getReactions();
		
		if ( !(semester.equals( s.getSemester() )) ){
			throw new ECISeriesExcepcion( ECISeriesExcepcion.NO_SEMESTER);
		}

		for ( Reaction r: reactions ){
			if ( r.getScene() == null  ){
				throw new ECISeriesExcepcion( ECISeriesExcepcion.NO_SCENES);
			}
			if ( r.getAfinity() > 75 ){
				cont++;
			}
		}
		return cont;
	
		
	}
	
	public int minutes( s Student ) throws  ECISeriesExcepcion{
		int minutes = 0;
		ArrayList<Reaction> reactions= s.getReactions();
		
		if ( !(semester.equals( s.getSemester() )) ){
			throw new ECISeriesExcepcion( ECISeriesExcepcion.NO_SEMESTER);
		}

		for ( Reaction r: reactions ){
			if ( r.getScene() == null  ){
				throw new ECISeriesExcepcion( ECISeriesExcepcion.NO_SCENES);
			}
			Aspect a = r.getAspect();
			minutes+= a.getMinutes();
		}
		return minutes;
	}