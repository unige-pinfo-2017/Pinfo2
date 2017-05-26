package ch.unige.pinfo2.service;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Test;

import ch.unige.pinfo2.dom.RegularUser;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class RegularUserServiceImplTest{
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager em = emf.createEntityManager();
	
	@Transactional
	public void addUser(RegularUser user) {
		if (!this.alreadyRegistered(user)) {
			if((user.getFirstName()!=null)&&(user.getLastName()!=null)&&(user.getUserName()!=null)&&(user.getPassword()!=null)){
				user.setToken(createToken());
				em.getTransaction().begin();
				em.persist(user);
				em.getTransaction().commit();
			}
			else{
				System.out.println("Some values are null");
			}
		}
	}

	public boolean alreadyRegistered(RegularUser user) {
		String sql = "SELECT u FROM RegularUser u WHERE u.username = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", user.getUserName());
		if (query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Integer loginUser(String username, String password) {
		String sql = "SELECT u.token FROM RegularUser u WHERE u.username = :arg1 AND u.password = :arg2";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", username);
		query.setParameter("arg2", password);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (Integer) query.getSingleResult();
	}


	public RegularUser getUserByToken(Integer token) {
		String sql = "SELECT u FROM RegularUser u WHERE u.token = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", token);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (RegularUser) query.getResultList().get(0);
	}
	
	public List<RegularUser> getUserByLastName(String lastName) {
		String sql = "SELECT u FROM RegularUser u WHERE u.lastName = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", lastName);
		return query.getResultList();
	}
	
	public List<RegularUser> getUserByFirstName(String firstName) {
		String sql = "SELECT u FROM RegularUser u WHERE u.firstName = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", firstName);
		return query.getResultList();
	}
	
	public RegularUser getUserByUsername(String username) {

		String sql = "SELECT u FROM RegularUser u WHERE u.username = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", username);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (RegularUser) query.getResultList().get(0);
	}

	public Integer createToken() {
		Random r = new Random();
		Integer token;
		while (true) {
			token = r.nextInt();
			if (this.getUserByToken(token) == null) {
				return token;
			}
		}
	}
	
	
	@Test
	public void testAddUserAndGetUserByFN() {
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		RegularUser user1=new RegularUser();
		user1.setFirstName("Rod");
		user1.setLastName("Bou1");
		user1.setUserName("RodBou2");
		user1.setPassword("PWD");
		addUser(user);
		addUser(user1);	
		List<RegularUser> lUS=getUserByFirstName("Rod");
		if(!lUS.isEmpty()){
			Assert.assertEquals(lUS.get(0).getFirstName(), user.getFirstName());
			//Assert.assertEquals(lUS.get(0).getLastName(), user.getLastName());
			Assert.assertEquals(lUS.get(1).getFirstName(), user1.getFirstName());
			//Assert.assertEquals(lUS.get(1).getLastName(), user1.getLastName());
		}
		Assert.assertEquals(2, lUS.size());
	}
	
	@Test
	public void testAddUserAndGetUserByLN() {
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		addUser(user);
		RegularUser user1=new RegularUser();
		user1.setFirstName("Rod");
		user1.setLastName("Bou1");
		user1.setUserName("RodBou2");
		user1.setPassword("PWD");
		addUser(user1);	
		List<RegularUser> lUS=getUserByLastName("Rod");
		if(!lUS.isEmpty()){
			Assert.assertEquals(lUS.get(0).getFirstName(), user.getFirstName());
			Assert.assertEquals(lUS.get(0).getLastName(), user.getLastName());
			Assert.assertEquals(lUS.get(1).getFirstName(), user1.getFirstName());
			Assert.assertEquals(lUS.get(1).getLastName(), user1.getLastName());
		}
	}
	
	@Test
	public void testAddUserAndGetUserByUN() {
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		addUser(user);
		RegularUser user1=new RegularUser();
		user1.setFirstName("Rod");
		user1.setLastName("Bou1");
		user1.setUserName("RodBou2");
		user1.setPassword("PWD");
		addUser(user1);	
		RegularUser ru=getUserByUsername("RodBou");
		Assert.assertEquals(ru.getFirstName(), user.getFirstName());
		Assert.assertEquals(ru.getLastName(), user.getLastName());
		RegularUser ru1=getUserByUsername("RodBou2");
		Assert.assertEquals(ru1.getFirstName(), user1.getFirstName());
		Assert.assertEquals(ru1.getLastName(), user1.getLastName());
	}
	
	@Test
	public void testAddUserAndAlreadyRegistered() {
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		RegularUser user1=new RegularUser();
		user1.setFirstName("Rod");
		user1.setLastName("Bou1");
		user1.setUserName("RodBou2");
		user1.setPassword("PWD");
		addUser(user1);	
		Assert.assertEquals(false, alreadyRegistered(user));
		Assert.assertEquals(true, alreadyRegistered(user1));
	}
	
	@Test
	public void testAddUserAndLogin(){
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertNotNull(loginUser(user.getUserName(), user.getPassword()));
	}
	
	@Test
	public void testAddUserAndGetUserByToken(){
		RegularUser user=new RegularUser();
		user.setFirstName("Rod");
		user.setLastName("Bou");
		user.setUserName("RodBou");
		user.setPassword("PWD");
		addUser(user);
		RegularUser user1=new RegularUser();
		user1.setFirstName("Rod");
		user1.setLastName("Bou1");
		user1.setUserName("RodBou2");
		user1.setPassword("PWD");
		addUser(user1);	
		RegularUser ru=getUserByToken(user.getToken());
		
		if(ru!=null){
			Assert.assertEquals(user.getUserName(), ru.getUserName());
		}
	}
	
	
	
	@Test
	public void addUserFirstNameNull(){
		RegularUser user=new RegularUser();
		user.setFirstName(null);
		user.setLastName("Cab");
		user.setUserName("VinCab");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertEquals(false, alreadyRegistered(user));
	}
	
	@Test
	public void addUserLastNameNull(){
		RegularUser user=new RegularUser();
		user.setFirstName("Tho");
		user.setLastName(null);
		user.setUserName("ThoMar");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertEquals(false, alreadyRegistered(user));
	}
	
	@Test
	public void addUserUserNameNull(){
		RegularUser user=new RegularUser();
		user.setFirstName("Nico");
		user.setLastName("Pap");
		user.setUserName(null);
		user.setPassword("PWD");
		addUser(user);
		Assert.assertEquals(false, alreadyRegistered(user));
	}
	
	
	@Test
	public void addUserPassWordNull(){
		RegularUser user=new RegularUser();
		user.setFirstName("Fre");
		user.setLastName("Sal");
		user.setUserName("FreSal");
		user.setPassword(null);
		addUser(user);
		Assert.assertEquals(false, alreadyRegistered(user));
	}
	
	@Test
	public void notAlreadyRegistered(){
		RegularUser user=new RegularUser();
		user.setFirstName("Tho");
		user.setLastName("Mar");
		user.setUserName("ThoMar");
		user.setPassword("PWD");
		Assert.assertEquals(false, alreadyRegistered(user));
	}
	
	@Test
	public void loginWrongUsernameGoodPassword(){
		RegularUser user=new RegularUser();
		user.setFirstName("Fre");
		user.setLastName("Sal");
		user.setUserName("FreSal");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertNull(loginUser("FreSal2", "PWD"));
	}
	
	@Test
	public void loginWrongUsernameWrongPassword(){
		RegularUser user=new RegularUser();
		user.setFirstName("Fre");
		user.setLastName("Sal");
		user.setUserName("FreSal");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertNull(loginUser("FreSal2", "PWD2"));
	}
	
	@Test
	public void loginGoodUsernameWrongPassword(){
		RegularUser user=new RegularUser();
		user.setFirstName("Fre");
		user.setLastName("Sal");
		user.setUserName("FreSal");
		user.setPassword("PWD");
		addUser(user);
		Assert.assertNull(loginUser("FreSal", "PWD2"));
	}
	
	@Test
	public void tokenNullGetUserByToken(){
		Assert.assertNull(getUserByToken(null));
	}
	
	@Test
	public void emptyListGetUserByLastName(){
		Assert.assertTrue(getUserByLastName(null).isEmpty());
	}
	
	@Test
	public void emptyListGetUserByFirstName(){
		Assert.assertTrue(getUserByFirstName(null).isEmpty());
	}
	
	@Test
	public void nullUsernameGetUserByUserName(){
		Assert.assertNull(getUserByUsername(null));
	}
	
}