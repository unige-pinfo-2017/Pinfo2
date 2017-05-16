package ch.unige.pinfo2.dom;

import java.util.Date;

public class Light extends Device {
	private double power;
	private boolean isOn;
	
	public Light(Date mesureTime, double power) {
		super(mesureTime);
		this.power = power;
		if(power != 0) {
			isOn = true;
		} 
		else 
		{
			isOn = false;
		}
	}
}
