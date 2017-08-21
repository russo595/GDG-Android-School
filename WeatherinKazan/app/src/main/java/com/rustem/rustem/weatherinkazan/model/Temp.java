package com.rustem.rustem.weatherinkazan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {
    @SerializedName("day")
    @Expose
    private double day;
    @SerializedName("min")
    @Expose
    private double min;
    @SerializedName("max")
    @Expose
    private double max;
    @SerializedName("night")
    @Expose
    private double night;
    @SerializedName("eve")
    @Expose
    private double evening;
    @SerializedName("morn")
    @Expose
    private double morning;

    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEvening() {
        return evening;
    }

    public void setEvening(double evening) {
        this.evening = evening;
    }

    public double getMorning() {
        return morning;
    }

    public void setMorning(double morning) {
        this.morning = morning;
    }
}
