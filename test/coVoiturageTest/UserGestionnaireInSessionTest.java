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

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUsersProcheDe() {
	
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		
		CoordGPS escalquens = new CoordGPS(43.518063, 1.562549);  
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS occitanie5 = new CoordGPS(43.542660, 1.508887);		
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);
		
		
		
		User user1 = new User("ESCALQUENS","11","toto");
		user1.setCoordonneesGPS(escalquens);
		myUserManager.add(user1);
		
		User user2 = new User("GAUMONT","11","titi");
		user2.setCoordonneesGPS(gaumontLabege);
		myUserManager.add(user2);
		
			
		User user3 = new User("OCCITANIE5","11","tutu");
		user3.setCoordonneesGPS(occitanie5);
		myUserManager.add(user3);
		
		HashMap <String,User> actual = myUserManager.usersProcheDeCoordonnees(carrefourLabege, 5);
		// On doit retrouver USER USER3  mais pas user1 qui est a escalquens
		
		System.out.println("Nb de users : " + actual.size());
		assertEquals(2, actual.size());
		
		assertTrue(actual.containsKey("GAUMONT"));
		assertTrue(actual.containsKey("OCCITANIE5"));
		assertFalse(actual.containsKey("ESCALQUENS"));
	
	}
	
	@Test
	public void testCorrelationAvecProfil() {
		// Creer un user fumeur , 40 ans , homme
		User user1 = new User("toto@oo", "11", "eric");
		user1.setSexe("H");
		user1.setAge(40);
		user1.setFumeur("F");

		// et une femme non fumeuse de 25 ans
		User user2 = new User("titi@oo", "11", "titi");
		user2.setSexe("F");
		user2.setAge(25);
		user2.setFumeur("N");

		// Et 3 autres users ( 5 en tout)
		// Les ajouter dans les utilisateurs
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		myUserManager.add(user1);
		myUserManager.add(user2);

		// On cree un profil
		// Toujours Poids critere  fumeur = 100, age=1, sexe = 10 ;
		int pondFumeur=100;
		int pondAge=1;
		int pondSexe=10;// Creer un profil en cherchant  Femme de moins de 30 ans, non fumeur
		// Score 0
		ProfilUser profil1 = new ProfilUser("N","1", "F", pondFumeur,pondAge,pondSexe); 
		HashMap<User,Integer> actual = myUserManager.correlationAvecProfil(profil1,myUserManager.getListeDesUsers());
		//assertEquals(5, profil1.scoreCompatibiliteAvecUser(user1));
		//Test que l'element 1 de la liste est bien celui le + compatible
		// que l'element 5 est le moins compatible
		// Et qu' il y a bien 5 élements.
		fail("not implemented");
	}
	
	
	// Et encore au dessus
	//  on s'assurera qu'on trouvera bien 3 des 5 conducteurs
	// passant pres de chez moi et trié en fonction de mes critères
	// Ce qui implique de stocker la route de 
	//  chaque conducteur vers son travail ?????
	// et balayer alors tous les points de cette route
	// (espacé d'un rayon a définir pour ne pas tout regarder tous les 100 metres)
	
	

}
