package com.jasty.components;

import com.jasty.components.std.Button;
import com.jasty.js.JsContext;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ButtonTest {
    
    @Test
    public void testInit() {
        final Button btn = new Button();
        JsContext.execute(new Runnable() {
            @Override
            public void run() {
                btn.setId("btn1");
                btn.setText("my text");
            }
        });
        String script = JsContext.execute(new Runnable() {
            @Override
            public void run() {
                btn.init();
            }
        }).encode();
        assertEquals("$$('btn1').jasty(\"Button\", \"init\", [{\"id\":\"btn1\", \"text\":\"my text\", \"visible\":true}]);", script);
    }
}
