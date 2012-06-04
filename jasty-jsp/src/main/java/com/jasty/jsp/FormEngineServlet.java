package com.jasty.jsp;

import com.jasty.core.*;
import com.jasty.js.JsCall;
import com.jasty.js.JsExpression;
import com.jasty.core.ThrowableHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle all requests to FormEngine
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public class FormEngineServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        ParameterProvider parameterProvider = new RequestParameterProvider(req);
        ViewRenderer viewRenderer = new JspViewRenderer(req, resp);

        JsExpression expr = new FormEngine(parameterProvider, viewRenderer, ClientSideFormPersister.getInstance()).processEvent(
                new ThrowableHandler() {

                    @Override
                    public JsExpression handle(Throwable t) {
                        t.printStackTrace();
                        return new JsCall("alert", t.getMessage());
                    }

                });

        if(resp.isCommitted()) return;

        resp.setContentType("text/javascript");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Cache-Control", "must-revalidate"); // HTTP 1.1
        resp.addHeader("Pragma", "no-cache"); // HTTP 1.0
        resp.setDateHeader("Expires", 0); // Proxies.

        resp.getWriter().write(expr.encode());
    }
}
