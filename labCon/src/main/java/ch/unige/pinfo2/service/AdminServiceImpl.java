package ch.unige.pinfo2.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ch.unige.pinfo2.dom.RegularUser;

public class AdminServiceImpl implements AdminService{
	
	@Inject
	RegularUserService userService;
	
	@PersistenceContext(unitName="ProjectPersistence")
    private EntityManager em;   
	
	@Transactional
	public boolean addUser(RegularUser user) {
		if (!this.alreadyRegistered(user)) {
			user.setToken(userService.createToken());
			em.persist(user);
			return true;
		}
		return false;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<RegularUser> getAllUsers() {
		String sql = "SELECT r FROM RegularUser r";
		Query query = em.createQuery(sql);
		return (List<RegularUser>) query.getResultList();
	}
}
