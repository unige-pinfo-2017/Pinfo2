package ch.unige.pinfo2.service;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.Socket;

/**
 * Facade for the socket services.
 */
@Path("/devices/sockets")
public class SocketServiceRs {

	@Inject
	private SocketService socketService;

	@GET
	@Produces("application/json")
	@Path("/getLastState")
	public JsonObject getLastState(@QueryParam("deviceId") String deviceId) {
		Socket socket = socketService.getLastState(deviceId);

		JsonBuilderFactory factory = Json.createBuilderFactory(null);

		JsonObject jsonSocket = factory.createObjectBuilder().add("socket", factory.createObjectBuilder()
				.add("power", socket.getPower().doubleValue()).add("onOffStatus", socket.getIsOn().booleanValue()))
				.build();

		return jsonSocket;
	}

	@GET
	@Produces("application/json")
	@Path("/getStates")
	public JsonObject getStates(@QueryParam("deviceId") String deviceId, @QueryParam("from") Long from,
			@QueryParam("to") Long to) {
		List<Socket> socketStates = socketService.getStates(deviceId, from, to);

		JsonBuilderFactory factory = Json.createBuilderFactory(null);

		JsonArrayBuilder jsonSocketStatesContentBuilder = factory.createArrayBuilder();

		for (Socket s : socketStates) {
			jsonSocketStatesContentBuilder.add(factory.createObjectBuilder()
					.add("timestamp", s.getTimestamp().longValue()).add("power", s.getPower().doubleValue()));

		}

		JsonObject jsonSocketStates = factory.createObjectBuilder()
				.add("socketStates", jsonSocketStatesContentBuilder.build()).build();

		return jsonSocketStates;
	}

	@POST
	@Produces("application/json")
	@Path("/setState")
	public Response setOnOrOff(@QueryParam("deviceId") String deviceId, @QueryParam("onOrOff") String onOrOff) {
		Boolean resp = socketService.setOnOrOff(deviceId, onOrOff);
		if (resp) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Can't turn on or off").build();
		}
	}
}
