package pl_filippeszke.model;

import pl_filippeszke.model.auxiliaryMethod.IconManager;
import pl_filippeszke.model.auxiliaryMethod.LabelDescription;
import pl_filippeszke.model.auxiliaryMethod.StringMethods;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CurrentWeatherView {

    private final StringMethods stringMethods = StringMethods.INSTANCE;
    WeatherManager weatherManager = new WeatherManager(new HttpWeatherApi());
    LabelDescription labelDescription = new LabelDescription();

    Integer WIDTH = 660;

    @FXML
    HBox currentWeatherHBox;

    @FXML
    Label currentDayLabel, currentTempLabel;

    @FXML
    Image currentWeatherIcon;

    @FXML
    GridPane currentWeatherData;

    @FXML
    Label currentWeatherDescriptionLabel, currentWindSpeedLabel, currentCloudinessLabel, currentPressureLabel,currentHumidityLabel;

    public CurrentWeatherView() {
    }

    public HBox currentWeatherView(String language) {
        currentDayLabel = setCurrentDay(language);
        currentWeatherIcon = setWeatherIcon();
        Node currentWeatherImage = new ImageView(currentWeatherIcon);
        currentTempLabel = setCurrentTemp();
        currentWeatherData = setCurrentWeatherDatabox(language);
        currentWeatherHBox = new HBox();
        currentWeatherHBox.setPadding(new Insets(0, 0, 0, 15));
        currentWeatherHBox.getChildren().addAll(currentDayLabel, currentWeatherImage, currentTempLabel, currentWeatherData);
        currentWeatherHBox.setAlignment(Pos.CENTER);
        currentWeatherHBox.setPrefHeight(181);
        currentWeatherHBox.setSpacing(10);
        return currentWeatherHBox;
    }

    public Label setCurrentTemp() {
        currentTempLabel = new Label(weatherManager.getTemperature().toString()+"°C");
        currentTempLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 40;"+"-fx-margin-right: 10px");
        currentTempLabel.setPrefWidth(WIDTH/5);
        return currentTempLabel;
    }

    public Label setCurrentDesc() {
        currentWeatherDescriptionLabel = new Label(stringMethods.capitalizeFirstLetter(weatherManager.getDescription()));
        currentWeatherDescriptionLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 12;");
        currentWeatherDescriptionLabel.setWrapText(true);
        currentWeatherDescriptionLabel.setPrefWidth(WIDTH/5);
        currentWeatherDescriptionLabel.setPrefHeight(49);
        return currentWeatherDescriptionLabel;
    }

    public Image setWeatherIcon() {
        currentWeatherIcon = new Image(IconManager.getImage(weatherManager.getIcon()), 67, 61, false, false);
        return currentWeatherIcon;
    }

    public Label setCurrentDay(String language) {
        currentDayLabel = new Label(labelDescription.dayLabelDescription(language));
        currentDayLabel.setStyle("-fx-background-color: #43709A;"+"-fx-background-radius: 50;"+
                "-fx-text-fill: white;"+"-fx-font-size: 20;"+"-fx-alignment: center;");
        currentDayLabel.setPrefWidth(WIDTH*0.31);
        currentDayLabel.setPrefHeight(43);
        return currentDayLabel;
    }

    public Label setCurrentWindSpeed(String language) {
        currentWindSpeedLabel = new Label(labelDescription.windSpeedLabelDescription(language) + weatherManager.getWindSpeed() +" m/s");
        currentWindSpeedLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 12");
        return currentWindSpeedLabel;
    }

    public Label setCurrentCloudiness(String language) {
        currentCloudinessLabel = new Label(labelDescription.cloudinessLabelDescription(language) + weatherManager.getCloudiness() + "%");
        currentCloudinessLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 12");
        return currentCloudinessLabel;
    }

    public Label setCurrentPressure(String language) {
        currentPressureLabel = new Label(labelDescription.pressureLabelDescription(language) + weatherManager.getPressure() + " hpa");
        currentPressureLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 12");
        return currentPressureLabel;
    }

    public Label setCurrentHumidity(String language) {
        currentHumidityLabel = new Label(labelDescription.humidityLabelDescription(language) + weatherManager.getHumidity() + "%");
        currentHumidityLabel.setStyle("-fx-text-fill: white;"+"-fx-font-size: 12");
        return currentHumidityLabel;
    }

    public GridPane setCurrentWeatherDatabox(String language) {
        currentWeatherData = new GridPane();
        currentWeatherData.add(currentWeatherDescriptionLabel = setCurrentDesc(), 0, 0);
        currentWeatherData.add(currentWindSpeedLabel = setCurrentWindSpeed(language), 0, 1);
        currentWeatherData.add(currentCloudinessLabel = setCurrentCloudiness(language), 0, 2);
        currentWeatherData.add(currentPressureLabel = setCurrentPressure(language), 1, 0);
        currentWeatherData.add(currentHumidityLabel = setCurrentHumidity(language), 1, 1);
        currentWeatherData.setPrefWidth(WIDTH/2);
        currentWeatherData.setPrefHeight(70);
        return currentWeatherData;
    }

    public HBox setCurrentWeather(String citySet, String language) {
        weatherManager.getCurrentWeatherData(citySet, language);
        currentWeatherHBox = new HBox(currentWeatherView(language));
        return currentWeatherHBox;
    }
}
