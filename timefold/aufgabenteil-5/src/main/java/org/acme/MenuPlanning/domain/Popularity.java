package org.acme.MenuPlanning.domain;

public class Popularity {

    public static final Popularity UNPOPULAR = new Popularity("UNPOPULAR", 1.5);
    public static final Popularity NEUTRAL = new Popularity("NEUTRAL", 2.2);
    public static final Popularity POPULAR = new Popularity("POPULAR", 2.5);
    public static final Popularity DESIRED = new Popularity("DESIRED", 3.0);

    private String name;
    private double priceFactor;

    public Popularity(String name, double priceFactor) {
        this.name = name;
        this.priceFactor = priceFactor;
    }

    public String getName() { return name; }
    public double getPriceFactor() { return priceFactor; }
}