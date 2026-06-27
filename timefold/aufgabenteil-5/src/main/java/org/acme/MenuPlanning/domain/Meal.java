package org.acme.MenuPlanning.domain;

import java.util.List;

public class Meal {
    private String name;
    private int mealIndex;
    private List<Ingredient> ingredients;
    private boolean isVegetarian;
    private Popularity popularity;

    public Meal() {}

    public Meal(int mealIndex, String name, List<Ingredient> ingredients, Popularity popularity) {
        this.mealIndex = mealIndex;
        this.name = name;
        this.ingredients = ingredients;
        this.isVegetarian = this.isVegetarianMeal();
        this.popularity = popularity;
    }

    // Checks if a meal is vegetarian
    private boolean isVegetarianMeal() {
        if (this.ingredients == null) return true;
        Ingredient protein = getMainProtein();
        if (protein != null) {
            return "TOFU".equals(protein.getName()) || "FLEISCHERSATZ".equals(protein.getName());
        }
        return true;
    }

    /**
     * Task 5b - calculate cost
     */
    public int getCostInCents() {
        int cost = 0;
        if (this.ingredients == null) return cost;

        for (Ingredient ingredient : this.ingredients) {
            switch (ingredient.getType().getName()) {
                case "KOHLENHYDRAT":
                    cost += 20;
                    break;
                case "PROTEIN":
                    cost += 100;
                    break;
                case "GEMUESE":
                    cost += 50;
                    break;
                case "SAUCE":
                    cost += 5;
                    break;
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

    public int getMealIndex() { return mealIndex; }
    public void setMealIndex(int mealIndex) { this.mealIndex = mealIndex; }

    public List<Ingredient> getIngredients() { return ingredients; }
    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }

    // Assumes meals only contain one of each
    public Ingredient getMainCarb() {
        if (ingredients == null) return null;
        return ingredients.stream()
                .filter(i -> "KOHLENHYDRAT".equals(i.getType().getName()))
                .findFirst()
                .orElse(null);
    }
    public Ingredient getMainProtein() {
        if (ingredients == null) return null;
        return ingredients.stream()
                .filter(i -> "PROTEIN".equals(i.getType().getName()))
                .findFirst()
                .orElse(null);
    }

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