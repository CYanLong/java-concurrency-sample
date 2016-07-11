package com.cyl.concurrency.chapter6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySemaphore {
	
	private int signal = 1;
	
	
	public void acquire() throws InterruptedException{
		if(signal <= 0)
			this.wait();
		signal--;		
	}
	
	public void release(){
		this.notify();
		signal++;
	}
	
	public static void main(String[] args){
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		MySemaphore sem = new MySemaphore();
		
		for(int i = 0 ; i < 4 ; i++){
			final int I = i;
			exec.execute(() -> {
				try {
					System.out.println("��" + I + "���߳̿�ʼִ��");
					sem.acquire();			
					System.out.println("��" + I + "���̵߳õ�permit");
					Thread.sleep(1000);
					sem.release();
					System.out.println("��" + I +"���߳��ͷ�permit");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
}
