package com.cyl.concurrency.chapter_10;

public class DeadLock2 {
	
	public void transferMemory(Account fromAccount, Account toAccount, int amount){
		
		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);
		Object tieLock = new Object();
		
		if(fromHash < toHash){
			synchronized(fromAccount){
				synchronized(toAccount){
					 //transfer();
				}
			}
			
		}else if(fromHash > toHash){
			synchronized(fromAccount){
				synchronized(toAccount){
					//transfer();
				}
			}
		}else{
			synchronized(tieLock){
				synchronized(fromAccount){
					synchronized(toAccount){
						//transfer();
					}
				}
			}
		}
	}
}