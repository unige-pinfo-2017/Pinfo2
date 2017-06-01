package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Hub;

public interface HubService {
	
	/**
	 * get the last state of a Hub
	 * 
	 * @param id of the Hub
	 * 
	 * @return the last state of the Hub
	 */
	Hub getLastState(String id);
	
	/**
	 * get the states of a Hub over a period
	 * 
	 * @param deviceId: id of the Hub
	 * @param from: timestamp of the beginning of the period
	 * @param to: timestamp of the end of the period
	 * 
	 * @return the ids of all the sockets of the hub
	 */
	List<Hub> getStates(String deviceId, Long from, Long to);

}
