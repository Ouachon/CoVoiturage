package covoiturage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	// Request attributs
	Map<String, String> error;// = new HashMap<String, String>();
	Map<String, String> form;// = new HashMap<String, String>();
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

		// Prepare data for view (attributs)
		error = new HashMap<String, String>();
		form = new HashMap<String, String>();
		statusMessage = null;

		// Validate page
		String msgVal = null;
		if (msgVal == null) {
			form.put(FIELD_EMAIL, email);
		} else {
			error.put(FIELD_EMAIL, msgVal);
		}
		msgVal = validatePwd(pwd);
		if (msgVal == null) {
			form.put(FIELD_PWD, pwd);
		} else {
			error.put(FIELD_PWD, msgVal);
		}

		if (error.isEmpty() && authenticate(email, pwd)) {
			statusOk = true;
			statusMessage = "Connexion acceptée";
		} else {
			statusOk = false;
			statusMessage = "Connexion refusée";

		}

		// Prepare model to view
		request.setAttribute("form", form);
		request.setAttribute("error", error);
		request.setAttribute("statusOK", statusOk);
		request.setAttribute("statusMessage", statusMessage);

		if (statusOk == true) {
			if (typeCovoit.equals("typeconducteur")) {
				RequestDispatcher dispat = request.getRequestDispatcher("conducteur.jsp");
				dispat.forward(request, response);
			}
			else if (typeCovoit.equals("typepassager")) {
			
				RequestDispatcher dispat = request.getRequestDispatcher("passager.jsp");
				dispat.forward(request, response);
			}
		}	
		else {
			// Build view
			this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).include(request, response);
		}
	}

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

	private String validatePwd(String pwd) {
		return (pwd == null || pwd.equals("")) ? "Le mot de passe doit être renseigné" : null;
	}

	private boolean isUserExist(String login) {
		return true;
	}

	private boolean authenticate(String login, String pwd) {
		return true;
	}
}
