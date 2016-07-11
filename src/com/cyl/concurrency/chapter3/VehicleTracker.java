package com.cyl.concurrency.chapter3;

import java.util.HashMap;
import java.util.Map;
/**
 * ������һ������׷������.������,MutablePoint��VehicleTracker��û�н��й��߳�ͬ������
 * ����������ӱ�Ȼ���̲߳���ȫ��.����һ����������Щ�ط����ڲ���ȫ����.(���Ҳ�����HashMap�İ�ȫ����)
 * 
 * 1.����getLocations����locations�����.���Կͻ��˴������getLocations����������
 * �̰߳�ȫ����.������ʵ����һ��ȱ����ǿͻ��˱�����ʾ�����Ե���getLocations��������
 * ��������. (getLocationͬ��)
 * 
 * 2.���������HashMap������̰߳�ȫ��,��ʵ������ӵİ�ȫ����������˿ɱ��Point��.
 * ���ǵ�һ���̵߳���getLocation��getLocationsִ�е�MutablePoint�Ĺ�������,��ִ����
 * x = point.x;��ʱ,��һ��setLocation�̶߳�ͬһ��pointִ��p.x = x; p.y = y
 * ����һ���߳�ִ��ʱ,point.y��ֵ�Ѿ��ı�,��Ȼ�´��������Ķ����x,y��һ��.
 * 
 * �����������̶߳�ͬһ��pointִ��setLocation. x �� y�ķ�ԭ��ִ�б�Ȼ��������ݲ�һ��.
 * 
 * �������Ƿ���.������ӵ��̰߳�ȫ�Գ�����HashMap����Ͷ�MutablePoint�ķ�ԭ�Ӳ���.
 * 
 * @author CYL
 *
 */
public class VehicleTracker {

	private Map<String, MutablePoint> locations ;
	
	public VehicleTracker(Map<String, MutablePoint> loc){
		locations = deepCopy(loc);
	}
		
	public Map<String, MutablePoint> getLocations(){
		return deepCopy(locations);
	}
	
	//���ڲ��ɱ����ķ���,�������´���һ����������.
	public MutablePoint getLocation(String name){
		MutablePoint p = locations.get(name);
		return p == null ? null : new MutablePoint(p);
	}
	
	public void setLocation(String name, int x, int y){
		
		MutablePoint p = locations.get(name);
		if( p != null){
			//����ӦΪԭ�Ӳ���.
			p.x = x;
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			p.y = y;
		}
	}
	
	private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> origin){
		
		Map<String, MutablePoint> result = 
							new HashMap<String, MutablePoint>();
		
		for(String key : origin.keySet()){
			result.put(key, new MutablePoint(origin.get(key)));
		}	
		
		return result;
	}
	
	
	public static void main(String[] args){
		Map<String, MutablePoint> loc = new HashMap<String, MutablePoint>();
		
		loc.put("car", new MutablePoint(3, 4));
		
		VehicleTracker tracker = new VehicleTracker(loc);
		
		for(int i = 0 ; i < 1000 ; i++){
			new Thread(() -> {
				tracker.setLocation("car", 5, 6);
			}).start();
			
			new Thread(() -> {
				MutablePoint p = tracker.getLocation("car");
				System.out.println("car:" + p.x + "," + p.y);
			}).start();
			
			new Thread(() -> {
				tracker.setLocation("car", 3, 4);
			}).start();
		}
	}
		
}
