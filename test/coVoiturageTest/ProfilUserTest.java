package coVoiturageTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import donnees.ProfilUser;
import donnees.User;

public class ProfilUserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testScoreCompatibiliteAvecUserSexe() {
		// Creer un user fumeur , 40 ans , homme
		User user1 = new User("toto@oo", "11", "eric");
		user1.setFumeur("F");
		user1.setAge(40);
		user1.setSexe("H");

		// et une femme non fumeuse de 25 ans
		User user2 = new User("titi@oo", "11", "titi");
		user2.setFumeur("N");
		user2.setAge(25);
		user2.setSexe("F");

		// Toujours Poids critere fumeur = 100, age=1, sexe = 10 ;
		int pondFumeur = 100;
		int pondAge = 1;
		int pondSexe = 10;

		// Creer un profil en cherchant Femme de moins de 30 ans, non fumeur
		// on doit trouver score 0 avec user1

		ProfilUser profil1 = new ProfilUser("N", "1", "F", pondFumeur, pondAge, pondSexe);
		assertEquals(0, profil1.scoreCompatibiliteAvecUser(user1));

		// Profil en cherchant Homme, 30 a 50 ans, fumeur
		// Score 111 (les 3 criteres sont ok) 
		ProfilUser profil2 = new ProfilUser("F", "2", "H", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil2.scoreCompatibiliteAvecUser(user1));

		// Test indifférencié
		// Profil avec tout sexe , tout age et fumeur indifférencie
		// Score = 111 avec user1
		// Score = 111 avec user2 , aussi avec une femme , non fumeur et de 25
		// ans

		ProfilUser profil3 = new ProfilUser("I", "0", "I", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil3.scoreCompatibiliteAvecUser(user1));
		assertEquals(111, profil3.scoreCompatibiliteAvecUser(user2));
	}

	@Test
	public void testScoreCompatibiliteAvecUserAge() {
		// Creer un user fumeur , 40 ans , homme
		User user1 = new User("toto@oo", "11", "eric");
		user1.setFumeur("F");
		user1.setAge(40);
		user1.setSexe("H");

		// et une femme non fumeuse de 25 ans
		User user2 = new User("titi@oo", "11", "titi");
		user2.setFumeur("N");
		user2.setAge(25);
		user2.setSexe("F");

		// Toujours Poids critere fumeur = 100, age=1, sexe = 10 ;
		int pondAge = 100;
		int pondFumeur = 10;
		int pondSexe = 1;

		// Creer un profil en cherchant Femme de moins plus de 50 ans, non
		// fumeur
		// Score 0
		ProfilUser profil1 = new ProfilUser("N", "3", "F", pondFumeur, pondAge, pondSexe);
		assertEquals(0, profil1.scoreCompatibiliteAvecUser(user1));

		// Profil en cherchant Homme, 30 a 50 ans, fumeur
		// Score 111( poids les 3) avec user1
		ProfilUser profil2 = new ProfilUser("F", "2", "H", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil2.scoreCompatibiliteAvecUser(user1));

		// Profil en cherchant Homme, moins de 30 ans, fumeur
		// Score 11 (poids fumeur et sexe) avec user1
		// Score 100 (poids age) avec user2
		ProfilUser profil3 = new ProfilUser("F", "1", "H", pondFumeur, pondAge, pondSexe);
		assertEquals(11, profil3.scoreCompatibiliteAvecUser(user1));
		assertEquals(100, profil3.scoreCompatibiliteAvecUser(user2));

		// Test indifférencié
		// Profil avec tout sexe , tout age et fumeur indifférencié
		// Score = 111 avec user1
		// Score = 111 avec user2

		ProfilUser profil4 = new ProfilUser("I", "0", "I", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil4.scoreCompatibiliteAvecUser(user1));
		assertEquals(111, profil4.scoreCompatibiliteAvecUser(user2));

	}

	@Test
	public void testScoreCompatibiliteAvecUserFumeur() {
		// Creer un user fumeur , 40 ans , homme
		User user1 = new User("toto@oo", "11", "eric");
		user1.setFumeur("F");
		user1.setAge(40);
		user1.setSexe("H");

		// et une femme non fumeuse de 25 ans
		User user2 = new User("titi@oo", "11", "titi");
		user2.setFumeur("N");
		user2.setAge(25);
		user2.setSexe("F");

		// Toujours Poids critere fumeur = 100, age=1, sexe = 10 ;
		int pondAge = 10;
		int pondFumeur = 1;
		int pondSexe = 100;

		// Creer un profil en cherchant Femme de moins plus de 50 ans, non
		// fumeur
		// Score 0 (aucun point commun)
		ProfilUser profil1 = new ProfilUser("N", "3", "F", pondFumeur, pondAge, pondSexe);
		assertEquals(0, profil1.scoreCompatibiliteAvecUser(user1));

		// Profil en cherchant Homme, 30 a 50 ans, fumeur
		// Score 111 (les 3 ) avec user1
		ProfilUser profil2 = new ProfilUser("F", "2", "H", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil2.scoreCompatibiliteAvecUser(user1));
		
		// Profil en cherchant Homme, moins de 30 ans, fumeur
		// Score 1 (poids fumeur) avec user1
		// Score 110 (poids age et sexe) avec user2
		ProfilUser profil3 = new ProfilUser("F", "1", "F", pondFumeur, pondAge, pondSexe);
		assertEquals(1, profil3.scoreCompatibiliteAvecUser(user1));
		assertEquals(110, profil3.scoreCompatibiliteAvecUser(user2));

		// Test indifférencié
		// Profil avec tout sexe , tout age et fumeur indifférencié
		// Score = 111 avec user1
		// Score = 111 avec user2

		ProfilUser profil4 = new ProfilUser("I", "0", "I", pondFumeur, pondAge, pondSexe);
		assertEquals(111, profil4.scoreCompatibiliteAvecUser(user1));
		assertEquals(111, profil4.scoreCompatibiliteAvecUser(user2));
	}
}
