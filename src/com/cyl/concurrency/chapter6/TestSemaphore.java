package com.cyl.concurrency.chapter6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
	
	public static void main(String[] args){
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final Semaphore semp = new Semaphore(5);
		
		for(int i = 0 ; i < 20 ; i++){
			final int NO = i;
			
			exec.execute(() -> {
				System.out.println("第" + NO + "个线程开始..");
				try{
					semp.acquire();
					System.out.println("Accessing:" + NO);
					//模拟访问过程
					Thread.sleep((long)(Math.random() * 10000));
					//访问完之后
					semp.release();
					System.out.println("release.." + NO);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println("第" + NO + "个线程结束..");
			});
		}
		System.out.println("主线程分配线程任务结束,,,");
		exec.shutdown();
	}
}
