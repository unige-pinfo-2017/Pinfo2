package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/rest/hub")
public class HubServiceRS {
	
	@Inject
	private HubService service;
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getSocketIds")
	public Response getSocketsIds(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getComsumption")
	public Response getConsumption(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getStatus")
	public Response getStatus(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getState")
	public Response setState(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	
	

}
