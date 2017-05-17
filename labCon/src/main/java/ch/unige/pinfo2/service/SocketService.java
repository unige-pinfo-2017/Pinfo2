package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Socket;

public interface SocketService {

	/**
	 * get the consumption of a socket over a period
	 * 
	 * @param from
	 * @param to
	 * @param device id
	 * 
	 * @return a list of the measures over this specified period
	 */
	public List<Socket> getState(Long from, Long to, int deviceId);
	

		
}
