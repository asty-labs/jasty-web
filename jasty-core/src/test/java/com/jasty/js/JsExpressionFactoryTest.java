package com.jasty.js;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class JsExpressionFactoryTest {

	@Test
	public void testSpecialCases() {
		check(null, JsVariable.class, "null");
		JsExpression e = new JsCall("init");
		assertSame(e, JsExpressionFactory.create(e));
	}
	
	@Test
	public void testArray() {
		Object[] data = new Object[] {"abc", 123, null};
		check(data, JsList.class, "[\"abc\", 123, null]");
		String[] d2 = new String[] {"q", "v"};
		check(d2, JsList.class, "[\"q\", \"v\"]");
	}

	@Test
	public void testList() {
		List<Object> data = new ArrayList<Object>();
		data.add("abc");
		data.add(123);
		data.add(null);
		check(data, JsList.class, "[\"abc\", 123, null]");
	}

	@Test
	public void testMap() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("abc", 123);
		check(data, JsMap.class, "{\"abc\":123}");
	}

	@Test
	public void testNumber() {
		check((byte)12, JsNumber.class, "12");
		check((short)12, JsNumber.class, "12");
		check((int)12, JsNumber.class, "12");
		check((long)12, JsNumber.class, "12");
		check(12.45, JsNumber.class, "12.45");
		check(new BigDecimal(BigInteger.valueOf(12345), 3), JsNumber.class, "12.345");
	}
	
	@Test
	public void testString() {
		check("abcd", JsString.class, "\"abcd\"");
		check('\n', JsString.class, "\"\\n\"");
		check(new StringBuffer("test"), JsString.class, "\"test\"");
	}
	
	private static void check(Object value, Class<?> clazz, String encoded) {
		JsExpression expr = JsExpressionFactory.create(value);
		assertSame(clazz, expr.getClass());
		assertEquals(encoded, expr.encode());
		
	}

}
