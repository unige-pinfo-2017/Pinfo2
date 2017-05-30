package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.SocketService;

@Alternative
public class SocketServiceMock implements SocketService {

	public Double randomDouble(long from, long to) {
		Random r = new Random();
		Double number = from + ((double) (r.nextDouble() * (to - from)));
		return number;
	}

	@Override
	public List<Socket> getState(String deviceId, Long from, Long to) {
		if ((from == null) && (to == null)) {
			from = 2000L;
			to = 2020L;
		} else if ((from != null) && (to == null)) {
			to = from + 20L;
		} else if ((from == null) && (to != null)) {
			from = to - 20L;
		}
		Long timestamp = from;
		Random r = new Random();
		Double power;
		Double current;
		List<Socket> socketStates = new ArrayList<Socket>();

		while (timestamp < to) {
			power = randomDouble(0, 5);
			current = randomDouble(0, 10);
			socketStates.add(new Socket(deviceId, timestamp, current, power));
			timestamp += 600000;
		}
		return socketStates;
	}

	@Override
	public Boolean setOnOrOff(String deviceId, String onOrOff) {
		return true;
	}

}
