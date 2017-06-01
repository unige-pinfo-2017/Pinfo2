package ch.unige.pinfo2.dom;

import javax.persistence.Entity;

/**
 * Models a Light
 */
@Entity
public class Light extends Device {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3317577738048150589L;

	/** The power of the Light at the timestamp 'timestamp' */
	private Double power;

	/** The status of the Light at the timestamp 'timestamp' */
	private Boolean onOffStatus;

	public Light() {
	}

	/**
	 * 
	 * @param deviceId
	 *            the id of the light.
	 * @param timestamp
	 *            the timestamp of the light.
	 * @param power
	 *            the power of the light.
	 */
	public Light(String deviceId, Long timestamp, Double power) {
		super(deviceId, DeviceType.LIGHT, timestamp);
		this.power = power;
		if (power != 0) {
			onOffStatus = new Boolean(true);
		} else {
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
