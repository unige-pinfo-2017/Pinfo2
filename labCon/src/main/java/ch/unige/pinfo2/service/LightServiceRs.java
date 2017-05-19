package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ch.unige.pinfo2.dom.Light;

@Path("/devices/lights")
public class LightServiceRs {
	
	@Inject
	private LightService lightService;
	
	@GET
	@Produces("application/json")
	@Path("/getState")
	public JsonObject getState(@QueryParam("deviceId") String deviceId) {
		Light light = lightService.getState(deviceId);
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		
		JsonObject jsonLight = factory.createObjectBuilder()
				.add("light", factory.createObjectBuilder()
						.add("power", light.getPower().doubleValue())
						.add("onOffStatus", light.getOnOffStatus().booleanValue()))
				.build();
		
		return jsonLight;
	}
}
