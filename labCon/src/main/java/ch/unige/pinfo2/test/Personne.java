package ch.unige.pinfo2.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Models a student.
 * 
 * @author hostettler modifié par Vincent
 */
// Nom dans la DB
@Entity(name = "PERSONNE")
public class Personne implements Serializable {

	/** The serial-id. */
	private static final long serialVersionUID = -6146935825517747043L;

	/** The unique id in a technical sense. */
	// Clé primaire
	@Id
	private int id;

	/** The student last name. */
	@Column(name = "LAST_NAME")
	private String lastName;

	/** The student first name. */
	@Column(name = "FIRST_NAME")
	private String firstName;

	public Personne() {
	}

	public Personne(final String lastName, final String firstName, final int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(final String pLastName) {
		this.lastName = pLastName;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(final String pFirstName) {
		this.firstName = pFirstName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
