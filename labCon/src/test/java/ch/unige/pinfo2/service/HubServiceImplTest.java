package ch.unige.pinfo2.service;

import java.util.List;
import javax.inject.Inject;
import org.junit.Test;
import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.mock.HubServiceMock;
import ch.unige.pinfo2.mock.LightServiceImplMock;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class HubServiceImplTest{
	
	@Inject
	private HubService hs;
	
	
	@Test
	public void testCountSockets(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		Assert.assertEquals(6, hub.getSockets().size());
	}
	
	@Test
	public void testLastStatePowerNotNull(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getPower()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testLastStateCurrentNotNull(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getCurrent()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testLastStateTimestampNotNull(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getTimestamp()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	
	@Test
	public void testLastStatePowerPositive(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getPower()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testLastStateCurrentPositive(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getCurrent()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testLastStateTimestampPositive(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getTimestamp()<0){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testLastStateIsOnNotNull(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		List<Socket> sockets=hub.getSockets();
		Boolean flag=true;
		for (Socket s: sockets){
			if(s.getIsOn()==null){
				flag=false;
			}
		}
		Assert.assertTrue(flag);
	}
	
	//GETSTATES
	
	
	@Test
	public void testStatesTimestampNotNull(){
		hs=new HubServiceMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s: h.getSockets()){
				if(s.getTimestamp()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testStatesCurrentNotNull(){
		hs=new HubServiceMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s: h.getSockets()){
				if(s.getCurrent()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testStatesPowerNotNull(){
		hs=new HubServiceMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s: h.getSockets()){
				if(s.getPower()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	
	
	@Test
	public void testStatesIsOnNotNull(){
		hs=new HubServiceMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s: h.getSockets()){
				if(s.getIsOn()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Test
	public void testCountSockets(){
		hs=new HubServiceMock();
		Hub hub = hs.getLastState("1");
		Assert.assertEquals(6, hub.getSockets().size());
	}
	
	@Test
	public void testHubGetStateNotEmpty(){
		hs=new HubServiceMock();
		Assert.assertEquals(1, hs.getState("1", 2000L, 2010L).size());
	}
	
	@Test
	public void testHubGetStateEmpty1(){
		hs=new HubServiceMock();
		Assert.assertEquals(0, hs.getState("1", 2010L, 2010L).size());
	}
	
	@Test
	public void testHubGetStateEmpty2(){
		hs=new HubServiceMock();
		Assert.assertEquals(0, hs.getState("1", 2020L, 2010L).size());
	}
	
	@Test
	public void testHubGetStateLength(){
		hs=new HubServiceMock();
		Long fromValue=1980L;
		Long toValue=3000L;
		Assert.assertEquals((toValue-fromValue)/20, hs.getState("1", fromValue, toValue).size());	
	}
	
	@Test
	public void testHubGetStateDeviceIDNull(){
		hs=new HubServiceMock();
		Assert.assertEquals(0, hs.getState(null, 2020L, 2010L));
	}
	
	@Test
	public void testHubGetStateFromValueNull(){
		hs=new HubServiceMock();
		Assert.assertEquals(1, hs.getState("1", null, 2010L).size());
	}
	
	@Test
	public void testHubGetStateToValueNull(){
		hs=new HubServiceMock();
		Assert.assertEquals(1, hs.getState("1", 2000L, null).size());
	}
	
	@Test
	public void testHubGetStateFromToValuesNull(){
		hs=new HubServiceMock();
		Assert.assertEquals(1, hs.getState("1", null, null).size());
	}
	
	@Test
	public void testHubGetStateSameValues(){
		hs=new HubServiceMock();
		Long fromValue=2000L;
		Long toValue=2000L;
		Assert.assertEquals((toValue-fromValue)/20, hs.getState("1", fromValue, toValue).size());	
	}
	
	@Test
	public void testHubGetStateTwoLists(){
		hs=new HubServiceMock();
		Assert.assertNotSame(hs.getState("1", 1980L, 2020L).size(), hs.getState("2", 2000L, 2010L).size());	
	}*/

}
