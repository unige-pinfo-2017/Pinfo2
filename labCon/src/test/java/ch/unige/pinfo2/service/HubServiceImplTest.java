package ch.unige.pinfo2.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import ch.unige.pinfo2.dom.Hub;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class HubServiceImplTest{
	
	@Inject
	private HubService hs;
	
	
	/*
	@Test
	public void testCountSockets(){
		hs=new HubServiceImplMock();
		Hub hub = hs.getLastState("1");
		Assert.assertEquals(6, hub.getSockets().size());
	}
	
	@Test
	public void testLastStatePowerNotNull(){
		hs=new HubServiceImplMock();
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
		hs=new HubServiceImplMock();
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
		hs=new HubServiceImplMock();
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
		hs=new HubServiceImplMock();
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
		hs=new HubServiceImplMock();
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
		hs=new HubServiceImplMock();
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
	public void testGetStateCurrentNotNull(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getCurrent()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetStateCurrentPositive(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getCurrent()<0){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetStatePowerNotNull(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getPower()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetStatePowerPositive(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getPower()<0){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetStateTimestampNotNull(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getTimestamp()==null){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	
	@Test
	public void testGetStateTimestampPositive(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Boolean flag=true;
		for (Hub h: hubs){
			for (Socket s:h.getSockets()){
				if(s.getTimestamp()<0){
					flag=false;
				}
			}
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetStateNotNull(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 2000L);
		Assert.assertNotNull(hubs);
	}
	
	@Test
	public void testStatesSizeNotZero(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 2000L, 3000L);
		Assert.assertTrue(hubs.size()>0);
	}
	
	@Test
	public void testStatesSizeZero2(){
		hs=new HubServiceImplMock();
		List<Hub> hubs = hs.getState("1", 3000L, 2000L);
		Assert.assertEquals(hubs.size(),0);
	}
	*/
}
