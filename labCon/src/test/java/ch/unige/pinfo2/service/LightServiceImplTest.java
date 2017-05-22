package ch.unige.pinfo2.service;

import org.junit.Test;
import ch.unige.pinfo2.mock.LightServiceImplMock;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class LightServiceImplTest {
	
	LightService ls;
	
	@Test
	public void testTimeStampTrue(){
		ls=new LightServiceImplMock();
		Assert.assertEquals(1493290847263L ,ls.getState("1").getTimestamp().longValue());
	}
	
	@Test
	public void testTimeStampFalse(){
		ls=new LightServiceImplMock();
		Assert.assertNotSame(1L ,ls.getState("1").getTimestamp().longValue());
	}
	
	@Test
	public void testPowerTrue(){
		ls=new LightServiceImplMock();
		Assert.assertEquals(20.3 ,ls.getState("1").getPower());
	}
	
	@Test
	public void testPowerFalse(){
		ls=new LightServiceImplMock();
		Assert.assertNotSame(20.1 ,ls.getState("1").getPower());
	}
	
}
