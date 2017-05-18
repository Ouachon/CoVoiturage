package coVoiturageTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.CoordGPS;
import covoiturage.ProfilUser;
import covoiturage.User;
import covoiturage.UserGestionnaireInSession;

public class UserGestionnaireInSessionTest {
	CoordGPS villefrancheL  = new CoordGPS(43.399575, 1.719759);
	CoordGPS escalquens = new CoordGPS(43.518063, 1.562549); 
	CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688); 
	CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069); 
	CoordGPS augustins = new CoordGPS(43.600953, 1.446260);
	CoordGPS cugnaux  = new CoordGPS(43.535666, 1.346324);
	CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887); 
	CoordGPS purpan = new CoordGPS(43.611634, 1.397174);
	CoordGPS stOrens = new CoordGPS(43.548706, 1.535876);
	UserGestionnaireInSession myUserManager =  UserGestionnaireInSession.getInstance();

	@Before
	public void setUp() throws Exception {
		//il est indispensable de vider la liste des users avant de lancer chaque test 
		//pour en maîtriser le contenu et donc la cohérence des tests
		//fonction de reset crée dans la classe
		 myUserManager.getListeDesUsers().clear();
	}

	@After
	public void tearDown() throws Exception {
		 myUserManager.getListeDesUsers().clear();
	}

	@Test
	public void testUsersProcheDe() {
		
		User user1 = new User("toto@cugnaux","11","toto");
		user1.setCoordonneesGPS(cugnaux);
		myUserManager.add(user1);
		
		User user2 = new User("titi@GAUMONT","11","titi");
		user2.setCoordonneesGPS(gaumontLabege);
		myUserManager.add(user2);
			
		User user3 = new User("tutu@OCCITANIE5","11","tutu");
		user3.setCoordonneesGPS(occitanie5);
		myUserManager.add(user3);
		
		HashMap <String,User> actual = myUserManager.usersProcheDeCoordonnees(carrefourLabege, 5);
		// On doit retrouver USER USER3  mais pas user1 qui est a escalquens	
		System.out.println("Nb de users : " + actual.size());
		assertEquals(2, actual.size());
		
		assertTrue(actual.containsKey("titi@GAUMONT"));
		assertTrue(actual.containsKey("tutu@OCCITANIE5"));
		assertFalse(actual.containsKey("toto@cugnaux"));	
	}
	@Test
	public void testConducteurPassePresDe(){
		// unPassager
		User unPassager = new User("stOrens@tracetaroute.com","11","StOrens");
		unPassager.setCoordonneesGPS(stOrens);  
		
		//Conducteur1 + setRoute passant à proximité
		CoordGPS route1[] = {villefrancheL,gaumontLabege,carrefourLabege,augustins};
		User conducteur1 = new User("VilleFrToAugustins@tracetareoute.com","11","VilleFrToAugustins");
		conducteur1.setRoute(route1);
		
		//Conducteur2 + setRoute pas à prox
		CoordGPS route2[] = {cugnaux, purpan,augustins};
		User conducteur2 = new User("CugnauxToAugustins@tracetareoute.com","11","CugnauxToAugustins");
		conducteur2.setRoute(route2);
		
		//Conducteur3 + setRoutepassant aussi à prox
		CoordGPS[] route3 = {escalquens,gaumontLabege,occitanie5};
		User conducteur3 = new User("EscalqToOccitanie@tracetaroute.com","11","EscalToOccitanie");
		conducteur3.setRoute(route3);

		myUserManager.add(conducteur1);
		myUserManager.add(conducteur2);
		myUserManager.add(conducteur3);
		
		//HashMap<String,User> conducteursPassantPresDe = new HashMap<String,User>();
		HashMap<String,User>conducteursPassantPresDe = myUserManager.conducteursPassePresDe(unPassager);
		
		//= myUserManager.conducteursPassePresDe(unPassager);
		//la map doit contenir 2 enregistrements : conducteur 1 et 3, le 2 ne doit pas y ^tre
		assertEquals(conducteursPassantPresDe.size(),2);
		assertTrue(conducteursPassantPresDe.containsKey("VilleFrToAugustins@tracetareoute.com"));
		assertFalse(conducteursPassantPresDe.containsKey("CugnauxToAugustins@tracetaroute.com"));
		assertTrue(conducteursPassantPresDe.containsKey("EscalqToOccitanie@tracetaroute.com"));	
		
	}
}

//	
//	@Test
//	public void testCorrelationAvecProfil() {
//		// Creer un user fumeur , 40 ans , homme
//		User user1 = new User("toto@oo", "11", "eric");
//		user1.setSexe("H");
//		user1.setAge(40);
//		user1.setFumeur("F");
//
//		// et une femme non fumeuse de 25 ans
//		User user2 = new User("titi@oo", "11", "titi");
//		user2.setSexe("F");
//		user2.setAge(25);
//		user2.setFumeur("N");
//
//		// Et 3 autres users ( 5 en tout)
//		// Les ajouter dans les utilisateurs
//		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
//		myUserManager.add(user1);
//		myUserManager.add(user2);
//
//		// On cree un profil
//		// Toujours Poids critere  fumeur = 100, age=1, sexe = 10 ;
//		int pondFumeur=100;
//		int pondAge=1;
//		int pondSexe=10;// Creer un profil en cherchant  Femme de moins de 30 ans, non fumeur
//		// Score 0
//		ProfilUser profil1 = new ProfilUser("N","1", "F", pondFumeur,pondAge,pondSexe); 
//		HashMap<User,Integer> actual = myUserManager.correlationAvecProfil(profil1,myUserManager.getListeDesUsers());
//		//assertEquals(5, profil1.scoreCompatibiliteAvecUser(user1));
//		//Test que l'element 1 de la liste est bien celui le + compatible
//		// que l'element 5 est le moins compatible
//		// Et qu' il y a bien 5 élements.
//		fail("not implemented");
//	}
//	
//	
//	// Et encore au dessus
//	//  on s'assurera qu'on trouvera bien 3 des 5 conducteurs
//	// passant pres de chez moi et trié en fonction de mes critères
//	// Ce qui implique de stocker la route de 
//	//  chaque conducteur vers son travail ?????
//	// et balayer alors tous les points de cette route
//	// (espacé d'un rayon a définir pour ne pas tout regarder tous les 100 metres)
//	
//	
//
//}
