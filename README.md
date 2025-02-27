# **WeatherApp**

A simple Spring Boot web app that fetches and displays weather data using the OpenWeather API.

## Features

* Retrieves real-time weather data
* Displays temperature and weather conditions for a given city

# **Getting started**

* Clone the repository
#####
    https://github.com/alexcuruti/weatherapp.git
    cd weatherapp

* Set up the API key
    * head to `openweathermap.org`, register or sign in if you already have an account, and get your own free API key
    * open `application.properties.example` and replace `YOUR_SECRET_API_KEY` with the actual API key from OpenWeather
    * execute this command in a terminal inside the folder that contains the file
#####
    cp application.properties.example application.properties

* Run the application
Execute this command in a terminal inside the folder with the app
#####
    mvnw spring-boot:run
* Open the website and insert a city of your preference
#####
    http://localhost:8080
* Enjoy
