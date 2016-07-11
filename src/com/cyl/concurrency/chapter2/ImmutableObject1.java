package com.cyl.concurrency.chapter2;
/**
 * 不可变类的第一个关注点:该如何维护内部的可变状态?
 * 	采用Copy的方法来实现.
 * 
 * 下面通过一个返例来说明当不可变对象中维护这可变状态时,直接返回此可变状态
 * 会破坏不可变类.这种类也不是不可变类.
 * 
 * 在此例中,如果直接返回int[]数组对象,外部操作此对象将会影响到不可变对象的状态,
 * 这是将出现一些线程安全问题.
 * 
 * 正确的做法是无论写入还是返回这些对象,都首先Copy一份.
 * 当写入是,我们保存Copy的那一份.当返回时,我们不直接返回,返回一份副本.
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
