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
 * Servlet implementation class UsersProcheServlet
 */
@WebServlet("/UsersProcheServlet")
public class UsersProcheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String name= request.getParameter("lat");
		
		// On recupère l'attribut
		HttpSession session = request.getSession();	
		int rayon = 5;
		CoordGPS uneCoord = new CoordGPS(45,45);
		
		session.setAttribute("listeUsersProche", User.unUserManager.usersProcheDeCoordonnees(uneCoord, rayon));
		dispat = request.getRequestDispatcher("index.jsp");
		
		dispat.include(request, response);
	}

}
