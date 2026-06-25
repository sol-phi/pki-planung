package org.acme.MenuPlanning.domain;

public class IngredientType {

    public static final IngredientType KOHLENHYDRAT = new IngredientType("KOHLENHYDRAT");
    public static final IngredientType PROTEIN = new IngredientType("PROTEIN");
    public static final IngredientType GEMUESE = new IngredientType("GEMUESE");
    public static final IngredientType SAUCE = new IngredientType("SAUCE");

    private final String name;

    public IngredientType(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}