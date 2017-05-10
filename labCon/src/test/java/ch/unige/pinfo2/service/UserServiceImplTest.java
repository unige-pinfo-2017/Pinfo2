package ch.unige.pinfo2.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import org.junit.Test;

import ch.unige.pinfo2.dom.RegularUser;
import junit.framework.Assert;;

public class UserServiceImplTest{
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAddUser() {
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod1");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		String sql="SELECT ru FROM RegularUser ru";
		Query query=em.createQuery(sql);
		List<RegularUser> listUsers=query.getResultList();
		
		int i=0;
		int userNotInDB=1;
		while (i<listUsers.size()){
			if((listUsers.get(i).getUserName()==user1.getUserName())||(listUsers.get(i).getId()==user1.getId())){
				userNotInDB=0;
				//System.out.println("User In DB");
				break;
			}
			i++;
		}
		if(userNotInDB==1){
			em.getTransaction().begin();
			em.persist(user1);
			em.getTransaction().commit();
		}

		Query query2=em.createQuery("SELECT ru FROM RegularUser ru");
		List<RegularUser> users=query2.getResultList();
		/*for (int j=0;j<users.size();j++){
			System.out.println(users.get(j).getFirstName());
		}*/
		Assert.assertEquals("Rod1", users.get(0).getFirstName());
		em.close();
		emf.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAlreadyRegistered(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		String sql="SELECT u FROM RegularUser u WHERE u.id = :arg1 AND u.firstName = :arg2 AND u.lastName = :arg3 AND u.userName = :arg4";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", user1.getId());
		query.setParameter("arg2", user1.getFirstName());
		query.setParameter("arg3", user1.getLastName());
		query.setParameter("arg4", user1.getUserName());
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i).getFirstName());
		}*/
		boolean value;
		if(query.getResultList().isEmpty()){
			value=false;
		}
		else{		
			value= true;	
		}
		Assert.assertEquals(value, true);
		em.close();
		emf.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testLoginUser(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		String sql="SELECT u.token FROM RegularUser u WHERE u.userName = :arg1 AND u.password = :arg2";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", user1.getUserName());
		query.setParameter("arg2", user1.getPassword());
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i));
		}*/
		Assert.assertEquals(users.get(0), user1.getToken());
		em.close();
		emf.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserById(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		RegularUser user2=new RegularUser();
		user2.setId((long) 2);
		user2.setFirstName("Vin");
		user2.setLastName("Cab");
		user2.setUserName("VinCab");
		user2.setPassword("PWD2");
		user2.setToken((long) 2);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		em.getTransaction().begin();	
		em.persist(user2);
		em.getTransaction().commit();
		String sql="SELECT u FROM RegularUser u WHERE u.id = :arg1";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", (long) 2);
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i).getFirstName());
		}*/
		Assert.assertEquals(users.get(0).getFirstName(), user2.getFirstName());
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserByToken(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		RegularUser user2=new RegularUser();
		user2.setId((long) 2);
		user2.setFirstName("Vin");
		user2.setLastName("Cab");
		user2.setUserName("VinCab");
		user2.setPassword("PWD2");
		user2.setToken((long) 2);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		em.getTransaction().begin();	
		em.persist(user2);
		em.getTransaction().commit();
		String sql="SELECT u FROM RegularUser u WHERE u.token = :arg1";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", (long) 2);
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i).getFirstName());
		}*/
		Assert.assertEquals(users.get(0).getFirstName(), user2.getFirstName());
		em.close();
		emf.close();
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserByLastName(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		RegularUser user2=new RegularUser();
		user2.setId((long) 2);
		user2.setFirstName("Rod2");
		user2.setLastName("Bou");
		user2.setUserName("RodBou2");
		user2.setPassword("PWD2");
		user2.setToken((long) 2);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		em.getTransaction().begin();	
		em.persist(user2);
		em.getTransaction().commit();
		String sql="SELECT u FROM RegularUser u WHERE u.lastName = :arg1";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", "Bou");
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i).getFirstName());
		}*/
		Assert.assertEquals(users.size(), 2);
		em.close();
		emf.close();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserByFirstName(){
		RegularUser user1=new RegularUser();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		user1.setToken((long) 1);
		RegularUser user2=new RegularUser();
		user2.setId((long) 2);
		user2.setFirstName("Rod");
		user2.setLastName("Bou2");
		user2.setUserName("RodBou2");
		user2.setPassword("PWD2");
		user2.setToken((long) 2);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		em.getTransaction().begin();	
		em.persist(user2);
		em.getTransaction().commit();
		String sql="SELECT u FROM RegularUser u WHERE u.firstName = :arg1";
		Query query=em.createQuery(sql);
		query.setParameter("arg1", "Rod");
		List<RegularUser> users=query.getResultList();
		/*for (int i=0;i<users.size();i++){
			System.out.println(users.get(i).getLastName());
		}*/
		Assert.assertEquals(users.size(), 2);
		em.close();
		emf.close();
	}
	
	
	
}