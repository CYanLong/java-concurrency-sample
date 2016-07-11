package com.cyl.concurrency.chapter2;
/**
 * ���ɱ���ĵ�һ����ע��:�����ά���ڲ��Ŀɱ�״̬?
 * 	����Copy�ķ�����ʵ��.
 * 
 * ����ͨ��һ��������˵�������ɱ������ά����ɱ�״̬ʱ,ֱ�ӷ��ش˿ɱ�״̬
 * ���ƻ����ɱ���.������Ҳ���ǲ��ɱ���.
 * 
 * �ڴ�����,���ֱ�ӷ���int[]�������,�ⲿ�����˶��󽫻�Ӱ�쵽���ɱ�����״̬,
 * ���ǽ�����һЩ�̰߳�ȫ����.
 * 
 * ��ȷ������������д�뻹�Ƿ�����Щ����,������Copyһ��.
 * ��д����,���Ǳ���Copy����һ��.������ʱ,���ǲ�ֱ�ӷ���,����һ�ݸ���.
 * @author CYL
 *
 */
public class ImmutableObject1 {
	
	OneValueCache1 o = new OneValueCache1(5, new int[]{});
	
	private int[] getPowerServer(int num){
		int[] value = o.getNumPower(num);
		if(value == null){
			
			value = getPower(num);
			o = new OneValueCache1(num, value);
		}
		return value;
	}

	private int[] getPower(int num){
		return new int[]{num*num, num*num*num};
	}
	
	public static void main(String[] args){
		ImmutableObject1 im = new ImmutableObject1();
		for(int i = 0 ; i < 100 ; i++){
			new Thread(() -> {
				int[] v = im.getPowerServer(5);
				System.out.println("5:" + v[0] + "," + v[1]);
				v[0] += 1;
				v[1] += 2;
			}).start();
			
			new Thread(() -> {
				int[] v = im.getPowerServer(6);
				System.out.println("6:" + v[0] + "," + v[1]);
				v[0] += 1;
				v[1] += 2;
			}).start();
		}
	}
	
}
class OneValueCache1{
	
	private final int lastNumber;
	private final int[] numPower;
	
	public OneValueCache1(int lastNumber, int[] numPower){
		this.lastNumber = lastNumber;
		this.numPower = numPower;
	}
	
	public int[] getNumPower(int num){
		if(num == lastNumber){
			return numPower;
		}
		return null;
	}
}
