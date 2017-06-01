package ch.unige.pinfo2.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.DeviceService;
import ch.unige.pinfo2.service.HubService;
import ch.unige.pinfo2.service.SocketService;

@Alternative
public class HubServiceImpl implements HubService{
	
	@Inject
	private DeviceService deviceService;
	
	@Inject
	private SocketService socketService;
	
	private ArrayList<Socket> fuseSocketStates(ArrayList<Socket> socketStates1, ArrayList<Socket> socketStates2) {
			ArrayList<Socket> fusedSocketStates = new ArrayList<Socket>();
			
			if(socketStates1.isEmpty() || socketStates2.isEmpty()) {
				return fusedSocketStates;
			}
			
			double lastCurrent1 = 0, lastCurrent2 = 0, lastPower1 = 0, lastPower2 = 0;
			// remove unsyncable states
			if(socketStates1.get(0).getTimestamp().longValue() < socketStates2.get(0).getTimestamp().longValue()) {
				while(socketStates1.get(0).getTimestamp().longValue() < socketStates2.get(0).getTimestamp().longValue()) {
					lastCurrent1 = socketStates1.get(0).getCurrent().doubleValue();
					lastPower1 = socketStates1.get(0).getPower().doubleValue();
					socketStates1.remove(0);
					if(socketStates1.isEmpty()) {
						break;
					}
				}
			} else if(socketStates1.get(0).getTimestamp().longValue() > socketStates2.get(0).getTimestamp().longValue()) {
				while(socketStates1.get(0).getTimestamp().longValue() < socketStates2.get(0).getTimestamp().longValue()) {
					lastCurrent2 = socketStates2.get(0).getCurrent().doubleValue();
					lastPower2 = socketStates2.get(0).getPower().doubleValue();
					socketStates2.remove(0);
					if(socketStates2.isEmpty()) {
						break;
					}
				}
			}
			
			while(true) {
				// if a list is empty, append the remaining elements of the other list
				if(socketStates1.isEmpty()) {
					while(!socketStates2.isEmpty()) {
						fusedSocketStates.add(new Socket("fusedSocket",socketStates2.get(0).getTimestamp(),socketStates2.get(0).getCurrent()+lastCurrent1,socketStates2.get(0).getPower()+lastPower1));
						socketStates2.remove(0);
					}
				}
				if(socketStates2.isEmpty()) {
					while(!socketStates1.isEmpty()) {
						fusedSocketStates.add(new Socket("fusedSocket",socketStates1.get(0).getTimestamp(),socketStates1.get(0).getCurrent()+lastCurrent2,socketStates1.get(0).getPower()+lastPower2));
						socketStates1.remove(0);
					}
				}
				
				// find the first element of the two lists and add it taking into account the last known state of the other socket at a time prior or equal
				if(socketStates1.get(0).getTimestamp().longValue() < socketStates2.get(0).getTimestamp().longValue()) {
					fusedSocketStates.add(new Socket("fusedSocket",socketStates1.get(0).getTimestamp(),socketStates1.get(0).getCurrent()+lastCurrent2,socketStates1.get(0).getPower()+lastPower2));
					lastCurrent1 = socketStates1.get(0).getCurrent().doubleValue();
					lastPower1 = socketStates1.get(0).getPower().doubleValue();
					socketStates1.remove(0);
				} else if(socketStates1.get(0).getTimestamp().longValue() > socketStates2.get(0).getTimestamp().longValue()) {
					fusedSocketStates.add(new Socket("fusedSocket",socketStates2.get(0).getTimestamp(),socketStates2.get(0).getCurrent()+lastCurrent1,socketStates2.get(0).getPower()+lastPower1));
					lastCurrent2 = socketStates2.get(0).getCurrent().doubleValue();
					lastPower2 = socketStates2.get(0).getPower().doubleValue();
					socketStates2.remove(0);
				} else {
					fusedSocketStates.add(new Socket("fusedSocket",socketStates1.get(0).getTimestamp(),socketStates1.get(0).getCurrent()+socketStates2.get(0).getCurrent(),socketStates1.get(0).getPower()+socketStates2.get(0).getPower()));
					lastCurrent1 = socketStates1.get(0).getCurrent().doubleValue();
					lastPower1 = socketStates1.get(0).getPower().doubleValue();
					lastCurrent2 = socketStates2.get(0).getCurrent().doubleValue();
					lastPower2 = socketStates2.get(0).getPower().doubleValue();
					socketStates1.remove(0);
					socketStates2.remove(0);
				}
			}
	}

	@Override
	public List<Hub> getStates(String deviceId, Long from, Long to){
		
		String hubWorkstation = deviceService.getWorkstation(deviceId);
		
		List<String> socketIds = deviceService.getSocketIds(hubWorkstation);
		
		ArrayList<ArrayList<Socket>> socketStates = new ArrayList<ArrayList<Socket>>();
		
		for(String socketId : socketIds) {
			socketStates.add((ArrayList<Socket>) socketService.getStates(socketId, from, to));
		}
		
		ArrayList<Socket> fusedSocketStates = new ArrayList<Socket>();
		
		fusedSocketStates = fuseSocketStates(socketStates.get(0),socketStates.get(1));
		
		for(int i=2; i<socketStates.size(); i++) {
			fusedSocketStates = fuseSocketStates(fusedSocketStates, socketStates.get(i));
		}
		
		// create the hub states
		List<Hub> hubStates = new ArrayList<Hub>();
		for(Socket fusedSocketState : fusedSocketStates) {
			hubStates.add(new Hub(deviceId, fusedSocketState.getTimestamp(), fusedSocketState.getCurrent(), fusedSocketState.getPower()));
		}
		
		return hubStates;
	}

	@Override
	public Hub getLastState(String deviceId) {

		String hubWorkstation = deviceService.getWorkstation(deviceId);
		
		List<String> socketIds = deviceService.getSocketIds(hubWorkstation);
		
		ArrayList<Socket> socketLastStates = new ArrayList<Socket>();
		
		for(String socketId : socketIds) {
			socketLastStates.add(socketService.getLastState(socketId));
		}
		
		long timestamp = 0;
		double current = 0, power = 0;
		for(Socket socketLastState : socketLastStates) {
			if(socketLastState.getTimestamp().longValue() > timestamp) {
				timestamp = socketLastState.getTimestamp().longValue();
			}
			current += socketLastState.getCurrent().doubleValue();
			power += socketLastState.getPower().doubleValue();
		}
		
		return new Hub(deviceId, new Long(timestamp), new Double(current), new Double(power));
	}

}
