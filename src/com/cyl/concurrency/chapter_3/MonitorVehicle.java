package com.cyl.concurrency.chapter_3;

public class MonitorVehicle implements Runnable{

	private MonitorVehicleTracker tracker;
	
	public MonitorVehicle(MonitorVehicleTracker tracker){
		this.tracker = tracker;
	}
	
	@Override
	public void run() {
		while(true) {		
			String name = "bus";//从标准输入中获取name.
			
			int x = 1;
			int y =2;
			MutablePoint point = new MutablePoint(x, y);
			System.out.println("Monitor start ..");
			tracker.setLocation(name, point);
			System.out.println("once success");
		}
	}
	
}
