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

import donnees.User;
import metier.UserGestionnaireInSession;



/**
 * Servlet implementation class ServletModifUser
 */
@WebServlet("/ServletModifUser")
public class ServletModifUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String FIELD_EMAIL = "email";
	private static final String FIELD_PWD1 = "pwd1";
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
    public ServletModifUser() {
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
		HttpSession session = request.getSession();
		HashMap<String, String> form = new HashMap<String, String>();

		//enregistrement des éléments du compte
		String identifiantUser = request.getParameter("userCourant");
			System.out.println("identifiant User : "+ identifiantUser);
	
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();
		User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);
		String email = userLogge.getEmail();
		String mdp = userLogge.getPwd();
		String name = userLogge.getNom();
		int age = userLogge.getAge();
		String userAge = Integer.toString(age);
		String sexe = userLogge.getSexe();	
		String adresse = userLogge.getAdresseComplete();
		String fumeur = userLogge.getFumeur();
		
		// On tient le user loggé on exporte son profil pour le mettre dans
		// une hashMap que le JSP exploitera
		
		System.out.println("conducteur.jsp email = " + email);
		form.put(FIELD_EMAIL, email);
		form.put(FIELD_PWD1, mdp);
		form.put(FIELD_NAME, name);
		form.put(FIELD_AGE, userAge);
		form.put( FIELD_SEXE, sexe);
		form.put(FIELD_FUMEUR,fumeur);
		form.put(FIELD_ADRESSE, adresse);
		request.setAttribute("form", form);
		

		// on charge les préférences du prefConducteur
		dispat = request.getRequestDispatcher("formModify.jsp");
		dispat.include(request, response);
		

		}


}
