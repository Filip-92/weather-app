package pl_filippeszke.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static pl_filippeszke.Config.OPEN_WEATHER_MAP_API_KEY;

public class HttpWeatherApi implements WeatherApi {

    @Override
    public JSONObject readJsonFromUrl(String url) throws IOException {
        try (InputStream is = new URL(url).openStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String jsonText = readAll(reader);
            JSONObject json = new JSONObject(jsonText);
            reader.close();
            return json;
        }
    }

    @Override
    public JSONObject returnJsonFromAPI(String city, String language) {

        JSONObject json = new JSONObject();
        String lang = "";

        if(language.equals("polish")) {
            lang = "&lang=pl";
        }

        String apiString = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + OPEN_WEATHER_MAP_API_KEY + lang + "&units=metric";

        try {
            json = readJsonFromUrl(apiString);
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }

        return json;
    }

    public String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public JSONObject CreatingJsonObject(URL url) throws IOException {
        Scanner jsonFile = new Scanner(url.openStream(), "UTF-8");
        StringBuilder str = new StringBuilder("");
        while(jsonFile.hasNext())
            str.append(jsonFile.nextLine());
        jsonFile.close();

        String singleString = str.toString();

        JSONObject obj = new JSONObject(singleString);
        return obj;
    }

    @Override
    public Map<String, String> ExtractDailyForecastFromApi(JSONObject obj) {
        Map<String, String> currentData = new HashMap<>();

        currentData.put("icon", obj.getJSONObject("currently").getString("icon"));
        int currentTemp = (int) obj.getJSONObject("currently").getLong("temperature");
        currentData.put("temp", currentTemp +"");
        return currentData;
    }

    @Override
    public List<Object> extractDataFromJson(JSONArray daily, List<Object> dailyData) {

        for ( int i = 1; i < daily.length() - 2; i++) {
            Map<String, String> dailyDataPoint = new HashMap<>();
            dailyDataPoint.put("day",
                    Instant.ofEpochSecond(daily
                                    .getJSONObject(i)
                                    .getLong("time"))
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime().getDayOfWeek().toString());
            dailyDataPoint.put("icon", daily.getJSONObject(i).getString("icon"));
            int dailyHigh = (int) daily
                    .getJSONObject(i)
                    .getLong("temperatureHigh");
            dailyDataPoint.put("high", fahrenheitToCelsius(dailyHigh)+"°C");
            int dailyLow = (int) daily
                    .getJSONObject(i)
                    .getLong("temperatureLow");
            dailyDataPoint.put("low", fahrenheitToCelsius(dailyLow)+"°C");
            dailyDataPoint.put("summary", daily
                    .getJSONObject(i)
                    .getString("summary"));

            dailyData.add(dailyDataPoint);
        }
        return dailyData;
    }

    public int fahrenheitToCelsius(int temp) {
        return ((temp - 32)*5)/9;
    }
}
