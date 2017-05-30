package ch.unige.pinfo2.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.HubService;

@Alternative
public class HubServiceImplMock implements HubService{
	
	
	public Double randomDouble(long from, long to){
		Random r = new Random();
		Double number = from+((double)(r.nextDouble()*(to-from)));
		return number;
	}

	@Override
	public List<Hub> getState(String id, Long from, Long to){
		if((from==null)&&(to==null)){
			from=2000L;
			to=2020L;
		}
		else if((from!=null)&&(to==null)){
			to=from+20L;
		}
		else if((from==null)&&(to!=null)){
			from=to-20L;
		}
		Long timestamp = from;
		Random r = new Random(); 
		Double power;
		Double current;
		List<Hub> hubStates = new ArrayList<Hub>();
		while (timestamp < to){
			List<Socket> sockets = new ArrayList<Socket>();
			for (int i=0; i<6; i++){
				power = randomDouble(0,5);
				current = randomDouble(0,10);
				sockets.add(new Socket(id, timestamp, current, power));
			}
			hubStates.add(new Hub(sockets));
			timestamp+=20;		
		}
		return hubStates;
	}

	@Override
	public Hub getLastState(String id) {
		Random r = new Random();
		List<Socket> sockets = new ArrayList<Socket>();
		Double power;
		Double current;
		Long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
		
		for (int i=0; i<6; i++){
			power = randomDouble(0,5);
			current = randomDouble(0,10);
			sockets.add(new Socket(id, timestamp, current, power));
		}
		
		return (new Hub(sockets));
	}



}
