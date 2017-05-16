package ch.unige.pinfo2.mock;

import java.util.List;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.service.HubService;

@Alternative
public class HubServiceMock implements HubService{

	@Override
	public List<Long> getSocketsIdByToken(Long token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getConsumption(String from, String to, String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getStatus(String from, String to, String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getStateCurrentSensor(String from, String to, String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setState(String state, String deviceId) {
		// TODO Auto-generated method stub
		return false;
	}

}
