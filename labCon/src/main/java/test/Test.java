package test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/*
 * Base-class of our app.
 * We define (inject) here which baker we want to use (GermanBaker, FrenchBaker).
 * Depending on our choice we will receive different dishes.
 */

@Path("/")
public class Test {

	
	public Test() {
	}
	
	/**
	 * The entity manager that manages the persistence. As there is only one
	 * persistence unit, it takes it by default.
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * Test routine to check that the server works.
	 */
	@GET
	@Path("/")
	@Produces({ "application/json" })
	@Transactional
	public String test(){
		//je créé l'entité
		Personne pers = new Personne("Cabrini", "Vincent", 1);
		//pers.setId(100);
		//ajout dans la DB grace a l'entity Manager
		entityManager.persist(pers);
		//je retourne ses attributs pour l'afficher sur la webapp
		return "Affichage de deux attributs d'une entité: "+pers.getFirstName()+" " + pers.getLastName();
	}
}