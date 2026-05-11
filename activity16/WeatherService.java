package com.weather.app.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import com.weather.app.models.WeatherResponse;

public class WeatherService {

    private HttpClient client;
    private Gson gson;

    public WeatherService() {
        client = HttpClient.newHttpClient();
        gson = new Gson();
    }

    public WeatherResponse getForecast(double lat, double lon) {

        String url = "https://www.7timer.info/bin/astro.php"
                + "?lon=" + lon
                + "&lat=" + lat
                + "&ac=0&unit=metric&output=json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                return gson.fromJson(
                        response.body(),
                        WeatherResponse.class
                );

            } else {
                System.out.println("Error: Received status code "
                        + response.statusCode());
            }

        } catch (IOException e) {
            System.out.println("Network error occurred: "
                    + e.getMessage());

        } catch (InterruptedException e) {
            System.out.println("Request was interrupted: "
                    + e.getMessage());
        }

        return null;
    }
}