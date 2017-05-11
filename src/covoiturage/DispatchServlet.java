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
 * Servlet implementation class DispatchServlet
 */
@WebServlet("/DispatchServlet")
public class DispatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String FIELD_PWD2 = "pwd2";
	private static final String FIELD_PWD1 = "pwd1";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_NAME = "name";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

    
    @Override
    public void init() throws ServletException {
    	// Remise a zero des champs et messages d'erreur
    	// Dans l'init , pas possible on n'a pas request
    	// Et c'est appelé uniquement au 1er user qui se connecte
   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet est appelé a la premiere creation de page
		// Mais ici on ne l'appele pas jamais car register.jsp appelle en fait doGet de ....
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String actionMessage="Succés";
		boolean presenceErreurs = false;
		HashMap<String,String> erreursParChamps = null;
		RequestDispatcher dispat;
		String email= request.getParameter(FIELD_EMAIL);
		String pwd1= request.getParameter(FIELD_PWD1);
		String name= request.getParameter(FIELD_NAME);
		
		// On recupère l'attribut
		HttpSession session = request.getSession();	
		
		User newUser = new User(email,pwd1,name);		
		newUser.validateAll();		
		erreursParChamps = newUser.getHashMapErrors();
		
		session.setAttribute("errors",erreursParChamps);
		session.setAttribute("newUser",newUser);
		session.setAttribute("form",newUser.getHashMapValeurs());
		
		if (erreursParChamps.size()  > 0) {
			// aucune erreur sur chaque paramètre, 
			// a) On teste la cohérence globale de tous les champs du formulaire
			
			// b) actionMessage
			actionMessage="Echec";
			presenceErreurs=true;
		} else
		{
			newUser.addToUsers();
			session.setAttribute("listeDesUsers",User.getListeDesUsers());
			
			// Ici il y a une dependance entre user qui doit connaitre userGestionnaire
			// Il serait mieux que UserGestionnaire ait une dependance sur User, voir DP fabrique
		}
		session.setAttribute("actionMessage",actionMessage);
		session.setAttribute("errorStatus",presenceErreurs);
		dispat = request.getRequestDispatcher("Register");
		
		dispat.include(request, response);

	}

}
