package test;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Models a student.
 * 
 * @author hostettler
 * modifié par Vincent
 */
@Entity(name = "PERSON")
//@NamedQuery(name = "findAllStudentsByFirstName", query = "SELECT s FROM Student s WHERE s.lastName = :lastname")
//@Table(name = "PERSON")
public class Personne implements Serializable {

	/** The serial-id. */
	private static final long serialVersionUID = -6146935825517747043L;
	
	/** The unique id in a technical sense. */
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ID")
	private int id;

	/** The student last name. */
	//@NotNull
	//@Size(min = 2, max = 35)
	@Column(name = "LAST_NAME")
	private String lastName;

	/** The student first name. */
	//@NotNull
	//@Size(min = 2, max = 35)
	@Column(name = "FIRST_NAME")
	private String firstName;


	public Personne() {
	}

	/**
	 * 
	 * @param lastName
	 *            The student last name.
	 * @param firstName
	 *            The student first name.
	 */
	public Personne(final String lastName, final String firstName, final int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}


	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public final void setLastName(final String pLastName) {
		this.lastName = pLastName;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public final void setFirstName(final String pFirstName) {
		this.firstName = pFirstName;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the studentId
	 */
	public int getId() {
		return id;
	}


}
