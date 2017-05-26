package ch.unige.pinfo2.service;

import org.junit.Test;

import ch.unige.pinfo2.dom.Hub;
import ch.unige.pinfo2.mock.HubServiceMock;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class HubServiceImplTest{
	
	HubService hs;
	
	@Test
	public void testGetLastStateSizeListHubs(){
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
	public void testHubGetStateEmpty(){
		hs=new HubServiceMock();
		Assert.assertEquals(0, hs.getState("1", 2010L, 2010L).size());
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
		Assert.assertEquals(0, hs.getState(null, 2020L, 2010L).size());
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
	}

}
