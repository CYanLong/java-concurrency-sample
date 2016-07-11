package com.cyl.concurrency.chapter3;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * ����VehicleTracker,���Ƿ����̰߳�ȫ���������HashMap�Ͷ�MutablePoint��������
 * ��ԭ�Ӳ�����.
 * 
 * ���ǿ���ʹ�ü�����ģʽ��ͬ���������.
 * ��ѭ������ģʽ�Ķ���Ὣ����˵Ķ�������пɱ�״̬����װ����,���ж����Լ���������
 * ������.
 * 
 * Vector��Hashtable������ôʵ�ֵ�.
 * 
 * ���Ǹ�ÿһ������ķ����ж�ʹ����synchronizedͬ��.
 * getLocations��getLocation��ͬ����ӦΪHashMap�ķ��̰߳�ȫ��.
 * �ڴ���(Delegating)ģʽ��,�㽫����������ʹ���̰߳�ȫ���������.getLocations������Ҫͬ��
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
