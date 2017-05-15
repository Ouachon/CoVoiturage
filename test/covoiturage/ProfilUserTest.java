package covoiturage;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProfilUserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testScoreCompatibiliteAvecUser() {
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
		
		// Toujours Poids critere  fumeur = 100, age=1, sexe = 10 ;
		int pondFumeur=100;
		int pondAge=1;
		int pondSexe=10;// Creer un profil en cherchant  Femme de moins de 30 ans, non fumeur
		// Score 0
		ProfilUser profil1 = new ProfilUser("N","1", "F", pondFumeur,pondAge,pondSexe); 
		assertEquals(0, profil1.scoreCompatibiliteAvecUser(user1));

		// Profil en cherchant Homme, 30 a 50 ans, fumeur
		ProfilUser profil2 = new ProfilUser("F","2", "H", pondFumeur,pondAge,pondSexe); 
		assertEquals(111, profil2.scoreCompatibiliteAvecUser(user1));

		
		// Test indifférencié
		// Profil avec sexe , age et fumeur indifférencie
		// Score = 111  avec user1
		// et aussi avec une femme , non fumeur et de 25 ans
		ProfilUser profil3 = new ProfilUser("I","0", "I", pondFumeur,pondAge,pondSexe); 
		assertEquals(111, profil3.scoreCompatibiliteAvecUser(user1));
		assertEquals(111, profil3.scoreCompatibiliteAvecUser(user2));

		
		fail("Not yet implemented");
	}

}
