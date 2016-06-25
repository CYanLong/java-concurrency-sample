package com.cyl.concurrency.chapter_14;
//通过轮询和休眠来实现简单的阻塞.
//这种实现将先验条件进行了管理
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	
	public void put(V v) throws InterruptedException{
		while(true){
			synchronized(this){
				if(!isFull()){
					doPut(v);
					return ;
				}
			}
			Thread.sleep(1000);//通过轮询和休眠来实现简单的阻塞.
		}
	}
}
