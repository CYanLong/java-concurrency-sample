package com.cyl.concurrency.chapter1;

/**
 * �漰�������������ϵĹ���״̬�ĸ��ϲ���ʱ���̰߳�ȫ����.
 * 
 * ����������漰�����̰߳�ȫ����:
 * 	1.if(num == lastNumber)...������Check-Then-Act��̬��������.
 * 	һ���߳̽���if����ִ��reture֮ǰ,��һ���߳̿��ܸ�����lastNumber��lastHalf.��ʱ,lastNumber������һ���߳���˵
 *  �Ѿ�ʧЧ��.
 *  
 *  2.lastNumber = num; lastHalf = getHalf(num); ����return lastHalf;
 *  	������  ���ϲ������ܻ������̵߳Ľ���ִ�ж������쳣.
 *  	
 * @author CYL
 */
public class CompositeOperation {

	private int lastNumber;
	
	private int lastHalf;
	
	public int getHalfServer(int num){
		if(num == lastNumber){
			return lastHalf;
		}
		lastNumber = num;
		lastHalf = getHalf(num);
		return lastHalf;
	}
	
	private int getHalf(int num){
		//ע��,��Ȼnum/2����ԭ�Ӳ���,������num���̷߳�յ�,���Բ�������̰߳�ȫ����.
		return num/2;
	}
	
	public static void main(String[] args){
		CompositeOperation co = new CompositeOperation();	
		for(int i = 0 ; i < 10 ; i++){
			new Thread(() -> {
				int value = co.getHalfServer(10);
				System.out.println("num:" + 10 + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(20);
				System.out.println("num:20" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(30);
				System.out.println("num:20" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(40);
				System.out.println("num:20" + "value:" + 40);
			}).start();
		}
	}
}
