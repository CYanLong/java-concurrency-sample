package com.cyl.concurrency.chapter4;
/**
 * 锁顺序死锁.
 * 两个线程以不同的顺序来获取相同的锁时会发生死锁.
 * 如果按照相同的顺序来请求锁,就不会出现循环的加锁依赖性.
 * @author CYL
 *
 */
public class LeftRightDeadLock {
	
	private final Object left = new Object();
	private final Object right = new Object();
	
	public void rightLeft(){
		synchronized(left){
			synchronized(right){
				
			}
		}
	}
	
	public void leftRight(){
		synchronized(right){
			synchronized(left){
				
			}
		}
	}
}
