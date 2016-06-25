 package com.cyl.concurrency.chapter4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程安全性的委托.
 * 将多个线程非安全的类组合为一个类时,Java监视器模式将非常有用.
 * 但是,但我们要组合的类都是线程安全的呢,还需要额外的同步机制吗?
 * 
 * 使用安全的类来组合对象的确可以将一些线程安全问题委托给这些类来实现.
 * 在某些情况下我们确实不需要额外的同步策略.
 * 但当我们对这些组合的安全类执行类层面的非原子操作时,可能还需要额外的同步.
 * 
 * @author CYL
 *
 */
public class DelegatingVehicleTracker {
	
	private final ConcurrentHashMap<String, ImmutablePoint>  locations;
	
	public DelegatingVehicleTracker(Map<String, ImmutablePoint> map){
		locations = new ConcurrentHashMap<String, ImmutablePoint>(map);
	}
	
	public Map<String, ImmutablePoint> getLocations(){
		return locations;
	}
	
	public ImmutablePoint getLocation(String name){
		return locations.get(name);
	}
	
	public void setLocation(String name, int x, int y){
		locations.put(name, new ImmutablePoint(x, y));
	}
}
