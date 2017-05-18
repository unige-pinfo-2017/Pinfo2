package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
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
	@Path("/getState")
	public String getState(@QueryParam("deviceId") String id,
			@QueryParam("from") Long fromDate,
			@QueryParam("to") Long toDate){
		return new Gson().toJson(socketService.getState(id, fromDate, toDate));
	}
}
