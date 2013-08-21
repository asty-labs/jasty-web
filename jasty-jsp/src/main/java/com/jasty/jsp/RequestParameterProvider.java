package com.jasty.jsp;

import com.jasty.core.ParameterProvider;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
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
    public Object getFile(String name) {
        throw new NotImplementedException();
    }

    @Override
    public Map<String, Object> getParameterMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        Enumeration e = req.getParameterNames();
        while(e.hasMoreElements()) {
            String key = (String)e.nextElement();
            result.put(key, req.getParameter(key));
        }
        return result;
    }
}
