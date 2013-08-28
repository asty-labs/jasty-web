package testapp;

import com.jasty.core.EventArgs;
import com.jasty.core.Form;

public class AddingForm extends Form {
    private int currentCount;
    private ListForm parent;

    public AddingForm(int currentCount, ListForm parent) {
        this.currentCount = currentCount;
        this.parent = parent;
    }

    @Override
    public Object prepareModel() {
        return currentCount + 1;
    }

    public void okClicked(EventArgs e) {
        parent.addItem(getParameters().getParameter("text"));
        replaceWith(parent);
    }

    public void cancelClicked(EventArgs e) {
        replaceWith(parent);
    }
}
