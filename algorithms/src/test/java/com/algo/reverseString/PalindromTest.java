package com.algo.reverseString;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PalindromTest {

	@InjectMocks
	Palindrom pal;
	
	@Test
	public void testPalindromString() {
		assertTrue(pal.palindrom("ABAABA"));
	}
	
	@Test
	public void testPalindrom2String() {
		assertTrue(pal.palindrom2("ABAABA"));
	}
}
