package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/rest/hub")
public class HubServiceRs {
	
	@Inject
	private HubService service;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public Response getState(@QueryParam("id") Integer id,
			@QueryParam("fromDate") Long fromDate,
			@QueryParam("toDate") Long toDate){
		return Response.ok(service.getState(fromDate, toDate, id)).build();
	}
}
