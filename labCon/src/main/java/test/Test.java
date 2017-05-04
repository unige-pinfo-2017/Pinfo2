package test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/*
 * Test-class
 */

@Path("/")
public class Test {

	public Test() {
	}

	// Ce qui permet de commiter sur la DB
	// Il faut bien utiliser ce nom la car c'est celui spécifié dans le
	// persistance.xml
	@PersistenceContext(name = "ProjectPersistence")
	private EntityManager em;

	@Resource
	private UserTransaction userTransaction;

	@GET
	@Path("/")
	@Produces({ "application/json" })
	// Mot clé obligatoire pour commiter la transaction (j'aimerais faire sans)
	@Transactional
	public String test() {
		// je créé les entités
		Personne pers = new Personne("Cabrini", "Vincent", 1);
		Personne pers2 = new Personne("Cule", "Jean", 100);
		// querry pour voir si il y a pas deja les entrées pers et pers1 dans la table
		String hql = "SELECT lastName FROM PERSONNE";
		Query query = em.createQuery(hql);
		List<?> personneExistante = query.getResultList();
		// ajout dans la DB grace a l'entity Manager du gestionnaire si ils y sont pas
		if (personneExistante.isEmpty()) {
			em.persist(pers);
			em.persist(pers2);
		}
		// querry pour récupérer les données qu'on vient de mettre dans la DB
		hql = "SELECT lastName FROM PERSONNE WHERE ID=1";
		query = em.createQuery(hql);
		Object personneRecuperee = query.getSingleResult();
		// je retourne ses attributs pour l'afficher sur la webapp
		return "Affichage du last name de la personne avec l'ID 1 : " + personneRecuperee;
	}
	
	@GET
	@Path("/testPlot")
	@Produces({ "application/json" })
	@Transactional
	public Json testPlot(){
		
		ArrayList<ValueForPlot> arrayValueForPlot = new ArrayList<ValueForPlot>();
		
		for (int i = 0; i<100; i++){
			
			ValueForPlot value = new ValueForPlot(i, Math.random(), i);
			arrayValueForPlot.add(value);
		}
		
		String hql = "SELECT x FROM VALUEFORPLOT";
		Query query = em.createQuery(hql);
		List<?> valuesExisting = query.getResultList();
		// ajout dans la DB grace a l'entity Manager du gestionnaire si ils y sont pas
		if (valuesExisting.isEmpty()) {
			for (int i = 0; i<100; i++){
				em.persist(arrayValueForPlot.get(i));
			}
		}
		// querry pour récupérer les données qu'on vient de mettre dans la DB
		hql = "SELECT x,y FROM VALUEFORPLOT";
		query = em.createQuery(hql);
		List<Object[]> listValueForPlot = query.getResultList();
		JsonArrayBuilder JsonArrayValues = Json.createArrayBuilder();	
				
		for (Object[] row : listValueForPlot) {
			System.out.println((String)row[0] + (double)row[1] + (double)row[2]);
			JsonArrayValues.add(Json.createObjectBuilder().add("name", (String) row[1]).add("value", (double) row[2]));
		    
		}
		
		return (Json) JsonArrayValues.build();
		
	}
	
}