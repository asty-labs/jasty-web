package com.jasty.jsp;

import com.jasty.core.ParameterProvider;
import com.jasty.core.UploadedFile;

import javax.servlet.ServletRequest;
import java.util.*;

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
    public UploadedFile getFile(String name) {
        return null;
    }

    @Override
    public Collection<String> getParameterNames() {
        Set<String> result = new HashSet<String>();
        Enumeration e = req.getParameterNames();
        while(e.hasMoreElements()) {
            String key = (String)e.nextElement();
            result.add(key);
        }
        return result;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = req.getParameterValues(name);
        if(values == null) return new String[0];
        return values;
    }
}
