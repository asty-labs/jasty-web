package com.jasty.js;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsVariableTest {
	
	@Test
	public void testEncode() {
		assertEquals("testVar", new JsVariable("testVar").encode());
	}
}
