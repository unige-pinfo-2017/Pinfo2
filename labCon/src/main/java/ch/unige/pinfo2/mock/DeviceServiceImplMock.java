package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.unige.pinfo2.dom.Device;
import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.DeviceService;

public class DeviceServiceImplMock implements DeviceService {

	@Override
	public List<Device> getDevices() {
		Random valueGenerator = new Random();
		
		List<Device> devices = new ArrayList<Device>();
		
		for(int i=0; i<3; i++) {
			devices.add(new Light("light"+i,Math.abs(valueGenerator.nextLong()),valueGenerator.nextDouble()*15));
		}
		for(int i=0; i<12; i++) {
			devices.add(new Socket("socket"+i,Math.abs(valueGenerator.nextLong()),valueGenerator.nextDouble()*100,valueGenerator.nextDouble()*100));
		}
		return devices;
	}

	@Override
	public void addDevice(Device newDevice) {
		// TODO ajouter un device a la base de donnee
		
	}

	@Override
	public boolean isInDatabase(String deviceId) {
		// TODO verifier si le device est dans la base de donnee
		return false;
	}

	@Override
	public void assignWorkstation(String deviceId, String workstation) {
		// TODO ajouter une workstation a un device dans la base de donnee
		
	}

	@Override
	public void denyWorkstation(String deviceId) {
		// TODO retirer la workstation d'un device a la base de donnee
		
	}

	@Override
	public String getWorkstation(String deviceId) {
		// TODO obtenir la workstation du device
		return null;
	}

}
