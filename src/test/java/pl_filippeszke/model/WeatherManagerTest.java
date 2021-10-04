package pl_filippeszke.model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl_filippeszke.GetWeatherStubCurrentLocation;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl_filippeszke.Config.OPEN_WEATHER_MAP_API_KEY;
import static org.mockito.Mockito.mock;

class WeatherManagerTest {

    @Test
    void shouldReturnJsonFromReceivedURL() {

        //given
        WeatherManager weatherManager = new WeatherManager(new HttpWeatherApi());
        String city = "Warszawa";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
                "&appid=" + OPEN_WEATHER_MAP_API_KEY + "&lang=pl&units=metric";

        //when
        JSONObject json = weatherManager.readJsonFromUrl(url); // calls real api in test

        //then
        assertThat(json, is(not(nullValue())));
    }

    @Test
    void shouldReturnNullValueWhenDailyForecastFromApiNotFound() {
        //given
        WeatherApi currentWeatherData = mock(WeatherApi.class);
        WeatherManager weatherManager = new WeatherManager(currentWeatherData);

        //when
        String currentWeatherDescription = weatherManager.getDescription();

        //then
        assertThat(currentWeatherDescription, nullValue());
    }

    @Test
    void shouldReturnCorrectDescriptionWhenJsonStringIsCorrect() {

        //given
        WeatherApi currentWeatherData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(currentWeatherData);

        //when
        JSONObject weather = weatherManager.getCurrentWeatherData("sample string", "polski");

        //then
        assertThat(weatherManager.getDescription(), is("bezchmurnie"));
    }

    @Test
    void shouldReturnCorrectTemperatureWhenJsonStringIsCorrect() {

        //given
        WeatherApi currentWeatherData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(currentWeatherData);

        //when
        JSONObject weather = weatherManager.getCurrentWeatherData("sample string", "polski");

        //then
        assertThat(weatherManager.getTemperature(), is(11));
    }

    @Test
    void shouldReturnCorrectWindSpeedWhenJsonStringIsCorrect() {

        //given
        WeatherApi currentWeatherData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(currentWeatherData);

        //when
        JSONObject weather = weatherManager.getCurrentWeatherData("sample string", "polski");

        //then
        assertThat(weatherManager.getWindSpeed(), is("0.45"));
    }

    @Test
    void shouldReturnCorrectMaxTemperatureWhenDailyForecastDataFromApiIsFound() throws IOException {
        //given
        WeatherApi dailyForecastData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(dailyForecastData);

        //when
        Map<String, Object> weather = weatherManager.getDailyForecast(0, 0, "polski");
        ArrayList<Map<String, String>> dailyForecast = (ArrayList<Map<String, String>>) weather.get("daily");
        String result = showMaxTemperature(dailyForecast);

        //then
        assertThat(result, is("16°C"));
    }

    @Test
    void shouldReturnCorrectDescriptionWhenDailyForecastDataFromApiIsFound() throws IOException {
        //given
        WeatherApi dailyForecastData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(dailyForecastData);

        //when
        Map<String, Object> weather = weatherManager.getDailyForecast(0, 0, "polski");
        ArrayList<Map<String, String>> dailyForecast = (ArrayList<Map<String, String>>) weather.get("daily");
        String result = displayDailyDescription(dailyForecast);

        //then
        assertThat(result, is("Średnie zachmurzenie w ciągu dnia."));
    }

    @Test
    void shouldReturnNullWhenDailyForecastDataFromApiIsNotFound() throws IOException {
        //given
        WeatherApi dailyForecastData = new GetWeatherStubCurrentLocation();
        WeatherManager weatherManager = new WeatherManager(dailyForecastData);

        //when
        Map<String, Object> weather = weatherManager.getDailyForecast(0, 0, "polski");

        //then
        assertThat(weather.get("high"), nullValue());
    }

    public String showMaxTemperature(ArrayList<Map<String, String>> dailyForecast) {
        String result = null;
        for ( int i = -1; i < dailyForecast.size(); i++) {
            if( i > -1 ) {
                result = dailyForecast.get(0).get("high");
            }
        }
        return result;
    }

    public String displayDailyDescription(ArrayList<Map<String, String>> dailyForecast) {
        String result = null;
        for ( int i = -1; i < dailyForecast.size(); i++) {
            if( i > -1 ) {
                result = dailyForecast.get(2).get("summary");
            }
        }
        return result;
    }
}