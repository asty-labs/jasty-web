package com.jasty.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.jasty.core.Component;
import com.jasty.core.ComponentRenderingHelper;
import com.jasty.core.RenderingContext;
import com.jasty.utils.ReflectionUtils;


/**
 * Base component tag for component proxies.
 * It instantiates appropriate component, initializes all properties with @InitProperty
 * it can find and registers the component in the current RenderingContext.
 * It renders a single HTML tag with id for the component proxy.
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public abstract class ComponentTag<T extends Component> extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private Class<T> componentClass;
	private T component;
	private boolean fullTag;

    private boolean visible = true;
	
	protected ComponentTag(Class<T> componentClass) {
		this(componentClass, false);
	}

	protected ComponentTag(Class<T> componentClass, boolean fullTag) {
		this.componentClass = componentClass;
		this.fullTag = fullTag;
	}

	private T createComponent() {
		try {
			return componentClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void fillComponent(T component) {
        component.setId(getId());
		ReflectionUtils.copyInitProperties(this, component);
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			BodyContent bodyContent = getBodyContent();
			if(bodyContent != null || fullTag) {
                out.write(ComponentRenderingHelper.getStartTag(component));
				if(bodyContent != null)
					out.write(bodyContent.getString());
				out.write(ComponentRenderingHelper.getEndTag(component));
			}
			else {
                out.write(ComponentRenderingHelper.getVoidTag(component));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		component = createComponent();
		fillComponent(component);
		RenderingContext.getInstance().registerComponent(component);
		return EVAL_BODY_BUFFERED;
	}

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
