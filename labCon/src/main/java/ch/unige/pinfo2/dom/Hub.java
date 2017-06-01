package ch.unige.pinfo2.dom;

import javax.persistence.Entity;

/**
 * Models a Hub
 */
@Entity
public class Hub extends Device {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3939494759384288631L;

	/** The current of the Hub at the timestamp 'timestamp' */
	private Double hubCurrent;

	/** The power of the Hub at the timestamp 'timestamp' */
	private Double power;

	/** The status of the Hub at the timestamp 'timestamp' */
	private Boolean onOffStatus;

	public Hub() {
	}

	/**
	 * 
	 * @param deviceId
	 *            the id of the hub
	 * @param timestamp
	 *            the timestamp of the hub
	 * @param hubCurrent
	 *            the current of the hub
	 * @param power
	 *            the power of the hub
	 */
	public Hub(String deviceId, Long timestamp, Double hubCurrent, Double power) {
		super(deviceId, DeviceType.HUB, timestamp);
		this.hubCurrent = hubCurrent;
		this.power = power;
		if (power.doubleValue() != 0) {
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
