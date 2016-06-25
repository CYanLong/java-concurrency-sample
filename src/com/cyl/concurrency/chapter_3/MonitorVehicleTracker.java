package com.cyl.concurrency.chapter_3;

import java.util.HashMap;
import java.util.Map;

public class MonitorVehicleTracker {
	
	private final Map<String, MutablePoint> locations = new HashMap<>();
		
	public MutablePoint getLocation(String name){
		MutablePoint point = locations.get(name);
		return point;
	}
	
	public void setLocation(String name, MutablePoint point){
		locations.put(name, point);
	}
	
	public Map<String, MutablePoint> getLocations(){
		return locations;
	}
}
