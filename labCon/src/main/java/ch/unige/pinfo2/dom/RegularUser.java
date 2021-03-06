package ch.unige.pinfo2.dom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models a regular user of the labCon application.
 * 
 */
@Entity
@Table(name = "RegularUser")
public class RegularUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4561966035983412307L;

	/** The username. Unique per user */
	@Id
	@Column(name = "username")
	private String username;

	/** The first name */
	@Column(name = "firstName")
	private String firstName;

	/** The last name */
	@Column(name = "lastName")
	private String lastName;

	/** The password */
	@Column(name = "password")
	private String password;

	/** The token. It's like a cookie. */
	@Column(name = "token")
	private String token;

	/** The role (RegularUser/admin) */
	@Column(name = "role")
	private String role;

	/** The status: true for connected, false for disconnected. */
	@Column(name = "status")
	private boolean status;

	public RegularUser() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
