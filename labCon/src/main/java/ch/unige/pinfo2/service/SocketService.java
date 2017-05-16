package ch.unige.pinfo2.service;

import java.util.List;

public interface SocketService {
	

	/**
	 * get the socket's id
	 * 
	 * @param token of the user
	 * 
	 * @return the socket's id
	 */
	Long getSocketIdByToken(Long token);

	/**
	 * get the consumption of a socket over a period
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return a list of the measures over this specified period
	 */
	List<Long> getStatePowerSensor(String from, String to, String deviceId);
	
	/**
	 * get the state (on/off) of a socket over a period
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return a list of state of the socket over a period
	 */
	List<Integer> getStatus(String from, String to, String deviceId);
	
	/**
	 * get the state of a socket
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return the current state of the socket
	 */
	List<Long> getStateCurrentSensor(String from, String to, String deviceId);
	
	/**
	 * change the state of a socket
	 * 
	 * @param ON or OFF
	 * @param device id
	 * 
	 * @return true if the process succeed
	 */
	boolean setState(String state, String deviceIf);
		
}
