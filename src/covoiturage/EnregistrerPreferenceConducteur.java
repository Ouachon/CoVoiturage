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
 * Servlet implementation class EnregistrerPreferenceConducteur
 */
@WebServlet("/EnregistrerPreferenceConducteur")
public class EnregistrerPreferenceConducteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String FIELD_NOM_USER = "email";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnregistrerPreferenceConducteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, String> form = new HashMap<String, String>();
		
		HttpSession session = request.getSession();

		// enregistrement des préference du conducteur
		
		String identifiantUser = request.getParameter("userCourant");
		System.out.println("identifiant = " + identifiantUser);

		
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();

		User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);
		
		System.out.println("valeur de fumeur = " + request.getParameter("preferences"));
		System.out.println("valeur de age = " + request.getParameter("age"));
		System.out.println("valeur de type_passager = " + request.getParameter("type_passager"));
		
		ProfilUser unProfil = userLogge.getProfilConducteur();
		
		// unProfil.setFumeur(request.getParameter(FIELD_FUMEUR));
		
		 unProfil.setFumeur(request.getParameter("preferences"));
		 unProfil.setFumeur(request.getParameter("age"));
		 unProfil.setFumeur(request.getParameter("type_passager"));
		
		 
		// Login on retourne le nom pour la page Conducteur
			String email = userLogge.getEmail();
			System.out.println("conducteur.jsp email = " + email);
			form.put(FIELD_NOM_USER, email); 
			
			request.setAttribute("formLogin", form);
			
		RequestDispatcher dispat = request.getRequestDispatcher("conducteur.jsp");
		dispat.forward(request, response);
	}

}
