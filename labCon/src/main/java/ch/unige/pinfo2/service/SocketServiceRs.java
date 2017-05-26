package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/devices/sockets")
public class SocketServiceRs {

	@Inject
	private SocketService socketService;

	@GET
	@Produces("application/json")
	@Path("/getStates")
	public String getState(@QueryParam("deviceId") String id, @QueryParam("from") Long fromDate,
			@QueryParam("to") Long toDate) {
		return new Gson().toJson(socketService.getState(id, fromDate, toDate));
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
