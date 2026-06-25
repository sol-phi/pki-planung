package org.acme.MenuPlanning.domain;

public class Ingredient {
    private String name;
    private IngredientType type;

    public Ingredient(String name, IngredientType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public IngredientType getType() { return type; }
}