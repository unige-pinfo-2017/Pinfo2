package ch.unige.pinfo2.service;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import ch.unige.pinfo2.dom.RegularUser;

/**
 * Defines the contract for the admin's service.
 */
@Local
public interface AdminService {

	/**
	 * add a User to the DB
	 * 
	 * @param user
	 *            to add
	 *
	 */
	boolean addUser(@NotNull RegularUser user);

	/**
	 * return all the user in the DB (even the admin)
	 */
	List<RegularUser> getAllUsers();

}
