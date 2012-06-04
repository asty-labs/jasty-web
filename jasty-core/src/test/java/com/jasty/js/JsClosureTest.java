package com.jasty.js;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsClosureTest {

	@Test
	public void testExpressionNoArgs() {
		JsClosure cl = new JsClosure(new JsCall("init"));
		Assert.assertEquals("function(){init()}", cl.encode());
	}

	@Test
	public void testExpressionOneArg() {
		final JsVariable v1 = new JsVariable("var1");
		JsClosure cl = new JsClosure(new JsCall("init"), v1);
		Assert.assertEquals("function(var1){init()}", cl.encode());
	}
	
	@Test
	public void testExpressionManyArgs() {
		final JsVariable v1 = new JsVariable("var1");
		final JsVariable v2 = new JsVariable("var2");
		JsClosure cl = new JsClosure(new JsCall("alert", v1, v2), v2, v1);
		Assert.assertEquals("function(var2, var1){alert(var1, var2)}", cl.encode());
	}

	@Test
	public void testRunnable() {
		final JsVariable v1 = new JsVariable("var1");
		final JsVariable v2 = new JsVariable("var2");
		JsClosure cl = new JsClosure(new Runnable() {
			@Override
			public void run() {
				JsContext.add(new JsCall("alert", v1, v2));
			}
		}, v2, v1);
		Assert.assertEquals("function(var2, var1){alert(var1, var2);}", cl.encode());
	}

    @Test
    public void testEncodeOnLoadAsHtml() {
        String result = new JsClosure(new Runnable() {

            @Override
            public void run() {
                JsContext.add(new JsCall("init"));
                JsContext.add(new JsCall("init2", 123));
            }

        }).encodeOnLoadAsHtml();
        assertEquals("<script>jQuery(function(){init();init2(123);})</script>", result);
    }
}
