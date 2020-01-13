package com.maliha.dpnc.dpnc.prime;

public class PrimeNumberCheckSimple implements PrimeNumberCheck {
	
	@Override
	public boolean isPrime(int n) {
		if (n <= 1) return false; // 0, 1, negative numbers are not prime numbers by definition
		
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

}
