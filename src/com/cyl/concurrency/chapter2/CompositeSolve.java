package com.cyl.concurrency.chapter2;

public class CompositeSolve {
	
	private int lastNumber;
	private int lastHalf;
	private Object lock = new Object();
	public int getHalfServer(int num){
		int value  = 0;
		synchronized(lock){
			if(num == lastNumber){
				value = lastHalf;
			}
		} 
		if(value == 0){
			//��num��value�Ĳ��������̷߳�յ�.�����ڰ�ȫ������.
			//����ʹ������һ��ʹ��getHalf()�������Բ���ִ��.
			//����getHalfΪ�������������¿�����������߲�����.
			value = getHalf(num);
			synchronized(lock){
				lastNumber = num;
				lastHalf = value;
			}
		}
		return value;
		
		
		/*��һ��ͬ����ʽ,�������Լ���Ϊ1,�ܵ�;
		 * synchronized(lock){
			if(num == lastNumber){
				return lastNumber;
			}
			lastNumber = num;
			lastHalf = getHalf(num);
			return lastHalf;
		}*/
	}
	
	public int getHalf(int num){
		return num/2;
	}
	
	public static void main(String[] args){
		CompositeSolve c = new CompositeSolve();
		for(int i = 0 ; i < 100 ; i++){
			new Thread(() -> {
				int value = c.getHalfServer(10);
				System.out.println("num:10" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = c.getHalfServer(20);
				System.out.println("num:20" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = c.getHalfServer(30);
				System.out.println("num:30" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = c.getHalfServer(40);
				System.out.println("num:40" + "value:" + value);
			}).start();
		}
	}	
}
