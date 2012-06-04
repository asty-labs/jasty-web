package com.jasty.jsp;

import com.jasty.core.Form;
import com.jasty.core.ViewRenderer;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * This is ViewRenderer implementation to resolve and render JSP-views via RequestDispatcher.include.
 * Naming convention for the view path resolution:
 * <Form package as directories>/<Form class name>[_<fragment name>].jsp
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public class JspViewRenderer implements ViewRenderer {

    private ServletRequest req;
    private ServletResponse resp;

    public JspViewRenderer(ServletRequest req, ServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    @Override
    public String renderMainView(Form form, Object model) {
        return renderFragment(form,  null, model);
    }

    @Override
    public String renderFragment(Form form, String fragmentName, Object model) {
        String fileName = form.getClass().getName().replace('.', '/') + (fragmentName == null ? "" : "_" + fragmentName) + ".jsp";
        req.setAttribute("currentForm", form);
        req.setAttribute("model", model);
        try {
            BufferedResponse r1 = new BufferedResponse(resp);
            req.getRequestDispatcher("/" + fileName).include(req, r1);
            return r1.getContent();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
