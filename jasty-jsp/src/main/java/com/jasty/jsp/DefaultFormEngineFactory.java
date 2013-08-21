package com.jasty.jsp;

import com.jasty.core.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DefaultFormEngineFactory extends FormEngineFactory {

    FormPersister formPersister = new ClientSideFormPersister();
    MethodInvoker methodInvoker = new SimpleExceptionHandler(new DefaultMethodInvoker());

    @Override
    public FormEngine getFormEngine(ServletRequest request, ServletResponse response) {
        ParameterProvider parameterProvider = new RequestParameterProvider(request);
        ViewRenderer viewRenderer = new JspViewRenderer(request, response);
        return new FormEngine(parameterProvider, viewRenderer, formPersister, methodInvoker);
    }
}
