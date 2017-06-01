package ch.unige.pinfo2.dom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
//@MappedSuperclass
@Table(name = "Device")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public abstract class Device implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6741428568934591396L;

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "workstation")
	private String workstation;
	
	@Column(name = "type")
	private DeviceType type;
	
	private Long timestamp;
	
	public Device() {}
	
	public Device(String id, DeviceType type, Long timestamp) {
		this.id = id;
		this.type = type;
		this.timestamp = timestamp;
		this.workstation=null;
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
	
	public String getId(){
		return id;
	}
}
