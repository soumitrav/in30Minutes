package com.algo.reverseString;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;

public class ReverseStringTest {

	@InjectMocks
	ReverseString programm;
	
	@Test
	public void testReverseString() {
		String actual = programm.reverseString1("Soumitra");
		assertEquals("artimuoS", actual);
	}
	
	@Test
	public void testReverseStringAlphaNumeric() {
		String actual = programm.stringReverse("  abcd");
		assertEquals("dcba  ", actual);
	}
}
