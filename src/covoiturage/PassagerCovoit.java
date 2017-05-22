package covoiturage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PassagerCovoit
 */
@WebServlet("/PassagerCovoit")
public class PassagerCovoit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String FIELD_DELETE = "suppression";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassagerCovoit() {
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
		HttpSession session = request.getSession();

		// suppression d'un compte saisi
		String identifiantUser = request.getParameter("userCourant");
		System.out.println("identifiant = " + identifiantUser);
		String delUser = request.getParameter(FIELD_DELETE);
		System.out.println("valeur de delUser = " + delUser);
		if (delUser.equals("Supprimer le compte")) {

			UserGestionnaireInSession myUserManager = UserGestionnaireInSession.getInstance();

			User userLogge = myUserManager.getListeDesUsers().get(identifiantUser);

			System.out.println("nbre utilisateurs avant la suppression =" + myUserManager.getListeDesUsers().size());
			session.setAttribute("listeDesUsers", myUserManager.getListeDesUsers().size());

			myUserManager.delete(identifiantUser);
			System.out.println(
					"nbre utilisateurs après la suppression du compte =" + myUserManager.getListeDesUsers().size());

			RequestDispatcher dispat = request.getRequestDispatcher("index.jsp");
			dispat.forward(request, response);
		}
	}
}
