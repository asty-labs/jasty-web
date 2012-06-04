package com.jasty.js;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class JsMapTest {
	
	@Test
	public void testEmpty() {
		assertEquals("{}", new JsMap().encode());
	}
	
	@Test
	public void testOneElement() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("a1", 11);
		assertEquals("{\"a1\":11}", new JsMap(data).encode());
	}

	@Test
	public void testManyElement() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("a1", 11);
		data.put("b1", "bb");
		data.put("c1", null);
		assertEquals("{\"a1\":11, \"b1\":\"bb\", \"c1\":null}", new JsMap(data).encode());
	}
}
