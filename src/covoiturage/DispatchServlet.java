package covoiturage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DispatchServlet
 */
@WebServlet("/DispatchServlet")
public class DispatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String FIELD_PWD2 = "pwd2";
	private static final String FIELD_PWD1 = "pwd1";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_ADRESSE = "adrDep";
	public static final String FIELD_AGE = "age";
	public static final String FIELD_SEXE = "sexe";
	public static final String FIELD_FUMEUR = "fumeur";
	public static final String FIELD_LATLONG = "latLong";
	public static final String FIELD_ROUTE = "route";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

    
    @Override
    public void init() throws ServletException {
    	// Remise a zero des champs et messages d'erreur
    	// Dans l'init , pas possible on n'a pas request
    	// Et c'est appelé uniquement au 1er user qui se connecte
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet est appelé a la premiere creation de page
		// Mais ici on ne l'appele pas jamais car register.jsp appelle en fait doGet de ....
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();
		String actionMessage="Succés";
		boolean presenceErreurs = false;
		HashMap<String,String> erreursParChamps = null;
		RequestDispatcher dispat;
		String email= request.getParameter(FIELD_EMAIL);
		String pwd1= request.getParameter(FIELD_PWD1);
		String name= request.getParameter(FIELD_NAME);
		
		// On recupère l'attribut
		HttpSession session = request.getSession();	
		
		User newUser = new User(email,pwd1,name);
		newUser.setAdresseComplete(request.getParameter(FIELD_ADRESSE));
		String ageCh = request.getParameter(FIELD_AGE);
		if (ageCh.length() > 0 ) newUser.setAge(Integer.parseInt(ageCh));
		newUser.setSexe(request.getParameter(FIELD_SEXE));
		newUser.setFumeur(request.getParameter(FIELD_FUMEUR));
		// On recupere les coordonnées calculées pour ce user pour les mémoriser
		String latLong = request.getParameter(FIELD_LATLONG);
		String[] parts = latLong.split(",");
		double dlat = Double.parseDouble(parts[0]);
		double dlong = Double.parseDouble(parts[1]);
		
		newUser.setCoordonneesGPS(new CoordGPS(dlat, dlong));
		
		String routeRecue = request.getParameter(FIELD_ROUTE);
		System.out.println("route" + routeRecue);
		
		newUser.setRoute(routeRecue);
		
		// ON insere un profil par defaut pour ce nouvel user
		// (non fumeur, indifférencié pour sexe, et 30 -50 pour age)
		ProfilUser profCond = new ProfilUser("N", "2", "I", 10, 1, 1);
		ProfilUser profPass = new ProfilUser("N", "2", "I", 10, 1, 1);
		newUser.profilConducteur=profCond;
		newUser.profilPassager=profPass;
		
		
		newUser.validateAll();		
		erreursParChamps = newUser.getHashMapErrors();
		
		session.setAttribute("errors",erreursParChamps);
		session.setAttribute("newUser",newUser);
		session.setAttribute("form",newUser.getHashMapValeurs());
		
		if (erreursParChamps.size()  > 0) {
			// aucune erreur sur chaque paramètre, 
			// a) On teste la cohérence globale de tous les champs du formulaire
			
			// b) actionMessage
			actionMessage="Echec";
			presenceErreurs=true;
			session.setAttribute("actionMessage",actionMessage);
			session.setAttribute("errorStatus",presenceErreurs);
			dispat = request.getRequestDispatcher("Register");
			
			dispat.include(request, response);
		} else
		{
			myUserManager.add(newUser);
			session.setAttribute("listeDesUsers",myUserManager.getListeDesUsers());
			// Ok c'est crée, on revient sur l'accueil
			System.out.println("Nombre de users : " + myUserManager.getListeDesUsers().size() );
			dispat = request.getRequestDispatcher("index.jsp");			
			dispat.include(request, response);
	
		}


	}

}
