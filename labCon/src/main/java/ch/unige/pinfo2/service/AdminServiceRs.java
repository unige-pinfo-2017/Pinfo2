package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.RegularUser;

@Path("/users")
public class AdminServiceRs {

	@Inject
	private AdminService service;

	@POST
	@Path("/addUser")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response addUser(JsonObject input) {
		String username = input.getString("username");
		String password = input.getString("password");
		String firstName = input.getString("firstName");
		String lastName = input.getString("lastName");
		String role = input.getString("role");

		RegularUser user = new RegularUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(role);
		boolean success = service.addUser(user);
		if (success) {
			return Response.ok(user).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Username already taken").build();
		}

	}
	
	@GET
	@Path("/getAllUsers")
	@Produces({ "application/json" })
	public Response getAllUsers() {
		return Response.ok(service.getAllUsers()).build();
	}

}
