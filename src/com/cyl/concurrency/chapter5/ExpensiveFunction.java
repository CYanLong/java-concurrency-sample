package com.cyl.concurrency.chapter5;

import java.math.BigInteger;
//Computable µœ÷¿‡
public class ExpensiveFunction implements Computable<String, BigInteger> {

	@Override
	public BigInteger computable(String args) {
		//do somethinig 
		return new BigInteger(args);
	}
	
}
