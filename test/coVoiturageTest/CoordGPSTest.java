package coVoiturageTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import donnees.CoordGPS;

public class CoordGPSTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void estNonProche() {
		CoordGPS escalquens = new CoordGPS(43.518063, 1.562549);  
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		
		
		assertFalse ( escalquens.estProche(carrefourLabege,5));   // Carrefour labege est a 7km de escalquens
		
	
	}
	
	@Test
	public void estProche() {
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887);
		
		assertTrue ( occitanie5.estProche(carrefourLabege, 5));   
		// Carrefour labege est a moins de 5 km de 5 rue occitanie
	
	}
	
	@Test
	public void construteurParChaine() {
		CoordGPS uneCoord = new CoordGPS("43.542660, 1.508887");
		System.out.println(uneCoord.getLatitude());
		System.out.println(uneCoord.getLongitude());
		assertEquals(43.542660, uneCoord.getLatitude());
		assertEquals(1.508887, uneCoord.getLongitude());
		
		
	}

}
