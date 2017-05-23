package covoiturage;

import java.awt.List;
import java.util.HashMap;
import java.util.Map.Entry;

public class UserGestionnaireInSession implements UserGestionnaireInterface {

	private HashMap<String, User> listeDesUsers = null;

	private static UserGestionnaireInSession userManagerUnique;

	public static UserGestionnaireInSession getInstance() {
		if (userManagerUnique == null) {
			userManagerUnique = new UserGestionnaireInSession();
		}
		return userManagerUnique;
	}

	public UserGestionnaireInSession() {
		super();
		listeDesUsers = new HashMap<String, User>();

	}

	@Override
	public void add(User newUser) {
		listeDesUsers.put(newUser.getEmail(), newUser);
	}

	@Override
	public void delete(String identifiantUser) {
		listeDesUsers.remove(identifiantUser);
	}

	@Override
	public HashMap<String, User> getListeDesUsers() {

		return listeDesUsers;
	}

	public HashMap<String, User> usersProcheDeCoordonnees(CoordGPS uneCoord, int rayon) {
		// On balaye tous les users
		// S'il est proche du point a l'ajoute a la liste retournée
		HashMap<String, User> retour = new HashMap<String, User>();
		String email;
		User unUser;

		for (Entry<String, User> entry : listeDesUsers.entrySet()) {
			unUser = entry.getValue();
			email = entry.getKey();
			if (unUser.estProcheDeCoord(uneCoord, rayon)) {
				retour.put(email, unUser);
				System.out.println("user proche : " + email);
			}
		}
		return retour;
	}

	public HashMap< User, IntersectionUser> conducteursPassePresDe(User unPassager) {
		HashMap< User, IntersectionUser> conducteursPassantPres = new HashMap<User,IntersectionUser>();
		String email;
		User conducteur;
		for (Entry<String, User> entry : listeDesUsers.entrySet()) {
			conducteur = entry.getValue();
			email = entry.getKey();
			if (conducteur!=unPassager) {

				if (conducteur.getIsConducteur() == true) {
					IntersectionUser uneIntersection = 
						(conducteur.passePresDeCoord(unPassager.getCoordonneesGPS(), CoordGPS.RAYON));
					if (uneIntersection != null) {
						// Le conducteur passe près de la coord GPS du passager
						// on a rétourné un objet détaillant cette intersection
						conducteursPassantPres.put(conducteur,uneIntersection);
						System.out.println("conducteur identifié :" + email);
					}
				}
			}
		}
		return conducteursPassantPres;
	}

	public HashMap<User, IntersectionUser> correlationEntre(User inUser, HashMap< User, IntersectionUser> listeDeUsers) {

		HashMap<User, IntersectionUser> usersEtScores = new HashMap<User, IntersectionUser>();
		int score1 = 0;
		int score2 = 0;
		User userCourant;
		IntersectionUser intersectionCourante;
		for (Entry<User, IntersectionUser> entry : listeDeUsers.entrySet()) {
			userCourant = entry.getKey();
			intersectionCourante = entry.getValue();
			ProfilUser profilCourant = userCourant.profilConducteur;
			if (profilCourant != null) {
				score1 = profilCourant.scoreCompatibiliteAvecUser(inUser);
			}
			intersectionCourante.setScoreUser1ConduitParUser2(score1);
			
//			ProfilUser profilCourantInverse= inUser.profilConducteur;
//			score2 = profilCourantInverse.scoreCompatibiliteAvecUser(userCourant);
			score2=score1;// TODO
			intersectionCourante.setScoreUser1ConduitUser2(score2);
			
			usersEtScores.put(userCourant, intersectionCourante);
			System.out.println(userCourant.getEmail() + " : " + score1);
		}
		return usersEtScores; // est une HasMap (User, detail de l'intersection)
	}

	public HashMap<User, IntersectionUser> conducteursPotentielsPour(User unPassager) {
		//HashMap<User, Integer> conducteursPotentiels = new HashMap<User, Integer>();

		HashMap< User, IntersectionUser> conducteursProches = conducteursPassePresDe(unPassager);
		HashMap<User, IntersectionUser> conducteursEtScore = correlationEntre(unPassager, conducteursProches);

		return conducteursEtScore;
	}

	public void preRemplir() {
		CoordGPS blagnac = new CoordGPS(43.637167, 1.390881);
		CoordGPS carrefourLabege = new CoordGPS(43.550481, 1.508069);
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);

		ProfilUser profil1 = new ProfilUser("F", "1", "H", 100, 10, 1);

		User user1 = new User("BLAGNAC@gmail", "11", "toto");
		user1.setCoordonneesGPS(blagnac);
		user1.profilPassager = profil1;
		user1.profilConducteur = profil1;
		add(user1);

		User user2 = new User("GAUMONT@gmail", "12", "titi");
		user2.setCoordonneesGPS(gaumontLabege);
		user2.profilPassager = profil1;
		user2.profilConducteur = profil1;
		add(user2);

		User user3 = new User("CARREFOURLABEGE@gmail", "13", "tutu");
		user3.setCoordonneesGPS(carrefourLabege);
		user3.profilPassager = profil1;
		user3.profilConducteur = profil1;
		add(user3);

		// User user4 = new User("CARREFOURLABEGE2@gmail","11","eric");
		// user4.setCoordonneesGPS(carrefourLabege);
		// add(user4);
	}

}
