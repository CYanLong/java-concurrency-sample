package com.cyl.concurrency.chapter2;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * ���һ����������Ļ�������ͨ��,���ǿ���ͨ��ʹ��Atomic��
 * ��ʵ��ͬ��.
 * 
 * ����,����ʹ��AtomicInteger������int,AtomicInteger
 * ��incrementAndGet()�Ȳ����ڵײ�ȷ����count++��ԭ����.
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
