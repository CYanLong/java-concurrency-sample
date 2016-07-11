package com.cyl.concurrency.chapter2;
/**
 * 可见性问题:指令重排序问题.
 * 当一个线程访问共享内存中的一个共享数据时,会先在本线程
 * 内copy一个副本进行操作,最后,再将新值更新回共享内存中.
 * 最后的更新会 源内存 的操作可能会由于指令重排序问题而延后执行.
 * 
 * 所以,看下面的示例,当写线程改变number的值并不会立即更新与源内存,
 * 读线程可能 看到了 写线程写入的ready的值而没有看到number的值.
 * 这种情况下,就出现了线程安全问题.
 * @author CYL
 *
 */
public class NoVisibility {
	
	private static boolean ready = false;
	private static int number = 0;

	public static void main(String[] args){
		//读线程.
		new Thread(() -> {
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}).start();
		//写线程.
		new Thread(() -> {
			number = 10;
			ready = true;
		}).start();
		
	}

}
