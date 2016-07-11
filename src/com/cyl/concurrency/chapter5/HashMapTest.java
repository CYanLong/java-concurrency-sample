package com.cyl.concurrency.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapTest {
	
	static final Map<Integer, String> cache = new HashMap<Integer, String>();
	
	ExecutorService exes = Executors.newCachedThreadPool();
	
	public static void main(String[] args){
		for(int i = 0 ; i < 20 ; i++){
			cache.put(i, "µÚ" + i+"¸ö");
		}
		for(int i = 0 ; i < 10 ; i++){
			new Thread(() -> {
				while(true){					
					cache.get(new Random().nextInt(10));
				}
			}).start();
		}
		
		for(int i = 0 ; i < 10 ; i++){
			new Thread(() -> {
				while(true){
					cache.put(new Random().nextInt(10), "dd");
				}
			}).start();
		}
		
	}
}
