package org.acme.MenuPlanning.domain;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public enum Popularity {
    UNPOPULAR(1.5),      // Materialkosten * 1.5
    NEUTRAL(2.2),        // Materialkosten * 2.2
    POPULAR(2.5),        // Materialkosten * 2.5
    DESIRED(3.0);        // Materialkosten * 3.0

    private final double priceFactor;

    Popularity(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public double getPriceFactor() {
        return priceFactor;
    }
}