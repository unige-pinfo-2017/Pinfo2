package ch.unige.pinfo2.service;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.RegularUser;

@Path("/rest")
public class UserServiceRs {
	
	//@Inject
	//private UserService service;
	
	@POST 
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/login")
	public Response verifiyLogin(JsonObject input){
		String username = input.getString("username");
		String password = input.getString("password");
		
		
		
		
		return Response.ok().build();
	}
	
	@PUT
	@Path("/add")
	@Produces({ "application/json" })
	public Response addUser(JsonObject input){
		String username = input.getString("username");
		String password = input.getString("password");
		String firstName = input.getString("first_name");
		String lastName = input.getString("last_name");
		String tokenString = input.getString("token");
		Long token = Long.valueOf(tokenString).longValue();
		
		
		return Response.ok().build();
	}

}
