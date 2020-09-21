package com.algo.reverseString;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReverseIntTest {

	@InjectMocks
	ReverseInt reverseInt;
	
	@Test
	public void testReverseInt() {
		assertEquals(1, reverseInt.reverseInt(100));
		assertEquals(381, reverseInt.reverseInt(183));
		assertEquals(-502, reverseInt.reverseInt(-205));
		assertEquals(-1, reverseInt.reverseInt(-100));
		assertEquals(-9532, reverseInt.reverseInt(-2359));
		assertEquals(9, reverseInt.reverseInt(90));
	}
	
	@Test
	public void testReverseInt2() {
		assertEquals(1, reverseInt.reverseInt2(100));
		assertEquals(381, reverseInt.reverseInt2(183));
		assertEquals(-502, reverseInt.reverseInt2(-205));
		assertEquals(-1, reverseInt.reverseInt2(-100));
		assertEquals(-9532, reverseInt.reverseInt2(-2359));
		assertEquals(9, reverseInt.reverseInt2(90));
	}
	
	@Test
	public void testReverseInt3() {
		assertEquals(381, reverseInt.reverseInt3(183));
		assertEquals(-502, reverseInt.reverseInt3(-205));
		assertEquals(-1, reverseInt.reverseInt2(-100));
		assertEquals(-9532, reverseInt.reverseInt2(-2359));
		assertEquals(9, reverseInt.reverseInt2(90));
	}
}
