package com.cyl.concurrency.chapter4;
/**
 * ��˳������.
 * �����߳��Բ�ͬ��˳������ȡ��ͬ����ʱ�ᷢ������.
 * ���������ͬ��˳����������,�Ͳ������ѭ���ļ���������.
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
