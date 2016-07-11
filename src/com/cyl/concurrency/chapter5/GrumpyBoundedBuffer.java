package com.cyl.concurrency.chapter5;

//将前提条件的失败传递给调用者.
//这是一个最糟糕的实现，缓存已满 并不是有界缓存的一个异常情况。
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v){
		if(isFull()){
			//抛异常
		}
		doPut(v);
	}
	
	public synchronized V take(){
		if(isEmpty()){
			
		}
		return doTake();
	}
}
