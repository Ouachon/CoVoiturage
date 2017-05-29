package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import donnees.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String VIEW_PAGES_URL="/WEB-INF/register.jsp"; // Page jsp de demarrage
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// RAZ des erreurs , des infos, et des champs formulaire, a la premiere creation de page
		HashMap<String,String> erreursParChamps = new HashMap<String, String>(); 
		HashMap<String,String>  valeursChampsFormulaire = new HashMap<String, String>();
		String actionMessage="";
		User razUser= new User("","","");
		HttpSession session = request.getSession();
		
		// Pour ne pas ressaisir capitole toulouse comme adresse arrivée
		valeursChampsFormulaire.put("adrArr","Capitole Toulouse");
		
		session.setAttribute("errors",erreursParChamps);
		session.setAttribute("form",valeursChampsFormulaire);
		session.setAttribute("actionMessage",actionMessage);
		session.setAttribute("errorStatus",false);
		
		
		// Puis on appele la page
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Quand c'est l'appel en post
		// On redessine la page sans faire de RAZ
		// dispatchServlet depuis le post fait un include, donc c'est aussi du POST
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward( request, response );
	}

}
