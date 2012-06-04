package com.jasty.js;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JsListTest {

	@Test
	public void testEmptyArray() {
		assertEquals("[]", new JsList(new Object[] {}).encode());
	}
	
	@Test
	public void testOneElementArray() {
		assertEquals("[\"123\"]", new JsList(new Object[] {"123"}).encode());
	}
	
	@Test
	public void testManyElementsArray() {
		Object[] data = new Object[] {123, "345", null};
		assertEquals("[123, \"345\", null]", new JsList(data).encode());
	}

	@Test
	public void testEmpty() {
		assertEquals("[]", new JsList().encode());
	}

	@Test
	public void testOneElement() {
		List<Object> data = new ArrayList<Object>();
		data.add(123);
		assertEquals("[123]", new JsList(data).encode());
	}

	@Test
	public void testManyElements() {
		List<Object> data = new ArrayList<Object>();
		data.add(123);
		data.add("345");
		data.add(null);
		assertEquals("[123, \"345\", null]", new JsList(data).encode());
	}

	@Test
	public void testFrom() {
		assertEquals("[123, \"345\", null]", JsList.from(123, "345", null).encode());
	}

}
