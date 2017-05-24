package ch.unige.pinfo2.mock;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.service.LightService;

@Alternative
public class LightServiceImplMock implements LightService {

	@Override
	public Light getState(String deviceId) {
		return new Light(new Long(Long.parseLong("1493290847263")), new Double(20.3));
	}

	@Override
	public Boolean setOnOrOff(String deviceId, String onOrOff) {
		return true;
	}
	
	
}
