package com.jasty.jsp;

import com.jasty.core.ParameterProvider;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * Facade to the parameters from ServletRequest
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public class RequestParameterProvider implements ParameterProvider {

    private ServletRequest req;

    public RequestParameterProvider(ServletRequest req) {
        this.req = req;
    }

    @Override
    public String getParameter(String name) {
        return req.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return req.getParameterMap();
    }
}
