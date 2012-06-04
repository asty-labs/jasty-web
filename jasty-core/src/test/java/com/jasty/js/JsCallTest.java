package com.jasty.js;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.jasty.js.JsCall;

public class JsCallTest {

	@Test
	public void testNoParameters() {
		assertEquals("init()", new JsCall("init").encode());
	}

	@Test
	public void testOneParameter() {
		JsCall call = new JsCall("init", 1);
		assertEquals("init(1)", call.encode());
	}

	@Test
	public void testManyParameters() {
		JsCall call = new JsCall("init", null, 1, "abc", new BigDecimal(BigInteger.valueOf(123), 2));
		assertEquals("init(null, 1, \"abc\", 1.23)", call.encode());
	}
}
