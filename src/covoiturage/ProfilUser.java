package covoiturage;

public class ProfilUser {

	String fumeur; // Oui Non Indifférencié
	String trancheAge; // 1: Moins de 30, 2 :30 50, 3:plus
	String sexe; // H , F ou I pour indifférencier
	
	int poidsCritereFumeur;
	int poidsCritereAge;
	int poidsCritereSexe;

	public int getPoidsCritereFumeur() {
		return poidsCritereFumeur;
	}

	public void setPoidsCritereFumeur(int poidsCritereFumeur) {
		this.poidsCritereFumeur = poidsCritereFumeur;
	}

	public int getPoidsCritereAge() {
		return poidsCritereAge;
	}

	public void setPoidsCritereAge(int poidsCritereAge) {
		this.poidsCritereAge = poidsCritereAge;
	}

	public int getPoidsCritereSexe() {
		return poidsCritereSexe;
	}

	public void setPoidsCritereSexe(int poidsCritereSexe) {
		this.poidsCritereSexe = poidsCritereSexe;
	}



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
		// Avec les caractéristique de <AutreUser> passé en paramètre
		int retour = 0;
		int unScore;
		
		// Compatibilité homme/femme
		unScore = 0;
		if (sexe.equals("I") || (sexe.equals(autreUser.getSexe()))) {
			unScore = 1;
		}
		if (poidsCritereSexe > 0)
			unScore = unScore * poidsCritereSexe;
		
		System.out.println("critere sexe unScore=" + unScore);
		
		retour = retour + unScore;
		
		unScore = 0;
		// Compatibilité fumeur/non fumeur
		if (fumeur.equals("I") || (fumeur.equals(autreUser.getFumeur()))) {
			unScore = 1;
		}
		if (poidsCritereFumeur > 0)
			unScore = unScore * poidsCritereFumeur;
		
		retour = retour + unScore;
		
		System.out.println("critere fumeur unScore=" + unScore);

		unScore = 0;
		// Compatibilité tranche d'age
		if (trancheAge.equals("0") || (trancheAge.equals(autreUser.getTranche()))) {
			unScore = 1;
		}
		
		if (poidsCritereAge > 0)
			unScore = unScore * poidsCritereAge;
		
		System.out.println("critere age unScore =" + unScore);
		System.out.println("critere age trancheAge =" + trancheAge);
		System.out.println("critere age autreUser.getTranche()=" + autreUser.getTranche());
		
		System.out.println("Retour SCORE = " + retour);

		// On obtient le score de compatibilité avec le user
		return retour = retour + unScore;
	}
}
