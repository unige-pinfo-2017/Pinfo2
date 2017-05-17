package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/rest/socket")
public class SocketServiceRs {
	
	@Inject
	private SocketService service;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public Response getState(@QueryParam("id") Integer id,
			@QueryParam("fromDate") Long fromDate,
			@QueryParam("toDate") Long toDate){
		return Response.ok(service.getState(fromDate, toDate, id)).build();
	}
}
