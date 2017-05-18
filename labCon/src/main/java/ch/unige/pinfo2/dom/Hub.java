package ch.unige.pinfo2.dom;

import java.util.Collection;

public class Hub extends Device {

	public Hub(Long timestamp, Collection<Socket> sockets) {
		super(timestamp);
		this.sockets = sockets;
	}

	private Collection<Socket> sockets;
}
