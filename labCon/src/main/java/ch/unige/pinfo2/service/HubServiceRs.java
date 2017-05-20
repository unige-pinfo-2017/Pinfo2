package ch.unige.pinfo2.service;

import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Socket;

@Path("/devices/hubs")
public class HubServiceRs {

	@Inject
	private HubService hubService;

	@GET
	@Produces("text/plain")
	@Path("/getState")
	public String getState(@QueryParam("deviceId") String deviceId,
			@QueryParam("from") Long from,
			@QueryParam("to") Long to){
		List<Hub> hubStates= 
				hubService.getState(deviceId, from, to);
		ListIterator<Hub> it = hubStates.listIterator();
		String hubStatesGson = "{ ";
		while (it.hasNext()){
			Hub state = (Hub) it.next();
			ListIterator<Socket> it2 = state.getSockets().listIterator();
			hubStatesGson = hubStatesGson.concat("{ ");
			String tmp = new Gson().toJson(state.getSockets());
			System.out.println("\n\n\n\n\n\n" + tmp+"\n\n\n\n\n\n");
			hubStatesGson = hubStatesGson.concat(new Gson().toJson(state.getSockets()));
			/*
			while (it2.hasNext()){
				Socket sock = (Socket) it2.next();
				hubStatesGson = hubStatesGson.concat(new Gson().toJson(sock));
				if (it2.hasNext()){
					hubStatesGson = hubStatesGson.concat(", ");
				}
			}
			*/
			hubStatesGson = hubStatesGson.concat(" }");
			if (it.hasNext()){
				hubStatesGson = hubStatesGson.concat(", ");
			}
		}
		hubStatesGson = hubStatesGson.concat(" }");
		return hubStatesGson;
	}
}
