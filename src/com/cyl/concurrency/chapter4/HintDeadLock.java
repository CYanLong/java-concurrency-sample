package com.cyl.concurrency.chapter4;
/**
 * һ�ָ����ε��������:��̬����˳������.
 * �����Ĵ�transferMoney()��ʵ��������û����������,
 * �����̶߳�������ͬ��˳���������.
 * 
 * �����ص�һ��������,������������Ϊ������������,�������̰߳����·�ʽ��������ʱ
 * transferMoney(fromAccount, toAccount)
 * transferMoney(toAccount, fromAccount)
 * ��ʱ����˳��ķ����ѱ���˶� fromAccount��toAccount �Ľ������.
 * 
 * 
 * ����������,������Ҫ����ȥ��������˳��.����ͨ�������β���������.
 * ������,����ʹ�ö����hashֵ��Ϊ����,������hashֵС����,
 * 
 * 
 * ����,�⻹������õĽ������,������hash��ͻʱ�����������.
 * ���ÿһ��Account����һ��Ψһ��ʶ��id,��������õĽ����ʽ.
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
	
	//����ͨ��������hashֵС�Ķ���ķ�����ʵ��˳����������.
	void transferMoney2(Account fromAccount, Account toAccount, int amount){
		//�ֱ�õ������hashֵ.
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
