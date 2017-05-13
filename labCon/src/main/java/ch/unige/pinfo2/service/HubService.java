package ch.unige.pinfo2.service;

import java.util.List;

public interface HubService {
	
	
	/**
	 * get the ids if all the sockets of the hub
	 * 
	 * @param token of the actual user
	 * 
	 * @return the ids of all the sockets of the hub
	 */
	List<Long> getSocketsIdByToken(Long token);
	
	/**
	 * get the consumption of a hub over a period
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return a list of the measures over this specified period
	 */
	List<Long> getConsumption(String from, String to, String deviceId);
	
	/**
	 * get the state (on/off) of a hub over a period
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return a list of state of the socket over a period
	 */
	List<Integer> getStatus(String from, String to, String deviceId);
	
	/**
	 * get the state of the sockets of the hub
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return the current state of the sockets of the hub
	 */
	List<Long> getStateCurrentSensor(String from, String to, String deviceId);
	
	/**
	 * change the state of the hub (usefull for the admin)
	 * 
	 * @param ON or OFF
	 * @param device id
	 * 
	 * @return true if the process succeed
	 */
	boolean setState(String state, String deviceId);
	

}
