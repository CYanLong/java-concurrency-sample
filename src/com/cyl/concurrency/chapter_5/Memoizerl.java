package com.cyl.concurrency.chapter_5;

import java.util.HashMap;
import java.util.Map;

//��װ��ͨ��c��������һ�ֻ��湦��.
public class Memoizerl<A, V> implements Computable<A, V>{
	
	private Map<A, V> cache = new HashMap<>();
	
	//����װ��Computable.
	private Computable<A, V> c ;
	
	public Memoizerl(Computable<A, V> c){
		this.c = c;
	}
	
	@Override
	public V computable(A args) {
		V v = cache.get(args);
		
		if(v == null){
			v = c.computable(args);//�̰߳�ȫ,û�й�������.
			cache.put(args, v);//��ʱ��put����һ��ԭ�Ӳ���,���Ի�����̰߳�ȫ����.
			//Map��put��������ƺܶ����⣬
			//������̰߳�ȫ������ǵ������̲߳�����ͬkey��value��ͬ��Entryʱ����ֶ�ʧ����.
		}
		return v;
	}
}
