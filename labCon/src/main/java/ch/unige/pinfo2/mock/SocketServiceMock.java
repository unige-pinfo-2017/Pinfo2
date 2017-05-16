package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.service.SocketService;

@Alternative
public class SocketServiceMock implements SocketService{
	
	private final long minId = 1;
	private final long maxId = 1000;
	
	public Long randomLong(long from, long to){
		Random r = new Random();
		Long number = from+((long)(r.nextDouble()*(to-from)));
		return number;
	}

	@Override
	public Long getSocketIdByToken(Long token) {		
		return this.randomLong(this.minId, this.maxId);
	}

	@Override
	public List<Long> getStatePowerSensor(String from, String to, String deviceId) {
		Long start = Long.valueOf(from);
		Long stop = Long.valueOf(to);
		List<Long> list = new ArrayList<Long>();
		while (start<stop){
			list.add(this.randomLong(minId, 5));
			start+=20;
		}
		return list;
	}

	@Override
	public List<Integer> getStatus(String from, String to, String deviceId) {
		Long start = Long.valueOf(from);
		Long stop = Long.valueOf(to);
		List<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		while (start<stop){
			list.add(r.nextInt(2));
			start+=20;
		}
		return list;
	}

	@Override
	public List<Long> getStateCurrentSensor(String from, String to, String deviceId) {
		Long start = Long.valueOf(from);
		Long stop = Long.valueOf(to);
		List<Long> list = new ArrayList<Long>();
		while (start<stop){
			list.add(this.randomLong(minId, 5));
			start+=20;
		}
		return list;
	}

	@Override
	public boolean setState(String state, String deviceId) {
		return true;
	}

}
