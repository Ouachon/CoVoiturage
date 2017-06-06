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
	ArrayList<String> erreurs = new ArrayList<String>();
	User u1_NF_30_H_StOrens;
	User u2_NF_25_H_Villefranche,u3_NF_40_H_cugnaux, u4_F_25_F_Escalquens; 
	@Before
	public void setUp() throws Exception {
		//il est indispensable de vider la liste des users avant de lancer chaque test 
		//pour en maîtriser le contenu et donc la cohérence des tests
		//fonction de reset crée dans la classe
		 myUserManager.getListeDesUsers().clear();
		 erreurs.clear();
		 
		preremplissage();

	}

	
	@After
	public void tearDown() throws Exception {
		 myUserManager.getListeDesUsers().clear();
		 erreurs.clear();
	}

	@Test
	public void testAuthenticate() {
		
		String expected = "unH25nf@VilleFrToAugustins.com";
		User userLogge = myUserManager.authenticate("unH25nf@VilleFrToAugustins.com", "Azerty12", erreurs);
		String actual = userLogge.getEmail();
		assertEquals(expected,actual);		
	}
	
	@Test
	public void testAuthenticateLoginInexistant() {
		String expected = "Login:Erreur 001:Utilisateur inconnu";
		User userLogge = myUserManager.authenticate("INCONNU@xyz.com", "11", erreurs);
		assertTrue(erreurs.contains(expected));		
	}
	@Test
	public void testAuthenticatePwdIncorrect() {
		String expected = "Pwd:Erreur 001:Mot de passe incorrect pour cet utilisateur";
		User userLogge = myUserManager.authenticate("unH25nf@VilleFrToAugustins.com", "pwdIncorrect", erreurs);
		assertTrue(erreurs.contains(expected));	
	}

	@Test
	public void testUsersProcheDe() {
		
		HashMap <String,User> actual = myUserManager.usersProcheDeCoordonnees(stOrens, 5);
		// On doit retrouver le user de st orens pas les autres 	
		System.out.println("Nb de users : " + actual.size());
		assertEquals(2, actual.size());
		assertTrue(actual.containsKey("unPassager@tracetaroute.com"));
		assertTrue(actual.containsKey("uneF25f@EscalqToOccitanie.com"));		
		assertFalse(actual.containsKey("unH40nf@CugnauxToAugustins.com"));	
	}
	
	@Test
	public void testConducteurPassePresDeMaisonDe(){
	
		// unUser qconque au carrefour labege, qui passe près de chez lui
		User unUser = new User("carrefourLabege@gmail.com","Azerty00","carrefour");
		unUser.setCoordonneesGPSMaison(carrefourLabege);
		
		//HashMap<String,User> conducteursPassantPresDe = new HashMap<String,User>();
		HashMap<User,IntersectionUser>conducteursPassantPresDe = myUserManager.conducteursPassePresDeMaisonDe(unUser);
		
		//= myUserManager.conducteursPassePresDe(unPassager);
		//la map doit contenir 2 enregistrements : conducteur 1 et 3, le 2 ne doit pas y ^tre
		assertEquals(conducteursPassantPresDe.size(),3);
		assertTrue(conducteursPassantPresDe.containsKey(u2_NF_25_H_Villefranche));
		assertTrue(conducteursPassantPresDe.containsKey(u4_F_25_F_Escalquens));
		assertTrue(conducteursPassantPresDe.containsKey(u1_NF_30_H_StOrens));
		
		assertFalse(conducteursPassantPresDe.containsKey(u3_NF_40_H_cugnaux));//"CugnauxToAugustins@tracetaroute.com"));
				
	}
	
	@Test
	public void testConducteursPassePresDeRouteDe() {
		// Le conducteur d'escalquens doivent se rejoindre a gaumont labege
		// Celui de cugnaux ne passe pas pres de la route de celui de villefranche
		

				HashMap<User,IntersectionUser> result = myUserManager.conducteursPassePresDeRouteDe(u2_NF_25_H_Villefranche);
				System.out.println("taille resultat = " + result.size());
				assertEquals(2, result.size());
				assertTrue(result.containsKey(u4_F_25_F_Escalquens));//"VilleFrToAugustins@tracetareoute.com"));
				assertFalse(result.containsKey(u3_NF_40_H_cugnaux));
				// Celui d'escalquens partage la route, pas les autres
	}
	
	@Test
	public void testCorrelationEntreUnUserEtListeConducteursProches() {


		// et on demande les conducteurs potentiels de unPassager
		HashMap<User,IntersectionUser> conducteursPotentiels = myUserManager.conducteursPotentielsPour(u1_NF_30_H_StOrens); 
		assertEquals(conducteursPotentiels.size(),2);
		System.out.println("testCorrelationEntreUnUserEtListeConducteursProches=" + conducteursPotentiels.size() );
		
		if (conducteursPotentiels.size()==2) {
			// La liste doit contenir user1 et user3 mais pas user2
			
			IntersectionUser inter1 = conducteursPotentiels.get(u2_NF_25_H_Villefranche);
			assertFalse(inter1==null);
			IntersectionUser inter2 = conducteursPotentiels.get(u4_F_25_F_Escalquens);
			assertFalse(inter2==null);
			
			IntersectionUser nonInter = conducteursPotentiels.get(u3_NF_40_H_cugnaux);
			assertTrue(nonInter==null);
			
			// Et maintenant on verifie les scores de correlation
			// 
			// unH25nf prefere conduire des passager non fumeur, jeune , et homme
			// Le passager est un non fumeur, de 30 ans, homme le score est donc
			// maximum de 111 puisque tout correspond
			// (ponderation figé a 100 pour fumeur, 1 pour age, 10 pour sexe)
			int scoreAvecConducteur1 = inter1.getScoreUser1ConduitParUser2();
			assertEquals(111,scoreAvecConducteur1);
			// Par contre le passager souhaite si il conduit voyager avec
			//  un non fumeur, jeune, et femme 
			//Donc si c'est lui qui conduit, le score est différent
			// et doit tomber a 101 (100 pour fumeur, 1 pour age, 0 pour sexe)
//			int scorePassagerConduitConducteur1= inter1.getScoreUser1ConduitUser2();
//			System.out.println("Score inverse=" + scorePassagerConduitConducteur1);
//			assertEquals(101, scorePassagerConduitConducteur1);
			
			// Les préferences de uneF25f sont : fumeuse, jeune, femme
			//Le passager est un 				non fumeur, de 25 ans, homme,
			// le score est donc de 1 puisque seul l'age compte
			// (ponderation figé a 100 pour fumeur, 1 pour age, 10 pour sexe)
			
			int scoreAvecConducteur3 = inter2.getScoreUser1ConduitParUser2();
			assertEquals(1, scoreAvecConducteur3);
		}
	}
	
	private void preremplissage() {
		// Creation d'un environnement propre avec 3 users existant
		// et un 4eme qui cherche a covoiturer
		// A) creation d'unPassager NonFumeur 30Ans Homme - Conducteurs:  Poids critere  fumeur = 100, age=1, sexe = 10 ;
			//===========================================
			u1_NF_30_H_StOrens = new User("unPassager@tracetaroute.com","Azerty11","unPassager");
			u1_NF_30_H_StOrens.setCoordonneesGPSMaison(stOrens); 
			u1_NF_30_H_StOrens.setAge(30);
			u1_NF_30_H_StOrens.setSexe("H");
			u1_NF_30_H_StOrens.setFumeur("N");
			ArrayList<CoordGPS> route0 = new ArrayList<CoordGPS>();
			route0.add(stOrens);
			route0.add(augustins);
			u1_NF_30_H_StOrens.setRoute(route0);
			ProfilUser profilPassager= new ProfilUser("N","0","F",100,1,10);

			//B) Conducteur1 + setRoute passant à proximité de storens
			u2_NF_25_H_Villefranche = new User("unH25nf@VilleFrToAugustins.com","Azerty12","VilleFrToAugustins");
			u2_NF_25_H_Villefranche.setCoordonneesGPSMaison(villefrancheL); 
			ArrayList<CoordGPS> route1 = new ArrayList<CoordGPS>();
			route1.add(villefrancheL);
			route1.add(gaumontLabege);
			route1.add(augustins);
			u2_NF_25_H_Villefranche.setRoute(route1);
			
			ProfilUser profilH25nf = new ProfilUser("N", "0", "H", 100, 1, 10);
			u2_NF_25_H_Villefranche.setProfilConducteur(profilH25nf);		
			//Ce User prefere conduire des passager non fumeur, jeune , et homme
			
			// Ce user est un Homme nonFumeur de 25ans ==> doit retourner score 111
			u2_NF_25_H_Villefranche.setSexe("H");
			u2_NF_25_H_Villefranche.setAge(25);
			u2_NF_25_H_Villefranche.setFumeur("N");

			//C) Conducteur2 + setRoute ne passant pas à proximité
			// ======================================
			u3_NF_40_H_cugnaux = new User("unH40nf@CugnauxToAugustins.com","Azerty13","CugnauxToAugustins");
			u3_NF_40_H_cugnaux.setCoordonneesGPSMaison(cugnaux); 			
			ArrayList<CoordGPS> route2 = new ArrayList<CoordGPS>();
			route2.add(cugnaux);
			route2.add(purpan);
			route2.add(augustins);
			u3_NF_40_H_cugnaux.setRoute(route2);
			
			// On crée des préférences et des caractéristiques
			// mais peu importe puisque ce user ne passe pas a proximité
			ProfilUser profilH40nf = new ProfilUser("N", "1", "H", 100, 1, 10);
			u3_NF_40_H_cugnaux.setProfilConducteur(profilH40nf);
			//C'est un homme non fumeur de 40 ans ==> 2/3 critères, mais pas à proximité, non retourné
			u3_NF_40_H_cugnaux.setSexe("H");
			u3_NF_40_H_cugnaux.setAge(40);
			u3_NF_40_H_cugnaux.setFumeur("N");

			//D) Conducteur3 + setRoute passant aussi à proximite
			u4_F_25_F_Escalquens = new User("uneF25f@EscalqToOccitanie.com","Azerty14","EscalToOccitanie");
			u4_F_25_F_Escalquens.setCoordonneesGPSMaison(escalquens); 			
			
			ArrayList<CoordGPS> route3 = new ArrayList<CoordGPS>();
			route3.add(escalquens);
			route3.add(gaumontLabege);
			route3.add(occitanie5);
			u4_F_25_F_Escalquens.setRoute(route3);
			
			ProfilUser profilF25f = new ProfilUser("F", "0", "F", 100, 1, 10);
			u4_F_25_F_Escalquens.setProfilConducteur(profilF25f);
			// Les préferences de uneF25f sont : fumeuse, jeune, femme
			
			// Ce user est une femme fumeuse de 25 ans ==> que critère age : score vaut 1
			u4_F_25_F_Escalquens.setSexe("F");
			u4_F_25_F_Escalquens.setAge(25);
			u4_F_25_F_Escalquens.setFumeur("F");

			// On ajoute tous ces users 
			myUserManager.add(u1_NF_30_H_StOrens);
			myUserManager.add(u2_NF_25_H_Villefranche);
			myUserManager.add(u3_NF_40_H_cugnaux);
			myUserManager.add(u4_F_25_F_Escalquens);
			
			System.out.println(myUserManager.getListeDesUsers().size());
	}

}

