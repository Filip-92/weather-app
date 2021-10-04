package pl_filippeszke.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public interface WeatherApi {

    JSONObject readJsonFromUrl(String url) throws IOException;

    JSONObject returnJsonFromAPI(String city, String language);

    JSONObject CreatingJsonObject(URL url) throws IOException;

    Map<String, String> ExtractDailyForecastFromApi(JSONObject obj);

    List<Object> extractDataFromJson(JSONArray daily, List<Object> dailyData);
}
