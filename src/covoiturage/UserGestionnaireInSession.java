package covoiturage;

import java.util.HashMap;

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
	
	
}
