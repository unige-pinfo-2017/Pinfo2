package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/restapi/devices/sockets")
public class SocketServiceRs {
	
	@Inject
	private SocketService socketService;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public Response getState(@QueryParam("id") String id,
			@QueryParam("fromDate") Long fromDate,
			@QueryParam("toDate") Long toDate){
		return Response.ok(socketService.getState(id, fromDate, toDate)).build();
	}
}
