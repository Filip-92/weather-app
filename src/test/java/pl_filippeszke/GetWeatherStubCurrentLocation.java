package pl_filippeszke;

import org.json.JSONArray;
import org.json.JSONObject;
import pl_filippeszke.model.WeatherApi;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetWeatherStubCurrentLocation implements WeatherApi {

    private final String file = "src/test/resources/weather.json";

    private final String fileForecast = "src/test/resources/forecast.json";

    private Integer dailyHigh;

    @Override
    public JSONObject readJsonFromUrl(String stringForApiRequest) {
        try {
            return new JSONObject(String.join("", Files.readAllLines(Paths.get(file))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject returnJsonFromAPI(String city, String language) {
        return readJsonFromUrl(file);
    }

    @Override
    public JSONObject CreatingJsonObject(URL url) {
        try {
            return new JSONObject(String.join("", Files.readAllLines(Paths.get(fileForecast))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
