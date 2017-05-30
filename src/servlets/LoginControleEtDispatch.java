package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import donnees.User;
import metier.IntersectionUser;
import metier.UserGestionnaireInSession;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/login")
public class LoginControleEtDispatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// View
	public static String VIEW_PAGES_URL = "/WEB-INF/login.jsp";

	// Form fields
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PWD = "pwd";
	public static final String FIELD_TYPECOVOIT = "typeCovoit";
	public static final String FIELD_NOM_USER = "nomUser";

	// Request attributs

	String statusMessage = "";
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginControleEtDispatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prepare model to view
		request.setAttribute("statusOK", false);
		request.setAttribute("statusMessage", "");

		// Build view
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Champ paramètre venu de la page HTML
		String email = request.getParameter(FIELD_EMAIL);
		String pwd = request.getParameter(FIELD_PWD);
		String typeCovoit = request.getParameter(FIELD_TYPECOVOIT);
		
		// Différente HASHMAP susceptible d'etre retournée vers les pages HTML
		HashMap<String, String> error= new HashMap<String, String>();
		HashMap<String, String> form = new HashMap<String, String>();
		
		statusMessage = null;
		
		// on verifie si le user existe, et on retourne
		// Sur passager.jsp , conducteur.jsp ou login.jsp
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		ArrayList<String> lesErreurs=new ArrayList<String> ();
		User userCourant = myUserManager.authenticate(email, pwd,lesErreurs);
		form.put(FIELD_EMAIL, email);
		request.setAttribute("formLogin", form);
		request.setAttribute("errorLogin", error);

		if (userCourant!=null) {
			if (typeCovoit.equals("typeconducteur")) {
				// on se logge en tant que conducteur
				RequestDispatcher dispat = request.getRequestDispatcher("conducteur.jsp");
				dispat.forward(request, response);
			}
			else if (typeCovoit.equals("typepassager")) {
				// on se logge en tant que passager
				// on va afficher la Liste des conducteurs passant près de ....
				HashMap<User,IntersectionUser>  condProches=null;
				condProches = myUserManager.conducteursPotentielsPour(userCourant);
				request.setAttribute("conducteursProche", condProches);
				System.out.println("ConducteursProche taille:" + condProches.size());
				
				RequestDispatcher dispat = request.getRequestDispatcher("passager.jsp");
				dispat.include(request, response);  // Difference avec forward
			}
			
		
			// A priori les Hashmap echangé entre servlet et HTML ne sont que 
			// Pour la page et pas pour toutes les pages
			// Sur chaque page connaitre le usercourant et le type
			// QUand on demande les preférences appeler
			// la servlet lira les preférences pour le user recu en parametre
			// et qui ouvrira alors la page JSP qui mettra par defaut les valeurs
			// recues
			
			
		}	
		else {
			// si login incorrect alors revenir a la page login
			// Apres avoir alimenté le hashmap d'erreur
			String errLogin="";
			String errPwd="";
			// On s'autorise plusieurs erreurs PAR CHAMP, on affichera la derniere
			for (int i=0; i < lesErreurs.size();i++) {
				String parts[] = lesErreurs.get(i).split(":");
				if (parts[0].equals("Login")) errLogin=parts[2];
				if (parts[0].equals("Pwd")) errPwd=parts[2];				
			}
			error.put(FIELD_EMAIL, errLogin);
			error.put(FIELD_PWD, errPwd);
			this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
		}
		
	}
	

}
