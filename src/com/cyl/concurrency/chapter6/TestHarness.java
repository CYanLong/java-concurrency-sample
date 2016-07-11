package com.cyl.concurrency.chapter6;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	public long timeTask(int nThreads, final Runnable task) throws InterruptedException{
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0 ; i < nThreads ; i++){
			new Thread(() -> {
				try {
					startGate.await();//先锁住.即使这个线程已经开启.
					try{
						task.run(); 
					}finally{
						endGate.countDown(); 
					}
				} catch (Exception e) {
				
				}
			}).start();
		}
		
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();//主线程到这里会停止,知道所有的Run线程都执行完自己的endGate.countDown().
		long end = System.nanoTime();
		return 0;
	}
}
