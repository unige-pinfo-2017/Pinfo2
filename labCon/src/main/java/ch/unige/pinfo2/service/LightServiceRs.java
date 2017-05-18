package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/restapi/devices/lights")
public class LightServiceRs {
	
	@Inject
	private LightService lightService;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public Response getState(@QueryParam("id") String id,
			@QueryParam("from") Long fromDate,
			@QueryParam("to") Long toDate){
		return Response.ok(lightService.getState(id, fromDate, toDate)).build();
	}
}
