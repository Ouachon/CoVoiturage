package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donnees.CoordGPS;
import metier.UserGestionnaireInSession;

/**
 * Servlet implementation class UsersProcheServlet
 */
@WebServlet("/UsersProcheServlet")
public class UsersProcheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String FIELD_ADR_DEP = "adrDep";
	private static final String FIELD_ADR_ARR = "adrArr";
	private static final String FIELD_LATLONG_DEP = "latLong";
	private static final String FIELD_LATLONG_ARR = "latLongArr";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersProcheServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispat;
		HttpSession session = request.getSession();	
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		int rayon = 5;
		HashMap<String,String> formulaireAccueil = new HashMap<String,String>();
		
		// PRovisoirement en attendant la persistence de données
		// On preremlit des comptes users
		myUserManager.preRemplir();
		
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		session.setAttribute("listeUsersProche", myUserManager.usersProcheDeCoordonnees(carrefourLabege, rayon));
		formulaireAccueil.put(FIELD_ADR_DEP, "carrefour labege") ;
		formulaireAccueil.put(FIELD_ADR_ARR, "capitole");
		formulaireAccueil.put(FIELD_LATLONG_DEP, "43.550481, 1.508069");
		formulaireAccueil.put(FIELD_LATLONG_ARR, "43.600953, 1.446260");
		
		dispat = request.getRequestDispatcher("index.jsp");		
		dispat.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispat;
		
		double dlat;// = Double.parseDouble(request.getParameter("latDep"));
		double dlong;// = Double.parseDouble(request.getParameter("longDep"));
		// On recupère l'attribut
		HttpSession session = request.getSession();	
		int rayon = 5;
		
		String latLong = request.getParameter("latLong");
		String[] parts = latLong.split(",");
		dlat = Double.parseDouble(parts[0]);
		dlong = Double.parseDouble(parts[1]);
		
		String adresseDepart = request.getParameter(FIELD_ADR_DEP);
		System.out.println("adresse de depart " + adresseDepart);
		
		
		System.out.println(request.getParameter("coord_001"));
		
		
		HashMap<String,String> formulaireAccueil = new HashMap<String,String>();
		
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		
//		// PRovisoirement en attendant la persistence de données
//		// On preremlit des comptes users
//		myUserManager.preRemplir();
		
		System.out.println(dlat);
		System.out.println(dlong);
		formulaireAccueil.put(FIELD_ADR_DEP, request.getParameter(FIELD_ADR_DEP));
		formulaireAccueil.put(FIELD_ADR_ARR, request.getParameter(FIELD_ADR_ARR));
		formulaireAccueil.put(FIELD_LATLONG_DEP, request.getParameter(FIELD_LATLONG_DEP));
		formulaireAccueil.put(FIELD_LATLONG_ARR, request.getParameter(FIELD_LATLONG_ARR));
		
		
		
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS uneCoord = new CoordGPS(dlat,dlong); 
		
		//session.setAttribute("listeUsersProche", User.unUserManager.usersProcheDeCoordonnees(uneCoord, rayon));
		session.setAttribute("listeUsersProche", myUserManager.usersProcheDeCoordonnees(uneCoord, rayon));
		session.setAttribute("formAccueil",formulaireAccueil);
		//session.setAttribute("listeConducteursPossible", myUserManager.usersProcheDeCoordonnees(uneCoord, rayon));// conducteursPossible
		//session.setAttribute("listePassagersProches", myUserManager.usersProcheDeCoordonnees(uneCoord, rayon));// conducteursPossible
		
		dispat = request.getRequestDispatcher("index.jsp");
		
		dispat.include(request, response);
	}

}
