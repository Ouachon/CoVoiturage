package covoiturage;
// DIfférent score caractérisant l'intersection entre 2 users
// Cet objet sera mis dans le HASPMAP <user, intersection> et non plus <user,score>

// C'est cet objet qui sera affiché sur la page passager
public class IntersectionUser {
	private User user1;
	private User user2;
	
	int scoreUser1ConduitUser2;
	int scoreUser1ConduitParUser2;
	double eloignementPointRencontre;
	CoordGPS coordRencontre;
	
	double pourcentageParcouruUser1ConduitUser2;
	double pourcentageParcouruUser1ConduitParUser2;
	

}
