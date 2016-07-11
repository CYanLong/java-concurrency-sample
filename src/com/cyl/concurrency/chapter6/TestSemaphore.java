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
				System.out.println("��" + NO + "���߳̿�ʼ..");
				try{
					semp.acquire();
					System.out.println("Accessing:" + NO);
					//ģ����ʹ���
					Thread.sleep((long)(Math.random() * 10000));
					//������֮��
					semp.release();
					System.out.println("release.." + NO);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				System.out.println("��" + NO + "���߳̽���..");
			});
		}
		System.out.println("���̷߳����߳��������,,,");
		exec.shutdown();
	}
}
