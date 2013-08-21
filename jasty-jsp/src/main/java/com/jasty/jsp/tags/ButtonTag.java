package com.jasty.jsp.tags;

import com.jasty.components.std.Button;
import com.jasty.jsp.ComponentTag;

public class ButtonTag extends ComponentTag<Button>{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String text;
	@SuppressWarnings("unused")
	private String onClick;

	public ButtonTag() {
		super(Button.class);
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
}
