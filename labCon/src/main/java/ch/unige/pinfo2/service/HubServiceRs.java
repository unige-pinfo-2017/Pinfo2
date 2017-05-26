package ch.unige.pinfo2.service;

import java.util.ArrayList;
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
	@Produces("application/json")
	@Path("/getStates")
	public String getState(@QueryParam("deviceId") String deviceId, @QueryParam("from") Long from,
			@QueryParam("to") Long to) {
		List<Hub> hubStates = hubService.getState(deviceId, from, to);
		ListIterator<Hub> it = hubStates.listIterator();
		String hubStatesGson = "{ \"states\": [ ";
		while (it.hasNext()) {
			Hub state = (Hub) it.next();
			ListIterator<Socket> it2 = state.getSockets().listIterator();

			// doing the sum over the sockets
			Double current = 0d;
			Double power = 0d;
			Long timestamp = 0l;
			while (it2.hasNext()) {
				Socket sock = (Socket) it2.next();
				current += sock.getCurrent();
				power += sock.getPower();
				timestamp = sock.getTimestamp();
			}
			// adding to the Json output
			hubStatesGson = hubStatesGson.concat(
					"{ \"current\": " + current + ", \"power\": " + power + ", \"timestamp\": " + timestamp + "}");
			if (it.hasNext()) {
				hubStatesGson = hubStatesGson.concat(", ");
			}
		}
		hubStatesGson = hubStatesGson.concat(" ] }");
		return hubStatesGson;
	}

	@GET
	@Produces("application/json")
	@Path("/getSocketsState")
	public String getSocketState(@QueryParam("deviceId") String deviceId) {
		Hub state = hubService.getLastState(deviceId);
		ListIterator<Socket> it2 = state.getSockets().listIterator();
		List<Boolean> socketState = new ArrayList<Boolean>();
		while (it2.hasNext()) {
			Boolean next = it2.next().getIsOn();
			socketState.add(next);
		}

		return new Gson().toJson(socketState);
	}
}
