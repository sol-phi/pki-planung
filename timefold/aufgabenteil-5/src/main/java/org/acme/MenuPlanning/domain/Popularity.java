package org.acme.MenuPlanning.domain;

public class Popularity {
    private final String name;
    private final double priceFactor;

    public Popularity(String name, double priceFactor) {
        this.name = name;
        this.priceFactor = priceFactor;
    }

    public String getName() { return name; }
    public double getPriceFactor() { return priceFactor; }
}