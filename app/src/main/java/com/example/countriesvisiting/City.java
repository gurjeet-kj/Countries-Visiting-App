package com.example.countriesvisiting;

public class City {
    private String Country;
    private String City;
    double expense;

    public City(String country, String city, double expense) {
        Country = country;
        City = city;
        this.expense = expense;
    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return City;
    }

    public double getExpense() {
        return expense;
    }
}
