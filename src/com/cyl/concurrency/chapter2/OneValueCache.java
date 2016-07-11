package com.cyl.concurrency.chapter2;

import java.util.Arrays;
/**
 * 另一种解决线程安全性问题的方式是封装不可变类.
 * 关于不可变类:
 * 	1.不可变类的内部仍然可以保存可变对象.我们可以通过 复制 的手段保证
 *    这些可变数据的不变性.
 *  2.对于使用不可变对象的程序,我们可以将一个维护新状态的新对象替换原来
 *    的那个不可变对象,从而实现状态的更新.
 *  3.如果实现的上面的两点,那么对这个对象的所有非复合操作将不需要额外的
 *    同步保证.不可变对象本身就是线程安全的.
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
