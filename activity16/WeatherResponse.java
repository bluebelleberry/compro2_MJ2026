package com.weather.app.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    private String product;

    @SerializedName("dataseries")
    private List<Forecast> dataseries;

    public String getProduct() {
        return product;
    }

    public List<Forecast> getDataseries() {
        return dataseries;
    }
}