package covoiturage;

public class ProfilUser {
	
	String fumeur; // Oui  Non Indiff�renci�
	String trancheAge; // 1: Moins de 30, 2 :30 50,  3:plus
	String sexe; // H , F ou I pour indiff�rencier
	
	int poidsCritereFumeur;
	int poidsCritereAge;
	int poidsCritereSexe;
	
	
	public ProfilUser(String fumeur, String trancheAge, String sexe, int poidsCritereFumeur, int poidsCritereAge,
			int poidsCritereSexe) {
		super();
		this.fumeur = fumeur;
		this.trancheAge = trancheAge;
		this.sexe = sexe;
		this.poidsCritereFumeur = poidsCritereFumeur;
		this.poidsCritereAge = poidsCritereAge;
		this.poidsCritereSexe = poidsCritereSexe;
	}
	public String getFumeur() {
		return fumeur;
	}
	public void setFumeur(String fumeur) {
		this.fumeur = fumeur;
	}
	public String getTrancheAge() {
		return trancheAge;
	}
	public void setTrancheAge(String trancheAge) {
		this.trancheAge = trancheAge;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public int scoreCompatibiliteAvecUser(User autreUser) {
		// On compare le profil courant
		// Avec les caract�ristique de <AutreUser> pass� en param�tre
		int retour=0;
		int unScore;
		// Compatibilit� homme/femme
		unScore=0;
		if (sexe.equals("I") || 
				(sexe.equals(autreUser.getSexe())) ){
			unScore=1;			
		} 
		if (poidsCritereSexe>0)	unScore=unScore * poidsCritereSexe;
		
		// IDem pour les autres criteres
			
		// On obtient le score de compatibilit� avec le user
		return retour;
	}

}
