package com.cyl.concurrency.chapter1;

/**
 * 涉及两个及两个以上的共享状态的复合操作时的线程安全问题.
 * 
 * 下面的例子涉及二处线程安全问题:
 * 	1.if(num == lastNumber)...处会有Check-Then-Act竞态条件问题.
 * 	一个线程进入if条件执行reture之前,另一个线程可能更新了lastNumber和lastHalf.这时,lastNumber对于上一个线程来说
 *  已经失效了.
 *  
 *  2.lastNumber = num; lastHalf = getHalf(num); 包括return lastHalf;
 *  	这三步  复合操作可能会由于线程的交替执行而出现异常.
 *  	
 * @author CYL
 */
public class CompositeOperation {

	private int lastNumber;
	
	private int lastHalf;
	
	public int getHalfServer(int num){
		if(num == lastNumber){
			return lastHalf;
		}
		lastNumber = num;
		lastHalf = getHalf(num);
		return lastHalf;
	}
	
	private int getHalf(int num){
		//注意,虽然num/2不是原子操作,但由于num是线程封闭的,所以不会出现线程安全问题.
		return num/2;
	}
	
	public static void main(String[] args){
		CompositeOperation co = new CompositeOperation();	
		for(int i = 0 ; i < 10 ; i++){
			new Thread(() -> {
				int value = co.getHalfServer(10);
				System.out.println("num:" + 10 + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(20);
				System.out.println("num:20" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(30);
				System.out.println("num:20" + "value:" + value);
			}).start();
			new Thread(() -> {
				int value = co.getHalfServer(40);
				System.out.println("num:20" + "value:" + 40);
			}).start();
		}
	}
}
