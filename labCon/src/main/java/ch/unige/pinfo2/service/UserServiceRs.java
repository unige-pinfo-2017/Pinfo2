package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.RegularUser;

@Path("/")
public class UserServiceRs {

	@Inject
	private UserService service;

	@SuppressWarnings("unused")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/login")
	public Response verifiyLogin(JsonObject input){
		String username = input.getString("username");
		String password = input.getString("password");
		Long response = service.loginUser(username, password);
		if (response==null)
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Username or password is incorrect").build();
		else
			return Response.ok().entity(service.getUserByToken(response)).build();
	}

	@POST
	@Path("/register")
	@Produces({ "application/json" })
	@Consumes({"application/json"})
	public Response addUser(JsonObject input){
		String username = input.getString("username");
		String password = input.getString("password");
		String firstName = input.getString("firstName");
		String lastName = input.getString("lastName");
		//String tokenString = input.getString("token");
		//Long token = Long.valueOf(tokenString).longValue();
		if (service.getUserByUsername(username)==null){
			RegularUser user = new RegularUser();
			user.setUserName(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setToken((long)99);
			service.addUser(user);
			return Response.ok().build();
		}
		else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Username already taken").build();
        }
	}

}
