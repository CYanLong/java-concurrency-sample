package com.cyl.concurrency.chapter_3;

import java.math.BigInteger;
import java.util.Arrays;

public class OneValueCache {
	
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public OneValueCache(BigInteger lastNumber, BigInteger[] factors){
		this.lastNumber = lastNumber;
		this.lastFactors = Arrays.copyOf(factors, factors.length);
	}
	
	public BigInteger[] getFactors(BigInteger i ){
		if(lastNumber == null || !lastNumber.equals(i)){
			return null;
		}else{
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}
