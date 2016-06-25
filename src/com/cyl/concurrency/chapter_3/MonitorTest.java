package com.cyl.concurrency.chapter_3;

public class MonitorTest {
	
	public static void main(String[] args){
		MonitorVehicleTracker tracker = new MonitorVehicleTracker();
		
		MonitorVehicle monitor = new MonitorVehicle(tracker);
		ViewVehicle view = new ViewVehicle(tracker);
		Thread t1 = new Thread(monitor);
		Thread t2 = new Thread(view);
		//Collections.unmodifiableMap(new HashMap<String, Point>())
		//HashMap
		t1.start();
		t2.start();
	}		
}
