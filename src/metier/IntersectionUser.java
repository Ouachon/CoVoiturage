package metier;
// DIfférent score caractérisant l'intersection entre 2 users
// Cet objet sera mis dans le HASPMAP <user, intersection> et non plus <user,score>

import donnees.CoordGPS;
import donnees.User;

// C'est cet objet qui sera affiché sur la page passager
public class IntersectionUser {
	private User user1;
	private User user2;
	
	private int scoreUser1ConduitUser2;
	private int scoreUser1ConduitParUser2;
	private double eloignementPointRencontre;
	private CoordGPS coordRencontre;
	
	private int pourcUser1ConduitUser2;
	private int pourcUser1ConduitParUser2;
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public int getScoreUser1ConduitUser2() {
		return scoreUser1ConduitUser2;
	}
	public void setScoreUser1ConduitUser2(int scoreUser1ConduitUser2) {
		this.scoreUser1ConduitUser2 = scoreUser1ConduitUser2;
	}
	public int getScoreUser1ConduitParUser2() {
		return scoreUser1ConduitParUser2;
	}
	public void setScoreUser1ConduitParUser2(int scoreUser1ConduitParUser2) {
		this.scoreUser1ConduitParUser2 = scoreUser1ConduitParUser2;
	}
	public double getEloignementPointRencontre() {
		return eloignementPointRencontre;
	}
	public void setEloignementPointRencontre(double eloignementPointRencontre) {
		this.eloignementPointRencontre = eloignementPointRencontre;
	}
	public CoordGPS getCoordRencontre() {
		return coordRencontre;
	}
	public void setCoordRencontre(CoordGPS coordRencontre) {
		this.coordRencontre = coordRencontre;
	}
	
	
	
	public IntersectionUser(User user1, User user2, int scoreUser1ConduitUser2, int scoreUser1ConduitParUser2,
			double eloignementPointRencontre, CoordGPS coordRencontre, int pourcUser1ConduitUser2,
			int pourcUser1ConduitParUser2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.scoreUser1ConduitUser2 = scoreUser1ConduitUser2;
		this.scoreUser1ConduitParUser2 = scoreUser1ConduitParUser2;
		this.eloignementPointRencontre = eloignementPointRencontre;
		this.coordRencontre = coordRencontre;
		this.pourcUser1ConduitUser2 = pourcUser1ConduitUser2;
		this.pourcUser1ConduitParUser2 = pourcUser1ConduitParUser2;
	}
	public IntersectionUser() {
		super();
	}
	public int getPourcUser1ConduitUser2() {
		return pourcUser1ConduitUser2;
	}
	public void setPourcUser1ConduitUser2(int pourcUser1ConduitUser2) {
		this.pourcUser1ConduitUser2 = pourcUser1ConduitUser2;
	}
	public int getPourcUser1ConduitParUser2() {
		return pourcUser1ConduitParUser2;
	}
	public void setPourcUser1ConduitParUser2(int pourcUser1ConduitParUser2) {
		this.pourcUser1ConduitParUser2 = pourcUser1ConduitParUser2;
	}
	
	
	

}
