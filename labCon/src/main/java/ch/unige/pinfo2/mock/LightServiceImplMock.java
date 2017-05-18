package ch.unige.pinfo2.mock;

import java.util.ArrayList;

import javax.enterprise.inject.Alternative;

import ch.unige.pinfo2.dom.Light;
import ch.unige.pinfo2.service.LightService;

@Alternative
public class LightServiceImplMock implements LightService {

	@Override
	public ArrayList<Light> getState(String deviceId, Long from, Long to) {
		ArrayList<Light> lightStates = new ArrayList<Light>();
		
		long startTime = Long.parseLong("1493290847263");
		for(int i=0; i<10; i++) {
			lightStates.add(new Light(new Long(startTime), new Double(0)));
			startTime += 20;
		}
		for(int i=0; i<10; i++) {
			lightStates.add(new Light(new Long(startTime), new Double(20.3)));
			startTime += 20;
		}
		
		return lightStates;
	}
	
	
}
