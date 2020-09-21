package com.algo.reverseString;

public class ReverseInt {

	public int reverseInt(int n) {
		float signum = Math.signum(n);
		StringBuilder numString = new StringBuilder(Integer.toString(Math.abs(n)));
		return (int) (Integer.parseInt(numString.reverse().toString())*signum);
	}
	
	public int reverseInt2(int n) {
		int signum = n/Math.abs(n);
		StringBuilder numString = new StringBuilder(Integer.toString(Math.abs(n)));
		return (int) (Integer.parseInt(numString.reverse().toString())*signum);
	}
	
	public int reverseInt3(int num) {
		int reversed = 0;
		while(num != 0) {
			int digit = num % 10;
			reversed = reversed * 10 + digit;
			num /= 10;
		}
		return reversed;
	}
}
