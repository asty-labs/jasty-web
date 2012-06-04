package com.jasty.components;

import com.jasty.core.Component;
import com.jasty.core.InitProperty;

import java.util.Map;

/**
 * Component proxy for HTML combo editor
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
public class ComboBox extends Component {
    @InitProperty
    private String value;

    @SuppressWarnings("unused")
    @InitProperty
    private String onChange;

    @InitProperty
    private Iterable<Option> options;

    @Override
    public void restore(Map<String, String[]> data) {
        if(data.containsKey(getId()))
            value = data.get(getId())[0];
    }

    @Override
    public String getHtmlTag() {
        return "select";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        invoke("value", value);
    }

    public void setOptions(Iterable<Option> options) {
        invoke("options", options);
    }
}
