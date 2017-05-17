package ch.unige.pinfo2.dom;

import java.util.Date;

public abstract class Device {
	private Long timestamp;
	
	public Device(Long timestamp) {
		this.timestamp = timestamp;
	}
}
