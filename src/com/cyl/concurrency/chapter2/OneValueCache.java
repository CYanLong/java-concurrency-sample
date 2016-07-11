package com.cyl.concurrency.chapter2;

import java.util.Arrays;
/**
 * ��һ�ֽ���̰߳�ȫ������ķ�ʽ�Ƿ�װ���ɱ���.
 * ���ڲ��ɱ���:
 * 	1.���ɱ�����ڲ���Ȼ���Ա���ɱ����.���ǿ���ͨ�� ���� ���ֶα�֤
 *    ��Щ�ɱ����ݵĲ�����.
 *  2.����ʹ�ò��ɱ����ĳ���,���ǿ��Խ�һ��ά����״̬���¶����滻ԭ��
 *    ���Ǹ����ɱ����,�Ӷ�ʵ��״̬�ĸ���.
 *  3.���ʵ�ֵ����������,��ô�������������зǸ��ϲ���������Ҫ�����
 *    ͬ����֤.���ɱ����������̰߳�ȫ��.
 *    
 * @author CYL
 *
 */
public class OneValueCache {
	
	private final int lastNumber;
	private final int[] numPower;
	
	public OneValueCache(int num, int[] numPower){
		this.lastNumber = num;
		this.numPower = Arrays.copyOf(numPower, numPower.length);
	}
	
	public int[] getNumPower(int num){
		if(lastNumber != num){
			return null;
		}
		return Arrays.copyOf(numPower, numPower.length);
	}
	
	public static void main(String[] args){
		
	}
}
