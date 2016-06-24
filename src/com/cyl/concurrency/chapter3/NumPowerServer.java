package com.cyl.concurrency.chapter3;
/**
 * ÿ����Ҫ��һ�����������ԭ�ӷ�ʽִ��ʱĳ������ʱ,
 * �Ϳ��Կ��Ǵ���һ�����ɱ������������Щ����.
 * 
 * ������һ�µ�����,���������ｫlastNumber��numPower��װΪһ�����ɱ���.
 * ����,���ǵ�Server������û�ж���ͬ��������²���ʹ��OneValueCache.
 * 
 * @author CYL
 *
 */
public class NumPowerServer {
	
	private volatile OneValueCache  s = new OneValueCache(0, null);
	
	public int[] getNumPower(int num){
		
		int[] power = s.getNumPower(num);
		
		if(power == null){
			power = getPower(num);
			s = new OneValueCache(num, power);
		}
		return power;
	}
	
	private int[] getPower(int num){
		return new int[]{num*num, num*num*num };
	}
	
	public static void main(String[] args){
		NumPowerServer server = new NumPowerServer();
		
		for(int i = 0 ; i < 1000 ; i++){
			new Thread(() -> {
				int[] v = server.getNumPower(5);
				System.out.println("5:" + v[0] + "," +v[1]);
			}).start();
			
			new Thread(() -> {
				int[] v = server.getNumPower(6);
				System.out.println("6:" + v[0] + ":" + v[1]);
			}).start();
			
			new Thread(() -> {
				int[] v = server.getNumPower(7);
				System.out.println("7:" + v[0] + ":" + v[1]);
			}).start();
		}
	}
	
}
