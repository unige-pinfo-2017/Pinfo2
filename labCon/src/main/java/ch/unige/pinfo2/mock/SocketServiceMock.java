package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.SocketService;

@Alternative
public class SocketServiceMock implements SocketService{
	
	public Double randomDouble(long from, long to){
		Random r = new Random();
		Double number = from+((double)(r.nextDouble()*(to-from)));
		return number;
	}

	@Override
	public List<Socket> getState(String deviceId, Long from, Long to) {
		Long timestamp = from;
		Random r = new Random(); 
		Double power;
		Double current;
		Long isOn;
		List<Socket> socketStates = new ArrayList<Socket>();
	
		while (timestamp < to){
			power = randomDouble(0,5);
			current = randomDouble(0,10);
			isOn = (long) ((r.nextBoolean())?1:0);
			socketStates.add(new Socket(timestamp, current, power, isOn ));
			timestamp+=20;		
		}
		return socketStates;
	}

	

}
