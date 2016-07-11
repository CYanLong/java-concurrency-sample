package com.cyl.concurrency.chapter3;

public class MutablePoint {
	public int x, y;
	
	public MutablePoint(){
		x = 0; y = 0;
	}
	
	public MutablePoint(MutablePoint point){
		//这里的point可能是共享变量.所以,这里应该为原子操作.
		x = point.x;
		y = point.y;
	}
	
	public MutablePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
}
