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
	//�˷����ɹ�ִ�е�ǰ��������buf��Ϊ��.
	//���ﲢû�и��������������߼�����.ֻ��һ������ʵ�ֵĻ���
//	���Խ�������Ϊprotected,�������ⲿ���벻����.
	protected synchronized final void doPut(V v){
		buf[tail] = v;
		if(++tail == buf.length)
			tail = 0;
		++count;
	}
	//ͬ��.
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
