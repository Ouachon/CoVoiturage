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
 * Servlet implementation class ConducteurCovoit
 */
@WebServlet("/ConducteurCovoit")
public class ConducteurCovoit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String FIELD_NOM_USER = "nomUser";
	public static final String FIELD_DELETE = "suppression";
	public static final String FIELD_PREF_USER = "prefConducteur";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConducteurCovoit() {
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
		RequestDispatcher dispat;

		HttpSession session = request.getSession();

		HashMap<String, String> form = new HashMap<String, String>();

		String identifiantUser = request.getParameter("userCourant");
		System.out.println("identifiant = " + identifiantUser);
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();
		User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);

		// On tient le user loggé on exporte son profil pour le mettre dans
		// une hashMap que le JSP exploitera
		String UserPref = request.getParameter(FIELD_PREF_USER);

		System.out.println("UserPref = " + UserPref);
		if (UserPref != null) {
			if (UserPref.equals("Préférences Conducteur")) {

				request.setAttribute("profilCourant", exporteUnProfilVersHTML(userLogge.profilConducteur));

				// Login on retourne le nom pour la page prefConducteur
				String email = userLogge.getEmail();
				System.out.println("conducteur.jsp email = " + email);
				form.put(FIELD_NOM_USER, email);
				request.setAttribute("formLogin", form);

				// on récupère le profil du userlogger
				ProfilUser profilConducteur = userLogge.getProfilConducteur();

				// String valFumeur = profilConducteur.getFumeur();
				// System.out.println("profilConducteur.getFumeur()= " +
				// profilConducteur.getFumeur());
				//
				// if (valFumeur == "Fu") {
				// System.out.println("Fumeur oui ");
				// form.put("fumeurOui", "checked");
				// } else {
				// form.put("fumeurOui", "");
				// }

				// on charge les préférences du prefConducteur
				dispat = request.getRequestDispatcher("prefConducteur.jsp");
				dispat.include(request, response);
			}
		}
		// suppression du compte
		String delUserSup = request.getParameter(FIELD_DELETE);
		System.out.println("valeur de delUser = " + delUserSup);

		if (delUserSup != null) {
			if (delUserSup.equals("Supprimer le compte")) {

				System.out
						.println("nbre utilisateurs avant la suppression =" + myUserManager.getListeDesUsers().size());
				session.setAttribute("listeDesUsers", myUserManager.getListeDesUsers().size());

				myUserManager.delete(identifiantUser);
				System.out.println(
						"nbre utilisateurs après la suppression du compte =" + myUserManager.getListeDesUsers().size());

				dispat = request.getRequestDispatcher("index.jsp");
				dispat.forward(request, response);
			}
		}

	}

	private HashMap<String, String> exporteUnProfilVersHTML(ProfilUser unProfil) {
		HashMap<String, String> retour = new HashMap<String, String>();

		// Idem pour age et sexe
		retour.put("fumeur", unProfil.getFumeur());
		retour.put("age", unProfil.getTrancheAge());
		retour.put("sexe", unProfil.getSexe());

		String valFumeur = unProfil.getFumeur();
		System.out.println("profilConducteur.getFumeur()= " + unProfil.getFumeur());

		if (valFumeur == "Fu") {
			System.out.println("Fumeur oui ");
			retour.put("fumeurOui", "checked");
		} else {
			retour.put("fumeurOui", "");
		}

		if (valFumeur == "N") {
			System.out.println("Fumeur non ");
			retour.put("fumeurNon", "checked");
		} else {
			retour.put("fumeurNon", "");
		}

		if (valFumeur == "I") {
			System.out.println("Fumeur indifferent ");
			retour.put("fumeurInd", "checked");
		} else {
			retour.put("fumeurInd", "");
		}

		System.out.println("fumeur= " + unProfil.getFumeur());
		System.out.println("age= " + unProfil.getTrancheAge());
		System.out.println("sexe= " + unProfil.getSexe());
		return retour;
	}

}
