package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.RegularUser;

@Path("/")
public class RegularUserServiceRs {

	@Inject
	private RegularUserService service;

	@SuppressWarnings("unused")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/login2")
	public Response verifiyLogin(JsonObject input) {
		String username = input.getString("username");
		String password = input.getString("password");
		String response = service.loginUser(username, password);
		if (response == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity("Username or password is incorrect").build();
		else
			return Response.ok().entity(response).build();
	}


	@POST
	@Path("/register")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response addUser(JsonObject input) {
		String username = input.getString("username");
		String password = input.getString("password");
		String firstName = input.getString("firstName");
		String lastName = input.getString("lastName");

		if (service.getUserByUsername(username) == null) {
			RegularUser user = new RegularUser();
			user.setUserName(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setRole("regularUser");
			service.addUser(user);
			return Response.ok(user).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Username already taken").build();
		}
	}
	
	@GET
	@Path("/ouioui")
	@Produces("text/plain")
	public String ouioui() {
		return "ouiouiouioui";
	}

}
