package ch.unige.pinfo2.dom;

import java.util.List;

public class Hub {
	
	public Hub(List<Socket> sockets) {
		this.sockets = sockets;
	}

	List<Socket> sockets;

	public List<Socket> getSockets() {
		return sockets;
	}
	

}
