package pl_filippeszke.model.auxiliaryMethod;

import javafx.scene.control.Label;

public class LabelDescription {

    public LabelDescription() {
    }

    public String dayLabelDescription(String language) {

        String currentDayDescription;
        currentDayDescription = changeLabelDescriptionLanguage(language, "dzisiaj", "today");
        return currentDayDescription;
    }

    public String windSpeedLabelDescription(String language) {

        String currentWindSpeedDescription;
        currentWindSpeedDescription = changeLabelDescriptionLanguage(language, "wiatr: ", "wind: ");
        return currentWindSpeedDescription;
    }

    public String cloudinessLabelDescription(String language) {

        String currentCloudinessDescription;
        currentCloudinessDescription = changeLabelDescriptionLanguage(language, "zachmurzenie: ", "cloudiness: ");
        return currentCloudinessDescription;
    }

    public String pressureLabelDescription(String language) {

        String currentPressureDescription;
        currentPressureDescription = changeLabelDescriptionLanguage(language, "ciśnienie: ", "pressure: ");
        return currentPressureDescription;
    }

    public String humidityLabelDescription(String language) {

        String currentHumidityDescription;
        currentHumidityDescription = changeLabelDescriptionLanguage(language, "wilgotność: ", "humidity: ");
        return currentHumidityDescription;
    }

    public String changeLabelDescriptionLanguage(String language, String polishName, String englishName) {

        String labelDescription;
        if(language.equals("polish")) {
            labelDescription = polishName;
        } else {
            labelDescription = englishName;
        }
        return labelDescription;
    }

    public void changeMainWindowLabelsDescription(String language, Label countryInputLabel1, Label countryInputLabel2, Label cityInputLabel1,
                                                  Label cityInputLabel2, Label currentLocationLabel, Label searchedLocationLabel) {
        if(language.equals("polish")) {
            countryInputLabel1.setText("Kraj");
            countryInputLabel2.setText("Kraj");
            cityInputLabel1.setText("Miasto");
            cityInputLabel2.setText("Miasto");
            currentLocationLabel.setText("Aktualna lokalizacja");
            searchedLocationLabel.setText("Wyszukiwana lokalizacja");
        } else {
            countryInputLabel1.setText("Country");
            countryInputLabel2.setText("Country");
            cityInputLabel1.setText("City");
            cityInputLabel2.setText("City");
            currentLocationLabel.setText("Current location");
            searchedLocationLabel.setText("Searched location");
        }
    }

    public String translateWeekDay(String weekDay) {

        if(weekDay.equals("poniedziałek")) {
            weekDay = "Monday";
        } else if(weekDay.equals("wtorek")) {
            weekDay = "Tuesday";
        } else if(weekDay.equals("środa")) {
            weekDay = "Wednesday";
        } else if(weekDay.equals("czwartek")) {
            weekDay = "Thursday";
        } else if(weekDay.equals("piątek")) {
            weekDay = "Friday";
        } else if(weekDay.equals("sobota")) {
            weekDay = "Saturday";
        } else if(weekDay.equals("niedziela")) {
            weekDay = "Sunday";
        }
        return weekDay;
    }
}
