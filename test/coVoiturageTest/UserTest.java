package coVoiturageTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
//import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.CoordGPS;
import covoiturage.IntersectionUser;
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
		ArrayList<CoordGPS> route = new ArrayList<CoordGPS>();
		route.add(carrefourLabege);
		route.add(gaumontLabege);
		route.add(villefrancheL);
		route.add(augustins);
		//CoordGPS route[] = {villefrancheL,gaumontLabege,carrefourLabege,augustins};
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
		IntersectionUser inter1= user.passePresDeCoord(cugnaux, rayon);
		assertTrue(inter1==null);
		
		//proche en point2 "gaumontLabege"-->vrai
		CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887);
		IntersectionUser inter2= user.passePresDeCoord(occitanie5, rayon);
		assertTrue(inter2!=null);
		
		//proche en point final "augustins"-->vrai
		CoordGPS capitole = new CoordGPS(43.604405, 1.443350);
		IntersectionUser inter3=user.passePresDeCoord(capitole, rayon);
		assertTrue(inter3!=null);
	}

	@Test
	public void testSetRouteEnChaine(){	
		String coord1Ch = "(43.399575,1.719759)";
		String coord2Ch = "(43.518063,1.562549)";
		String coord3Ch = "(43.540139,1.510688)";
		
		String uneRoute = coord1Ch + coord2Ch + coord3Ch; 
		user.setRoute(uneRoute);
		
		ArrayList<CoordGPS> ptsDeRoute = user.getRoute();
		


		assertEquals(ptsDeRoute.size(),3);
		assertEquals(ptsDeRoute.get(0).toString(), coord1Ch); 
		assertEquals(ptsDeRoute.get(1).toString(), coord2Ch); 
		assertEquals(ptsDeRoute.get(2).toString(), coord3Ch); 
	}
}
