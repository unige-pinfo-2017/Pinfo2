package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

@Path("/devices/lights")
public class LightServiceRs {
	
	@Inject
	private LightService lightService;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public String getState(@QueryParam("deviceId") String deviceId,
			@QueryParam("from") Long from,
			@QueryParam("to") Long to){
		return new Gson().toJson(lightService.getState(deviceId, from, to));
	}
}
