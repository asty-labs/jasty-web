package guessnumber;

import com.jasty.components.JQuery;
import com.jasty.components.TextBox;
import com.jasty.core.EventArgs;
import com.jasty.core.Form;

public class MainForm extends Form {

    private int randomNumber = ((int) Math.floor(Math.random() * 100)) + 1;
    private int numberOfTries = 0;
    private int lowerBound = 1;
    private int upperBound = 100;

    @Override
    public Object prepareModel() {
        return "";
    }

    public void startNewGame(EventArgs e) {
        replaceWith(new MainForm());
    }

    public void processGuess(EventArgs e) {
        JQuery stats = get(JQuery.class, "stats");
        TextBox guessEntryField = get(TextBox.class, "guessEntryField");

        int guess;
        try {
            guess = Integer.parseInt(guessEntryField.getValue());
        } catch (NumberFormatException ex) {
            JQuery statusLabel = query(JQuery.class, "#stats .status");
            statusLabel.text("Your guess was not valid.");
            return;
        }


        ++numberOfTries;

        if (guess == randomNumber) {
            replaceWith(new CongratulationForm(numberOfTries));
            return;
        }

        guessEntryField.setValue("");

        String statusText = "";

        if (guess < 1 || guess > 100) {
            statusText = "Your guess, " + guess
                    + " was not between 1 and 100.";
        } else if (guess < randomNumber) {
            if (guess >= lowerBound) {
                lowerBound = guess + 1;
            }
            statusText = "Your guess, " + guess
                    + " was too low.  Try again:";
        } else if (guess > randomNumber) {
            statusText = "Your guess, " + guess
                    + " was too high.  Try again:";
            if (guess <= upperBound) {
                upperBound = guess - 1;
            }
        }

        stats.html(renderFragment("stats", statusText));
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public String getCounter() {
        // Update number of tries label.
        if(numberOfTries == 0)
            return "You have made no guesses";
        if (numberOfTries == 1)
            return "You have made 1 guess.";
        return "You have made " + numberOfTries + " guesses.";
    }

}
