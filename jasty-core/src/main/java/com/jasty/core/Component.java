package com.jasty.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.jasty.js.JsClosure;
import com.jasty.js.JsExpression;
import com.jasty.js.JsSerializable;
import com.jasty.utils.ReflectionUtils;

/**
 * This is the base class for all components, requiring initialisation
 * (having the init-method).
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public abstract class Component extends ComponentProxy implements JsSerializable {

    /**
     * Custom data, associated with the component instance
     */
    @InitProperty
    private String data;

    @InitProperty
    private boolean visible = true;

    public void setData(String data) {
        this.data = data;
    }

    public String getHtmlTag() {
        return "div";
    }
    
    public final Map<String, String> getHtmlAttributes() {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("id", getClientId());
        fillHtmlAttributes(attributes);
        return attributes;
    }
    
    protected void fillHtmlAttributes(Map<String, String> attributes) {

    }

	private Map<String, Object> getInitialOptions() {
		Map<String, Object> opts = new HashMap<String, Object>();
		fillInitialOptions(opts);
		return opts;
	}

    /**
     * Default implementation collects all fields, marked with @InitProperty
     *
     * @param opts init parameter to be filled by the method
     */
	protected void fillInitialOptions(Map<String, Object> opts) {
        opts.put("id", getId());
		for(Field f : ReflectionUtils.getAllInitProperties(getClass())) {
			f.setAccessible(true);
			try {
				Object value = f.get(this);
                if(value != null)
				    opts.put(f.getName(), value);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void init() {
		invoke("init", getInitialOptions());
	}

    public JsExpression toJsExpression() {
        final Component component = this;
        JsClosure script = new JsClosure(new Runnable() {
            @Override
            public void run() {
                component.init();
            }
        });
        return new HtmlFragment(ComponentRenderingHelper.getStartTag(this) + ComponentRenderingHelper.getEndTag(this),
                script).toJsExpression();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        invoke("visible", visible);
    }
}
