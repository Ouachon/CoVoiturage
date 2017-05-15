package covoiturage;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class User {
	private Long Id;
	private String identifiant;
	private String email;
	private String pwd;
	private String nom;
	private String telephone;	
	private int age;
	private int nbAnneesPermis;
	
	private HashMap<String,String>  hashMapErrors=new HashMap<String,String>();
	private HashMap<String,String>  hashMapValeurs=new HashMap<String,String>();
	
	CoordGPS coordonneesGPS;
	
	public static UserGestionnaireInSession unUserManager=null;
	
	private static final String FIELD_PWD1 = "pwd1";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_NAME = "name";
	public String getEmail() {
		return email;
	}
	public User(String email, String pwd, String nom) {
		super();
		if (unUserManager==null) unUserManager = new UserGestionnaireInSession();
		// Ici on pourrait changer pour passer sur un UserDAO pour la base de données
		this.email = email;
		this.pwd = pwd;
		this.nom = nom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	
	public CoordGPS getCoordonneesGPS() {
		return coordonneesGPS;
	}
	public void setCoordonneesGPS(CoordGPS coordonneesGPS) {
		this.coordonneesGPS = coordonneesGPS;
	}
	// Il faudrait les fonctions de validation dans la classe User pour etre plus propre
	public String validateEmail() {
	  	String ret = "";
    	if (email !=null && email.trim().length() != 0 )   {
    		if (!email.contains("@")) {
    			ret = "L'adresse mail doit au moins contenir un @";
    		}    		
    	} else
    		ret = "L adresse mail est obligatoire";
    	return ret;
	}
	
	public String validatePwd () {
	    	String ret="";
	    	// deux Mot de passe identique est testé en javascript

	    	if (pwd.length() <= 1) {
	    		ret = "Mot de passe trop court";
	    	}
	    	// Verifier qu'il contient des minuscule majuscules, des chiffres et aussi des caractères spéciaux ...
	    	
	    	for (int i=0; i < pwd.length();i++) {
	    		
	    	}
	    	return ret;
	    }
	public String validateName() { return "";}
	
	private boolean ajouteDansErreurs(String key, String mess)  {
    	boolean ret = false;
    	if (mess.length() !=0) {
    		hashMapErrors.put(key, mess);		
			ret = true;
		} 
    	return ret;
    	
    }
	private boolean ajouteDansValeurs(String key, String mess)  {
    	boolean ret = false;
    	if (mess.length() !=0) {
    		hashMapValeurs.put(key, mess);		
			ret = true;
		} 
    	return ret;
    	
    }
	
	public void validateAll() {
		hashMapErrors.clear();
		hashMapValeurs.clear();
		ajouteDansErreurs(FIELD_EMAIL, validateEmail());
		ajouteDansErreurs(FIELD_PWD1,validatePwd());
		ajouteDansErreurs(FIELD_NAME,validateName());
		
		ajouteDansValeurs(FIELD_EMAIL,email);
		ajouteDansValeurs(FIELD_PWD1,pwd);
		ajouteDansValeurs(FIELD_NAME,nom);
		
		
		// A finir ....  
		// Controle sur d'autres champs et 
		// Erreurs de cohérence entre les champs d'un User
		
	}
	
	public HashMap<String,String>  getHashMapValeurs() {
		return this.hashMapValeurs;
	}

	
	public HashMap<String, String> getHashMapErrors() {
		return hashMapErrors;
	}

	public static HashMap<String,User>  getListeDesUsers() {
		return unUserManager.getListeDesUsers();
	}
	
	
	
	public void addToUsers() {
		unUserManager.add(this);
	}
	
	boolean estProcheDeUser(User autreUser, int rayon)  {
		return (coordonneesGPS.estProche(autreUser.getCoordonneesGPS(),rayon));
	}

	boolean estProcheDeCoord(CoordGPS autreCoord, int rayon)  {
		return coordonneesGPS.estProche(autreCoord,rayon);
	}
	
	
	
	
	
	

}
