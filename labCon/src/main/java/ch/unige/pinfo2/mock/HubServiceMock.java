package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.HubService;
import ch.unige.pinfo2.service.SocketService;

@Alternative
public class HubServiceMock implements HubService{
	
	@Inject
	SocketService service;
	
	public Long randomLong(long from, long to){
		Random r = new Random();
		Long number = from+((long)(r.nextDouble()*(to-from)));
		return number;
	}

	@Override
	public List<Hub> getState(Long from, Long to, Integer id) {
		Long timestamp = from;
		Random r = new Random(); 
		Long power;
		Long current;
		Long isOn;
		List<Hub> hubStates = new ArrayList<Hub>();
		Collection<Socket> sockets = new ArrayList<Socket>();
		while (timestamp < to){
			for (int i=0; i<7; i++){
				power = randomLong(0,5);
				current = randomLong(0,10);
				isOn = (long) ((r.nextBoolean())?1:0);
				sockets.add(new Socket(timestamp, current, power, isOn));
			}
			hubStates.add(new Hub(timestamp, sockets));
			sockets.clear();
			timestamp+=20;		
		}
		return hubStates;
	}



}
