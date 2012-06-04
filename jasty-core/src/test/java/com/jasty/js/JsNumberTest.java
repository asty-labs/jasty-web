package com.jasty.js;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class JsNumberTest {
	
	@Test
	public void testInt() {
		assertEquals("42", new JsNumber(42).encode());
	}

	@Test
	public void testFloat() {
		assertEquals("42.56", new JsNumber(42.56).encode());
	}

	@Test
	public void testBigDecimal() {
		assertEquals("42.56", new JsNumber(new BigDecimal(BigInteger.valueOf(4256), 2)).encode());
	}

}
