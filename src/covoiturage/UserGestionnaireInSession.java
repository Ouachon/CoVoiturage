package covoiturage;

import java.awt.List;
import java.util.HashMap;
import java.util.Map.Entry;


public class UserGestionnaireInSession implements UserGestionnaireInterface {

	private HashMap<String,User> listeDesUsers = null;


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
	
	
}
