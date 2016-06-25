package com.cyl.concurrency.chapter_10;

public class LeftRightDeadLock {
	
	private final Object left = new Object();
	private final Object right = new Object();
	
	public void leftRight(){
		synchronized(left){
			synchronized(right){
			}
		}
	}
	//当两个线程试图以不同的顺序来获取相同的锁.
	public void rightLeft(){
		synchronized(right){
			synchronized(left){
				
			}
		}
	}
	//如果按照相同的顺序来请求锁，那么就不会出现循环的加锁依赖.0
	public void leftRight2(){
		synchronized(left){
			synchronized(right){
				
			}
		}
	}
}
