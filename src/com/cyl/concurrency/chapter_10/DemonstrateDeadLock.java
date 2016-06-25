package com.cyl.concurrency.chapter_10;

import java.util.Random;

public class DemonstrateDeadLock {

	private static final int NUM_THREADS = 20;
	private static final int NUM_ACCOUNTS = 5;
	private static final int NUM_ITERATORS = 1000000;
	
	public static void main(String[] args){
		final Random rnd = new Random();
		final Account[] accounts = new Account[NUM_ACCOUNTS];
		//初始化5个Account
		for(int i = 0 ; i < accounts.length ; i++){
			accounts[i] = new Account();
		}
		
		for(int i = 0 ; i < NUM_THREADS ; i++){
			new Thread(() -> {
				//每一个Thread都进行1000000次
				for(int j = 0 ; j < NUM_ITERATORS ; j++){
					int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
					int toAcct = rnd.nextInt(NUM_ACCOUNTS);
					
				}           
				
			}).start();
		}
	}
}
