package testapp;

import com.jasty.components.CheckBox;
import com.jasty.components.ComboBox;
import com.jasty.components.Option;
import com.jasty.components.TextBox;
import com.jasty.core.EventArgs;
import com.jasty.core.Form;
import com.jasty.core.FormFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentDemoForm extends Form implements FormFactory {
    private String caption;
    private int resetCounter;

    @Override
    public Form createForm(String parameters) {
        caption = parameters;
        return this;
    }

    @Override
    public Object prepareModel() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("caption", caption);
        List<Option> opts = new ArrayList<Option>();
        opts.add(new Option("", "please select..."));
        opts.add(new Option("set1", "Set 1"));
        opts.add(new Option("set2", "Set 2"));
        model.put("mainOptions", opts);
        return model;
    }

    public void mainComboChanged(EventArgs e) {
        ComboBox mainCombo = get(ComboBox.class, e.getSrcId());
        ComboBox detailCombo = get(ComboBox.class, "detailCombo");
        List<Option> opts = new ArrayList<Option>();
        if("set1".equals(mainCombo.getValue())) {
            opts.add(new Option("", "not selected"));
            opts.add(new Option("s1o1", "Unique option for Set 1"));
            opts.add(new Option("common", "Option, common for both sets"));
            opts.add(new Option("s1o2", "Another unique option for Set 1"));
        }
        if("set2".equals(mainCombo.getValue())) {
            opts.add(new Option("", "not selected"));
            opts.add(new Option("s2o1", "Unique option for Set 2"));
            opts.add(new Option("s2o2", "Another unique option for Set 2"));
            opts.add(new Option("common", "Option, common for both sets"));
        }
        detailCombo.setVisible(opts.size() > 0);
        detailCombo.setOptions(opts);
    }
    
    public void switcherChanged(EventArgs e) {
        CheckBox switcher = get(CheckBox.class, e.getSrcId());
        String newText = switcher.isChecked() ? "checked" : "unchecked";
        get(TextBox.class, "text").setValue(newText);
    }

    public void resetClicked(EventArgs e) {
        resetCounter++;
        replaceWith(this);
    }
    
    public int getResetCounter() {
        return resetCounter;
    }
}
