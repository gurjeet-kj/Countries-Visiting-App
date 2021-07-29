package com.example.countriesvisiting;

public class SelectedCity {
    private String cityName;
    private int days;
    private double total;

    public SelectedCity(String cityName, int days, double total) {
        this.cityName = cityName;
        this.days = days;
        this.total = total;
    }

    public String getCityName() {
        return cityName;
    }

    public int getDays() {
        return days;
    }

    public double getTotal() {
        return total;
    }
}
