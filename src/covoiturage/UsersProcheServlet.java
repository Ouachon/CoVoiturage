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
 * Servlet implementation class UsersProcheServlet
 */
@WebServlet("/UsersProcheServlet")
public class UsersProcheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String FIELD_ADR_DEP = "adrDep";
	private static final String FIELD_ADR_ARR = "adrArr";
	private static final String FIELD_LAT_DEP = "latDep";
	private static final String FIELD_LONG_DEP = "longDep";
       
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispat;
		
		double dlat;// = Double.parseDouble(request.getParameter("latDep"));
		double dlong;// = Double.parseDouble(request.getParameter("longDep"));
		// On recupère l'attribut
		HttpSession session = request.getSession();	
		int rayon = 5;
		
		String latLong = request.getParameter("latDep");
		String[] parts = latLong.split(",");
		dlat = Double.parseDouble(parts[0]);
		dlong = Double.parseDouble(parts[1]);
		
		
		HashMap<String,String> formulaireAccueil = new HashMap<String,String>();
		
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		
		// PRovisoirement en attendant la persistence de données
		// On preremlit des comptes users
		myUserManager.preRemplir();
		
		System.out.println(dlat);
		System.out.println(dlong);
		formulaireAccueil.put(FIELD_ADR_DEP, request.getParameter(FIELD_ADR_DEP));
		formulaireAccueil.put(FIELD_ADR_ARR, request.getParameter(FIELD_ADR_ARR));
		formulaireAccueil.put(FIELD_LAT_DEP, request.getParameter(FIELD_LAT_DEP));
		formulaireAccueil.put(FIELD_LONG_DEP, request.getParameter(FIELD_LONG_DEP));
		
		
		CoordGPS carrefourLabege = new  CoordGPS(43.550481, 1.508069);
		CoordGPS uneCoord = new CoordGPS(dlat,dlong); 
		
		//session.setAttribute("listeUsersProche", User.unUserManager.usersProcheDeCoordonnees(uneCoord, rayon));
		session.setAttribute("listeUsersProche", myUserManager.usersProcheDeCoordonnees(uneCoord, rayon));
		session.setAttribute("formAccueil",formulaireAccueil);
		dispat = request.getRequestDispatcher("index.jsp");
		
		dispat.include(request, response);
	}

}
