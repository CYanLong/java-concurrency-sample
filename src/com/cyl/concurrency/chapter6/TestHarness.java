package com.cyl.concurrency.chapter6;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	
	public long timeTask(int nThreads, final Runnable task) throws InterruptedException{
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);
		
		for(int i = 0 ; i < nThreads ; i++){
			new Thread(() -> {
				try {
					startGate.await();//����ס.��ʹ����߳��Ѿ�����.
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
		endGate.await();//���̵߳������ֹͣ,֪�����е�Run�̶߳�ִ�����Լ���endGate.countDown().
		long end = System.nanoTime();
		return 0;
	}
}
