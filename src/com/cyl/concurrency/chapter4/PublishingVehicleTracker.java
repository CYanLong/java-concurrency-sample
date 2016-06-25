package com.cyl.concurrency.chapter4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * ����ʵ���̰߳�ȫ�ĵ����ַ�ʽ.
 * ����ʹ��synchronized��Point��װ��һ���ɱ䵫�̰߳�ȫ����.
 * �ⲻͬ�������ַ�ʽ:��Point��װ�ɲ��ɱ��ಢ��ȫ�����򷢲��ɱ�Point�Ŀ��հ汾.
 * ����ʵ�ַ�ʽû���κ�������ڴ濪��.�������ַ�ʽ����Ҫ���ⴴ��һЩ"�м�"����.
 * ά����������Ҫ�������´����µĶ���������״̬����
 * ά���Կɱ���ķ��ʶ���Ҫʹ�ø��ư汾.
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
