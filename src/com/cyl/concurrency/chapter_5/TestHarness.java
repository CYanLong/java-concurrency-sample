package com.cyl.concurrency.chapter_5;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	public long timeTask(int nThreads, final Runnable task){
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0 ; i < nThreads ; i++){
			new Thread(() -> {
				try {
					startGate.await();
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
		long end = System.nanoTime();

		return 0;
	}
}
