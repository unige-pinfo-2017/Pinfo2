package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Socket;

/**
 * Defines the contract for the socket service.
 */
public interface SocketService {

	/**
	 * get a socket's latest state
	 * 
	 * @param deviceId
	 *            a string identifying the socket
	 * 
	 * @return a Socket containing the last state
	 */
	public Socket getLastState(String deviceId);

	/**
	 * get the consumption of a socket over a period
	 * 
	 * @param from
	 * @param to
	 * @param device
	 *            id
	 * 
	 * @return a list of the measures over this specified period
	 */
	public List<Socket> getStates(String deviceId, Long from, Long to);

	/**
	 * set the socket on or off
	 * 
	 * @param String
	 *            of the device id
	 * @param String
	 *            onOrOff
	 * 
	 * @return true if the process did'nt failed
	 */
	public Boolean setOnOrOff(String deviceId, String onOrOff);

}
