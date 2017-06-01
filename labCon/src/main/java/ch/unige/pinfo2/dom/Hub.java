package ch.unige.pinfo2.dom;

import javax.persistence.Entity;

@Entity
public class Hub extends Device {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3939494759384288631L;

	private Double hubCurrent;
	
	private Double power;
	
	private Boolean onOffStatus;
	
	public Hub() {}
	
	public Hub(String deviceId, Long timestamp, Double hubCurrent, Double power) {
		super(deviceId, DeviceType.HUB, timestamp);
		this.hubCurrent = hubCurrent;
		this.power = power;
		if(power.doubleValue() != 0) {
			onOffStatus = new Boolean(true);
		} else {
			onOffStatus = new Boolean(false);
		}
	}
	
	public Double getHubCurrent() {
		return hubCurrent;
	}
	
	public Double getPower() {
		return power;
	}
	
	public Boolean getOnOffStatus() {
		return onOffStatus;
	}

}
