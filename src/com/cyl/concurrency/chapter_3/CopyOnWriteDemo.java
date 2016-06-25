package com.cyl.concurrency.chapter_3;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {
	
	public static void main(String[] args){
		//ArrayList ConcurrentHashMap
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++){			
			list.add(i);
		}
		
		new Thread(() -> {
			Iterator<Integer> iterator = list.iterator();
			while(iterator.hasNext()){
				int v = iterator.next();
				System.out.println("µü´ú" + v);
				try {
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(() -> {
			for(int i = 10 ; i < 50 ; i++){
				list.add(i);
				System.out.println("Ìí¼Ó.." + i);
			}
		}).start();
		
		
	}
	
}
