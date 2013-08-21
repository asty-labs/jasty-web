package com.jasty.jsp.tags;

import com.jasty.components.std.ComboBox;
import com.jasty.components.std.Option;
import com.jasty.jsp.ComponentTag;

public class ComboBoxTag extends ComponentTag<ComboBox> {

    private static final long serialVersionUID = 1L;

    private String value;

    private String onChange;

    private Iterable<Option> options;

    public ComboBoxTag() {
        super(ComboBox.class, true);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public void setOptions(Iterable<Option> options) {
        this.options = options;
    }
}
