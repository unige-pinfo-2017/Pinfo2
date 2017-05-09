package ch.unige.pinfo2.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;

import ch.unige.pinfo2.dom.User;

@Stateless
public class UserServiceImpl implements UserService{
	@PersistenceContext(name ="ProjectPersistence")
	private EntityManager em;

	public void addUser(User user) {
		//Creation de la requete SQL
		String sql="SELECT u FROM USER u";
		Query query=em.createQuery(sql);
		//Resultat de la requete SQL
		List<User> listUsers=query.getResultList();
		int i=0;
		int userNotInDB=1;
		//On parcourt la liste des Users et on regarde l'unicité de certains des attibuts de user
		while (i<listUsers.size()){
			if((listUsers.get(i).getUserName()==user.getUserName())||(listUsers.get(i).getId()==user.getId())){
				userNotInDB=0;
				System.out.println("User In DB");
				break;
			}
			i++;
		}
		//Si le user n'est pas dans la base de données, on le met dans la base de données
		if(userNotInDB==0){
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
	}

	public boolean alreadyRegistered(User user) {
		String sql="SELECT u FROM USER u WHERE u.ID = ?1 AND u.FIRST_NAME = ?2 AND u.LAST_NAME = ?3 AND u.USERNAME = ?4";
		Query query=em.createQuery(sql);
		query.setParameter(1, user.getId());
		query.setParameter(2, user.getFirstName());
		query.setParameter(3, user.getLastName());
		query.setParameter(4, user.getUserName());
		if(query.getResultList().isEmpty()){
			return false;
		}
		return true;	
	}

	public long loginUser(String username, String password) {
		String sql="SELECT u.TOKEN FROM USER u WHERE u.USERNAME = ?1 AND u.PASSWORD = ?2";
		Query query=em.createQuery(sql);
		query.setParameter(1, username);
		query.setParameter(2, password);
		if(query.getResultList().isEmpty()){
			return (Long) null;
		}
		return query.getFirstResult();
	}

	public User getUserById(long id) {
		String sql="SELECT u FROM USER u WHERE u.ID = ?1";
		Query query=em.createQuery(sql);
		query.setParameter(1, id);
		if(query.getResultList().isEmpty()){
			return null;
		}
		return (User) query.getSingleResult();
	}

	public User getUserByToken(long token) {
		String sql="SELECT u FROM USER u WHERE u.TOKEN = ?1";
		Query query=em.createQuery(sql);
		query.setParameter(1, token);
		if(query.getResultList().isEmpty()){
			return null;
		}
		return (User) query.getSingleResult();
	}

	public List<User> getUserByLastName(String lastName) {
		String sql="SELECT u FROM USER u WHERE u.LASTNAME = ?1";
		Query query=em.createQuery(sql);
		query.setParameter(1, lastName);
		if(query.getResultList().isEmpty()){
			return null;
		}
		return query.getResultList();
	}

	public List<User> getUserByFirstName(String firstName) {
		String sql="SELECT u FROM USER u WHERE u.FIRSTNAME = ?1";
		Query query=em.createQuery(sql);
		query.setParameter(1, firstName);
		if(query.getResultList().isEmpty()){
			return null;
		}
		return query.getResultList();
	}

	
	
	
}