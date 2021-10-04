package pl_filippeszke.model;

import pl_filippeszke.GetWeatherCurrentLocation;
import pl_filippeszke.model.auxiliaryMethod.CurrentTimeAndDate;
import pl_filippeszke.model.auxiliaryMethod.LabelDescription;
import pl_filippeszke.model.auxiliaryMethod.StatusImage;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DailyForecastView {

    Map<String, Object> weatherData = new HashMap<>();
    LabelDescription labelDescription = new LabelDescription();
    CurrentTimeAndDate currentDate = new CurrentTimeAndDate(Clock.systemDefaultZone());
    private static final int WIDTH=1315;
    String nextDay;

    @FXML
    VBox dailyPane;

    @FXML
    HBox daily;

    @FXML
    Label day, high, low, summary;

    @FXML
    Node summaryIcon;

    @FXML
    Image icon;

    public VBox setDaily(Double lat, Double lon, String language) throws ParseException, IOException {

        dailyPane = new VBox();
        dailyPane.setPrefWidth(WIDTH*0.625);
        dailyPane.setAlignment(Pos.CENTER);

        weatherData = GetWeatherCurrentLocation.getDailyForecastActualLocation(lat, lon, language);

        ArrayList<Map<String, String>> dailyForecast = (ArrayList<Map<String, String>>) weatherData.get("daily");
        dailyPane = setDailyView(dailyForecast, dailyPane, language);

        return dailyPane;
    }

    private VBox setDailyView(ArrayList<Map<String, String>> dailyForecast, VBox dailyPane, String language) throws ParseException {

        for ( int i = -1; i < dailyForecast.size(); i++) {
            daily = new HBox();
            daily.setPadding(new Insets(0, 15, 25, 15));

            day = setDayDailyLabel(i, language);
            summaryIcon = setDailyIcon(dailyForecast, i);
            high = setHighDailyLabel(dailyForecast, i);
            low = setLowDailyLabel(dailyForecast, i);
            summary = setSummaryDailyLabel(dailyForecast, i);

            daily.getChildren().addAll(day, summaryIcon, high, low, summary);
            daily.setSpacing(10);
            dailyPane.getChildren().add(daily);
        }
        return dailyPane;
    }

    private Label setDayDailyLabel ( int i, String language ) throws ParseException {
        day = new Label("dzisiaj");
        day.setVisible(false);
        day.setManaged(false);
        if( i > -1 ) {
            if(language.equals("polish")) {
                day = new Label(nextDay(i+1));
            } else {
                day = new Label(labelDescription.translateWeekDay(nextDay(i+1)));
            }
        }
        day.setStyle("-fx-background-color: #43709A;"+"-fx-background-radius: 50;"+
                "-fx-text-fill: white;"+"-fx-font-size: 20;"+"-fx-alignment: center");
        day.setMaxHeight(43);
        day.setPrefWidth(WIDTH/8);
        return day;
    }

    protected String nextDay ( int days ) {
        final DateTimeFormatter currentDay = DateTimeFormatter.ofPattern("EEEE");
        LocalDateTime today = currentDate.currentDay();
        nextDay = today.plusDays(days).format(currentDay);
        return nextDay;
    }

    private Node setDailyIcon(ArrayList<Map<String, String>> dailyForecast, int i) {
        icon = new Image("/img/icons/icon1.png", 1, 1, false, false);
        if( i > -1 ) {
            icon = new Image(StatusImage.getImage(dailyForecast.get(i).get("icon")), 43, 43, false, false);
        }
        summaryIcon = new ImageView(icon);
        summaryIcon.scaleXProperty();
        return summaryIcon;
    }

    private Label setHighDailyLabel(ArrayList<Map<String, String>> dailyForecast, int i) {
        Label high = new Label("");
        high.setVisible(false);
        high.setManaged(false);
        if( i > -1 ) {
            high = new Label(dailyForecast.get(i).get("high") + "  /");
        }
        high.setStyle("-fx-text-fill: white;" + "-fx-font-size: 30;");
        high.setPadding(new Insets(0, 0, 0, 15));
        high.setMaxHeight(Double.MAX_VALUE);
        high.setPrefWidth(WIDTH * 0.08);
        return high;
    }

    private Label setLowDailyLabel(ArrayList<Map<String, String>> dailyForecast, int i) {
        Label low = new Label("");
        low.setVisible(false);
        low.setManaged(false);
        if( i > -1 ) {
            low = new Label(dailyForecast.get(i).get("low"));
        }
        low.setStyle("-fx-text-fill: white;" + "-fx-font-size: 30;");
        low.setMaxHeight(Double.MAX_VALUE);
        low.setPrefWidth(WIDTH * 0.07);
        return low;
    }

    private Label setSummaryDailyLabel(ArrayList<Map<String, String>> dailyForecast, int i) {
        Label summary = new Label("");
        summary.setVisible(false);
        summary.setManaged(false);
        if( i > -1 ) {
            summary = new Label(dailyForecast.get(i).get("summary"));
            if (dailyForecast.get(i).get("summary").length() >= 50) {
                summary.setStyle("-fx-text-fill: white;" + "-fx-font-size: 10;" + "-fx-word-wrap: break-word;");
            } else {
                summary.setStyle("-fx-text-fill: white;" + "-fx-font-size: 14;" + "-fx-word-wrap: break-word;");
            }
        }
        summary.setMaxHeight(Double.MAX_VALUE);
        summary.setPrefWidth(WIDTH * 0.15);
        summary.setWrapText(true);
        return summary;
    }
}