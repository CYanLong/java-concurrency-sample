package com.cyl.concurrency.chapter2;
/**
 * 竞态条件类线程安全问题:Check-Then-Act先检查再执行.
 * 出现问题的核心是基于一种可能已经过时的Check结果来执行一些操作,
 * 很明显,这些操作必然导致程序出现异常.
 * 
 * 如下所示的例子,我们通过维护类变量con来实现单例模式.
 * 但在下面的测试代码中,1000个线程几乎同时调用getInstance会发现单例模式被破坏.
 * 问题就出在 if(con == null)的check-and-act上.一个线程在进入if到执行new时,con==null
 * 已经失效.
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
