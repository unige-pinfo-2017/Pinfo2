package ch.unige.pinfo2.dom;

public class Hub extends Device {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3939494759384288631L;

	private Double current;
	
	private Double power;
	
	private Boolean onOffStatus;
	
	public Hub(String deviceId, Long timestamp, Double current, Double power) {
		super(deviceId, DeviceType.HUB, timestamp);
		this.current = current;
		this.power = power;
		if(power.doubleValue() != 0) {
			onOffStatus = new Boolean(true);
		} else {
			onOffStatus = new Boolean(false);
		}
	}
	
	public Double getCurrent() {
		return current;
	}
	
	public Double getPower() {
		return power;
	}
	
	public Boolean getOnOffStatus() {
		return onOffStatus;
	}

}
