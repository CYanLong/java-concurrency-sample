package com.cyl.concurrency.chapter_14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//����������ν�ʷֿ����ŵ������ȴ��̼߳���,Conditionʹ����������㵥��֪ͨ������.
//����������£�signal������Ч��������ļ�������ÿ�λ�������з������������л������������.  
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
