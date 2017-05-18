package coVoiturageTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.CoordGPS;
import covoiturage.User;

public class UserTest {

	private User user;
	private int rayon = 5;

	@Before
	public void setUp() throws Exception {
		//fabrique route
		CoordGPS villefrancheL = new CoordGPS(43.399575, 1.719759);
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS augustins = new CoordGPS(43.600953, 1.446260);			
		CoordGPS route[] = {villefrancheL,gaumontLabege,carrefourLabege,augustins};
		//new User + setRoute
		user = new User("Toto@tracetareoute.com","11","toto");
		user.setRoute(route);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPassePresDeCoord() {
		//non proche doit retourner faux
		CoordGPS cugnaux = new CoordGPS(43.535666, 1.346324);
		assertFalse(user.passePresDeCoord(cugnaux, rayon));
		
		//proche en point2 "gaumontLabege"-->vrai
		CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887);
		assertTrue(user.passePresDeCoord(occitanie5, rayon));
		
		//proche en point final "augustins"-->vrai
		CoordGPS capitole = new CoordGPS(43.604405, 1.443350);
		assertTrue(user.passePresDeCoord(capitole, rayon));
	}
}
