//package com.cyl.concurrency.chapter_5;
//
//public class MySemaphoreDemo {
//	
//	public static void main(String[] args){
//		MySemaphore sem = new MySemaphore();
//		
//		SendingThread sender = new SendingThread(sem);
//		ReceivingThread receiver = new ReceivingThread(sem);
//		
//		receiver.start();
//		sender.start();
//	}
//}
//
//class SendingThread extends Thread{
//	
//	MySemaphore sem = null;
//	
//	public SendingThread(MySemaphore sem){
//		this.sem = sem;
//	}
//	
//	public void run(){
//		while(true){
//			this.sem.take();
//		}
//	}
//}
//
//class ReceivingThread extends Thread{
//	MySemaphore sem = null;
//	
//	public ReceivingThread(MySemaphore sem){
//		this.sem = sem;
//	}
//	public void run(){
//		try {
//			while(true){
//				this.sem.release();
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}