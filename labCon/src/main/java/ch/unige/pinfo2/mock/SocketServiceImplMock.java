package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.service.SocketService;

@Alternative
public class SocketServiceImplMock implements SocketService {

	@Override
	public Socket getLastState(String deviceId) {
		Random valueGenerator = new Random();
		return new Socket(deviceId, new Long(Math.abs(valueGenerator.nextLong())),
				new Double(valueGenerator.nextDouble() * 100), new Double(valueGenerator.nextDouble() * 100));
	}

	@Override
	public List<Socket> getStates(String deviceId, Long from, Long to) {
		Random valueGenerator = new Random();

		List<Socket> sockets = new ArrayList<Socket>();

		Long timestamp = from + Math.abs((int) (valueGenerator.nextDouble() * 600000));

		while (timestamp.longValue() <= to.longValue()) {
			sockets.add(new Socket(deviceId, timestamp, new Double(valueGenerator.nextDouble() * 100),
					new Double(valueGenerator.nextDouble() * 100)));
			timestamp += Math.abs((int) (valueGenerator.nextDouble() * 600000));
		}

		return sockets;
	}

	@Override
	public Boolean setOnOrOff(String deviceId, String onOrOff) {
		return true;
	}

}
