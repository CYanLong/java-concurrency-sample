package com.cyl.concurrency.chapter5;

import java.util.HashMap;
import java.util.Map;
//这个类只提供一种缓存，并不实现具体的计算.
public class Memoizer1<A, K> implements Computable<A, K>{

	private final Map<A, K> cache = new HashMap<A, K>();
	
	private final Computable<A, K> c;
	
	public Memoizer1(Computable<A, K> c){
		this.c = c;
	}
	
	@Override
	public K computable(A args) {
		K value;
		if((value = cache.get(args)) == null){			
			value = c.computable(args);
			cache.put(args, value);
		}
		return value;
	}
}
