package com.jasty.jsp;

import com.jasty.core.*;
import com.jasty.servlet.FormEngineFactory;
import com.jasty.servlet.FormEngineServlet;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFormEngineServlet extends FormEngineServlet {

    @Override
    protected FormEngineFactory createFormEngineFactory(ServletConfig config) {
        return new DefaultFormEngineFactory();
    }

    class DefaultFormEngineFactory extends FormEngineFactory {

        FormPersister formPersister = new ClientSideFormPersister();
        MethodInvoker methodInvoker = new SimpleExceptionHandler(new DefaultMethodInvoker());

        @Override
        public FormEngine getFormEngine(HttpServletRequest request, HttpServletResponse response) {

            ParameterProvider parameterProvider = ServletFileUpload.isMultipartContent(request) ?
                    new MultipartParameterProvider(request) :
                    new RequestParameterProvider(request);
            ViewRenderer viewRenderer = new JspViewRenderer(request, response);
            return new FormEngine(parameterProvider, viewRenderer, formPersister, methodInvoker);
        }
    }
}
