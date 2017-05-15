package coVoiturageTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.CoordGPS;

public class CoordGPSTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void estProche() {
		CoordGPS BL = new CoordGPS(45.25,45.25);
		CoordGPS StOrens = new CoordGPS(45.26,45.25);
		
		assertTrue ( BL.estProche(StOrens,5));   // BL a moins de 5km de st orens
	
	}
	
	@Test
	public void estNonProche() {
		
		CoordGPS BL = new CoordGPS(45.25,45.25);
		CoordGPS Blagnac = new CoordGPS(55.25,50.25);
		
		assertFalse ( BL.estProche(Blagnac, 5));   // BL a plus  de 5km de Blagnac
	
		fail("Not yet implemented");
	}

}
