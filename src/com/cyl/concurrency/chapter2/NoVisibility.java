package com.cyl.concurrency.chapter2;
/**
 * �ɼ�������:ָ������������.
 * ��һ���̷߳��ʹ����ڴ��е�һ����������ʱ,�����ڱ��߳�
 * ��copyһ���������в���,���,�ٽ���ֵ���»ع����ڴ���.
 * ���ĸ��»� Դ�ڴ� �Ĳ������ܻ�����ָ��������������Ӻ�ִ��.
 * 
 * ����,�������ʾ��,��д�̸߳ı�number��ֵ����������������Դ�ڴ�,
 * ���߳̿��� ������ д�߳�д���ready��ֵ��û�п���number��ֵ.
 * ���������,�ͳ������̰߳�ȫ����.
 * @author CYL
 *
 */
public class NoVisibility {
	
	private static boolean ready = false;
	private static int number = 0;

	public static void main(String[] args){
		//���߳�.
		new Thread(() -> {
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}).start();
		//д�߳�.
		new Thread(() -> {
			number = 10;
			ready = true;
		}).start();
		
	}

}
