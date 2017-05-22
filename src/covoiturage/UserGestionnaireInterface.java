package covoiturage;

import java.util.HashMap;

public interface UserGestionnaireInterface {
	public void add (User newUser);
	public HashMap<String, User> getListeDesUsers();
	void delete(String identifiantUser);
	
}
