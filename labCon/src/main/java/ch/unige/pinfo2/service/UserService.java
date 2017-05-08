package ch.unige.pinfo2.service;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import ch.unige.pinfo2.dom.User;

@Local
public interface UserService {
	
	/**
	 * add a User to the DB
	 * 
	 * @param user to add
	 *
	 */
	void addUser(@NotNull User user);
	
	/**
	 * check if a User is already registered
	 * 
	 * @param user to verify 
	 *
	 */
	boolean alreadyRegistered(@NotNull User user);
	
	/**
	 * check the login is correct and give the user's token
	 * 
	 * @param username
	 * @param password
	 * 
	 * @return the user's token
	 */
	long loginUser(String username, String password);
	
	/**
	 * find a User by the id
	 * 
	 * @param id of the user
	 *
	 */
	User getUserById(long id);
	
	/**
	 * find a User by the token
	 * 
	 * @param token of the user
	 *
	 *@return the user corresponding to the token
	 */
	User getUserByToken(long token);
	
	/**
	 * find a User by the lastName
	 * 
	 * @param last name of the user
	 *
	 *@return the user corresponding to the last name
	 */
	List<User> getUserByLastName(String lastName);
	
	/**
	 * find a User by the firstName
	 * 
	 * @param first name of the user
	 *
	 *@return the user corresponding to the first name
	 */
	List<User> getUserByFiratName(String firstName);
	
	
	

}
