package com.cyl.concurrency.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 同样,这个例子也是一个隐蔽的死锁问题.其根源还是对锁的交替获取.
 * 
 * 当我们并发调用Taxi的setLocation和Dispatcher的getImage时会出现对taxi对象和Dispatcher的交替锁定.
 * @author CYL
 *
 */
public class HintDeadLock2 {	
}

class Taxi{
	private final Dispatcher dispatcher;
	private String location, destination;
	
	public Taxi(Dispatcher dispatcher){
		this.dispatcher = dispatcher;
	}
	
	public synchronized String getLocation(){
		return location;
	}
	
	public synchronized void setLocation(String location){
		this.location = location;
		if(location.equals(destination))
			dispatcher.notifyAvailable(this);
	}
}

class Dispatcher{
	
	private final Set<Taxi> taxis;
	
	private final Set<Taxi> availableTaxis;
	
	public Dispatcher(Set<Taxi> taxi, Set<Taxi> availableTaxis){
		this.taxis = taxi;
		this.availableTaxis = availableTaxis;
	}
	
	public synchronized void notifyAvailable(Taxi taxi){
		availableTaxis.add(taxi);
	}
	
	public synchronized Image getImage(){
		Image image = new Image();
		for(Taxi t : taxis){
			image.drawMarker(t.getLocation());
		}
		return image;
	}
}

class Image{
	List<String> ls = new ArrayList<String>();
	public void drawMarker(String location){
		ls.add(location);
	}
}