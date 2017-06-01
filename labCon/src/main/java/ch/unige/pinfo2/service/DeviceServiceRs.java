package ch.unige.pinfo2.service;


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
	@Path("/")
	public JsonObject getDeviceIds() {
		List<String> deviceIds = deviceService.getDeviceIds();
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		
		JsonArrayBuilder jsonLightIdsBuilder = factory.createArrayBuilder();
		JsonArrayBuilder jsonSocketIdsBuilder = factory.createArrayBuilder();
		JsonArrayBuilder jsonHubIdsBuilder = factory.createArrayBuilder();
		
		//TODO add a method allowing one to get a device type from its name
		
		/*for(String deviceId : deviceIds) {
			if()
		}
		
		for(Socket s : socketStates) {
			jsonSocketStatesContentBuilder.add(factory.createObjectBuilder()
					.add("timestamp", s.getTimestamp().longValue())
					.add("power",s.getPower().doubleValue()));
					
		}
		
		JsonObject jsonSocketStates = factory.createObjectBuilder()
				.add("socketStates", jsonSocketStatesContentBuilder.build())
				.build();
		
		return jsonSocket;*/
		return null;
	}
}
