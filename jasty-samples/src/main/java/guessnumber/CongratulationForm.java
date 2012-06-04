package guessnumber;

import com.jasty.core.EventArgs;
import com.jasty.core.Form;

public class CongratulationForm extends Form {

    private int numberOfTries;

    public CongratulationForm(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    @Override
    public Object prepareModel() {
        return numberOfTries;
    }

    public void startNewGame(EventArgs e) {
        replaceWith(new MainForm());
    }
}
