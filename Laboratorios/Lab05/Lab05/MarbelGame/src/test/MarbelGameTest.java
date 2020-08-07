package test;

import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;



public class MarbelGameTest {
	
	
	public void mostrar( Elemento[][] prueba ){
		for( Elemento[] ele : prueba ){
			for ( Elemento e :ele ){
				if ( e instanceof Hueco ){
					System.out.print( "3");
					System.out.print(",");
				}
				else if ( e instanceof Pelota ){
					System.out.print( "1");
					System.out.print(",");
				}
				else if ( e instanceof Barrera ){
					System.out.print( "2");
					System.out.print(",");
				}
				else{
					System.out.print( "0");
					System.out.print(",");
				}
			}
			System.out.println();
		}
		System.out.println("\n");
	}		
	@Test
	public void deberiaMoverArriba() {
		Elemento [][] prueba = new Elemento[][] {
			{null,null,(Elemento)new Barrera(0,2,Color.BLACK)},
			{null,(Elemento) new Hueco(1,1,Color.GREEN),(Elemento)new Barrera(1,2,Color.BLACK)},
			{(Elemento)new Pelota(0,1,Color.RED),null,(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarNorte();
	
		assertTrue( m.getElemento(0,0) instanceof Pelota );
		assertTrue( m.getElemento(2,0) == null   );
		
		
		
										   
	}	
	
	@Test
	public void deberiaMoverAbajo() {
		Elemento [][] prueba = new Elemento[][] {
			{(Elemento)new Pelota(0,1,Color.RED),null,(Elemento)new Barrera(0,2,Color.BLACK)},
			{null,(Elemento) new Hueco(1,1,Color.GREEN),(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,null,(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarSur();
	
		assertTrue( m.getElemento(0,0)  == null   );
		assertTrue( m.getElemento(2,0)instanceof Pelota  );
	}	
	
	@Test
	public void deberiaMoverDerecha() {
		Elemento [][] prueba = new Elemento[][] {
			{(Elemento)new Pelota(0,1,Color.RED),null,null},
			{null,(Elemento) new Hueco(1,1,Color.GREEN),(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,null,(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarEste();
	
		assertTrue( m.getElemento(0,0)  == null   );
		assertTrue( m.getElemento(0,2)instanceof Pelota  );
	}	
	
	@Test
	public void deberiaMoverIzquierda() {
		Elemento [][] prueba = new Elemento[][] {
			{null,null,(Elemento)new Pelota(0,1,Color.RED)},
			{null,(Elemento) new Hueco(1,1,Color.GREEN),(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,null,(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarOeste();
	
		assertTrue( m.getElemento(0,0)  != null   );
		assertTrue( m.getElemento(0,0)  instanceof Pelota   );
		assertFalse( m.getElemento(0,2) != null );
	}
	
	@Test
	public void deberiaCaerEnHueco() {
		Elemento [][] prueba = new Elemento[][] {{null,(Elemento)new Pelota(0,1,Color.RED),(Elemento)new Barrera(0,2,Color.BLACK)},{null,(Elemento)new Hueco(1,1,Color.RED),(Elemento)new Barrera(1,2,Color.BLACK)},{null,null,null}};
		
		MarbelGame m =  new MarbelGame( prueba );
		m.deslizarSur();
		
		
		
		assertEquals( m.getElemento(0,1) , null   );
		Hueco h = (Hueco)m.getElemento(1,1);
		assertFalse( h.isEmpty() );
	}
	
	@Test
	public void deberiaPasarEncimaDeUnHuecoLLeno() {
		Hueco h = new Hueco(1,1,Color.GREEN);
		h.llenarHueco( new Pelota(0,1,Color.GREEN) );
		
		Elemento [][] prueba = new Elemento[][] {
			{null,null,(Elemento)new Barrera(0,2,Color.BLACK)},
			{null,(Elemento) h,(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,(Elemento)new Pelota(0,1,Color.RED),(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarNorte();
	
		assertFalse( m.getElemento(0,1) == null   );
		assertTrue( m.getElemento(0,1) instanceof Pelota);
		assertTrue( m.getElemento(2,1) == null   );
	}
		
	@Test
	public void deberiaReiniciar() {
		Hueco h = new Hueco(1,1,Color.GREEN);
		h.llenarHueco( new Pelota(0,1,Color.GREEN) );
		
		Elemento [][] prueba = new Elemento[][] {
			{null,null,(Elemento)new Barrera(0,2,Color.BLACK)},
			{null,(Elemento) h,(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,(Elemento)new Pelota(0,1,Color.RED),(Elemento)new Hueco(1,1,Color.RED)}};
		
		MarbelGame m =  new MarbelGame( prueba );
		
		m.deslizarNorte();
		m.setTableroInicial();
		
		Elemento[][] t = m.getTablero();
		for ( int i = 0; i < prueba.length;i++ ){
			for ( int j = 0; j < prueba.length;j++ ){
				if ( t[i][j]!= null ){
					assertTrue( t[i][j].getClass() == prueba[i][j].getClass());
					assertTrue( t[i][j].getColor() == prueba[i][j].getColor());
				}
				else{
					assertTrue( (prueba[i][j] == t[i][j]) && ( prueba[i][j] == null ));
				}		
			}
		}
	}
	
	@Test
	public void deberiaCambiarConfiguracion() {
		MarbelGame m =  new MarbelGame( 4, Color.RED );
		m.setElementosAleatorios( 1,2,Color.BLACK);
		
		Elemento [][] configuracion = new Elemento[][] {
			{(Elemento)new Pelota(0,1,Color.RED),null,null},
			{null,(Elemento) new Hueco(1,1,Color.GREEN),(Elemento)new Barrera(1,2,Color.BLACK)},
			{null,null,(Elemento)new Hueco(1,1,Color.RED)}};
		
		m =  new MarbelGame( configuracion );
		
		assertFalse( m.getSize() == 4);
		assertEquals( m.getSize() ,3);
		
	}
		
	
	
}					