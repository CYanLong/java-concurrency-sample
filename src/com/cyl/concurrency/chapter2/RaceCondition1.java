package com.cyl.concurrency.chapter2;
/**
 * ��̬�������̰߳�ȫ����:Check-Then-Act�ȼ����ִ��.
 * ��������ĺ����ǻ���һ�ֿ����Ѿ���ʱ��Check�����ִ��һЩ����,
 * ������,��Щ������Ȼ���³�������쳣.
 * 
 * ������ʾ������,����ͨ��ά�������con��ʵ�ֵ���ģʽ.
 * ��������Ĳ��Դ�����,1000���̼߳���ͬʱ����getInstance�ᷢ�ֵ���ģʽ���ƻ�.
 * ����ͳ��� if(con == null)��check-and-act��.һ���߳��ڽ���if��ִ��newʱ,con==null
 * �Ѿ�ʧЧ.
 * @author CYL
 */
public class RaceCondition1 {
	
	private static RaceCondition1 con = null;
	
	public static RaceCondition1 getInstance(){
		if(con == null){
			System.out.println("new RaceCondtion1");
			con = new RaceCondition1();
		}
		return con;
	}

	public static void main(String[] args){
		for(int i = 0 ; i < 1000 ; i++){
			new Thread(() -> {
				RaceCondition1.getInstance();
			}).start();
		}
	}
}
