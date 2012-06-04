package com.jasty.core;

import com.jasty.js.JsCall;
import com.jasty.js.JsExpression;
import com.jasty.utils.SerializationUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class FormEngineTest {

    @Test
    public void testProcess() {
        TestParameterProvider parameterProvider = new TestParameterProvider();
        parameterProvider.put("eventHandler", "successfulEvent");
        FormEngine formEngine = new FormEngine(parameterProvider, null, ClientSideFormPersister.getInstance());
        MyForm form = new MyForm();
        form.setId("formId");
        parameterProvider.put("state", Base64.encodeBase64String(SerializationUtils.serializeObject(form)));
        parameterProvider.put("EVT.srcId", "formId.testSrcId");
        parameterProvider.put("EVT.someParameter", "testParameter");
        JsExpression expr = formEngine.processEvent(null);
        form.output = "testSrcId/testParameter";
        assertEquals("$$('formId').jasty(\"Form\", \"update\", [\"" +
                Base64.encodeBase64String(SerializationUtils.serializeObject(form)) + "\"]);", expr.encode());
    }

    @Test
    public void testProcessWithError() {
        TestParameterProvider parameterProvider = new TestParameterProvider();
        FormEngine formEngine = new FormEngine(parameterProvider, null, ClientSideFormPersister.getInstance());
        parameterProvider.put("eventHandler", "erroneousEvent");
        Form form = new MyForm();
        form.setId("formId");
        parameterProvider.put("state", Base64.encodeBase64String(SerializationUtils.serializeObject(form)));
        parameterProvider.put("EVT.srcId", "formId.testSrcId");
        parameterProvider.put("EVT.someParameter", "testParameter");
                
        JsExpression expr = formEngine.processEvent(new ThrowableHandler() {

            @Override
            public JsExpression handle(Throwable t) {
                return new JsCall("alert", t.getMessage());
            }

        });
        assertEquals("alert(\"some error\")", expr.encode());
    }

    public class TestParameterProvider implements ParameterProvider {

        private Map<String, String[]> parameters = new HashMap<String, String[]>();
        
        public void put(String key, String value) {
            parameters.put(key, new String[] {value});
        }

        @Override
        public String getParameter(String name) {
            return parameters.get(name)[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return parameters;
        }
    }
}
