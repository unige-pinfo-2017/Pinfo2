package ch.unige.pinfo2.dom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Device")
public abstract class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741428568934591396L;

	@Id
	@Column(name = "id")
	private String id;
	
	private DeviceType type;
	
	private Long timestamp;
	
	public Device() {}
	
	public Device(String id, DeviceType type, Long timestamp) {
		this.id = id;
		this.type = type;
		//TODO initialize workstation
		this.timestamp = timestamp;
	}
	
	public DeviceType getType() {
		return type;
	}

	public Long getTimestamp() {
		return timestamp;
	}
}
