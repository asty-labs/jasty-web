package com.jasty.jsp.tags;

import com.jasty.components.std.TextBox;
import com.jasty.jsp.ComponentTag;

public class TextBoxTag extends ComponentTag<TextBox> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String value;

	public TextBoxTag() {
		super(TextBox.class);
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
