package com.jasty.components;

import java.util.Map;

import com.jasty.core.Component;
import com.jasty.core.InitProperty;

/**
 * Component proxy for HTML text editor
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public class TextBox extends Component {

    @InitProperty
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		invoke("value", value);
	}

	@Override
	public void restore(Map<String, String[]> data) {
        if(data.containsKey(getId()))
    		value = data.get(getId())[0];
	}

    @Override
    public String getHtmlTag() {
        return "input";
    }

    @Override
    protected void fillHtmlAttributes(Map<String, String> attributes) {
        attributes.put("type", "text");
    }
}
