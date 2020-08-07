package aplicacion;

import java.util.Random;

/**
 * clase que representa la clase Objetivo del juego Poong
 * Los objetivos tendrán un valor aleatorio entre 2 y el número de puntos totales dividido en dos. Los
 *objetivos permanecen 10s.
 * */
public class Objetivo extends Sorpresa {
	private static final long serialVersionUID = 2194631718840527649L;
	private int allPoints; 
	
	/**
	 * constructor de la clase Objetivo
	 * @param xPos ,double que representa la posicion en x del Objetivo
	 * @param yPos, double que representa la posicion en y del Objetivo
	 * */
	public Objetivo(double xPos, double yPos){
		super(xPos, yPos);
	}

	
	/**
	 * 
	 * */
	public void reaccionar(POOng p){
		Random rnd = new Random();
		int index = p.getLastPlayer();
		allPoints = 0;
		for ( int x=0; x < p.getAmountPersonajes() ;x++){
			allPoints+=p.getJugador(x).getScore();
		}
		int newPoints = rnd.nextInt((int)allPoints/2 + 1);
		newPoints = ( newPoints<2)?2:newPoints;
		System.out.println(newPoints);
		p.getJugador(index).addScore(newPoints);
		
		if(p.getJugador(index).getScore()> p.maxScore){
			p.terminar( p.getJugador(index));
		}
		
		
		
		
	}
	
	
}
