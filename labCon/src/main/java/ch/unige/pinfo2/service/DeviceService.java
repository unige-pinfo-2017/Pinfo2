package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Device;

public interface DeviceService {

	/**
	 * get all devices
	 * 
	 * @return a List of Device in which every Device is a Device is a specific Device
	 */
	public List<Device> getDevices();
	
	
	/**
	 * add a new device to the database
	 * 
	 * @param newDevice the Device to be added
	 */
	public void addDevice(Device newDevice);
	
	/**
	 * check if a device is present in the database
	 * 
	 * @param deviceId a String identifying the device
	 */
	public boolean isInDatabase(String deviceId);
	
	/**
	 * assign a workstation to a device
	 * 
	 * @param workstation
	 */
	public void assignWorkstation(String deviceId, String workstation);
		
	/**
	 * deny a device its workstation
	 * 
	 * @param deviceId a String identifying the device
	 */
	public void denyWorkstation(String deviceId);
	
	/**
	 * get a device's workstation
	 * 
	 * @return a String identifying the workstation
	 */
	public String getWorkstation(String deviceId);
}
