package com.example.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String showWeatherForm() {
        return "index";
    }

    @PostMapping("/getWeather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String weather = weatherService.fetchWeather(city);
        model.addAttribute("weather", weather);
        return "index";
    }
}
