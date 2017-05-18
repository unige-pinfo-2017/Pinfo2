package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Hub;

public interface HubService {
	
	
	/**
	 * get the ids if all the sockets of the hub
	 * 
	 * @param token of the actual user
	 * 
	 * @return the ids of all the sockets of the hub
	 */
	List<Hub> getState(Long from, Long to, Integer id);
	

}
