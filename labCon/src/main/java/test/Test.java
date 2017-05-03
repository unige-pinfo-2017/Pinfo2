package test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


/*
 * Base-class of our app.
 * We define (inject) here which baker we want to use (GermanBaker, FrenchBaker).
 * Depending on our choice we will receive different dishes.
 */

@Path("/")
public class Test {

	
	public Test() {
	}

	
	/*
	 * Test routine to check that the server works.
	 */
	@GET
	@Path("/")
	public String test(){
		//je créé l'entité
		Personne pers = new Personne("Cabrini", "Vincent");
		//je retourne ses attributs pour l'afficher sur la webapp
		return "Affichage de deux attributs d'une entité: "+pers.getFirstName()+" " + pers.getLastName();
	}
}