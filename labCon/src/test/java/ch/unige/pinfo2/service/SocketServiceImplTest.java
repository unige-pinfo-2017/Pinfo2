package ch.unige.pinfo2.service;

import org.junit.Test;

import ch.unige.pinfo2.mock.SocketServiceMock;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class SocketServiceImplTest {
	
	SocketService ssm;
	
	@Test
	public void testSocketGetStateNotEmpty(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(1, ssm.getState("1", 2000L, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateEmpty(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(0, ssm.getState("1", 2010L, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateLength(){
		ssm=new SocketServiceMock();
		Long fromValue=1980L;
		Long toValue=3000L;
		Assert.assertEquals((toValue-fromValue)/20, ssm.getState("1", fromValue, toValue).size());	
	}
	
	@Test
	public void testSocketGetStateDeviceIDNull(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(0, ssm.getState(null, 2020L, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateFromValueNull(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(1, ssm.getState("1", null, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateToValueNull(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(1, ssm.getState("1", 2000L, null).size());
	}
	
	@Test
	public void testSocketGetStateFromToValuesNull(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(1, ssm.getState("1", null, null).size());
	}
	
	@Test
	public void testSocketGetStateSameValues(){
		ssm=new SocketServiceMock();
		Long fromValue=2000L;
		Long toValue=2000L;
		Assert.assertEquals((toValue-fromValue)/20, ssm.getState("1", fromValue, toValue).size());	
	}
	
	@Test
	public void testSocketGetStateTwoLists(){
		ssm=new SocketServiceMock();
		Assert.assertNotSame(ssm.getState("1", 1980L, 2020L).size(), ssm.getState("2", 2000L, 2010L).size());	
	}
	
	
}
