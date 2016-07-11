package com.cyl.concurrency.chapter3;

public class MutablePoint {
	public int x, y;
	
	public MutablePoint(){
		x = 0; y = 0;
	}
	
	public MutablePoint(MutablePoint point){
		//�����point�����ǹ������.����,����Ӧ��Ϊԭ�Ӳ���.
		x = point.x;
		y = point.y;
	}
	
	public MutablePoint(int x, int y){
		this.x = x;
		this.y = y;
	}
}
