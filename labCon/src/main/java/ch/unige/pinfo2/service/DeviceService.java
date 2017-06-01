package ch.unige.pinfo2.service;

import java.util.ArrayList;
import java.util.List;

import ch.unige.pinfo2.dom.Device;
import ch.unige.pinfo2.dom.DeviceType;

/**
 * Defines the contract for the device service.
 */
public interface DeviceService {

	/**
	 * get all devices identifiers
	 * 
	 * @return an ArrayList of ArrayList of String in which every ArrayList
	 *         contains Strings identifying a device of a specific type
	 */
	public ArrayList<ArrayList<String>> getDeviceIds();

	/**
	 * add a new device to the database
	 * 
	 * @param newDevice
	 *            the Device to be added
	 */
	public void addDevice(Device newDevice);

	/**
	 * check if a device is present in the database
	 * 
	 * @param deviceId
	 *            a String identifying the device
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
	 * @param deviceId
	 *            a String identifying the device
	 */
	public void denyWorkstation(String deviceId);

	/**
	 * get a device's workstation
	 * 
	 * @return a String identifying the workstation
	 */
	public String getWorkstation(String deviceId);

	/**
	 * get all sockets assigned to a workstation
	 * 
	 * @param workstation
	 *            the name of the workstaion
	 * 
	 * @return a List of String containing all socket ids
	 */
	public List<String> getSocketIds(String workstation);

	/**
	 * @param deviceId
	 *            a String identifying a Device
	 * 
	 * @return the type of the device
	 */
	public DeviceType getDeviceType(String deviceId);
}
