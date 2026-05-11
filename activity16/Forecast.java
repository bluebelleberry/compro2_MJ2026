package com.weather.app.models;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    private int timepoint;

    @SerializedName("temp2m")
    private int temp2m;

    @SerializedName("wind10m")
    private Wind wind;

    public int getTimepoint() {
        return timepoint;
    }

    public int getTemp2m() {
        return temp2m;
    }

    public Wind getWind() {
        return wind;
    }
}