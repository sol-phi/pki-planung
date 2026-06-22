package org.acme.MenuPlanning.domain;

public enum Ingredient {
    // Kohlenhydrate
    PASTA(IngredientType.KOHLENHYDRAT),
    KARTOFFELN(IngredientType.KOHLENHYDRAT),
    REIS(IngredientType.KOHLENHYDRAT),
    // Proteine
    HAEHNCHEN(IngredientType.PROTEIN),
    SCHWEIN(IngredientType.PROTEIN),
    FISCH(IngredientType.PROTEIN),
    TOFU(IngredientType.PROTEIN),
    FLEISCHERSATZ(IngredientType.PROTEIN),
    // Gemüse
    ZWIEBELN(IngredientType.GEMUESE),
    BOHNEN(IngredientType.GEMUESE),
    SPINAT(IngredientType.GEMUESE),
    TOMATE(IngredientType.GEMUESE),
    PILZE(IngredientType.GEMUESE),
    ZUCCHINI(IngredientType.GEMUESE),
    BROKKOLI(IngredientType.GEMUESE),
    // Saucen
    SAHNESAUCE(IngredientType.SAUCE),
    TOMATENSAUCE(IngredientType.SAUCE),
    PFEFFERSAUCE(IngredientType.SAUCE),
    SOUR_CREAM(IngredientType.SAUCE);

    private final IngredientType type;
    Ingredient(IngredientType type) { this.type = type; }
    public IngredientType getType() { return type; }
}