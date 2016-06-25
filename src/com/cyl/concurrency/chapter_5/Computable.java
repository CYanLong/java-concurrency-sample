package com.cyl.concurrency.chapter_5;

//传入A 通过计算输出 V
public interface Computable<A, V> {
	
	V computable(A args);
}
