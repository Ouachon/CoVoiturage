package covoiturage;

import java.awt.List;
import java.util.HashMap;
import java.util.Map.Entry;


public class UserGestionnaireInSession implements UserGestionnaireInterface {

	private HashMap<String,User> listeDesUsers = null;
	
	
	private static UserGestionnaireInSession userManagerUnique;
	
	public static UserGestionnaireInSession getInstance() {
		if (userManagerUnique==null) userManagerUnique=new UserGestionnaireInSession();
		return userManagerUnique;
	}

	public UserGestionnaireInSession() {
		super();
		listeDesUsers = new HashMap<String,User>();
		
	}

	
	@Override
	public void add(User newUser) {
		listeDesUsers.put(newUser.getEmail(), newUser);
	}

	@Override
	public HashMap<String, User> getListeDesUsers() {
		return listeDesUsers;
	}
	
	public HashMap<String,User> usersProcheDeCoordonnees(CoordGPS uneCoord,int rayon) {
		
		// On balaye tous les users 
		// S'il est proche du point a l'ajoute a la liste retournée
		HashMap<String,User> retour = new HashMap<String,User>();
		String email;
		User unUser;
		
		for (Entry<String,User> entry : listeDesUsers.entrySet()) {
			unUser = entry.getValue();
			email = entry.getKey();	
			if (unUser.estProcheDeCoord(uneCoord, rayon)) {
				retour.put(email, unUser);
			}
			
		}
		return retour;
		
	}
	
	public HashMap<User,Integer> correlationAvecProfil(ProfilUser unProfil, HashMap<String,User> parmHashMap)  {
		// Travaille sur une HASHMAP<String,User> (resultat des users sur le parcours)
		//   Et retourne une HashMAP<User,Integer> avec le score de correlation
		//             de chaque user retenu (A TRIER)
		HashMap<User,Integer> retour=new HashMap<User,Integer>();
		// Balayer la hashmap en parametre
		//  score = unProfil.scoreCompatibiliteAvecUser(entry.getValue)
		//retour.put(user, score);
		return retour;
	}
	
	public void preRemplir() {
		CoordGPS blagnac = new CoordGPS(43.518063, 1.562549);  
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);
		
		User user1 = new User("BLAGNAC@gmail","11","toto");
		user1.setCoordonneesGPS(blagnac);
		add(user1);
		
		User user2 = new User("GAUMONT@gmail","11","titi");
		user2.setCoordonneesGPS(gaumontLabege);
		add(user2);
		
			
		User user3 = new User("CARREFOURLABEGE@gmail","11","tutu");
		user3.setCoordonneesGPS(carrefourLabege);
		add(user3);
		
//		User user4 = new User("CARREFOURLABEGE2@gmail","11","eric");
//		user4.setCoordonneesGPS(carrefourLabege);
//		add(user4);
	}

	
	public HashMap<User,Integer> conducteursPotentielPourPassager(User unUser) {
		HashMap<User,Integer> retour = new HashMap<User,Integer>();
	
		
		
		// a) Fabriquer la liste des users passant de <unUser>.coordGPS
		HashMap<String,User> listeInitiale = new HashMap<String,User>();
		// Pour chaque User,
		//   si userCourant.passePresDeCoord(unUser.getCoordGPS()) alors
		//      l'ajouter a liste initiale
		
		// b) Recuperer le profil Passager de <unUser>
		ProfilUser profilDuPassager=null;
		// c) Trier par correlation profilPassager/caractUser
		retour = correlationAvecProfil(profilDuPassager, listeInitiale);
	
		return retour;
	
	}
	

	
	
	
}
