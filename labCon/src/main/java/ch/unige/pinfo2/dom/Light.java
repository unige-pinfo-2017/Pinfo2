package ch.unige.pinfo2.dom;

public class Light extends Device {
	private double power;
	private boolean isOn;
	
	public Light(Long mesureTime, double power) {
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
