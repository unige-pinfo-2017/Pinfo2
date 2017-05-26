package ch.unige.pinfo2.service;

import ch.unige.pinfo2.dom.Light;

public interface LightService {

	/**
	 * get a light's state over a period
	 * 
	 * @param deviceId a string identifying the light
	 * @param from the start time for the period
	 * @param to the end time for the period
	 * 
	 * @return an arraylist of lights in which each light represents the state of the light at a given time
	 */
	public Light  getState(String deviceId);
	
	/**
	 * turn on or off a light
	 * 
	 * @param deviceId a string identifying the light
	 * @param onOrOff a string
	 * 
	 * @return true or false
	 */
	public Boolean setOnOrOff(String deviceId, String onOrOff);
	
	
}
