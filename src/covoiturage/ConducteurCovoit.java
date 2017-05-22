package covoiturage;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConducteurCovoit
 */
@WebServlet("/ConducteurCovoit")
public class ConducteurCovoit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String FIELD_NOM_USER = "nomUser";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConducteurCovoit() {
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
		RequestDispatcher dispat;
		
		HashMap<String, String> form = new HashMap<String, String>();
		
		String identifiantUser = request.getParameter("userCourant");
		System.out.println("identifiant = " + identifiantUser);
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();
		User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);
		// On tient le user loggé on exporte son profil pour le mettre dans
		// une hashMap que le JSP exploitera

		request.setAttribute("profilCourant", 
				exporteUnProfilVersHTML(userLogge.profilConducteur));
		
		// Login on retourne le nom pour la page prefConducteur
		String email = userLogge.getEmail();
		System.out.println("conducteur.jsp email = " + email);
		form.put(FIELD_NOM_USER, email); 
		request.setAttribute("formLogin", form);
		
		// on charge les préférences du prefConducteur
		dispat = request.getRequestDispatcher("prefConducteur.jsp");		
		dispat.include(request, response);
		}
	
	private HashMap<String,String>  exporteUnProfilVersHTML(ProfilUser unProfil) {
		HashMap<String,String> retour = new HashMap<String,String>();
		retour.put("fumeur",unProfil.getFumeur());
		retour.put("age", unProfil.getTrancheAge());
		retour.put("sexe", unProfil.getSexe());
		System.out.println("fumeur= " + unProfil.getFumeur());
		System.out.println("age= " + unProfil.getTrancheAge());
		System.out.println("sexe= " + unProfil.getSexe());
		return retour;
	}

}
