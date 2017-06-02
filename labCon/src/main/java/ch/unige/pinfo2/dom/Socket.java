package ch.unige.pinfo2.dom;

import javax.persistence.Entity;

/**
 * Models a Socket
 */
@Entity
public class Socket extends Device {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4077936754856570858L;

	/** The current of the socket */
	private Double currentSocket;

	/** The power of the socket */
	private Double power;

	/** The status of the socket */
	private Boolean isOn;

	/**
	 * 
	 * @param deviceId
	 *            the id of the socket.
	 * @param timestamp
	 *            the timestamp of the socket.
	 * @param current
	 *            the current of the socket.
	 * @param power
	 *            the power of the socket.
	 */
	public Socket(String deviceId, Long timestamp, Double current, Double power) {
		super(deviceId, DeviceType.SOCKET, timestamp);
		this.currentSocket = current;
		this.power = power;
		if (power.doubleValue() == 0) {
			this.isOn = false;
		} else {
			this.isOn = true;
		}
	}

	public Socket() {
	}

	public Double getCurrent() {
		return currentSocket;
	}

	public void setCurrent(Double current) {
		this.currentSocket = current;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Boolean getIsOn() {
		return isOn;
	}

	public void setIsOn(Boolean isOn) {
		this.isOn = isOn;
	}

}
