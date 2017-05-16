package ch.unige.pinfo2.dom;

import java.util.Date;

public abstract class Device {
	private Date mesureTime;
	
	public Device(Date mesureTime) {
		this.mesureTime = mesureTime;
	}
}
