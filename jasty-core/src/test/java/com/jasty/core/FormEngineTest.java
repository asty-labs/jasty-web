package com.jasty.core;

import com.jasty.js.JsExpression;
import com.jasty.utils.SerializationUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FormEngineTest {

    @Test
    public void testProcess() {
        TestParameterProvider parameterProvider = new TestParameterProvider();
        parameterProvider.put("eventHandler", "successfulEvent");
        FormEngine formEngine = new FormEngine(parameterProvider, null, new ClientSideFormPersister(), new SimpleExceptionHandler(new DefaultMethodInvoker()));
        MyForm form = new MyForm();
        form.setId("formId");
        parameterProvider.put("state", Base64.encodeBase64String(SerializationUtils.serializeObject(form)));
        parameterProvider.put("EVT.srcId", "formId.testSrcId");
        parameterProvider.put("EVT.someParameter", "testParameter");
        JsExpression expr = formEngine.processEvent();
        form.output = "testSrcId/testParameter";
        assertEquals("$$('formId').jasty(\"Form\", \"update\", [\"" +
                Base64.encodeBase64String(SerializationUtils.serializeObject(form)) + "\"]);", expr.encode());
    }

    @Test
    public void testProcessWithError() {
        TestParameterProvider parameterProvider = new TestParameterProvider();
        FormEngine formEngine = new FormEngine(parameterProvider, null, new ClientSideFormPersister(), new SimpleExceptionHandler(new DefaultMethodInvoker()));
        parameterProvider.put("eventHandler", "erroneousEvent");
        Form form = new MyForm();
        form.setId("formId");
        parameterProvider.put("state", Base64.encodeBase64String(SerializationUtils.serializeObject(form)));
        parameterProvider.put("EVT.srcId", "formId.testSrcId");
        parameterProvider.put("EVT.someParameter", "testParameter");
                
        JsExpression expr = formEngine.processEvent();
        assertTrue(expr.encode().startsWith("$$('formId').jasty(\"Form\", \"errors\", [\"some error\"]);"));
    }

    public class TestParameterProvider implements ParameterProvider {

        private Map<String, Object> parameters = new HashMap<String, Object>();
        
        public void put(String key, String value) {
            parameters.put(key, value);
        }

        @Override
        public String getParameter(String name) {
            return (String)parameters.get(name);
        }

        @Override
        public Object getFile(String name) {
            throw new NotImplementedException();
        }

        @Override
        public Map<String, Object> getParameterMap() {
            return parameters;
        }
    }
}
