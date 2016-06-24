package com.cyl.concurrency.chapter2;

/**
 * 针对Check-Then-Act线程安全性问题,我们使用同步代码块来
 * 确保Check-Then-Act的原子性.
 * 
 * 如下getInstance方法,我们使用object锁来控制Check(rc==null)-Then-Act(new Ract..)
 * 的原子性.
 * 
 * @author CYL
 *
 */
public class RaceCondition1Solve{
	private static Object o = new Object();//充当锁.
	private static RaceCondition1Solve rc = null;
	
	public static RaceCondition1Solve getInstance(){
		if(rc != null)
			return rc;
		synchronized(o){//对象为空时,不能用作内置锁.
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
