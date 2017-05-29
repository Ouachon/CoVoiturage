package coVoiturageTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import donnees.CoordGPS;
import donnees.ProfilUser;
import donnees.User;
import metier.IntersectionUser;
import metier.UserGestionnaireInSession;

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


	public void testUsersProcheDe() {
		
		User user1 = new User("toto@cugnaux","11","toto");
		user1.setCoordonneesGPSMaison(cugnaux);
		myUserManager.add(user1);
		
		User user2 = new User("titi@GAUMONT","11","titi");
		user2.setCoordonneesGPSMaison(gaumontLabege);
		myUserManager.add(user2);
			
		User user3 = new User("tutu@OCCITANIE5","11","tutu");
		user3.setCoordonneesGPSMaison(occitanie5);
		myUserManager.add(user3);
		
		HashMap <String,User> actual = myUserManager.usersProcheDeCoordonnees(carrefourLabege, 5);
		// On doit retrouver USER USER3  mais pas user1 qui est a escalquens	
		System.out.println("Nb de users : " + actual.size());
		assertEquals(2, actual.size());
		
		assertTrue(actual.containsKey("titi@GAUMONT"));
		assertTrue(actual.containsKey("tutu@OCCITANIE5"));
		assertFalse(actual.containsKey("toto@cugnaux"));	
	}
	
	public void testConducteurPassePresDeMaisonDe(){
		// unPassager
		User unPassager = new User("stOrens@tracetaroute.com","11","StOrens");
		unPassager.setCoordonneesGPSMaison(stOrens);  
		
		//Conducteur1 + setRoute passant à proximité
		ArrayList<CoordGPS> route1 = new ArrayList<CoordGPS>();
		route1.add(villefrancheL);
		route1.add(gaumontLabege);
		route1.add(carrefourLabege);
		route1.add(augustins);
			//{villefrancheL,gaumontLabege,carrefourLabege,augustins};
		User conducteurVillefranche = new User("VilleFrToAugustins@tracetareoute.com","11","VilleFrToAugustins");
		conducteurVillefranche.setRoute(route1);
		
		//Conducteur2 + setRoute pas à prox
		ArrayList<CoordGPS> route2 = new ArrayList<CoordGPS>();
		route2.add(cugnaux);
		route2.add(purpan);
		route2.add(augustins);
		User conducteurCugnaux = new User("CugnauxToAugustins@tracetareoute.com","11","CugnauxToAugustins");
		conducteurCugnaux.setRoute(route2);
		
		//Conducteur3 + setRoutepassant aussi à prox
		ArrayList<CoordGPS> route3 = new ArrayList<CoordGPS>();
		route3.add(escalquens);
		route3.add(gaumontLabege);
		route3.add(occitanie5);
		User conducteurEscalquens = new User("EscalqToOccitanie@tracetaroute.com","11","EscalToOccitanie");
		conducteurEscalquens.setRoute(route3);

		myUserManager.add(conducteurVillefranche);
		myUserManager.add(conducteurCugnaux);
		myUserManager.add(conducteurEscalquens);
		
		//HashMap<String,User> conducteursPassantPresDe = new HashMap<String,User>();
		HashMap<User,IntersectionUser>conducteursPassantPresDe = myUserManager.conducteursPassePresDeMaisonDe(unPassager);
		
		//= myUserManager.conducteursPassePresDe(unPassager);
		//la map doit contenir 2 enregistrements : conducteur 1 et 3, le 2 ne doit pas y ^tre
		assertEquals(conducteursPassantPresDe.size(),2);
		assertTrue(conducteursPassantPresDe.containsKey(conducteurVillefranche));//"VilleFrToAugustins@tracetareoute.com"));
		assertFalse(conducteursPassantPresDe.containsKey(conducteurCugnaux));//"CugnauxToAugustins@tracetaroute.com"));
		assertTrue(conducteursPassantPresDe.containsKey(conducteurEscalquens));//"EscalqToOccitanie@tracetaroute.com"));		
	}
	
	@Test
	public void testConducteursPassePresDeRouteDe() {
		//Conducteur1 + setRoute passant à proximité
				ArrayList<CoordGPS> route1 = new ArrayList<CoordGPS>();
				route1.add(villefrancheL);
				route1.add(carrefourLabege);
				route1.add(augustins);
					//{villefrancheL,gaumontLabege,carrefourLabege,augustins};
				User conducteurVillefranche = new User("VilleFrToAugustins@tracetareoute.com","11","VilleFrToAugustins");
				conducteurVillefranche.setRoute(route1);
				
				ArrayList<CoordGPS> route2 = new ArrayList<CoordGPS>();
				route2.add(purpan);
				route2.add(cugnaux);
				route2.add(augustins);
					//{villefrancheL,gaumontLabege,carrefourLabege,augustins};
				User conducteurPurpan = new User("purpan@tracetareoute.com","11","purpan");
				conducteurPurpan.setRoute(route2);
				
				myUserManager.add(conducteurVillefranche);
				myUserManager.add(conducteurPurpan);
				
				HashMap<User,IntersectionUser> result = myUserManager.conducteursPassePresDeRouteDe(conducteurPurpan);
				System.out.println("taille resultat = " + result.size());
				assertEquals(1, result.size());
	}
	
	
	public void testCorrelationEntreUnUserEtListeConducteursProches() {

		// unPassager NonFumeur 30Ans Homme - Conducteurs:  Poids critere  fumeur = 100, age=1, sexe = 10 ;
		User unPassager = new User("unPassager@tracetaroute.com","11","unPassager");
		unPassager.setCoordonneesGPSMaison(stOrens); 
		unPassager.setAge(30);
		unPassager.setSexe("H");
		unPassager.setFumeur("N");

		//Conducteur1 + setRoute passant à proximité
		ArrayList<CoordGPS> route1 = new ArrayList<CoordGPS>();
		route1.add(villefrancheL);
		route1.add(gaumontLabege);
		route1.add(carrefourLabege);
		route1.add(augustins);
		User unH25nf = new User("unH25nf@VilleFrToAugustins","11","VilleFrToAugustins");
		ProfilUser profilH25nf = new ProfilUser("N", "0", "H", 100, 1, 10);
		unH25nf.setRoute(route1);
		unH25nf.setProfilConducteur(profilH25nf);
		//User Homme nonFumeur 25ans ==> doit retourner score 111
		unH25nf.setSexe("H");
		unH25nf.setAge(25);
		unH25nf.setFumeur("N");

		//Conducteur2 + setRoute pas à prox
		ArrayList<CoordGPS> route2 = new ArrayList<CoordGPS>();
		route2.add(cugnaux);
		route2.add(purpan);
		route2.add(augustins);
		User unH40nf = new User("unH40nf@CugnauxToAugustins","11","CugnauxToAugustins");
		ProfilUser profilH40nf = new ProfilUser("N", "1", "H", 100, 1, 10);
		unH40nf.setRoute(route2);
		unH40nf.setProfilConducteur(profilH40nf);
		//User homme non fumeur 40 ans ==> 2/3 critères, mais pas à proximité, non retourné
		unH40nf.setSexe("H");
		unH40nf.setAge(40);
		unH40nf.setFumeur("N");

		//Conducteur3 + setRoutepassant aussi à prox
		ArrayList<CoordGPS> route3 = new ArrayList<CoordGPS>();
		route3.add(escalquens);
		route3.add(gaumontLabege);
		route3.add(occitanie5);
		User uneF25f = new User("uneF25f@EscalqToOccitanie","11","EscalToOccitanie");
		ProfilUser profilF25f = new ProfilUser("F", "0", "F", 100, 1, 10);
		uneF25f.setRoute(route3);
		uneF25f.setProfilConducteur(profilF25f);
		// User femme fumeuse 25 ans ==> que critère age : score vaut 1
		uneF25f.setSexe("F");
		uneF25f.setAge(25);
		uneF25f.setFumeur("F");

		myUserManager.add(unH25nf);
		myUserManager.add(unH40nf);
		myUserManager.add(uneF25f);

		HashMap<User,IntersectionUser> conducteursPotentiels = myUserManager.conducteursPotentielsPour(unPassager); 
		assertEquals(conducteursPotentiels.size(),2);
		
		//TODO
//		if (conducteursPotentiels.size()==2) {
//			int scoreAvecConducteur1 = conducteursPotentiels.get(0).getScoreUser1ConduitParUser2();
//			//System.out.println("score1" + score);
//			assertTrue(scoreAvecConducteur1==111); //containsValue(111));
//			int scoreAvecConducteur2 = conducteursPotentiels.get(1).getScoreUser1ConduitParUser2();
//			assertTrue(scoreAvecConducteur2==1);
//		}
	}
}
//// User homme fumeur 40 ans
//User unH40f = new User("unH40F@tracetaroute.com", "11", "unH40F");
//unH40f.setSexe("H");
//unH40f.setAge(40);
//unH40f.setFumeur("F");
//
//// User femme non fumeuse 25 ans
//User uneF25nf = new User("uneF25nf@tracetaroute.com", "11", "uneF25nf");
//uneF25nf.setSexe("F");
//uneF25nf.setAge(25);
//uneF25nf.setFumeur("N");

////User homme fumeur 20 ans
//User unH20f = new User("unH20f@tracetaroute.com", "11", "unH20f");
//unH20f.setSexe("H");
//unH20f.setAge(20);
//unH20f.setFumeur("F");

//		// Score 0
//		ProfilUser profil1 = new ProfilUser("N","1", "F", pondFumeur,pondAge,pondSexe); 
//		HashMap<User,Integer> actual = myUserManager.correlationAvecProfil(profil1,myUserManager.getListeDesUsers());
//		//assertEquals(5, profil1.scoreCompatibiliteAvecUser(user1));
//		//Test que l'element 1 de la liste est bien celui le + compatible
//		// que l'element 5 est le moins compatible
//		// Et qu' il y a bien 5 élements.
//		fail("not implemented");
//	}

//	// Et encore au dessus
//	//  on s'assurera qu'on trouvera bien 3 des 5 conducteurs
//	// passant pres de chez moi et trié en fonction de mes critères
//	// Ce qui implique de stocker la route de 
//	//  chaque conducteur vers son travail ?????
//	// et balayer alors tous les points de cette route
//	// (espacé d'un rayon a définir pour ne pas tout regarder tous les 100 metres)

//}
