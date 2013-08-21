package com.jasty.jsp;

import com.jasty.js.JsExpression;

import javax.servlet.ServletConfig;
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
    public void init(ServletConfig config) throws ServletException {
        FormEngineFactory.setInstance(new DefaultFormEngineFactory());
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        JsExpression expr = FormEngineFactory.getInstance().getFormEngine(req, resp).processEvent();

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
