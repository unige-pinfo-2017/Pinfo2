package ch.unige.pinfo2.service;
import javax.transaction.Transactional;
import ch.unige.pinfo2.dom.User;
import ch.unige.pinfo2.service.UserServiceImpl;;

public class UserServiceImplTest{
	@Transactional
	public void test() {
		User user1=new User();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		
		UserServiceImpl databaseUser=new UserServiceImpl();
		databaseUser.addUser(user1);
		databaseUser.getUserByFirstName("Rod");
	}
	
	
}