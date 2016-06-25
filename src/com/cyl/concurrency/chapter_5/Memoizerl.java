package com.cyl.concurrency.chapter_5;

import java.util.HashMap;
import java.util.Map;

//包装普通的c，增加了一种缓存功能.
public class Memoizerl<A, V> implements Computable<A, V>{
	
	private Map<A, V> cache = new HashMap<>();
	
	//被包装的Computable.
	private Computable<A, V> c ;
	
	public Memoizerl(Computable<A, V> c){
		this.c = c;
	}
	
	@Override
	public V computable(A args) {
		V v = cache.get(args);
		
		if(v == null){
			v = c.computable(args);//线程安全,没有共享数据.
			cache.put(args, v);//此时的put并非一个原子操作,所以会存在线程安全问题.
			//Map的put操作会设计很多问题，
			//最常见的线程安全问题会是当两个线程插入相同key而value不同的Entry时会出现丢失问题.
		}
		return v;
	}
}
