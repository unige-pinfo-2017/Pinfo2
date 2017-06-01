package ch.unige.pinfo2.service;

import java.util.List;

import ch.unige.pinfo2.dom.Device;

public class DeviceServiceImpl implements DeviceService {

	@Override
	public List<String> getDeviceIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDevice(Device newDevice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInDatabase(String deviceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void assignWorkstation(String deviceId, String workstation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void denyWorkstation(String deviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getWorkstation(String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSocketIds(String workstation) {
		// TODO Auto-generated method stub
		return null;
	}

}
