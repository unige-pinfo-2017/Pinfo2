package ch.unige.pinfo2.service;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ch.unige.pinfo2.dom.RegularUser;

@Stateless
public class UserServiceImpl implements UserService {
	@PersistenceContext(name = "ProjectPersistence")
	private EntityManager em;

	@Transactional
	public void addUser(RegularUser user) {
		String sql = "SELECT ru FROM RegularUser ru";
		Query query = em.createQuery(sql);
		List<RegularUser> listUsers = query.getResultList();

		int i = 0;
		int userNotInDB = 1;
		while (i < listUsers.size()) {
			if (listUsers.get(i).getUserName() == user.getUserName()) {
				userNotInDB = 0;
				break;
			}
			i++;
		}
		if (userNotInDB == 1) {
			// em.getTransaction().begin();
			em.persist(user);
			// em.getTransaction().commit();
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

	public long loginUser(String username, String password) {
		String sql = "SELECT u.token FROM RegularUser u WHERE u.username = :arg1 AND u.password = :arg2";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", username);
		query.setParameter("arg2", password);
		List<RegularUser> users = query.getResultList();
		if (query.getResultList().isEmpty()) {
			return (Long) null;
		}
		return (long) query.getResultList().get(0);
	}

	public RegularUser getUserById(long id) {
		String sql = "SELECT u FROM RegularUser u WHERE u.id = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", id);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (RegularUser) query.getSingleResult();
	}

	public RegularUser getUserByToken(long token) {
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
		List<RegularUser> users = query.getResultList();
		return users;
	}

	public List<RegularUser> getUserByFirstName(String firstName) {
		String sql = "SELECT u FROM RegularUser u WHERE u.firstName = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", firstName);
		List<RegularUser> users = query.getResultList();
		return users;
		/*
		 * String sql="SELECT u FROM USER u WHERE u.firstName = ?1"; Query
		 * query=em.createQuery(sql); query.setParameter(1, firstName);
		 * if(query.getResultList().isEmpty()){ return null; } return
		 * query.getResultList();
		 */
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

	@Override
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

}