package com.cyl.concurrency.chapter5;

//����A ͨ��������� V
public interface Computable<A, V> {
	
	V computable(A args);
}
