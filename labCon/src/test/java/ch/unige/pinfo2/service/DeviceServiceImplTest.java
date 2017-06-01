package ch.unige.pinfo2.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Test;

import ch.unige.pinfo2.dom.Device;
import ch.unige.pinfo2.dom.DeviceType;
import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.dom.Socket;
import ch.unige.pinfo2.mock.DeviceServiceImplMock;
import junit.framework.Assert;

import junit.framework.TestCase;




public class DeviceServiceImplTest {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager em = emf.createEntityManager();
	
	@Transactional
	public void addDevice(Device newDevice) {
		if (!this.isInDatabase(newDevice.getId())){
			em.getTransaction().begin();
			em.persist(newDevice);
			em.getTransaction().commit();
		}
		
	}

	public boolean isInDatabase(String deviceId) {
		String sql = "SELECT d FROM Device d WHERE d.id = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", deviceId);
		if (query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	public void assignWorkstation(String deviceId, String workstation) {
		if (isInDatabase(deviceId)){
			em.clear();
			em.getTransaction().begin();
			String sql = "UPDATE Device d SET d.workstation = :arg1 WHERE d.id = :arg2";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", workstation);
			query.setParameter("arg2", deviceId);
			query.executeUpdate();
			em.getTransaction().commit();
			em.clear();
		}
	}

	public void denyWorkstation(String deviceId) {
		if (this.isInDatabase(deviceId)){
			em.clear();
			em.getTransaction().begin();
			String sql = "UPDATE Device d SET d.workstation = :arg1 WHERE d.id = :arg2";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", null);
			query.setParameter("arg2", deviceId);
			query.executeUpdate();
			em.getTransaction().commit();
			em.clear();
		}	
	}

	public String getWorkstation(String deviceId) {
		if (this.isInDatabase(deviceId)){
			String sql = "SELECT d FROM Device d WHERE d.id = :arg1";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", deviceId);
			List<Device> ld=query.getResultList();
			return ld.get(0).getWorkstation();
		}
		return null;
	}
	
	public List<String> getSocketIds(String workstation) {
		String sql = "SELECT d.id FROM Device d WHERE d.workstation = :arg1 and d.type= :arg2";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", workstation);
		query.setParameter("arg2", DeviceType.SOCKET);
		return query.getResultList();
	}
	
	public DeviceType getDeviceType(String deviceId) {
		String sql = "SELECT d.type FROM Device d WHERE d.id=:arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", deviceId);
		return (DeviceType) query.getResultList().get(0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testAddDeviceLight(){
		addDevice(new Light("LightAdd",50L,20D));
		Assert.assertTrue(isInDatabase("LightAdd"));
	}
	
	/*@Test
	public void testLightAlreadyInDatabase(){
		addDevice(new Light("LightAdd",50L,20D));
		Assert.assertTrue(isInDatabase("Light1"));
	}*/
	
	@Test
	public void testLightNotInDatabase(){
		//addDevice(new Light("LightAdd",50L,20D));
		Assert.assertTrue(!isInDatabase("Light2fyzet"));
	}
	
	@Test
	public void testLightWorkstationNull(){
		Light l=new Light("LightWSNull",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNull(workstationLight);
	}
	
	@Test
	public void testLightAssignWorkstationNull(){
		Light l=new Light("LightAsgnWSNull",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		assignWorkstation("LightAsgnWSNull", "WS1");
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNotNull(workstationLight);
		Assert.assertEquals("WS1", workstationLight);
	}
	
	@Test
	public void testAssignWorkstationNotNull(){
		Light l=new Light("LightAsgnWSNotNull",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		assignWorkstation("LightAsgnWSNotNull", "WS2");
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNotNull(workstationLight);
		Assert.assertEquals("WS2", workstationLight);
		assignWorkstation("LightAsgnWSNotNull", "WS1");
		workstationLight=getWorkstation(l.getId());
		Assert.assertNotNull(workstationLight);
		Assert.assertEquals("WS1", workstationLight);
	}
	
	@Test
	public void testDenyWorkstationNotNull(){
		Light l=new Light("LightDAsgnWSNotNull",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		assignWorkstation("LightDAsgnWSNotNull", "WS2");
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNotNull(workstationLight);
		Assert.assertEquals("WS2", workstationLight);
		denyWorkstation("LightDAsgnWSNotNull");
		workstationLight=getWorkstation(l.getId());
		Assert.assertNull(workstationLight);
	}
	
	@Test
	public void testDenyWorkstationNull(){
		Light l=new Light("LightDAsgnWSNull",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		denyWorkstation("LightDAsgnWSNull");
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNull(workstationLight);
	}
	
	@Test
	public void testGetWorkstationIdNotNull(){
		Light l=new Light("LightGetWorkstation1",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		assignWorkstation("LightGetWorkstation1", "WS1");
		String workstationLight=getWorkstation(l.getId());
		Assert.assertNotNull(workstationLight);
	}
	
	@Test
	public void testGetWorkstationIdNull(){
		Light l=new Light("LightGetWorkstation1",50L,20D);
		addDevice(l);
		Assert.assertNull(l.getWorkstation());
		assignWorkstation("LightGetWorkstation1", "WS1");
		Assert.assertTrue(!isInDatabase("Light"));
		String workstationLight=getWorkstation("Light");
		Assert.assertNull(workstationLight);
	}
	
	@Test
	public void testGetSockets(){
		Socket s1=new Socket("Socket1",50L,2D,20D);
		Socket s2=new Socket("Socket2",50L,2D,20D);
		Socket s3=new Socket("Socket3",50L,2D,20D);
		Socket s4=new Socket("Socket4",50L,2D,20D);
		Socket s5=new Socket("Socket5",50L,2D,20D);
		Socket s6=new Socket("Socket6",50L,2D,20D);
		Socket s7=new Socket("Socket7",50L,2D,20D);
		Socket s8=new Socket("Socket8",50L,2D,20D);
		Socket s9=new Socket("Socket9",50L,2D,20D);
		Socket s10=new Socket("Socket10",50L,2D,20D);
		Socket s11=new Socket("Socket11",50L,2D,20D);
		Socket s12=new Socket("Socket12",50L,2D,20D);
		addDevice(s1);
		addDevice(s2);
		addDevice(s3);
		addDevice(s4);
		addDevice(s5);
		addDevice(s6);
		addDevice(s7);
		addDevice(s8);
		addDevice(s9);
		addDevice(s10);
		addDevice(s11);
		addDevice(s12);
		assignWorkstation("Socket1", "WS1");
		assignWorkstation("Socket2", "WS1");
		assignWorkstation("Socket3", "WS1");
		assignWorkstation("Socket4", "WS1");
		assignWorkstation("Socket5", "WS1");
		assignWorkstation("Socket6", "WS1");
		assignWorkstation("Socket7", "WS2");
		assignWorkstation("Socket8", "WS2");
		assignWorkstation("Socket9", "WS2");
		assignWorkstation("Socket10", "WS2");
		assignWorkstation("Socket11", "WS2");
		assignWorkstation("Socket12", "WS2");
		List<String> ws1Sockets=getSocketIds("WS1");
		List<String> ws2Sockets=getSocketIds("WS2");
		List<String> ws1SocketsVerif=new ArrayList<String>();
		ws1SocketsVerif.add("Socket1");
		ws1SocketsVerif.add("Socket2");
		ws1SocketsVerif.add("Socket3");
		ws1SocketsVerif.add("Socket4");
		ws1SocketsVerif.add("Socket5");
		ws1SocketsVerif.add("Socket6");
		List<String> ws2SocketsVerif=new ArrayList<String>();
		ws2SocketsVerif.add("Socket7");
		ws2SocketsVerif.add("Socket8");
		ws2SocketsVerif.add("Socket9");
		ws2SocketsVerif.add("Socket10");
		ws2SocketsVerif.add("Socket11");
		ws2SocketsVerif.add("Socket12");
		
		for (int i=0; i<ws1Sockets.size();i++){
			Assert.assertEquals(ws1SocketsVerif.get(i), ws1Sockets.get(i));
			Assert.assertEquals(ws2SocketsVerif.get(i), ws2Sockets.get(i));
		}
		//Assert.assertThat(ws1Sockets,contains(ws1SocketsVerif);
		//Assert.assertSame(ws2Sockets,ws2SocketsVerif);
	}
	
	@Test
	public void testGetDeviceType(){
		Light l=new Light("LightGetDeviceType",50L,20D);
		addDevice(l);
		DeviceType dt=getDeviceType("LightGetDeviceType");
		Assert.assertEquals(DeviceType.LIGHT, dt);
	}
	
}
