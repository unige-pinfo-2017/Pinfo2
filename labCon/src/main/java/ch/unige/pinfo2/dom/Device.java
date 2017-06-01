package ch.unige.pinfo2.dom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Models a device of the labCon application.
 * 
 */
@Entity
@Table(name = "Device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741428568934591396L;

	/** The id. Unique per device */
	@Id
	@Column(name = "id")
	private String id;

	/** The workstation assigned to the device */
	@Column(name = "workstation")
	private String workstation;

	/** The type of the device. Example: Light */
	@Column(name = "type")
	private DeviceType type;

	/** The timestamp of the device. */
	private Long timestamp;

	public Device() {
	}

	/**
	 * 
	 * @param id
	 *            The device id.
	 * @param type
	 *            The device type.
	 * @param birthDate
	 *            The device timestamp.
	 */
	public Device(String id, DeviceType type, Long timestamp) {
		this.id = id;
		this.type = type;
		this.timestamp = timestamp;
		this.workstation = null;
	}

	public DeviceType getType() {
		return type;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getWorkstation() {
		return workstation;
	}

	public String getId() {
		return id;
	}
}
