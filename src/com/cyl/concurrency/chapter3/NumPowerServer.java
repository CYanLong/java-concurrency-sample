package com.cyl.concurrency.chapter3;
/**
 * 每当需要对一组相关数据以原子方式执行时某个操作时,
 * 就可以考虑创建一个不可变的类来包含这些数据.
 * 
 * 接着上一章的例子,我们在这里将lastNumber和numPower封装为一个不可变类.
 * 这样,我们的Server可以在没有额外同步的情况下并发使用OneValueCache.
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
