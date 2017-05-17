package ch.unige.pinfo2.dom;

public class Socket extends Device {
	private double current;
	private double power;
	private boolean isOn;

	public Socket(Long timestamp, double current, double power, double isOnDouble) {
		super(timestamp);
		this.current = current;
		this.power = power;
		if (isOnDouble == 0) {
			this.isOn = false;
		} else {
			this.isOn = true;
		}
	}

}
