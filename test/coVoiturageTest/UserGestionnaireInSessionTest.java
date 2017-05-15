package coVoiturageTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.CoordGPS;
import covoiturage.User;

public class UserGestionnaireInSessionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		float longitude=45;
		float latitude=50;
		
		CoordGPS escalquens = new CoordGPS(43.518063, 1.562549);  
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887);		
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);
		
		
		
		User user1 = new User("ESCALQUENS","11","toto");
		user1.setCoordonneesGPS(escalquens);
		User.unUserManager.add(user1);
		
		User user2 = new User("GAUMONT","11","titi");
		user2.setCoordonneesGPS(gaumontLabege);
		User.unUserManager.add(user2);
		
			
		User user3 = new User("OCCITANIE5","11","tutu");
		user3.setCoordonneesGPS(occitanie5);
		User.unUserManager.add(user3);
		
		HashMap <String,User> actual = User.unUserManager.usersProcheDeCoordonnees(carrefourLabege, 5);
		// On doit retrouver USER USER3  mais pas user1 qui est a escalquens
		
		System.out.println("Nb de users : " + actual.size());
		assertEquals(2, actual.size());
		
		assertTrue(actual.containsKey("GAUMONT"));
		assertTrue(actual.containsKey("OCCITANIE5"));
		assertFalse(actual.containsKey("ESCALQUENS"));
	
	}

}
