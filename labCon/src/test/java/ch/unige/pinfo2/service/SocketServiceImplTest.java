package ch.unige.pinfo2.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;


import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.mock.SocketServiceMock;
import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class SocketServiceImplTest {
	
	@Inject 
	private SocketService ssm;
	
	/*@Test
	public void testSocketGetStateTwoLists(){
		ssm=new SocketServiceMock();
		Assert.assertNotSame(ssm.getState("1", 1980L, 2020L).size(), ssm.getState("2", 2000L, 2010L).size());	
	}*/
	
	@Test
	public void testSocketPowerNotNull(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getPower()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketCurrentNotNull(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getCurrent()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketTimeStampNotNull(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getTimestamp()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketCurrentNotNegative(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getCurrent()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketPowerNotNegative(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getPower()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketTimestampNotNegative(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getTimestamp()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketIsOnNotNull(){
		ssm=new SocketServiceMock();
		List<Socket> sockets=ssm.getState("1", 2000L, 2010L);
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getIsOn()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testSocketIsOff(){
		ssm=new SocketServiceMock();
		Socket socket=ssm.getState("1", 2000L, 2010L).get(0);
		socket.setIsOn(false);
		Assert.assertTrue(!socket.getIsOn());
	}
	
	
	/*@Test
	public void testSocketGetStateNotEmpty(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(1, ssm.getState("1", 2000L, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateEmpty1(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(0, ssm.getState("1", 2010L, 2010L).size());
	}
	
	@Test
	public void testSocketGetStateEmpty2(){
		ssm=new SocketServiceMock();
		Assert.assertEquals(0, ssm.getState("1", 2020L, 2010L).size());
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
	}*/
	
	/*@Test
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
	}*/
	
	/*@Test
	public void testSocketGetStateSameValues(){
		ssm=new SocketServiceMock();
		Long fromValue=2000L;
		Long toValue=2000L;
		Assert.assertEquals((toValue-fromValue)/20, ssm.getState("1", fromValue, toValue).size());	
	}*/
	
	
	
	
	
}
