package com.jasty.jsp.tags;

import com.jasty.core.*;
import com.jasty.servlet.FormEngineFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class FormViewerTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private Form form;
	private String entryPointClass;
	private String parameters;

	@Override
	public int doEndTag() throws JspException {
		try {
			form = createForm();
			form.setId(id);
			FormEngine formEngine = FormEngineFactory.getInstance().getFormEngine((HttpServletRequest)pageContext.getRequest(), (HttpServletResponse)pageContext.getResponse());
			HtmlFragment htmlFragment = formEngine.renderMainView(form);
            JspWriter writer = pageContext.getOut();
            writer.print(htmlFragment.getHtml());
            writer.print(htmlFragment.getScript().encodeOnLoadAsHtml());
			form = null;
			entryPointClass = null;
			parameters = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return super.doEndTag();
	}

	private Form createForm() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(form != null) return form;
        return FormEngine.createInitialForm(entryPointClass, parameters);
	}
	
	public void setEntryPointClass(String value) {
		entryPointClass = value;
	}
	
	public void setParameters(String value) {
		parameters = value;
	}
}
