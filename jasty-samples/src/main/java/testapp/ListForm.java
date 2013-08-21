package testapp;

import java.util.*;

import com.jasty.components.JQuery;
import com.jasty.components.std.Link;
import com.jasty.core.EventArgs;
import com.jasty.core.Form;

public class ListForm extends Form {

	private static final long serialVersionUID = 1L;

	private List<String> items = new ArrayList<String>();

    @Override
    public void afterInit() {
        super.afterInit();
        for(String item : items) {
            displayItem(item);
        }
    }

    private void displayItem(String text) {
        Link link = $$(Link.class, null);
        link.setText(text);
        link.setData(text);
        link.setOnClick("deleteClicked");
        $(JQuery.class, ".list").append(link);
    }
    
    public void searchClicked(EventArgs e) {
        $(JQuery.class, ".list").empty();
        String text = getParameter("searchText");
        for(String item : items) {
            if(item.contains(text))
                displayItem(item);
        }
	}
    
    public void addClicked(EventArgs e) {
        replaceWith(new AddingForm(items.size(), this));
    }
    
    public void deleteClicked(EventArgs e) {
        $$(Link.class, e.getSrcId()).remove();
        items.remove(e.get("data"));
    }
    
    public void addItem(String item) {
        if(!items.contains(item))
            items.add(item);
    }
}
