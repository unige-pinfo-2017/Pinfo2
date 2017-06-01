package ch.unige.pinfo2.service;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;


import ch.unige.pinfo2.dom.RegularUser;
import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegularUserServiceImplTest{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager em = emf.createEntityManager();
	
	public void addUser(RegularUser user) {
		if (!this.alreadyRegistered(user)) {
			if((user.getFirstName()!=null)&&(user.getLastName()!=null)&&(user.getUsername()!=null)&&(user.getPassword()!=null)){
				user.setToken(createToken());
				user.setRole("regularUser");
				em.getTransaction().begin();
				em.persist(user);
				em.getTransaction().commit();
			}
		}
	}

	public boolean alreadyRegistered(RegularUser user) {

		String sql = "SELECT u FROM RegularUser u WHERE u.username = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", user.getUsername());
		if (query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	public String loginUser(String username, String password) {

		String sql = "SELECT u.token FROM RegularUser u WHERE u.username = :arg1 AND u.password = :arg2";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", username);
		query.setParameter("arg2", password);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (String) query.getSingleResult();
	}

	public RegularUser getUserByToken(String token) {

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

	public String createToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	@Test
	public void dropTable(){
		em.getTransaction().begin();
		em.createQuery("DELETE FROM RegularUser ru").executeUpdate();
		em.getTransaction().commit();
	}
	
	
	
	
	@Test
	public void testAddUserNotAlreadyRegistered() {
		RegularUser user = new RegularUser();
		user.setFirstName("Fred");
		user.setLastName("Martinico");
		user.setUsername("MaxBeni");
		user.setPassword("VincRod");
		addUser(user);
		Assert.assertTrue(alreadyRegistered(user));
	}
	
	@Test 
	public void testAddUserAndLogin(){ 
		RegularUser user=new RegularUser(); 
		user.setFirstName("Rod"); 
		user.setLastName("Bou");
		user.setUsername("RodBou"); 
		user.setPassword("PWDRodBou"); 
		addUser(user);
		Assert.assertNotNull(loginUser(user.getUsername(), user.getPassword()));
	}
	
	 @Test 
	 public void testAddUserAndGetUserByToken(){ 
		 RegularUser user=new RegularUser(); 
		 user.setFirstName("Fred"); 
		 user.setLastName("Sal");
		 user.setUsername("FredSal"); 
		 user.setPassword("PWDFredSal"); 
		 addUser(user);
		 RegularUser user1=new RegularUser();
		 user1.setFirstName("Tho");
		 user1.setLastName("Mar"); 
		 user1.setUsername("ThoMar");
		 user1.setPassword("PWDThoMar"); 
		 addUser(user1); 
		 RegularUser ru=getUserByToken(user.getToken());  
		 Assert.assertEquals(user.getUsername(), ru.getUsername());
		 Assert.assertNotSame(user1.getUsername(), ru.getUsername());
	}
	 
	
	@Test
	public void testAddUserAndGetUserByFN() {
		RegularUser user = new RegularUser();
		user.setFirstName("Fred");
		user.setLastName("MartiNico");
		user.setUsername("MaxBeni");
		user.setPassword("VincRod");
		addUser(user);
		List<RegularUser> lUS = getUserByFirstName("Fred");
		if (!lUS.isEmpty()) {
			Assert.assertEquals(lUS.get(0).getFirstName(), user.getFirstName());
		}
		Assert.assertEquals(1, lUS.size());
	}
	
	@Test
	public void testAddUserAndGetUserByLN() {
		RegularUser user = new RegularUser();
		user.setFirstName("Fred");
		user.setLastName("MartiNico");
		user.setUsername("MaxBeni");
		user.setPassword("VincRod");
		List<RegularUser> lUS = getUserByLastName("MartiNico");
		if (!lUS.isEmpty()) {
			Assert.assertEquals(lUS.get(0).getLastName(), user.getLastName());
		}
		Assert.assertEquals(1, lUS.size());
	}
	
	@Test
	public void testAddUserAndGetUserByUN() {
		RegularUser user = new RegularUser();
		user.setFirstName("Fred");
		user.setLastName("MartiNico");
		user.setUsername("MaxBeni");
		user.setPassword("VincRod");
		RegularUser rUS = getUserByUsername("MaxBeni");
		Assert.assertEquals(rUS.getFirstName(), user.getFirstName());
		Assert.assertEquals(rUS.getLastName(), user.getLastName());
		Assert.assertEquals(rUS.getUsername(), user.getUsername());
	}
	
	@Test 
	public void testAddUserFirstNameNull(){ 
		RegularUser user=new RegularUser(); 
		user.setFirstName(null); 
		user.setLastName("Cab");
		user.setUsername("VinCab2545"); 
		user.setPassword("PWD"); 
		addUser(user);
		Assert.assertEquals(false, alreadyRegistered(user)); 
		}
	
		@Test 
		public void testAddUserLastNameNull(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Tho"); 
			user.setLastName(null);
			user.setUsername("ThoMar2537g4h"); 
			user.setPassword("PWD"); 
			addUser(user);
			Assert.assertEquals(false, alreadyRegistered(user));
		}
			 
		@Test 	
		public void testAddUserUserNameNull(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Nico"); 
			user.setLastName("Pap");
			user.setUsername(null);
			user.setPassword("PWD");
			addUser(user);
			Assert.assertEquals(false, alreadyRegistered(user));
		}

		@Test 
		public void testAddUserPassWordNull(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Fre"); 
			user.setLastName("Sal");
			user.setUsername("FreSal24gh"); 
			user.setPassword(null); 
			addUser(user);
			Assert.assertEquals(false, alreadyRegistered(user)); 
		}
		
		@Test 
		public void testNotAlreadyRegistered(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Tho"); 
			user.setLastName("Mar");
			user.setUsername("ThoMar5454");
			user.setPassword("PWD");
			Assert.assertEquals(false, alreadyRegistered(user)); 
			 
		}
		
		@Test 
		public void testLoginWrongUsernameGoodPassword(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Fre"); 
			user.setLastName("Sal");
			user.setUsername("FreSal");
			user.setPassword("PWD");
			addUser(user);
			Assert.assertNull(loginUser("FreSal2", "PWD")); 
		}
			  
		@Test 
		public void testLoginWrongUsernameWrongPassword(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Fre");
			user.setLastName("Sal");
			user.setUsername("FreSal"); 
			user.setPassword("PWD"); 
			addUser(user);
			Assert.assertNull(loginUser("FreSal2", "PWD2")); 
		}
			  
		@Test 
		public void testLoginGoodUsernameWrongPassword(){ 
			RegularUser user=new RegularUser(); 
			user.setFirstName("Fre"); 
			user.setLastName("Sal");
			user.setUsername("FreSal"); 
			user.setPassword("PWD"); 
			addUser(user);
			Assert.assertNull(loginUser("FreSal", "PWD2"));
		}

		
		@Test 
		public void testTokenNullGetUserByToken(){
			Assert.assertNull(getUserByToken(null));
		}
			 
		@Test 
		public void testEmptyListGetUserByLastName(){
			Assert.assertTrue(getUserByLastName(null).isEmpty()); 
		} 
			  
		@Test 
		public void testEmptyListGetUserByFirstName(){
			Assert.assertTrue(getUserByFirstName(null).isEmpty());
		}
			 
		@Test 
		public void testNullUsernameGetUserByUserName(){
			 Assert.assertNull(getUserByUsername(null));
		}
	
}



