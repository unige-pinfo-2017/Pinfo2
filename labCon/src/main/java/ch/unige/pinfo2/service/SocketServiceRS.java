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
		Long token = Long.valueOf(input.getString("token"));
		return Response.ok(service.getSocketIdByToken(token)).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getStatePowerSensor")
	public Response getStatePowerSensor(JsonObject input){
		String id = input.getString("id");
		String from = input.getString("from");
		String to = input.getString("to");
		return Response.ok(service.getStatePowerSensor(from, to, id)).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/getStatus")
	public Response getStatus(JsonObject input){
		String id = input.getString("id");
		String from = input.getString("from");
		String to = input.getString("to");
		return Response.ok(service.getStatus(from, to, id)).build();
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
