package ch.unige.pinfo2.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import ch.unige.pinfo2.dom.RegularUser;

public interface AdminService {
	
	/**
	 * add a User to the DB
	 * 
	 * @param user to add
	 *
	 */
	boolean addUser(@NotNull RegularUser user);
	
	/**
	 * return all the user in the DB
	 */
	List<RegularUser> getAllUsers();

}
