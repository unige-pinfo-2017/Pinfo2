package ch.unige.pinfo2.service;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import ch.unige.pinfo2.dom.RegularUser;

@Local
public interface RegularUserService {
	
	/**
	 * add a User to the DB
	 * 
	 * @param user to add
	 *
	 */
	void addUser(@NotNull RegularUser user);
	
	/**
	 * check if a User is already registered
	 * 
	 * @param user to verify 
	 *
	 */
	boolean alreadyRegistered(@NotNull RegularUser user);
	
	/**
	 * check the login is correct and give the user's token
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return the user's token
	 */
	Integer loginUser(String username, String password);
	
	/**
	 * find a User by the token
	 * 
	 * @param token of the user
	 *
	 *@return the user corresponding to the token
	 */
	RegularUser getUserByToken(Integer token);
	
	/**
	 * find a User by the lastName
	 * 
	 * @param last name of the user
	 *
	 *@return the user corresponding to the last name
	 */
	List<RegularUser> getUserByLastName(String lastName);
	
	/**
	 * find a User by the firstName
	 * 
	 * @param first name of the user
	 *
	 *@return the user corresponding to the first name
	 */
	List<RegularUser> getUserByFirstName(String firstName);
	
	/**
	 * find a User by the firstName
	 * 
	 * @param first name of the user
	 *
	 *@return the user corresponding to the first name
	 */
	RegularUser getUserByUsername(String username);
	
	/**
	 * generate a unique Token

	 *@return the token
	 */
	Integer createToken();
	
	
	
	
	

}
