package ch.unige.pinfo2.service;



import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ch.unige.pinfo2.dom.RegularUser;



/**
 * Provides methods for authentication on the server.
 * 
 * @author aurelien_coet
 *
 */
@WebServlet(name = "/login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
	
	/**
	 * Generated serial ID.
	 */
	private static final long serialVersionUID = -1516258385227218380L;
	
	@Inject
	private RegularUserService userService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String body = req.getReader().readLine();
		JSONObject jObject  = new JSONObject(body); // json
		String username = jObject.getString("username"); // get data object
		String password = jObject.getString("password"); // get the name from data.
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n"+username+"\n"+password+"\n\n\n\n\n\n\n");
		// We try to perform authentication on the server with the input parameters.
		try{
			req.login(username, password);
			//Long id = userService.getByEmail(username).getUserId();
			resp.setStatus(200);
			// The id of the user who authenticated is returned in the body of the response.
			RegularUser usr = userService.getUserByUsername(username);
			resp.getWriter().print(usr);
		} catch(ServletException e){
			resp.setStatus(401);
		}
	}
}
