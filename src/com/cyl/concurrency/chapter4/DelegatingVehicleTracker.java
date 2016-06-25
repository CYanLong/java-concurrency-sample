 package com.cyl.concurrency.chapter4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �̰߳�ȫ�Ե�ί��.
 * ������̷߳ǰ�ȫ�������Ϊһ����ʱ,Java������ģʽ���ǳ�����.
 * ����,������Ҫ��ϵ��඼���̰߳�ȫ����,����Ҫ�����ͬ��������?
 * 
 * ʹ�ð�ȫ��������϶����ȷ���Խ�һЩ�̰߳�ȫ����ί�и���Щ����ʵ��.
 * ��ĳЩ���������ȷʵ����Ҫ�����ͬ������.
 * �������Ƕ���Щ��ϵİ�ȫ��ִ�������ķ�ԭ�Ӳ���ʱ,���ܻ���Ҫ�����ͬ��.
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
