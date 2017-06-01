package ch.unige.pinfo2.test;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * J'ai été obligé de rajouter cette classe sinon j'ai un 'Forbiden' quand
 * j'essaie d'aller sur la webapp
 */
@ApplicationPath("/")
public class App extends Application {

}