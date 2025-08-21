package com.example.gdp;

public class GDPData {
    private String year;
    private Double value;

    public GDPData(String year, Double value) {
        this.year = year;
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Year: " + year + ", GDP: " + value;
    }
}
