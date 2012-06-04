package com.jasty.core;

import java.util.Map;

/**
 * This interface is an abstraction to get request parameters. Used by FormEngine
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public interface ParameterProvider {

    String getParameter(String name);
    
    Map<String, String[]> getParameterMap();
    
}
