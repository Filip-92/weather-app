package pl_filippeszke.model;

import pl_filippeszke.model.auxiliaryMethod.CurrentTimeAndDate;
import pl_filippeszke.model.auxiliaryMethod.StringMethods;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import pl_filippeszke.view.Messages;

import java.time.Clock;
import java.util.Random;

public class MainWindowView {

    CurrentTimeAndDate currentDate = new CurrentTimeAndDate(Clock.systemDefaultZone());
    private final StringMethods stringMethods = StringMethods.INSTANCE;

    String citySet1, citySet2;
    String countrySet1, countrySet2;

    public MainWindowView () throws JSONException {
        this.citySet1 = "Warszawa";
        this.countrySet1 = "Polska";
        this.citySet2 = "Rzym";
        this.countrySet2 = "Włochy";
    }

    public int getRandomImage()
    {
        Random rand = new Random();
        int n = rand.nextInt(7);
        return n + 1;
    }

    public void setMainWindowView(AnchorPane body, Label date, Label timer, Label cityLabel1,
                                  Label cityLabel2, Label countryLabel1, Label countryLabel2, Label errorLabel) {
        body.setStyle("-fx-background-image: url('/img/background-image" + getRandomImage() + ".jpg');"
                + "-fx-background-size: cover;");
        timer.setText(currentDate.currentTime());
        dateDisplay(date);
        cityLabel1.setText(citySet1);
        countryLabel1.setText(countrySet1);
        cityLabel2.setText(citySet2);
        countryLabel2.setText(countrySet2);
        errorLabel.setText("");
    }

    public Label dateDisplay(Label date) {
        date.setText(currentDate.currentDate());
        return date;
    }

    public void updateTime(Label timer) {
        timer.setText(currentDate.currentTime());
    }

    public void topHBoxView(String city, String country, Label cityLabel, Label countryLabel) {
        cityLabel.setText(city);
        countryLabel.setText(country);
    }

    public void noInternetView(Label cityLabel1, Label cityLabel2, TextField cityInput1, TextField cityInput2, Label errorLabel) {
        cityLabel1.setText(Messages.NO_INTERNET_ALERT);
        cityLabel2.setText(Messages.NO_INTERNET_ALERT);
        stringMethods.showError(Messages.NO_INTERNET_MESSAGE, errorLabel);
        cityInput1.setText("");
        cityInput2.setText("");
    }
}