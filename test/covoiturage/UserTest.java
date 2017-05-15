package covoiturage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPassePresDeCoord() {
		// Creer un user.
		// Creer une route avec 4 coordonnées GPS.
		// Ex: Villefranche de lauragais, carrefour labege, gaumont labege, capitole
		
		// Creer coordonnées 5 rue occitanie a labege
		
		// Appeler user1.passePresDeCoord(occitanie5, 5)
		// Doit repondre vrai car la 2eme etape passe pres de l'adresse occitanie5
		
		
		// Creer coord a escalquens, qui doit repondre faux.
		
		// Verifier aussi que ca marche sur le premier et dernier point
		// de la route a savoir, près de villefranche et près du capitole
		
		// Creer une coordGPS pas loi
		fail("Not yet implemented");
	}

}
