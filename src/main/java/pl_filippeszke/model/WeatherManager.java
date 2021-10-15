package pl_filippeszke.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

import static pl_filippeszke.Config.OPEN_WEATHER_MAP_API_KEY;

public class WeatherManager {

    private WeatherApi weatherApi;
    private Integer temperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String cloudiness;
    private String pressure;
    private String humidity;

    public WeatherManager(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public JSONObject returnJsonFromAPI(String city, String language) {
        return weatherApi.returnJsonFromAPI(city, language);
    }

    public JSONObject readJsonFromUrl(String url) {
        try {
            return weatherApi.readJsonFromUrl(url);
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO properly handle error
        }
    }

    public Map<String, Object> getDailyForecast(double lat, double lon, String language) throws IOException, JSONException {

        String apiString = "https://api.darksky.net/forecast/40c495d946e7f24d0470be483f05d4d6/" + lat + "," + lon;
        if(language.equals(("polish"))) {
            apiString = apiString + "?lang=pl";
        }

        URL url = new URL(apiString);

        JSONObject obj = weatherApi.CreatingJsonObject(url);

        Map<String, String> currentData = weatherApi.ExtractDailyForecastFromApi(obj);
        List<Object> dailyData = new ArrayList<>();
        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("current", currentData);

        JSONArray daily = obj.getJSONObject("daily").getJSONArray("data");
        {
            dailyData = weatherApi.extractDataFromJson(daily, dailyData);
        }
        weatherData.put("daily", dailyData);
        return weatherData;
    }

    public JSONObject getCurrentWeatherData(String city, String language) {
        JSONObject json;
        JSONObject json_specific;

        json = weatherApi.returnJsonFromAPI(city, language);

        json_specific = json.getJSONObject("main");
        this.temperature = json_specific.getInt("temp");
        this.pressure = json_specific.get("pressure").toString();
        this.humidity = json_specific.get("humidity").toString();
        json_specific = json.getJSONObject("wind");
        this.windSpeed = json_specific.get("speed").toString();
        json_specific = json.getJSONObject("clouds");
        this.cloudiness = json_specific.get("all").toString();

        json_specific = json.getJSONArray("weather").getJSONObject(0);
        this.description = json_specific.get("description").toString();
        this.icon = json_specific.get("icon").toString();
        return  json_specific;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }
}
