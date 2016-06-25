package com.cyl.concurrency.chapter_10;

public class DeadLock1 {
	
	public void transferMemory(Account fromAccount,
					Account toAccount,int amount){
		//以下两部必须为原子操作
		//可能发生死锁，当一个线程从X->Y 而另一个线程从Y->X，还是会发生对相同的两个锁的不同顺序获取操作。
		synchronized(fromAccount){
			synchronized(toAccount){
				if( true/*判断余额*/){
					
				}
				fromAccount.debit(amount);
				toAccount.credit(amount);
			}
		}
	}
}
class Account{
	
	public void debit(int amount){
		
	}
	
	public void credit(int amount){
		
	}
}