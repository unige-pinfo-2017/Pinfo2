package ch.unige.pinfo2.service;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/devices")
public class DeviceServiceRs {

	@Inject
	private DeviceService deviceService;
	
	@GET
	@Produces("application/json")
	@Path("/getIds")
	public JsonObject getIds() {
		ArrayList<ArrayList<String>> deviceIds = deviceService.getDeviceIds();
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		
		JsonArrayBuilder jsonLightIdsContentBuilder = factory.createArrayBuilder();
		JsonArrayBuilder jsonSocketIdsContentBuilder = factory.createArrayBuilder();
		JsonArrayBuilder jsonHubIdsContentBuilder = factory.createArrayBuilder();
		
		for(String lightId : deviceIds.get(0)) {
			jsonLightIdsContentBuilder.add(lightId);
		}
		
		for(String socketId : deviceIds.get(1)) {
			jsonSocketIdsContentBuilder.add(socketId);
		}
		
		for(String hubId : deviceIds.get(2)) {
			jsonHubIdsContentBuilder.add(hubId);
		}
		
		JsonObject jsonDeviceIds = factory.createObjectBuilder()
				.add("deviceIds", factory.createObjectBuilder()
						.add("lightIds", jsonLightIdsContentBuilder.build())
						.add("socketIds", jsonSocketIdsContentBuilder.build())
						.add("hubIds", jsonHubIdsContentBuilder.build()))
				.build();
		
		return jsonDeviceIds;
	}
	
	@GET
	@Produces("application/json")
	@Path("/getWorkstation")
	public JsonObject getWorkstation(@QueryParam("deviceId") String deviceId) {
		String deviceWorkstation = deviceService.getWorkstation(deviceId);
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		
		JsonObject jsonWorkstation = factory.createObjectBuilder()
				.add("workstation", deviceWorkstation)
				.build();
		
		return jsonWorkstation;
	}
	
	@GET
	@Produces("application/json")
	@Path("/getWorkstationSocketIds")
	public JsonObject getHubSocketIds(@QueryParam("workstation") String workstation) {
		List<String> socketIds = deviceService.getSocketIds(workstation);
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		
		JsonArrayBuilder jsonSocketIdsContentBuilder = factory.createArrayBuilder();
		
		for(String socketId : socketIds) {
			jsonSocketIdsContentBuilder.add(socketId);
		}
		
		JsonObject jsonSocketIds = factory.createObjectBuilder()
				.add("socketIds", jsonSocketIdsContentBuilder.build())
				.build();
		
		return jsonSocketIds;
	}
}
