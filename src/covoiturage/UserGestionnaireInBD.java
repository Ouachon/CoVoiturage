package covoiturage;

import java.util.HashMap;

public class UserGestionnaireInBD implements UserGestionnaireInterface {

	private HashMap<String,User> listeDesUsers = null;
	// A gérer en mode base de données
	
	@Override
	public void add(User newUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, User> getListeDesUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String identifiantUser) {
		// TODO Auto-generated method stub
		
	}

}
