package ch.unige.pinfo2.service;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ch.unige.pinfo2.dom.Light;

/**
 * Facade for the light services.
 */
@Path("/devices/lights")
public class LightServiceRs {

	@Inject
	private LightService lightService;

	@GET
	@Produces("application/json")
	@Path("/getLastState")
	public JsonObject getLastState(@QueryParam("deviceId") String deviceId) {
		Light light = lightService.getLastState(deviceId);

		JsonBuilderFactory factory = Json.createBuilderFactory(null);

		JsonObject jsonLight = factory.createObjectBuilder().add("light", factory.createObjectBuilder()
				.add("power", light.getPower().doubleValue()).add("onOffStatus", light.getOnOffStatus().booleanValue()))
				.build();

		return jsonLight;
	}

	@POST
	@Produces("application/json")
	@Path("/setOnOrOff")
	public Response setOnOrOff(@QueryParam("deviceId") String deviceId, @QueryParam("onOrOff") String onOrOff) {
		Boolean resp = lightService.setOnOrOff(deviceId, onOrOff);
		if (resp) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Can't turn on or off").build();
		}
	}
}
