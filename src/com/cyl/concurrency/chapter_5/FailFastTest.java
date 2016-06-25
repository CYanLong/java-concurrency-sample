package com.cyl.concurrency.chapter_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
//CopyOnWriteArrayList
public class FailFastTest {
	
	private static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
	
	
	public static void main(String[] args){
		for(int i = 0 ; i < 20 ; i++){
			list.add(i);
		}
		
		new Thread(() -> {
			Iterator<Integer> iterator = list.iterator();
			while(iterator.hasNext()){
				int i = iterator.next();
				System.out.println("ThreadOne ±éÀú" + i);
				
			}
		}).start();
		
		new Thread(() -> {
			for(int i = 0 ; i < 10 ; i++){
				list.add(i);
			}
		});
	}
}
