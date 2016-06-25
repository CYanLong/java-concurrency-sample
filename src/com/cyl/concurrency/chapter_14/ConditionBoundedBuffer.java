package com.cyl.concurrency.chapter_14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//将两个条件谓词分开并放到两个等待线程集中,Condition使其更容易满足单次通知的需求.
//在这种情况下，signal将更高效，它极大的减少了在每次缓存操作中发生的上下文切换和锁请求次数.  
public class ConditionBoundedBuffer<T> extends BaseBoundedBuffer<T>{
	
	protected final Lock lock = new ReentrantLock();
	
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	private int BUFFER_SIZE;
	@SuppressWarnings("unchecked")
	private final T[] items = (T[])new Object[BUFFER_SIZE];
	
	public ConditionBoundedBuffer(int size){
		super(size);
	}
	
	public void put(T t) throws InterruptedException{
		lock.lock();
		while(isFull()){
			notFull.await();
		}
		
		doPut(t);
		notFull.signal();
		lock.unlock();
	}
	
	public T get() throws InterruptedException{
		lock.lock();
		while(isEmpty()){
			notEmpty.await();
		}
		T t = doTake();
		notEmpty.signal();
		lock.unlock();
		return t;
	}
}	
