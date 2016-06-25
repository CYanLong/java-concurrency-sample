package com.cyl.concurrency.chapter_3;

public class ViewTest {
	public static void main(String[] args){
		MonitorVehicleTracker tracker = new MonitorVehicleTracker();	
		ViewVehicle view = new ViewVehicle(tracker);
		Thread t1 = new Thread(view);
		t1.start();
	}
}
