package ch.unige.pinfo2.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.unige.pinfo2.dom.Device;
import ch.unige.pinfo2.dom.DeviceType;
import ch.unige.pinfo2.service.DeviceService;
import javax.enterprise.inject.Alternative;

@Alternative
public class DeviceServiceImplMock implements DeviceService {

	@PersistenceContext(unitName = "ProjectPersistence")
	private EntityManager em;

	@Override
	public ArrayList<ArrayList<String>> getDeviceIds() {
		ArrayList<ArrayList<String>> deviceIds = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < 3; i++) {
			deviceIds.add(new ArrayList<String>());
		}

		for (int i = 1; i <= 2; i++) {
			deviceIds.get(0).add("light" + i);
		}

		for (int i = 1; i <= 12; i++) {
			deviceIds.get(1).add("socket" + i);
		}

		for (int i = 1; i <= 2; i++) {
			deviceIds.get(2).add("hub" + i);
		}

		return deviceIds;
	}

	public void addDevice(Device newDevice) {
		if (!this.isInDatabase(newDevice.getId())) {
			em.persist(newDevice);
		}

	}

	@Override
	public boolean isInDatabase(String deviceId) {
		String sql = "SELECT d.id FROM Device d WHERE d.id = :arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", deviceId);
		if (query.getResultList().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void assignWorkstation(String deviceId, String workstation) {
		if (this.isInDatabase(deviceId)) {
			String sql = "UPDATE Device d SET d.workstation = :arg1 WHERE d.id = :arg2";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", deviceId);
			query.setParameter("arg2", workstation);
		}
	}

	@Override
	public void denyWorkstation(String deviceId) {
		if (this.isInDatabase(deviceId)) {
			String sql = "UPDATE Device d SET d.workstation = :arg1 WHERE d.id = :arg2";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", deviceId);
			query.setParameter("arg2", null);
		}
	}

	@Override
	public String getWorkstation(String deviceId) {
		if (this.isInDatabase(deviceId)) {
			String sql = "SELECT d.workstation FROM Device d WHERE d.id = :arg1";
			Query query = em.createQuery(sql);
			query.setParameter("arg1", deviceId);
			return (String) query.getResultList().get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<String> getSocketIds(String workstation) {
		String sql = "SELECT d.id FROM Device d WHERE d.workstation = :arg1 and d.type= 'SOCKET'";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", workstation);
		return query.getResultList();
	}

	public DeviceType getDeviceType(String deviceId) {
		String sql = "SELECT d.type FROM Device d WHERE d.id=:arg1";
		Query query = em.createQuery(sql);
		query.setParameter("arg1", deviceId);
		return (DeviceType) query.getResultList().get(0);
	}

}