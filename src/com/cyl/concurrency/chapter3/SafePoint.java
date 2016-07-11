package com.cyl.concurrency.chapter3;
/**
 * 
 * @author CYL
 *
 */
public class SafePoint {
	
	private int x, y;
	
	public SafePoint(int[] a){
		this(a[0], a[1]);
	}
	
	public SafePoint(SafePoint point){
		this(point.get());
	}
	
	public SafePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public synchronized int[] get(){
		return new int[]{x, y};
	}
	
	public synchronized void set(int x, int y){
		this.x = x;
		this.y = y;
	}	
}
