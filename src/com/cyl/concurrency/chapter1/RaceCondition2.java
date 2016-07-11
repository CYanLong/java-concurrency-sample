package com.cyl.concurrency.chapter1;
/**
 * 第二种竞态条件问题:Read-Modify-Write.读取-修改-写入.
 * 即使是像count++这样一行的代码,在执行过程中也是分为三步原子操作的
 * 分别为读取-修改-写入.
 * 并且修改时要依赖于读取的值.所以,在多线程下会出现会出现在读取完修改前读取的数据
 * 已经失效的情况.
 * 
 * 下面的例子展示了count++的非原子性操作带来的线程问题.这会导致一些自加的值被覆盖掉.
 * 所以实际结果会小于预期结果.(很难重现这样的问题)
 * @author CYL 
 *
 */
public class RaceCondition2 {
	private int count = 0;
	
	public void increase(){
		count++;
	}
	
	public static void main(String[] args) throws InterruptedException{
		RaceCondition2 r2 = new RaceCondition2();
		for(int i = 0 ; i < 100000 ; i++){
			Thread t = new Thread(() -> {
				for(int j = 0 ; j < 20 ; j++)
					r2.increase();
			});
			t.start();
			t.join();
		}
		System.out.println(r2.count);
	}
}
