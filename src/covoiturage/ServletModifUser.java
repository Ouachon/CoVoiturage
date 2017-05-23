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
 * Servlet implementation class ServletModifUser
 */
@WebServlet("/ServletModifUser")
public class ServletModifUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String VIEW_PAGES_URL="/WEB-INF/user/formModify.jsp"; 
	
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
		//enregistrement des éléments du compte
		String identifiantUser = request.getParameter("userCourant");
			System.out.println("identifiant User : "+ identifiantUser);
	
		UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();
		User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);
		String email = userLogge.getEmail();
		String mdp = userLogge.getPwd();
		String name = userLogge.getNom();
		int age = userLogge.getAge();
		String sexe = userLogge.getSexe();	
		String adresse = userLogge.getAdresseComplete();
		String fumer = userLogge.getFumeur();
		
		// On tient le user loggé on exporte son profil pour le mettre dans
		// une hashMap que le JSP exploitera

		request.setAttribute("email", email);
		dispat = request.getRequestDispatcher("/formModify.jsp");		
		dispat.include(request, response);
		}


}
