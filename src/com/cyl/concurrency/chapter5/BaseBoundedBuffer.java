package com.cyl.concurrency.chapter5;

public abstract class BaseBoundedBuffer<V> {
	
	private final V[] buf;
	private int tail;
	private int head;
	private int count;
	
	@SuppressWarnings("unchecked")
	protected BaseBoundedBuffer(int capacity){
		this.buf = (V[])new Object[capacity];
	}
	//此方法成功执行的前提条件是buf不为空.
	//这里并没有给出先验条件的逻辑控制.只是一个可用实现的基类
//	所以将其修饰为protected,发布后外部代码不可用.
	protected synchronized final void doPut(V v){
		buf[tail] = v;
		if(++tail == buf.length)
			tail = 0;
		++count;
	}
	//同上.
	protected synchronized final V doTake(){
		V v = buf[head];
		buf[head] = null;
		if(++head == buf.length){
			head = 0;
		}
		return v;
	}
	
	protected synchronized final boolean isFull(){
		return count == buf.length;
	}
	
	public synchronized final boolean isEmpty(){
		return count == 0;
	}
}
