package covoiturage;

import java.awt.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

public class User {
	private Long Id;
	private String identifiant;
	private String email;
	private String pwd;
	private String nom;
	private String telephone;	
	private int age;
	private String tranche;
	private int nbAnneesPermis;
	private String adresseComplete;
	private String sexe;
	private String fumeur; 
	
	ProfilUser profilConducteur;
	ProfilUser profilPassager;
	//ajout des boolean pour identifier si conducteur ou passager 
	private Boolean isConducteur;
	private Boolean isPassager;
	

	private ArrayList<CoordGPS> route;
//	private CoordGPS[] route = null; //new CoordGPS[]();
	
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

	public String getAdresseComplete() {
		return adresseComplete;
	}
	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}
	
	public String getTranche(){
		if (age <30) {
		  tranche = "1";
		}
		else if (age >=30 && age <50) {
			tranche = "2";
		}
		else 
		{	
			tranche ="3";
		}	
		
		return tranche;
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
	
	public IntersectionUser passePresDeCoord(CoordGPS autreCoord, int rayon) {
		IntersectionUser retour = null;
		boolean estProche = false;	
		CoordGPS unPointDeLaRoute;
		ArrayList<CoordGPS> route = this.getRoute();
		
		
		if (route==null) return retour;
		
		int nbPoints= route.size();
		
		for (int i = 0; i < nbPoints; i++) {
			unPointDeLaRoute = route.get(i);
			estProche = unPointDeLaRoute.estProche(autreCoord, rayon);
			if (estProche) {
				retour = new IntersectionUser();
				retour.setUser1(this);
				retour.setCoordRencontre(unPointDeLaRoute);
				retour.setEloignementPointRencontre(unPointDeLaRoute.kmAVolOiseauDe(autreCoord));
				// On calcule le % de parcours commun
				CoordGPS depart = route.get(0);
				CoordGPS arrivee = route.get(nbPoints - 1);
				double kmTotalConducteur= depart.kmAVolOiseauDe(arrivee);
				double kmEnCommun = unPointDeLaRoute.kmAVolOiseauDe(arrivee);
				int pourcArrondi = (int) Math.round(kmEnCommun / kmTotalConducteur * 100);
				//retour.setPourcUser1ConduitParUser2(pourcArrondi);
				retour.setPourcUser1ConduitUser2(pourcArrondi);
				break;
			}
			}
		System.out.println("estProcheFINAL : "+estProche);
		return retour;
	}	
	
	
	public CoordGPS getCoordonneesGPS() {
		return coordonneesGPS;
	}
	public void setCoordonneesGPS(CoordGPS coordonneesGPS) {
		this.coordonneesGPS = coordonneesGPS;
	}

	public ArrayList<CoordGPS> getRoute() {
		return route;
	}

	public void setRoute(ArrayList<CoordGPS> route) {
		this.route = route;
	}
	
	public void setRoute(String uneRoute) {
		this.route = convertStringToRouteGPS(uneRoute);
	}

	private static  ArrayList<CoordGPS> convertStringToRouteGPS(String uneRoute){
		//décortiquer la chaine entrée de la forme   ’45.32121,1.6:46.1255454,1.5…
		//String stringPoint="";
		ArrayList<CoordGPS> listePtsDeRoute = new ArrayList<CoordGPS>();
		System.out.println(uneRoute);
			uneRoute = uneRoute.replace(")",":");
			uneRoute = uneRoute.replace("(", "");
			System.out.println(uneRoute);
			String[] tabStringPts = uneRoute.split(":");
			System.out.println("toto");
			System.out.println(tabStringPts.length);
			for (int i = 0; i < tabStringPts.length; i++) {
				String uneCoordCh = tabStringPts[i];
				if (uneCoordCh.length() > 0 )  {
					CoordGPS coordGpsi = new CoordGPS(uneCoordCh);
					listePtsDeRoute.add(coordGpsi);
				}
			}

		return listePtsDeRoute;
	}

	
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	
}
