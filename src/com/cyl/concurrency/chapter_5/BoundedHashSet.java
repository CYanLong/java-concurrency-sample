package com.cyl.concurrency.chapter_5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
	
	private final Set<T> set;
	private final Semaphore sem;
	
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.sem = new Semaphore(bound);
	}
	

	public boolean add(T o) throws InterruptedException{
		sem.acquire();
		boolean wasAdded = false;
		
		wasAdded = set.add(o);
		
		if(wasAdded == false){
			sem.release();
		}
		return wasAdded;
	}
	
	public boolean remove(Object o){
		boolean wasRemoved = set.remove(o);
		if(wasRemoved)
			sem.release();
		return wasRemoved;
	}
}
