package ch.unige.pinfo2.dom;

public class Light extends Device {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3317577738048150589L;
	
	private Double power;
	private Boolean onOffStatus;
	
	public Light(String deviceId, Long timestamp, Double power) {
		super(deviceId,DeviceType.LIGHT,timestamp);
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
