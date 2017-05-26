package ch.unige.pinfo2.dom;

public class Socket extends Device {
	private Double current;
	private Double power;
	private Boolean isOn;

	public Socket(Long timestamp, Double current, Double power, Long isOn2) {
		super(timestamp);
		this.current = current;
		this.power = power;
		if (isOn2 == 0) {
			this.isOn = false;
		} else {
			this.isOn = true;
		}
	}

	public Double getCurrent() {
		return current;
	}

	public void setCurrent(Double current) {
		this.current = current;
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
