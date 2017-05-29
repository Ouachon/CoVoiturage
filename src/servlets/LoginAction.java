package servlets;

import java.io.IOException;
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
public class LoginAction extends HttpServlet {
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
	boolean statusOk = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
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
		// Get form fields
		String email = request.getParameter(FIELD_EMAIL);
		String pwd = request.getParameter(FIELD_PWD);
		String typeCovoit = request.getParameter(FIELD_TYPECOVOIT);
		
		HashMap<String, String> error= new HashMap<String, String>();
		HashMap<String, String> form = new HashMap<String, String>();
		
		HashMap<String, String> profil = new HashMap<String, String>();
		HashMap<String, String> compte = new HashMap<String, String>();
		statusMessage = null;
		
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		
		
			


		User userCourant = authenticate(email, pwd,error,form);
		if (userCourant!=null ) {

			form.put(FIELD_EMAIL, email);  // la page appelée doit connaitre l'identifiant courant

			statusOk = true;
			

			
		} else {
			// C'est ici qu'il faut pousser les form quand login non reconnu=>Hicham
			form.put(FIELD_EMAIL, email);  // on ne retourne pas le password
			statusOk = false;


		}

		// Prepare model to view
		request.setAttribute("formLogin", form);
		request.setAttribute("errorLogin", error);
		


		if (statusOk == true) {
			if (typeCovoit.equals("typeconducteur")) {

				RequestDispatcher dispat = request.getRequestDispatcher("conducteur.jsp");
				dispat.forward(request, response);
			}
			else if (typeCovoit.equals("typepassager")) {
				HashMap<User,IntersectionUser>  condProches=null;
				// Liste des conducteurs passant près de ....
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
			// Build view
			this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
		}
		
	}
	


	// Hicham, le test sur la validité du mail est sur la creation du compte
	// pas sur le login (on doit juste verifier que l'identifiant existe et a
	// a le mot de passe associé
	private String validateEmail(String email) {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				return "Veuillez saisir une adresse mail valide";
			} else {
				if (!isUserExist(email)) {
					return "Login inconu";
				}
			}
		} else {
			return "L'adresse mail est obligatoire";
		}
		return null;
	}

	// Hicham, idem pas de securité mot de passe necessaire....
	private String validatePwd(String pwd) {
		return (pwd == null || pwd.equals("")) ? "Le mot de passe doit être renseigné" : null;
	}

	// Hicham, inclus dans authenticate pour retourner le nom, donc a virer
	private boolean isUserExist(String login) {
		return true;
	}

	private User authenticate(String login, String pwd, 
			HashMap<String, String> parmErreurs, HashMap<String,String> parmForm) {
		boolean loginCorrect = true;
		User userLogge=null;
		
		UserGestionnaireInSession myUserManager= UserGestionnaireInSession.getInstance();
		// PRovisoirement en attendant la persistence de données
				// On preremlit des comptes users
		if (myUserManager.getListeDesUsers().size()==0) myUserManager.preRemplir();
		
		userLogge = myUserManager.getListeDesUsers().get(login);
		
		if (userLogge==null) {
			parmErreurs.put(FIELD_EMAIL, "Utilisateur inconnu");
			loginCorrect=false;

		} else
		{
			
			if (!userLogge.getPwd().equals(pwd)) {
				parmErreurs.put(FIELD_PWD, "Mot de passe incorrect pour cet utilisateur");
				loginCorrect=false;
				userLogge=null;
			} else
			{  // Login correct on retourne le nom pour la page suivante
				String leNom = userLogge.getNom();
				if (leNom.length()==0) leNom=userLogge.getEmail();
				parmForm.put(FIELD_NOM_USER, leNom);
			}
		}
	

		return userLogge;
	}
}
