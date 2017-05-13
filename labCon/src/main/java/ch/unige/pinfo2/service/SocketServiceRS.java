package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/rest/socket")
public class SocketServiceRS {
	
	@Inject
	private SocketService service;
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getSocketId")
	public Response getSocketId(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getStatePowerSensor")
	public Response getStatePowerSensor(JsonObject input){
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
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getStateCurrentSensor")
	public Response getStateCurrentSensor(JsonObject input){
		// TODO
		return Response.ok().build();
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/setStatus")
	public Response setStatus(JsonObject input){
		// TODO
		return Response.ok().build();
	}

}
