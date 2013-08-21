package com.jasty.jsp.tags;

import com.jasty.components.std.CheckBox;
import com.jasty.jsp.ComponentTag;

public class CheckBoxTag extends ComponentTag<CheckBox> {

    private static final long serialVersionUID = 1L;

    private boolean checked;

    private String onChange;

    public CheckBoxTag() {
        super(CheckBox.class);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }
}
