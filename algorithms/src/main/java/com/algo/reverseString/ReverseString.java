package com.algo.reverseString;

import java.util.Optional;
import java.util.stream.Stream;

public class ReverseString {

	public static void main(String [] args) {
		System.out.println(stringReverse(args[0]));
	}

	public static String stringReverse(String str) {
		Stream<String> stream = Stream.of(str.split(""));
		Optional<String> reduce = stream.reduce((reverse,character)->{
			return character+reverse;
		});
		return reduce.get();
	}
	
	public static String reverseString1(String str) {
		String[] arr = str.split("");
		for(int i=0;i<str.length()/2;i++) {
			String temp = arr[i];
			arr[i] = arr[str.length()-1-i];
			arr[str.length()-1-i] = temp;
		}
		return String.join("", arr);
	}
}
