package ch.unige.pinfo2.test;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestServiceMock implements TestService{
	
	public String methode(){
		return "MOCK";
	}

}
