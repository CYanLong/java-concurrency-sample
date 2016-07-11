package com.cyl.concurrency.chapter5;

import java.util.HashMap;
import java.util.Map;
//�����ֻ�ṩһ�ֻ��棬����ʵ�־���ļ���.
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
