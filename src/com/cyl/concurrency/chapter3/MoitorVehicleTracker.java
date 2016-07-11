package com.cyl.concurrency.chapter3;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * 接着VehicleTracker,我们发现线程安全性问题出在HashMap和对MutablePoint共享对象的
 * 非原子操作上.
 * 
 * 我们可以使用监视器模式来同步这个例子.
 * 遵循监视器模式的对象会将组合了的对象的所有可变状态都封装起来,并有对象自己的内置锁
 * 来保护.
 * 
 * Vector和Hashtable就是这么实现的.
 * 
 * 我们给每一个对外的方法中都使用了synchronized同步.
 * getLocations和getLocation的同步是应为HashMap的非线程安全性.
 * 在代理(Delegating)模式下,你将看到若我们使用线程安全的类来组合.getLocations将不需要同步
 * @author CYL
 *
 */
public class MoitorVehicleTracker {
	
	private Map<String, MutablePoint> locations;
	
	public MoitorVehicleTracker(Map<String, MutablePoint> lo){
		locations = deepCopy(lo);
	}
	
	public synchronized Map<String, MutablePoint> getLocations(){
		return deepCopy(locations);
	}
	
	public synchronized MutablePoint getLocation(String name){
		return locations.get(name);
	}
	
	public synchronized void setLocation(String name, int x, int y){
		
		MutablePoint p = locations.get(name);
		if(p != null){		
			p.x = x;
			p.y = y;
		}
	}
		
	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> origin){
		
		Map<String, MutablePoint> copy = new HashMap<String, MutablePoint>();
		
		for(String key : origin.keySet()){
			copy.put(key, new MutablePoint(origin.get(key)));
		}
		
		return copy;
	}
	
}
