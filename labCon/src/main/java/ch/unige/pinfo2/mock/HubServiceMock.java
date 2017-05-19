package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.HubService;

@Alternative
public class HubServiceMock implements HubService{
	
	
	public Double randomDouble(long from, long to){
		Random r = new Random();
		Double number = from+((double)(r.nextDouble()*(to-from)));
		return number;
	}

	@Override
	public List<Hub> getState(String id, Long from, Long to) {
		Long timestamp = from;
		Random r = new Random(); 
		Double power;
		Double current;
		Long isOn;
		List<Hub> hubStates = new ArrayList<Hub>();
		while (timestamp < to){
			List<Socket> sockets = new ArrayList<Socket>();
			for (int i=0; i<6; i++){
				power = randomDouble(0,5);
				current = randomDouble(0,10);
				isOn = (long) ((r.nextBoolean())?1:0);
				sockets.add(new Socket(timestamp, current, power, isOn));
			}
			hubStates.add(new Hub(sockets));
			timestamp+=20;		
		}
		return hubStates;
	}



}
