package org.acme.MenuPlanning.domain;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Meal {
    private String name;
    private int mealNumber;  // 1-31 für Zuordnung der Beliebtheit
    private List<Ingredient> ingredients;
    private Ingredient mainCarb;
    private Ingredient mainProtein;
    private boolean isVegetarian;
    private Popularity popularity;

    public Meal() {}

    public Meal(String name, int mealNumber, Ingredient mainCarb, Ingredient mainProtein, List<Ingredient> ingredients) {
        this.name = name;
        this.mealNumber = mealNumber;
        this.mainCarb = mainCarb;
        this.mainProtein = mainProtein;
        this.ingredients = ingredients;

        // Vegetarisch-Check: Kein tierisches Protein
        this.isVegetarian = isVegetarianProtein(mainProtein);

        // Beliebtheit basierend auf Gericht-Nummer zuordnen
        this.popularity = assignPopularity(mealNumber);
    }

    /**
     *
     * Unbeliebt: 4, 5, 6, 10, 16, 22, 27, 29
     * Beliebt: 1, 9, 14, 15, 20, 21, 24, 25
     * Begehrt: 2, 12, 17, 26
     * Neutral: Alle anderen
     */
    private static Popularity assignPopularity(int mealNumber) {
        // Begehrt
        if (mealNumber == 2 || mealNumber == 12 || mealNumber == 17 || mealNumber == 26) {
            return Popularity.DESIRED;
        }

        // Beliebt
        if (mealNumber == 1 || mealNumber == 9 || mealNumber == 14 || mealNumber == 15 ||
                mealNumber == 20 || mealNumber == 21 || mealNumber == 24 || mealNumber == 25) {
            return Popularity.POPULAR;
        }

        // Unbeliebt
        if (mealNumber == 4 || mealNumber == 5 || mealNumber == 6 || mealNumber == 10 ||
                mealNumber == 16 || mealNumber == 22 || mealNumber == 27 || mealNumber == 29) {
            return Popularity.UNPOPULAR;
        }

        // Neutral
        return Popularity.NEUTRAL;
    }


     // Prüft, ob ein Protein vegetarisch ist

    private static boolean isVegetarianProtein(Ingredient protein) {
        if (protein == null) {
            return true;  // Kein Protein = vegetarisch
        }
        // Nur Tofu und Fleischersatz sind vegetarisch
        return protein == Ingredient.TOFU || protein == Ingredient.FLEISCHERSATZ;
    }

    /**
     * Task 5b - Kostenberechnung
     *
     * - Kohlenhydrate: 20ct/Portion
     * - Protein: 100ct/Portion (gilt auch für Fleischersatz)
     * - Gemüse: 50ct/Portion (pro Zutat, aber nicht doppelt!)
     * - Saucen: 5ct/Portion
     *
     */
    public int getCostInCents() {
        int cost = 0;
        Set<Ingredient> alreadyCounted = new HashSet<>();

        // 1. Kohlenhydrate (20ct)
        if (this.mainCarb != null) {
            cost += 20;
            alreadyCounted.add(this.mainCarb);
        }

        // 2. Protein (100ct)
        if (this.mainProtein != null) {
            cost += 100;
            alreadyCounted.add(this.mainProtein);
        }

        // 3. Gemüse und Saucen (aber nicht doppelt zählen!)
        if (this.ingredients != null) {
            for (Ingredient ingredient : this.ingredients) {
                // Überspringen, wenn schon als Haupt-Komponente gezählt
                if (alreadyCounted.contains(ingredient)) {
                    continue;
                }

                IngredientType type = ingredient.getType();

                if (type == IngredientType.SAUCE) {
                    cost += 5;
                } else if (type == IngredientType.GEMUESE) {
                    cost += 50;
                }

                alreadyCounted.add(ingredient);
            }
        }

        return cost;
    }

    /**
     * Task 5c - Verkaufspreis berechnen basierend auf Beliebtheit
     *
     * Formel: Materialkosten * PopularityFactor
     *
     */
    public int getSalesPriceInCents() {
        int materialCost = getCostInCents();
        double priceFactor = this.popularity.getPriceFactor();
        return (int) Math.round(materialCost * priceFactor);
    }

    /**
     * Task 5c - Gewinn berechnen
     *
     * Gewinn = Verkaufspreis - Materialkosten
     *
     */
    public int getProfitInCents() {
        return getSalesPriceInCents() - getCostInCents();
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getMealNumber() { return mealNumber; }
    public void setMealNumber(int mealNumber) { this.mealNumber = mealNumber; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    public Ingredient getMainCarb() { return mainCarb; }
    public void setMainCarb(Ingredient mainCarb) { this.mainCarb = mainCarb; }

    public Ingredient getMainProtein() { return mainProtein; }
    public void setMainProtein(Ingredient mainProtein) { this.mainProtein = mainProtein; }

    public boolean isVegetarian() { return isVegetarian; }
    public void setVegetarian(boolean vegetarian) { isVegetarian = vegetarian; }

    public Popularity getPopularity() { return popularity; }
    public void setPopularity(Popularity popularity) { this.popularity = popularity; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return name != null && name.equals(meal.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}