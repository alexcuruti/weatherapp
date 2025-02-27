package com.example.weatherapp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    @Value("${api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public String fetchWeather(String city) {
        String url = BASE_URL + city + "&appid=" + apiKey + "&units=metric";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseWeatherData(response.body());
        } catch (IOException | InterruptedException e) {
            return "Error retrieving weather data: " + e.getMessage();
        }
    }

    private String parseWeatherData(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            
            if (root.has("cod") && root.get("cod").asInt() != 200) {
                return "City not found or API error: " + root.get("message").asText();
            }

            String cityName = root.get("name").asText();
            double temperature = root.get("main").get("temp").asDouble();
            String weatherDescription = root.get("weather").get(0).get("description").asText();

            return "Weather in " + cityName + ": " + temperature + "°C, " + weatherDescription;
        } catch (Exception e) {
            return "Error reading weather data: " + e.getMessage();
        }
    }
}
