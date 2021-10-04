package pl_filippeszke;

import pl_filippeszke.model.HttpWeatherApi;
import pl_filippeszke.model.WeatherManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class GetWeatherSearchedLocation {

    static WeatherManager weatherManager = new WeatherManager(new HttpWeatherApi());

    public List<Double> getCurrentCoordinatesSearchedLocation(String city) {

        JSONObject json;
        JSONObject json_specific;
        String language = "polish"; // not relevant here

        json = weatherManager.returnJsonFromAPI(city, language);
        json_specific = json.getJSONObject("coord");

        List<Double> coordinates = new ArrayList<>();
        coordinates.add(0, json_specific.getDouble("lat"));
        coordinates.add(1, json_specific.getDouble("lon"));

        return coordinates;
    }

    public static Map<String, Object> getDailyForecastSearchedLocation(double lat, double lon, String language) throws IOException, JSONException {

        Map<String, Object> weatherData = new HashMap<>();
        weatherData = weatherManager.getDailyForecast(lat, lon, language);
        return weatherData;
    }
}

