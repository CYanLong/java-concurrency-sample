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
	//�������߳���ͼ�Բ�ͬ��˳������ȡ��ͬ����.
	public void rightLeft(){
		synchronized(right){
			synchronized(left){
				
			}
		}
	}
	//���������ͬ��˳��������������ô�Ͳ������ѭ���ļ�������.0
	public void leftRight2(){
		synchronized(left){
			synchronized(right){
				
			}
		}
	}
}
