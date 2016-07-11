package com.cyl.concurrency.chapter1;
/**
 * �ڶ��־�̬��������:Read-Modify-Write.��ȡ-�޸�-д��.
 * ��ʹ����count++����һ�еĴ���,��ִ�й�����Ҳ�Ƿ�Ϊ����ԭ�Ӳ�����
 * �ֱ�Ϊ��ȡ-�޸�-д��.
 * �����޸�ʱҪ�����ڶ�ȡ��ֵ.����,�ڶ��߳��»���ֻ�����ڶ�ȡ���޸�ǰ��ȡ������
 * �Ѿ�ʧЧ�����.
 * 
 * ���������չʾ��count++�ķ�ԭ���Բ����������߳�����.��ᵼ��һЩ�Լӵ�ֵ�����ǵ�.
 * ����ʵ�ʽ����С��Ԥ�ڽ��.(������������������)
 * @author CYL 
 *
 */
public class RaceCondition2 {
	private int count = 0;
	
	public void increase(){
		count++;
	}
	
	public static void main(String[] args) throws InterruptedException{
		RaceCondition2 r2 = new RaceCondition2();
		for(int i = 0 ; i < 100000 ; i++){
			Thread t = new Thread(() -> {
				for(int j = 0 ; j < 20 ; j++)
					r2.increase();
			});
			t.start();
			t.join();
		}
		System.out.println(r2.count);
	}
}
