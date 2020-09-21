package com.algo.reverseString;

public class Palindrom {
	
	public boolean palindrom(String str) {
		for(int i=0;i<str.length()/2;i++) {
			if(str.charAt(i) != str.charAt(str.length()-1-i)) {
				return false;
			}else {
				continue;
			}
		}
		
		return true;
	}
	
	public boolean palindrom2(String str) {
		
		StringBuilder builder = new StringBuilder(str);
		return builder.reverse().toString().equals(str);
	}

}
