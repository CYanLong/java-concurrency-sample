package com.cyl.concurrency.chapter2;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 针对一个共享变量的基本操作通过,我们可以通过使用Atomic类
 * 来实现同步.
 * 
 * 下面,我们使用AtomicInteger来代替int,AtomicInteger
 * 的incrementAndGet()等操作在底层确保了count++的原子性.
 * 
 * @author CYL
 *
 */
public class RaceCondition2Solve {
	
	private AtomicInteger count = new AtomicInteger(0);
	
	public void increase(){
		count.incrementAndGet();
	}
	
	public static void main(String[] args) throws InterruptedException{
		RaceCondition2Solve r2 = new RaceCondition2Solve();
		for(int i = 0 ; i < 1000 ; i++){
			Thread t = new Thread(() -> {
				r2.increase();
			});
			t.start();
			t.join();
		}
		System.out.println(r2.count);
	}
}
