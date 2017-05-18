package ch.unige.pinfo2.dom;

public class Light extends Device {
	private Double power;
	private Boolean isOn;
	
	public Light(Long timestamp, Double power) {
		super(timestamp);
		this.power = power;
		if(power != 0) {
			isOn = new Boolean(true);
		} 
		else 
		{
			isOn = new Boolean(false);
		}
	}
}
