package com.cyl.concurrency.chapter_10;

public class DeadLock1 {
	
	public void transferMemory(Account fromAccount,
					Account toAccount,int amount){
		//������������Ϊԭ�Ӳ���
		//���ܷ�����������һ���̴߳�X->Y ����һ���̴߳�Y->X�����ǻᷢ������ͬ���������Ĳ�ͬ˳���ȡ������
		synchronized(fromAccount){
			synchronized(toAccount){
				if( true/*�ж����*/){
					
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