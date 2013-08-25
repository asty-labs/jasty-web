package com.jasty.jsp;

import com.jasty.core.*;
import com.jasty.servlet.FormEngineFactory;
import com.jasty.servlet.FormEngineServlet;
import com.jasty.servlet.RequestParameterProvider;

import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JspFormEngineServlet extends FormEngineServlet {

    @Override
    protected FormEngineFactory createFormEngineFactory(ServletConfig config) {
        return new DefaultFormEngineFactory();
    }

    class DefaultFormEngineFactory extends FormEngineFactory {

        FormPersister formPersister = new ClientSideFormPersister();
        MethodInvoker methodInvoker = new SimpleExceptionHandler(new DefaultMethodInvoker());

        @Override
        public FormEngine getFormEngine(ServletRequest request, ServletResponse response) {
            ParameterProvider parameterProvider = new RequestParameterProvider(request);
            ViewRenderer viewRenderer = new JspViewRenderer(request, response);
            return new FormEngine(parameterProvider, viewRenderer, formPersister, methodInvoker);
        }
    }
}
