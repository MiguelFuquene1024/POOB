
package test;

import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class sinapTest {
   
	@Test
	public void deberiaAdicionar() throws SINAPExcepcion {
		Sinap sinap = new Sinap();
		Area[] areas = {new Area("Laguna de Guatavita","Lake of Guatavita","Sesquile","2000"," La laguna esta a una de altitud de 3.100 m s. n. m. y a una temperatura de 5 a 11c. Es una depresión montañosa de forma perfectamente circular, con cerca 700 m de diámetro, rodeada de bosques nativos de encenillos"),
						new Area("Laguna De Tota","Lake of Tota","Boyaca","52 km^2","El lago de Tota es un cuerpo de agua natural situado en el departamento de Boyaca, Colombia, en jurisdiccion de los municipios de Cuitiva, Tota y Aquitania. Se encuentra ubicado a 34 km al sur de Sogamoso, aproximadamente 200 km al noroeste de Bogota, la capital del pais.")};
		
		for (Area a : areas ){
			sinap.adicioneDetalles( a );
			assertEquals( a, sinap.getDetalles(a.getNombre(),a.getName()));
		}		
	}	
		
	@Test
	public void deberiaEnlistar()  throws SINAPExcepcion{
		Sinap sinap = new Sinap();
		Area[] areas = {new Area("Laguna de Guatavita","Lake of Guatavita","Sesquile","2000"," La laguna esta a una de altitud de 3.100 m s. n. m. y a una temperatura de 5 a 11c."),
						new Area("Laguna De Tota","Lake of Tota","Boyaca","52 km^2","El lago de Tota es un cuerpo de agua natural situado en el departamento de Boyaca, Colombia.")};
		StringBuffer answ =new StringBuffer();
		
		for (Area a : areas ){
			sinap.adicioneDetalles( a );
			answ.append( a.toString() );
			answ.append('\n');
			answ.append('\n');
		}
		assertEquals( answ.toString(), sinap.toString())   ;
	}
	
	
	@Test
	public void deberiaContarCuantasAreasSeAñadieron() throws SINAPExcepcion {
		Sinap sinap = new Sinap();
		Area[] areas = {new Area("Laguna de Guatavita","Lake of Guatavita","Sesquile","2000"," La laguna esta a una de altitud de 3.100 m s. n. m. y a una temperatura de 5 a 11c."),
						new Area("Laguna De Tota","Lake of Tota","Boyaca","52 km^2","El lago de Tota es un cuerpo de agua natural situado en el departamento de Boyaca, Colombia.")};
		for (Area a : areas ){
			sinap.adicioneDetalles( a );
		}
		assertTrue( sinap.numeroAreas() == 2)   ;
	}
	
	@Test
	public void deberiaFallarPorFaltaDeNombreInternacional() throws SINAPExcepcion{
		Sinap sinap = new Sinap();
		try{
			sinap.adicione( "Tuparro","","Orinoquía ","548.000","Es una extensa sabana verde surcada por grandes ríos con potentes raudales y playas doradas, pequeños caños de aguas cristalinas, bosques de galería, morichales y saladillales, además de enormes rocas cristalinas en forma de cerros redondeados.") ;
		}
		catch(SINAPExcepcion e){
			assertEquals(e.getMessage(), SINAPExcepcion.EMPTY_INERNATIONAL_NAME);
		}
	}
	
	@Test
	public void deberiaFallarPorIngresarLaMismaArea() throws SINAPExcepcion{
		Sinap sinap = new Sinap();
		
		try{
			for ( int i = 0; i < 2; i++ ){
				sinap.adicione("Tuparro","Tuparro National Park","Orinoquía ","548.000","Es una extensa sabana verde surcada por grandes ríos con potentes raudales y playas doradas, pequeños caños de aguas cristalinas, bosques de galería, morichales y saladillales, además de enormes rocas cristalinas en forma de cerros redondeados.");
			}
		}
		catch(SINAPExcepcion e){
			assertEquals(e.getMessage(), SINAPExcepcion.DUPLICATED_AREA);
		}
	}
	
	@Test
	public void deberiaFallarPorFaltaDeParametros() throws SINAPExcepcion{
		Sinap sinap = new Sinap();
		try{
			sinap.adicione( "Tuparro","Tuparro National Park","Orinoquía ","548.000","") ;
		}
		catch(SINAPExcepcion e){
			assertEquals(e.getMessage(), SINAPExcepcion.EMPTY_PARAMETERS);
			
		}
	}
	
	@Test
	public void deberiaBuscar() throws SINAPExcepcion{
		Sinap sinap = new Sinap();
		try{
			
			
			//sinap.busque(String prefijo);
			
			Area area = new Area("Tuparro","Tuparro National Park","Orinoquía ","548.000","Es una extensa sabana verde surcada por grandes ríos con potentes raudales y playas doradas, pequeños caños de aguas cristalinas, bosques de galería, morichales y saladillales, además de enormes rocas cristalinas en forma de cerros redondeados.");
			sinap.adicioneDetalles(area);
			
			//System.out.println( sinap.busque("T").contains( area ) );
			assertFalse( !(sinap.busque("T").contains( area )) );
		}
		catch(SINAPExcepcion e){
			throw e;
		}
	}
	
	
}					