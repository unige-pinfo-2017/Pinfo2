package ch.unige.pinfo2.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ch.unige.pinfo2.dom.RegularUser;


@Stateless
public class RegularUserServiceImpl implements RegularUserService {
		
	@PersistenceContext(unitName="ProjectPersistence")
    private EntityManager em;   

    
	@Transactional
	public void addUser(RegularUser user) {
		if (!this.alreadyRegistered(user)) {
			user.setToken(this.createToken());
			em.persist(user);
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

	public String loginUser(String username, String password) {

		String sql = "SELECT u.username FROM RegularUser u WHERE u.username = :arg1 AND u.password = :arg2";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", username);
		query.setParameter("arg2", password);
		if (query.getResultList().isEmpty()) {
			return null;
		}
		return (String) query.getSingleResult();
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

	@Override
	public String createToken() {
		SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
}