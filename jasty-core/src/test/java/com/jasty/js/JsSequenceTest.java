package com.jasty.js;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsSequenceTest {
	
	@Test
	public void testEmpty() {
		assertEquals("", new JsSequence().encode());
	}

	@Test
	public void testSingle() {
		assertEquals("init();", new JsSequence(new JsCall("init")).encode());
	}

	@Test
	public void testMany() {
		assertEquals("init();init2();", new JsSequence(new JsCall("init"), new JsCall("init2")).encode());
	}
}
