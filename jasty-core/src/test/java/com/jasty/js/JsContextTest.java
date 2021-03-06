package com.jasty.js;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsContextTest {

	@Test
	public void testErrorNoContext() {
        JsContext.add(new JsCall("init"));
	}
	
	@Test
	public void testExecute() {
		JsExpression expr = JsContext.execute(new Runnable() {

			@Override
			public void run() {
				JsContext.add(new JsCall("init"));
				JsContext.add(new JsCall("init2", 123));
			}
			
		});
		assertEquals("init();init2(123);", expr.encode());
	}
}
