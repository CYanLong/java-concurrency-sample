package com.cyl.concurrency.chapter4;

public class ImmutablePoint {
	
	private final int x, y;
	
	public ImmutablePoint(ImmutablePoint point){
		x = point.x;
		y = point.y;
	}
	
	public ImmutablePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}
