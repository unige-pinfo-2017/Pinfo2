package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Light;

/**
 * Defines the contract for the light service.
 */
public interface LightService {

	/**
	 * get a light's latest state
	 * 
	 * @param deviceId
	 *            a string identifying the light
	 * 
	 * @return a Light containing the last state
	 */
	public Light getLastState(String deviceId);

	/**
	 * get a light's states over a period of time
	 * 
	 * @param deviceId
	 *            a string identifying the light
	 * @param from
	 *            the start time for the period
	 * @param to
	 *            the end time for the period
	 * 
	 * @return a List of Light in which every Light corresponds to a state
	 */
	public List<Light> getStates(String deviceId, Long from, Long to);

	/**
	 * turn on or off a light
	 * 
	 * @param deviceId
	 *            a string identifying the light
	 * @param onOrOff
	 *            a string
	 * 
	 * @return true or false
	 */
	public Boolean setOnOrOff(String deviceId, String onOrOff);

}
