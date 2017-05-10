package ch.unige.pinfo2.service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.unige.pinfo2.dom.User;;

public class UserServiceImplTest{
	
	@Test
	public void test() {
		User user1=new User();
		user1.setId((long) 1);
		user1.setFirstName("Rod");
		user1.setLastName("Bou");
		user1.setUserName("RodBou");
		user1.setPassword("PWD");
		//user1.setToken((long) 1); pas besoin car c'est généré
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		em.persist(user1);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	
}