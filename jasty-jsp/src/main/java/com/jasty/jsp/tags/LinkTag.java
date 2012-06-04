package com.jasty.jsp.tags;

import com.jasty.components.Link;
import com.jasty.jsp.ComponentTag;

public class LinkTag extends ComponentTag<Link> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String text;
	@SuppressWarnings("unused")
	private String onClick;

	public LinkTag() {
		super(Link.class);
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}
