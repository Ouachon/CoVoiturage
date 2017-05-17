package covoiturage;

import java.util.HashMap;
import java.util.Iterator;

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
	private String adresseComplete;
	private String sexe;
	private String fumeur; 
	
	ProfilUser profilConducteur;
	ProfilUser profilPassager;
	//ajout des boolean pour identifier si conducteur ou passager 
	private Boolean isConducteur;
	private Boolean isPassager;
	
	public Boolean getIsConducteur() {
		//forcer à true pour les besoins de tests 
		//depuis UserGestionnaireInSession
		return true;
	}

	public void setIsConducteur(Boolean isConducteur) {
		this.isConducteur = isConducteur;
	}

	public Boolean getIsPassager() {
		return isPassager;
	}

	public void setIsPassager(Boolean isPassager) {
		this.isPassager = isPassager;
	}

	private CoordGPS[] route = null; //new CoordGPS[]();
	
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
	
	public ProfilUser getProfilConducteur() {
		return profilConducteur;
	}

	public void setProfilConducteur(ProfilUser profilConducteur) {
		this.profilConducteur = profilConducteur;
	}

	public ProfilUser getProfilPassager() {
		return profilPassager;
	}

	public void setProfilPassager(ProfilUser profilPassager) {
		this.profilPassager = profilPassager;
	}

	// EN package car les users ne se crée que depuis la fabrique
	public User(String email, String pwd, String nom) {
		super();
//		if (unUserManager==null) {
//			//TODO
//			unUserManager = new UserGestionnaireInSession();
//			//unUserManager.preRemplir();
//		}
//		// Ici on pourrait changer pour passer sur un UserDAO pour la base de données
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
	
	
	public String getAdresseComplete() {
		return adresseComplete;
	}
	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getFumeur() {
		return fumeur;
	}

	public void setFumeur(String fumeur) {
		this.fumeur = fumeur;
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
	
	boolean passePresDeCoord(CoordGPS autreCoord, int rayon) {
		boolean estProche = false;	
		CoordGPS unPointDeLaRoute;
		CoordGPS[] route = this.getRoute();
		
		for (int i = 0; i < route.length; i++) {
		unPointDeLaRoute = route[i];
		System.out.println("verif route point "+(i+1) + "/ "+ route.length);
		estProche = unPointDeLaRoute.estProche(autreCoord, rayon);
		if (estProche) 
			{break;}
		}
			System.out.println("estProcheFINAL : "+estProche);
		return estProche;
}

	public CoordGPS[] getRoute() {
		return route;
	}

	public void setRoute(CoordGPS[] route) {
		this.route = route;
	}
}
