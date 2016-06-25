package com.cyl.concurrency.chapter_3;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo2 {
	
	static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
	
	public static void main(String[] args){
		
		for(int i = 0 ; i < 10 ; i++){
			list.add(i);
		}
		
		//ÆÕÍ¨µÄ±éÀú
		new Thread(() -> {
			int len = list.size();
			for(int i = 0 ; i < list.size() ; i++){
				int v = list.get(i);
				System.out.println("get" + v);
				if( i == 3){
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		
		new Thread(() -> {
			list.remove(5);
			System.out.println("remove 5" );
		}).start();
	}
}
