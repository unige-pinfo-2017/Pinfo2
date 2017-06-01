package ch.unige.pinfo2.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.mock.LightServiceImplMock;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class LightServiceImplTest {
	
	@Inject
	private LightService ls;
	
	@Test
	public void testLastStatePowerNotNull(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertNotNull(light.getPower());
	}
	
	@Test
	public void testLastStatePowerPositive(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertTrue(light.getPower()>=0);
	}
	
	@Test
	public void testLastStateTimestampNotNull(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertNotNull(light.getTimestamp());
	}
	
	//Bug ici, timestamp peut etre n�gatif
	@Test
	public void testLastStateTimestampPositive(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertTrue(light.getTimestamp()>=0);
	}
	
	@Test
	public void testLastStateStatusOn(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertNotNull(light.getOnOffStatus());
	}
	
	@Test
	public void testLastStateStatusNotNull(){
		ls=new LightServiceImplMock();
		Light light = ls.getLastState("1");
		Assert.assertNotNull(light.getOnOffStatus());
	}
	
	@Test
	public void testStatesNotNull(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 2000L);
		Assert.assertNotNull(lights);
	}
	
	@Test
	public void testStatesSizeZero2(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 3000L, 2000L);
		Assert.assertEquals(lights.size(),0);
	}
	
	@Test
	public void testStatesPowerNotNull(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 3000L);
		Boolean flag=true;
		for (Light l: lights){
			if(l.getPower()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testStatesPowerPositive(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 3000L);
		Boolean flag=true;
		for (Light l: lights){
			if(l.getPower()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testStatesTimestampNotNull(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 3000L);
		Boolean flag=true;
		for (Light l: lights){
			if(l.getTimestamp()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testStatesTimestampPositive(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 3000L);
		Boolean flag=true;
		for (Light l: lights){
			if(l.getTimestamp()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	
	
	@Test
	public void testStatesGetOn(){
		ls=new LightServiceImplMock();
		List<Light> lights = ls.getStates("1", 2000L, 3000L);
		Boolean flag=true;
		for (Light l: lights){
			if(l.getPower()==0){
				if(l.getOnOffStatus()==true){
					flag=false;
				}
			}
			else{
				if(l.getOnOffStatus()==false){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
}

