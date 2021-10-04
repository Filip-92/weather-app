package pl_filippeszke.model.auxiliaryMethod;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;
import pl_filippeszke.view.Messages;

import java.util.Objects;

public class StringMethods {

    protected StringMethods() {}

    public static final StringMethods INSTANCE = new StringMethods();

    public String capitalizeFirstLetter(String description) {
        description = description.substring(0,1).toUpperCase() + description.substring(1).toLowerCase();
        return description;
    }

    public boolean validateInput(String city, String country, Label errorLabel, String language) {
        if((city.isEmpty())&&(country.isEmpty())) {
            if(Objects.equals(language, "polish"))
            {
                showError(Messages.EMPTY_CITY_AND_COUNTRY_INPUT, errorLabel);
            }
            else
            {
                showError(Messages.EMPTY_CITY_AND_COUNTRY_INPUT_EN, errorLabel);
            }
            return false;
        }
        if(city.isEmpty()) {
            if(Objects.equals(language, "polish"))
            {
                showError(Messages.EMPTY_CITY_INPUT, errorLabel);
            }
            else
            {
                showError(Messages.EMPTY_CITY_INPUT_EN, errorLabel);
            }
            return false;
        }
        if(country.isEmpty()) {
            if(Objects.equals(language, "polish"))
            {
                showError(Messages.EMPTY_COUNTRY_INPUT, errorLabel);
            }
            else
            {
                showError(Messages.EMPTY_COUNTRY_INPUT_EN, errorLabel);
            }
            return false;
        }
        if(isNumeric(city)) {
            if(Objects.equals(language, "polish"))
            {
                showError(Messages.NUMERIC_VALUE, errorLabel);
            }
            else
            {
                showError(Messages.NUMERIC_VALUE_EN, errorLabel);
            }
            return false;
        }
        if(isNumeric(country)) {
            if(Objects.equals(language, "polish"))
            {
                showError(Messages.NUMERIC_VALUE, errorLabel);
            }
            else
            {
                showError(Messages.NUMERIC_VALUE_EN, errorLabel);
            }
            return false;
        }
        return true;
    }

    public void showError(String message, Label errorLabel) {
        errorLabel.setText(message);
        errorLabel.setStyle("-fx-border-color: red; -fx-background-color: black; -fx-border-radius: 8px;");

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), errorLabel);
        fadeIn.setFromValue(1);
        fadeIn.setToValue(0);
        fadeIn.play();
    }

    boolean isNumeric(String string) {
        boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;
    }

    public String replaceSpecialCharacters(String location) {
        String input = location.trim().toLowerCase();
        String tmp = input.replace("ą", "a");
        tmp = tmp.replace("ć", "c");
        tmp = tmp.replace("ę", "e");
        tmp = tmp.replace("ł", "l");
        tmp = tmp.replace("ń", "n");
        tmp = tmp.replace("ó", "o");
        tmp = tmp.replace("ś", "s");
        tmp = tmp.replace("ź", "z");
        tmp = tmp.replace("ż", "z");
        String output = tmp.replace(" ", "-");
        return output;
    }
}