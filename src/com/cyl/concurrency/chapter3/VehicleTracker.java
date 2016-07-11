package com.cyl.concurrency.chapter3;

import java.util.HashMap;
import java.util.Map;
/**
 * 下面是一个车辆追踪例子.很明显,MutablePoint和VehicleTracker都没有进行过线程同步处理
 * 所以这个例子必然是线程不安全的.我们一起来看看哪些地方存在不安全问题.(暂且不考虑HashMap的安全问题)
 * 
 * 1.由于getLocations返回locations的深拷贝.所以客户端代码调用getLocations方法不存在
 * 线程安全问题.但这种实现有一个缺点就是客户端必须显示周期性调用getLocations方法才能
 * 看到更新. (getLocation同理)
 * 
 * 2.如果不考虑HashMap本身的线程安全性,其实这个例子的安全性问题出在了可变的Point上.
 * 考虑当一个线程调用getLocation或getLocations执行到MutablePoint的构造器中,并执行完
 * x = point.x;这时,另一个setLocation线程对同一个point执行p.x = x; p.y = y
 * 当第一个线程执行时,point.y的值已经改变,必然新创建出来的对象的x,y不一致.
 * 
 * 再如有两个线程对同一个point执行setLocation. x 和 y的非原子执行必然会造成数据不一致.
 * 
 * 所以我们发现.这个例子的线程安全性出在了HashMap本身和对MutablePoint的非原子操作.
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
	
	//对于不可变对象的发布,我们重新创建一个副本返回.
	public MutablePoint getLocation(String name){
		MutablePoint p = locations.get(name);
		return p == null ? null : new MutablePoint(p);
	}
	
	public void setLocation(String name, int x, int y){
		
		MutablePoint p = locations.get(name);
		if( p != null){
			//这里应为原子操作.
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
