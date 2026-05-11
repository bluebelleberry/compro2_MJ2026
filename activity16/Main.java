package com.weather.app;

import com.weather.app.models.*;
import com.weather.app.services.WeatherService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Longitude: ");
        double lon = scanner.nextDouble();

        System.out.print("Enter Latitude: ");
        double lat = scanner.nextDouble();

        System.out.println("\nFetching weather data for the Longitude: "
                + lon + ", Latitude: " + lat + "...");

        WeatherService service = new WeatherService();

        WeatherResponse response = service.getForecast(lat, lon);

        // defensive programming
        if (response == null || response.getDataseries() == null) {
            System.out.println("Could not retrieve weather data.");
            scanner.close();
            return;
        }

        List<Forecast> forecasts = response.getDataseries();

        int limit = Math.min(3, forecasts.size());

        System.out.println("\nWeather Forecast:\n");

        for (int i = 0; i < limit; i++) {

            Forecast forecast = forecasts.get(i);

            Wind wind = forecast.getWind();

            System.out.println(
                    "At hour " + forecast.getTimepoint()
                    + ": " + forecast.getTemp2m() + "°C with "
                    + wind.getSpeed() + " speed winds from the "
                    + wind.getDirection() + "."
            );
        }

        scanner.close();
    }
}