package com.jasty.js;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsBooleanTest {

	@Test
	public void testEncode() {
		assertEquals("true", new JsBoolean(true).encode());
		assertEquals("false", new JsBoolean(false).encode());
	}
}
