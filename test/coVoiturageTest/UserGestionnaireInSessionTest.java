package coVoiturageTest;

import static org.junit.Assert.*;

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
		CoordGPS coordRef = new CoordGPS(longitude, latitude);
		User user1 = new User("USER1","11","toto");
		user1.setCoordonneesGPS(new CoordGPS(longitude+1, latitude+ 1));
		
		User user2 = new User("USER2","11","titi");
		user2.setCoordonneesGPS(new CoordGPS(longitude+5, latitude));
		
		User user2bis = new User("USER2","11","titi");
		user2bis.setCoordonneesGPS(new CoordGPS(longitude, latitude+5));
		
		
		User user3 = new User("USER3","11","tutu");
		user2.setCoordonneesGPS(new CoordGPS(longitude + 10, latitude +10));
		
		User.unUserManager.usersProcheDeCoordonnees(coordRef, 7);
		// On doit retrouver USER USER2 USER2BIS mais pas USER3
		
		fail("Not yet implemented");
	}

}
