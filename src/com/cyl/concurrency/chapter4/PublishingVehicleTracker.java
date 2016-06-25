package com.cyl.concurrency.chapter4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 这是实现线程安全的第三种方式.
 * 我们使用synchronized将Point封装成一个可变但线程安全的类.
 * 这不同其他两种方式:将Point封装成不可变类并安全发布或发布可变Point的快照版本.
 * 这种实现方式没有任何冗余的内存开销.其他两种方式都需要额外创建一些"中间"对象.
 * 维护不变类需要不断重新创建新的对象来更新状态数据
 * 维护对可变类的访问都需要使用复制版本.
 * 
 * 
 * @author CYL
 *
 */
public class PublishingVehicleTracker {
	
	private final Map<String, SafePoint> locations;
	
	public PublishingVehicleTracker(Map<String, SafePoint> loc){
		locations = new ConcurrentHashMap<String, SafePoint>(loc);
	}
	
	public Map<String, SafePoint> getLocations(){
		return locations;
	}
	
	public SafePoint getLocation(String name){
		return locations.get(name);
	}
	
	public void setLocation(String name, int x, int y){
		SafePoint point = locations.get(name);
		point.set(x, y);
	}
	
}
