package com.cyl.concurrency.chapter_14;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E>{
	
		
	AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
	
	public void push(E item){
		Node<E> newNode = new Node<E>(item);
		Node<E> oldHead;
		
		do{
			oldHead = top.get();//
			newNode.next = oldHead;
		}while(!top.compareAndSet(oldHead, newNode));
		
	}
	
	public E pop(){
		
		Node<E> head ;
		Node<E> next ;
		E item;
		do{
			head = top.get();
			if(head == null){
				return null;
			}
			item = head.item;
			next = head.next;
		}while(!top.compareAndSet(head, next));
		return item;
	}
	
	
	private static class Node<E>{
		public final E item;
		public Node<E> next;
		
		public Node(E item){
			this.item = item;
		}
	}

}

