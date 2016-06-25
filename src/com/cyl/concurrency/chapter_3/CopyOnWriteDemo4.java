package com.cyl.concurrency.chapter_3;

import java.util.ArrayList;

public class CopyOnWriteDemo4 {
	
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args){
		
		for(int i = 0 ; i < 10 ; i++){
			list.add(i);
		}
		
		new Thread(() -> {
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
			list.remove(4);
			System.out.println("remove " + 4);
		}).start();
	}
}
