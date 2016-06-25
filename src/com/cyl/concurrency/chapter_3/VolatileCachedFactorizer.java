package com.cyl.concurrency.chapter_3;

import java.math.BigInteger;

public class VolatileCachedFactorizer {
	//不可变类.
	private volatile OneValueCache cache = new OneValueCache(null, null);
	
	public void service(){
		BigInteger i = extractFromRequest();//不线程共享
		BigInteger[] factors = cache.getFactors(i);//
		if(factors == null){
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		response(factors);
	}
	
	private void response(BigInteger[] factors) {
		// TODO Auto-generated method stub
		
	}

	private BigInteger[] factor(BigInteger i) {
		// TODO Auto-generated method stub
		return null;
	}

	private BigInteger extractFromRequest() {
		// TODO Auto-generated method stub
		return null;
	}
}
