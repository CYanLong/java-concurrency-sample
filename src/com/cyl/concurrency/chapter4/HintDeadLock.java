package com.cyl.concurrency.chapter4;
/**
 * 一种更隐蔽的死锁情况:动态的锁顺序死锁.
 * 单纯的从transferMoney()的实现来看并没有死锁问题,
 * 所以线程都是以相同的顺序来获得锁.
 * 
 * 但隐藏的一个问题是,这两个锁是作为参数传进来的,当两个线程按如下方式并发调用时
 * transferMoney(fromAccount, toAccount)
 * transferMoney(toAccount, fromAccount)
 * 这时看似顺序的访问已变成了对 fromAccount和toAccount 的交替访问.
 * 
 * 
 * 针对这种情况,我们需要重新去定义锁的顺序.不是通过锁的形参名来定义.
 * 在这里,我们使用对象的hash值作为参照,先锁定hash值小的锁,
 * 
 * 
 * 或者,这还不是最好的解决方法,当出现hash冲突时还会出现死锁.
 * 如果每一个Account都有一个唯一标识的id,这种是最好的解决方式.
 * @author CYL
 * ConcurrentHashMap
 */
public class HintDeadLock {
	
	void transferMoney(Account fromAccount, Account toAccount, int amount){
		synchronized(fromAccount){
			synchronized(toAccount){
				if(fromAccount.getBalance() < amount){
					return ;
				}
				fromAccount.debit(amount);
				toAccount.credit(amount);
			}
		}
	}
	
	public static void main(String[] args){
		Account fromAccount = new Account(1000);
		Account toAccount = new Account(1000);
		
		new Thread(() -> {
			new HintDeadLock().transferMoney(fromAccount, toAccount, 200);
		}).start();
		
		new Thread(() -> {
			new HintDeadLock().transferMoney(toAccount, fromAccount, 200);
		}).start();
	}
	
	//我们通过先锁定hash值小的对象的方法来实现顺序锁定对象.
	void transferMoney2(Account fromAccount, Account toAccount, int amount){
		//分别得到对象的hash值.
		int toHash = System.identityHashCode(toAccount);
		int fromHash = System.identityHashCode(fromAccount);
		
		if(toHash > fromHash){
			synchronized(fromAccount){
				synchronized(toAccount){
					
				}
			}
		}else if(toHash < fromHash){
			synchronized(toAccount){
				synchronized(fromAccount){
					
				}
			}
		}
	}
}

class Account{
	
	int balance;
	
	public Account(int amount){
		balance = amount;
	}
	int getBalance(){
		return balance;
	}
	void debit(int amount){
		balance -= amount;
	}
	void credit(int amount){
		balance += amount;
	}
	
}
