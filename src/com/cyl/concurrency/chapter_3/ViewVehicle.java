package com.cyl.concurrency.chapter_3;

import java.util.Map;
import java.util.Set;

public class ViewVehicle implements Runnable{

	private MonitorVehicleTracker tracker;
	
	public ViewVehicle(MonitorVehicleTracker tracker){
		this.tracker = tracker;
	}
	
	@Override
	public void run(){
		while(true){
			System.out.println("view thread start");
			Map<String, MutablePoint> m = tracker.getLocations();
			System.out.println("trackers size" + m.size());
			
			Set<String> keys = m.keySet();
			for(String name : keys){
				MutablePoint point = m.get(name);
				System.out.println("x=" + point.x + ",y=" + point.y);
			}
		}
	}
}
