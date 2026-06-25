package org.acme.MenuPlanning.domain;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Meal {
    private String name;
    private int mealNumber;
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

        // Vegetarian - check
        this.isVegetarian = isVegetarianProtein(mainProtein);

        // Assign popularity based on menu number
        this.popularity = assignPopularity(mealNumber);
    }


    private static Popularity assignPopularity(int mealNumber) {

        if (mealNumber == 2 || mealNumber == 12 || mealNumber == 17 || mealNumber == 26) {
            return Popularity.DESIRED;
        }

        if (mealNumber == 1 || mealNumber == 9 || mealNumber == 14 || mealNumber == 15 ||
                mealNumber == 20 || mealNumber == 21 || mealNumber == 24 || mealNumber == 25) {
            return Popularity.POPULAR;
        }

        if (mealNumber == 4 || mealNumber == 5 || mealNumber == 6 || mealNumber == 10 ||
                mealNumber == 16 || mealNumber == 22 || mealNumber == 27 || mealNumber == 29) {
            return Popularity.UNPOPULAR;
        }

        return Popularity.NEUTRAL;
    }


    // Checks if a protein is vegetarian
    private static boolean isVegetarianProtein(Ingredient protein) {
        if (protein == null) {
            return true;
        }
        return "TOFU".equals(protein.getName()) || "FLEISCHERSATZ".equals(protein.getName());
    }

    /**
     * Task 5b - calculate cost
     */
    public int getCostInCents() {
        int cost = 0;
        Set<Ingredient> alreadyCounted = new HashSet<>();

        // Carbs - 20ct
        if (this.mainCarb != null) {
            cost += 20;
            alreadyCounted.add(this.mainCarb);
        }

        // Protein - 100ct
        if (this.mainProtein != null) {
            cost += 100;
            alreadyCounted.add(this.mainProtein);
        }

        // Vegetables and sauces
        if (this.ingredients != null) {
            for (Ingredient ingredient : this.ingredients) {
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
     * Task 5c - Calculate selling price based on popularity
     */
    public int getSalesPriceInCents() {
        int materialCost = getCostInCents();
        double priceFactor = this.popularity.getPriceFactor();
        return (int) Math.round(materialCost * priceFactor);
    }

    /**
     * Task 5c - Calculate profit
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