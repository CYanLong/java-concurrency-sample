package com.cyl.concurrency.chapter5;

//��ǰ��������ʧ�ܴ��ݸ�������.
//����һ��������ʵ�֣��������� �������н绺���һ���쳣�����
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void put(V v){
		if(isFull()){
			//���쳣
		}
		doPut(v);
	}
	
	public synchronized V take(){
		if(isEmpty()){
			
		}
		return doTake();
	}
}
