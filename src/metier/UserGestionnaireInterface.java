package metier;

import java.util.HashMap;

import donnees.User;

public interface UserGestionnaireInterface {
	public void add (User newUser);
	public HashMap<String, User> getListeDesUsers();
	void delete(String identifiantUser);
	
}
