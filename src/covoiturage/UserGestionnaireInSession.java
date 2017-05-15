package covoiturage;

import java.awt.List;
import java.util.HashMap;
import java.util.Map.Entry;


public class UserGestionnaireInSession implements UserGestionnaireInterface {

	private HashMap<String,User> listeDesUsers = null;


	public UserGestionnaireInSession() {
		super();
		
	}

	@Override
	public void add(User newUser) {
		if (listeDesUsers == null) listeDesUsers=new HashMap<String,User>();
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
	}
	
	
}
