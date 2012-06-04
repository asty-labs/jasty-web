package com.jasty.js;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsStringTest {

	@Test
	public void testEncode() {
		assertEquals("\"\"", new JsString("").encode());
		assertEquals("\"abc\"", new JsString("abc").encode());
		assertEquals("\"'a' \\\"b\\\" \\nc\\n\"", new JsString("'a' \"b\" \nc\n").encode());
	}
}
