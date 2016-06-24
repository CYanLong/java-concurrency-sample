package com.cyl.concurrency.chapter2;

/**
 * ���Check-Then-Act�̰߳�ȫ������,����ʹ��ͬ���������
 * ȷ��Check-Then-Act��ԭ����.
 * 
 * ����getInstance����,����ʹ��object��������Check(rc==null)-Then-Act(new Ract..)
 * ��ԭ����.
 * 
 * @author CYL
 *
 */
public class RaceCondition1Solve{
	private static Object o = new Object();//�䵱��.
	private static RaceCondition1Solve rc = null;
	
	public static RaceCondition1Solve getInstance(){
		if(rc != null)
			return rc;
		synchronized(o){//����Ϊ��ʱ,��������������.
			if(rc == null){
				System.out.println("new object");
				rc = new RaceCondition1Solve();
			}
		}
		return rc;
	}
	
	public static void main(String[] args){
		for(int i = 0 ; i < 10000 ; i++){
			new Thread(() -> {
				RaceCondition1Solve.getInstance();
			}).start();
		}
	}
}
