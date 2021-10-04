package pl_filippeszke.controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pl_filippeszke.GetWeatherCurrentLocation;
import pl_filippeszke.GetWeatherSearchedLocation;
import pl_filippeszke.model.auxiliaryMethod.LabelDescription;
import pl_filippeszke.model.auxiliaryMethod.StringMethods;
import pl_filippeszke.view.Messages;
import pl_filippeszke.view.ViewFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainWindowController implements Initializable {

    private final ViewFactory viewFactory = new ViewFactory();
    private final StringMethods stringMethods = StringMethods.INSTANCE;
    private final LabelDescription labelDescription = new LabelDescription();
    Map<String, Object> weatherData;
    Map<String, Object> weatherData2;
    MouseEvent event;

    private String citySet1, citySet2;
    String countrySet1, countrySet2;
    private String language;
    private Double lat1, lat2;
    private Double lon1, lon2;

    @FXML
    public AnchorPane body;

    @FXML
    private Button changeLanguagePolish, changeLanguageEnglish, checkWeatherForLocation1, checkWeatherForLocation2;

    @FXML
    private HBox currentWeatherActualLocationHBox, currentWeatherSearchedLocationHBox;

    @FXML
    private Pane leftPaneDaily, rightPaneDaily;

    @FXML
    public Label timer, date, cityLabel1, countryLabel1, cityLabel2, countryLabel2, errorLabel;

    @FXML
    public Label countryInputLabel1, countryInputLabel2, cityInputLabel1, cityInputLabel2, currentLocationLabel, searchedLocationLabel;

    @FXML
    private TextField countryInput1, cityInput1, countryInput2, cityInput2;

    URL location;
    ResourceBundle resources;

    public MainWindowController () throws JSONException {
        this.citySet1 = "Warszawa";
        this.countrySet1 = "Polska";
        this.lat1 = 52.229676;
        this.lon1 = 21.012229;
        this.citySet2 = "Rzym";
        this.countrySet2 = "Włochy";
        this.lat2 = 41.902782;
        this.lon2 = 12.496366;
        this.language = "polish";
    }

    @FXML
    void refreshView(MouseEvent event) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        initialize(location, resources);
        timer.setText(dtf.format(now));
    }

    @FXML
    void exit() {
        Platform.exit();
    }

    @FXML
    private void searchForWeatherLocation1ButtonAction() {
        String country1 = countryInput1.getText();
        String city1 = cityInput1.getText();
        boolean valid = stringMethods.validateInput(cityInput1.getText(), countryInput1.getText(), errorLabel, language);
        {
            if(valid) {
                viewFactory.setLocationLabelsView(city1, country1, cityLabel1, countryLabel1);
                this.citySet1 = stringMethods.replaceSpecialCharacters(city1.trim());
                this.countrySet1 = stringMethods.replaceSpecialCharacters(country1.trim());
                try {
                    GetWeatherCurrentLocation getWeatherCurrentLocation = new GetWeatherCurrentLocation();
                    List<Double> coordinates1 = getWeatherCurrentLocation.getCurrentCoordinatesActualLocation(citySet1);
                    this.lat1 = coordinates1.get(0);
                    this.lon1 = coordinates1.get(1);
                    showWeatherLocation1();
                } catch (Exception e) {
                    cityLabel1.setText(Messages.ERROR);
                    stringMethods.showError(Messages.NO_CITY_FOUND, errorLabel);
                    resetLocation(currentWeatherActualLocationHBox, leftPaneDaily);
                }
            }
        }
    }

    @FXML
    private void searchForWeatherLocation2ButtonAction() {
        String country2 = countryInput2.getText();
        String city2 = cityInput2.getText();
        boolean valid = stringMethods.validateInput(cityInput2.getText(), countryInput2.getText(), errorLabel, language);
        {
            if(valid) {
                viewFactory.setLocationLabelsView(city2, country2, cityLabel2, countryLabel2);
                this.citySet2 = stringMethods.replaceSpecialCharacters(city2.trim());
                this.countrySet2 = stringMethods.replaceSpecialCharacters(country2.trim());
                try {
                    GetWeatherSearchedLocation getWeatherSearchedLocation = new GetWeatherSearchedLocation();
                    List<Double> coordinates2 = getWeatherSearchedLocation.getCurrentCoordinatesSearchedLocation(citySet2);
                    this.lat2 = coordinates2.get(0);
                    this.lon2 = coordinates2.get(1);
                    showWeatherLocation2();
                } catch (Exception e) {
                    cityLabel2.setText(Messages.ERROR);
                    stringMethods.showError(Messages.NO_CITY_FOUND, errorLabel);
                    resetLocation(currentWeatherSearchedLocationHBox, rightPaneDaily);
                }
            }
        }
    }

    public void resetLocation(HBox currentWeatherHBox, Pane dailyPane) {
        currentWeatherHBox.getChildren().clear();
        dailyPane.getChildren().clear();
    }

    public void showWeatherLocation1() throws IOException, ParseException {
        weatherData = GetWeatherCurrentLocation.getDailyForecastActualLocation(this.lat1, this.lon1, language);
        viewFactory.setShowWeatherView(citySet1, language, currentWeatherActualLocationHBox, leftPaneDaily, this.lat1, this.lon1, date, timer);
    }

    private void showWeatherLocation2() throws IOException, ParseException {
        weatherData2 = GetWeatherSearchedLocation.getDailyForecastSearchedLocation(this.lat2, this.lon2, language);
        viewFactory.setShowWeatherView(citySet2, language, currentWeatherSearchedLocationHBox, rightPaneDaily, this.lat2, this.lon2, date, timer);
    }

    private void changeLanguage(Button changeLanguageButton1, Button changeLanguageButton2, String message) throws IOException, ParseException {
        showWeatherLocation1();
        showWeatherLocation2();
        changeLanguageButton1.setStyle("-fx-border-width: 2px;" + "-fx-border-color: lime;" +
                "-fx-border-insets: -2px;");
        changeLanguageButton2.setStyle(null);
        checkWeatherForLocation1.setText(message);
        checkWeatherForLocation2.setText(message);
        refreshView(event);
    }

    @FXML
    String changeLanguageToPolish() throws IOException, ParseException {
        language = "polish";
        changeLanguage(changeLanguagePolish, changeLanguageEnglish, "Sprawdź pogodę");
        labelDescription.changeMainWindowLabelsDescription(language, countryInputLabel1, countryInputLabel2, cityInputLabel1, cityInputLabel2,
                currentLocationLabel, searchedLocationLabel);
        return language;
    }

    @FXML
    String changeLanguageToEnglish() throws IOException, ParseException {
        language = "english";
        changeLanguage(changeLanguageEnglish, changeLanguagePolish, "Check weather");
        labelDescription.changeMainWindowLabelsDescription(language, countryInputLabel1, countryInputLabel2, cityInputLabel1, cityInputLabel2,
                currentLocationLabel, searchedLocationLabel);
        return language;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        viewFactory.setMainWindowView(body, timer, date, cityLabel1, cityLabel2, countryLabel1, countryLabel2, errorLabel);
        if(language.equals("polish")) {
            changeLanguagePolish.setStyle("-fx-border-width: 2px;" + "-fx-border-color: lime;" + "-fx-border-insets: -2px;");
        } else {
            changeLanguageEnglish.setStyle("-fx-border-width: 2px;" + "-fx-border-color: lime;" + "-fx-border-insets: -2px;");
        }
        try {
            showWeatherLocation1();
            showWeatherLocation2();
        } catch (ParseException | IOException e) {
            viewFactory.setNoInternetView(cityLabel1, cityLabel2, cityInput1, cityInput2, errorLabel);
            resetLocation(currentWeatherActualLocationHBox, leftPaneDaily);
            resetLocation(currentWeatherSearchedLocationHBox, rightPaneDaily);
            e.printStackTrace();
        }
    }
}