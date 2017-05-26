package ch.unige.pinfo2.dom;

public class Light extends Device {
	private Double power;
	private Boolean onOffStatus;
	
	public Light(Long timestamp, Double power) {
		super(timestamp);
		this.power = power;
		if(power != 0) {
			onOffStatus = new Boolean(true);
		} 
		else 
		{
			onOffStatus = new Boolean(false);
		}
	}
	
	public Double getPower() {
		return power;
	}
	
	public Boolean getOnOffStatus() {
		return onOffStatus;
	}
}
