package covoiturage;

import java.awt.List;
import java.util.HashMap;
import java.util.Map.Entry;


public class UserGestionnaireInSession implements UserGestionnaireInterface {

	private HashMap<String,User> listeDesUsers = null;
	
	
	private static UserGestionnaireInSession userManagerUnique;
	
	public static UserGestionnaireInSession getInstance() {
		if (userManagerUnique==null){
			userManagerUnique=new UserGestionnaireInSession();
		}
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
				System.out.println("user proche : "+email);
			}	
		}
		return retour;
	}
	
	public HashMap<String,User> conducteursPassePresDe(User unPassager){
		HashMap<String,User> conducteursPassantPres = new HashMap<String,User>();
		String email;
		User conducteur;
		for (Entry<String,User> entry : listeDesUsers.entrySet()) {
			conducteur = entry.getValue();
			email = entry.getKey();	
			if (conducteur.getIsConducteur()==true){
				if (conducteur.passePresDeCoord(unPassager.getCoordonneesGPS(), CoordGPS.RAYON) == true) 
					{
					conducteursPassantPres.put(email, conducteur)  ; 
					System.out.println("conducteur identifié :" +email);
					}
			}	
		}
		return conducteursPassantPres;
	}
	
	public HashMap<User,Integer> correlationEntre(User unPassager, HashMap<String,User> conducteursProches){
		
		HashMap<User,Integer> conducteursProchesEtScores =new HashMap<User,Integer>();
			int score =0;
			User conducteurProche;
			
			for (Entry<String,User> entry : conducteursProches.entrySet()) {
				conducteurProche = entry.getValue();				
				score = conducteurProche.profilConducteur.scoreCompatibiliteAvecUser(unPassager);
				conducteursProchesEtScores.put(conducteurProche, score);
			}
		return conducteursProchesEtScores; //est une HasMap (conducteurProche,score)
	}
	
	public void preRemplir() {
		CoordGPS blagnac = new CoordGPS(43.637167, 1.390881);  
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS gaumontLabege = new CoordGPS(43.540139, 1.510688);
		
		ProfilUser profil1 = new ProfilUser("Fu", "Moinsde30", "Homme", 100, 10,1);
		
		User user1 = new User("BLAGNAC@gmail","11","toto");
		user1.setCoordonneesGPS(blagnac);
		user1.profilPassager=profil1;
		user1.profilConducteur=profil1;		
		add(user1);
		
		User user2 = new User("GAUMONT@gmail","12","titi");
		user2.setCoordonneesGPS(gaumontLabege);
		user2.profilPassager=profil1;
		user2.profilConducteur=profil1;		
		add(user2);
		
			
		User user3 = new User("CARREFOURLABEGE@gmail","13","tutu");
		user3.setCoordonneesGPS(carrefourLabege);
		user3.profilPassager=profil1;
		user3.profilConducteur=profil1;		
		add(user3);
		
//		User user4 = new User("CARREFOURLABEGE2@gmail","11","eric");
//		user4.setCoordonneesGPS(carrefourLabege);
//		add(user4);
	}
	
}
