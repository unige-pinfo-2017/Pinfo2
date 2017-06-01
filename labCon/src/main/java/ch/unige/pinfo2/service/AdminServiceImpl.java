package ch.unige.pinfo2.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ch.unige.pinfo2.dom.RegularUser;

/**
 * Provides a set of service for the admin of the smartlab.
 */
@Stateless
public class AdminServiceImpl implements AdminService{
	
	@Inject
	RegularUserService userService;
	
	@PersistenceContext(unitName="ProjectPersistence")
    private EntityManager em;   
	
	@Transactional
	public boolean addUser(RegularUser user) {
		if (!userService.alreadyRegistered(user)) {
			user.setToken(userService.createToken());
			em.persist(user);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegularUser> getAllUsers() {
		String sql = "SELECT r FROM RegularUser r";
		Query query = em.createQuery(sql);
		return (List<RegularUser>) query.getResultList();
	}
}
