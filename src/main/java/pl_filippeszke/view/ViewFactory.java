package pl_filippeszke.view;

import pl_filippeszke.model.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.ParseException;

public class ViewFactory {

    private final DailyForecastView dailyForecastView = new DailyForecastView();
    private final CurrentWeatherView currentWeatherView = new CurrentWeatherView();
    private final MainWindowView mainWindowView = new MainWindowView();
    private final WeatherManager weatherManager = new WeatherManager(new HttpWeatherApi());

    public void setMainWindowView(AnchorPane body, Label timer, Label date, Label cityLabel1, Label cityLabel2,
                                   Label countryLabel1, Label countryLabel2, Label errorLabel, String language) {
        mainWindowView.setMainWindowView(body, timer, date, cityLabel1, cityLabel2, countryLabel1, countryLabel2, errorLabel, language);
    }

    public void setLocationLabelsView (String city, String country, Label cityLabel, Label countryLabel) {
        mainWindowView.topHBoxView(city, country, cityLabel, countryLabel);
    }

    public void setShowWeatherView(String citySet, String language, HBox currentWeatherHBox, Pane dailyForecastPane,
                                   Double lat, Double lon, Label date, Label timer) throws IOException, ParseException {
        weatherManager.getCurrentWeatherData(citySet, language);
        mainWindowView.dateDisplay(date, language);
        mainWindowView.updateTime(timer);
        currentWeatherHBox.getChildren().clear();
        currentWeatherHBox.getChildren().add(currentWeatherView.setCurrentWeather(citySet, language));
        dailyForecastPane.getChildren().clear();
        dailyForecastPane.getChildren().add(dailyForecastView.setDaily(lat, lon, language));
    }

    public void setNoInternetView(Label cityLabel1, Label cityLabel2, TextField cityInput1, TextField cityInput2, Label errorLabel) {
        mainWindowView.noInternetView(cityLabel1, cityLabel2, cityInput1, cityInput2, errorLabel);
    }
}