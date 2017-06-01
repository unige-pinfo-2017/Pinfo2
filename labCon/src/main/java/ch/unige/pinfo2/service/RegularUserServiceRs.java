package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.RegularUser;

@Path("/")
public class RegularUserServiceRs {

	@Inject
	private RegularUserService service;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/login")
	public Response verifiyLogin(JsonObject input) {
		String username = input.getString("username");
		String password = input.getString("password");
		String response = service.loginUser(username, password);
		if (response == null)
			return Response.status(Response.Status.UNAUTHORIZED).entity("Username or password is incorrect").build();
		else {
			RegularUser usr = service.getUserByToken(response);
			usr.setStatus(true);
			return Response.ok().entity(usr).build();
		}
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
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			service.addUser(user);
			return Response.ok(user).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Username already taken").build();
		}
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/logout")
	public Response logout(JsonObject input) {
		String token = input.getString("token");
		RegularUser usr = service.getUserByToken(token);
		usr.setStatus(false);
		return Response.ok().entity(usr).build();
	}

}
