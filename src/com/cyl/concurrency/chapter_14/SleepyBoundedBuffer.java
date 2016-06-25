package com.cyl.concurrency.chapter_14;
//ͨ����ѯ��������ʵ�ּ򵥵�����.
//����ʵ�ֽ��������������˹���
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
			Thread.sleep(1000);//ͨ����ѯ��������ʵ�ּ򵥵�����.
		}
	}
}
