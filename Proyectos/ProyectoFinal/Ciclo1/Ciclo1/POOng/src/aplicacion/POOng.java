package aplicacion;

import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JOptionPane;

import presentacion.Hilo;


/**
 * clase que representa la logica del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 23/04/2020
 * @version 3.0 24/04/2020        
 * */
public class POOng {
	public static int maxScore;
	public Hilo h;
	protected boolean paused; 
	private ArrayList<Jugador> jugadores;
	private ArrayList<Personaje> personajes; 
	private ArrayList<Pelota> pelotas;
	
	
	/**
	 *Constructor del juego POOng
	 *@param maxScore, el puntaje maximo acordado para que acabe el juego
	 * */
	
	
	public POOng(  int maxScore){
		this.h = h;
		this.maxScore = maxScore;
		personajes = new ArrayList<Personaje>();
		jugadores = new ArrayList<Jugador>();
		pelotas = new ArrayList<Pelota>();
		
		pelotas.add( new Pelota(200,200) );
	}
	
	/**
	 * metodo encargado de empezar el juego
	 * */
	public void jugar(){
		if ( rebotaPersonaje() ){
			getPelota(0).reverseD();
		}
		getPelota(0).move();
		int punto = getPelota(0).getSali(); 
		if ( punto!=-1){
			if(jugadores.get(punto).punto()){
				terminar( jugadores.get(punto));
			}
			getPelota(0).reaparecer();
			
		}
		
	}
	
	public void setHilo( Hilo h){
		this.h = h;
	}
	
	/**
	 * metodo encargado de terminar el juego
	 * @param j, Jugador que gano la partida
	 * */
	public void terminar( Jugador j)  {
		JOptionPane.showMessageDialog(null,"El ganador de la partida fue"+j.getNombre(),"Ganador", 0);
		h.pareEjecucion();
		
	}
	
	/**
	 * metodo que dice si la pelota fue golpeada por algun personaje
	 * @return boolean, booleando que dice si la pelota fue golpeada por algun personaje 
	 * */
	public boolean  rebotaPersonaje(){
		return getPelota(0).rebotaPersonaje(getShapePersonaje( 0)) || getPelota(0).rebotaPersonaje(getShapePersonaje( 1));
		
	}
	
	
	/**
	 * metodo que retorna cuantos personajes hay en el juego
	 * @return int, el numero de jugadores en el juego
	 * */
	public int getPersonajes(){
		return personajes.size();
	
	}
	
	/**
	 * metodo que retorna cuantos personajes hay en el juego
	 * @return int, el numero de jugadores en el juego
	 * */
	public void setImagePersonaje( int index, String rute ){
		personajes.get(index).setImage(rute);

	}
	
	
	/**
	 * metodo que se encarga de mover un personaje a la izquierda
	 * @param index, indica cual personaje quiere mover
	 * */
	public void moverPersonaje( int index ){
		if ( index == 0){
			personajes.get(index).moverPersonaje1();
		}
		else{
			personajes.get(index).moverPersonaje2();
		}
		
	}

	/**
	 * metodo encargado de pausar tods los elementos del juego
	 * */
	public void pauseAll(){
		paused = !paused;
		for ( Personaje p: personajes){
			p.pause();
		}
		
		for ( Pelota pe: pelotas){
			pe.pause();
		}
	}
	
	
	/**
	 * clase encargada de devolver la figura de las pelotas que se encuentren en el juego
	 * @param index, indice asociado a la pelota que deseamos mostrar
	 * @retun  Ellipse2D.Double, pelota en el indice indicado en el tablero de juego
	 * */
	public  Ellipse2D.Double getShapePelota( int index ){ 
		return pelotas.get(index).getShape();	
	}
	
	/**
	 * clase encargada de devolver la figura de los personajes que se encuentren en el juego
	 * @param index, indice asociado al personaje que deseamos mostrar
	 * @retun  Rectangle2D.Double, personaje en el indice indicado en el tablero de juego
	 * */
	public Rectangle2D.Double getShapePersonaje( int index){
		return personajes.get(index).getshape();
	}
	
	/**
	 * clase encargada de devolver el personaje que se encuentren en el juego
	 * @param index, indice asociado al personaje que deseamos mostrar
	 * @retun  Personaje , personaje en el indice indicado en el tablero de juego
	 * */
	public  Personaje getPersonaje( int index ){
		return personajes.get(index);
	}
	
	/**
	 * clase encargada de llenar la lista de los personajes en el juego
	 * */
	public void establezcaPersonajes(){
		for( Jugador j:jugadores){
			personajes.add( j.getPersonaje() );
		}
	
	}
	
	/**
	 * clase encargada de añadir un nuevo jugador
	 * @param j, nuevo jugador que ingreso al juego
	 * */
	public void addJugador( Jugador j ){
		jugadores.add(j);
	}
	
	public void addCpu( ){
	
	}
	
	/**
	 * clase encargada de retornar la pelota del juego actual
	 * @param index, indice de la pelota que deseamos recuperar
	 * @return Pelota, retorna la pelota en el indice deseado
	 * */
	public Pelota getPelota( int index ){
		return pelotas.get(index);
	}
	

}
