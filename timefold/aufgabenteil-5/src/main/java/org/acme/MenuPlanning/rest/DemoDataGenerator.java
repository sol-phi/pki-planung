package org.acme.MenuPlanning.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.MenuPlanning.domain.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DemoDataGenerator {

    private final Ingredient PASTA = new Ingredient("PASTA", IngredientType.KOHLENHYDRAT);
    private final Ingredient KARTOFFELN = new Ingredient("KARTOFFELN", IngredientType.KOHLENHYDRAT);
    private final Ingredient REIS = new Ingredient("REIS", IngredientType.KOHLENHYDRAT);
    private final Ingredient HAEHNCHEN = new Ingredient("HAEHNCHEN", IngredientType.PROTEIN);
    private final Ingredient SCHWEIN = new Ingredient("SCHWEIN", IngredientType.PROTEIN);
    private final Ingredient FISCH = new Ingredient("FISCH", IngredientType.PROTEIN);
    private final Ingredient TOFU = new Ingredient("TOFU", IngredientType.PROTEIN);
    private final Ingredient FLEISCHERSATZ = new Ingredient("FLEISCHERSATZ", IngredientType.PROTEIN);
    private final Ingredient ZWIEBELN = new Ingredient("ZWIEBELN", IngredientType.GEMUESE);
    private final Ingredient BOHNEN = new Ingredient("BOHNEN", IngredientType.GEMUESE);
    private final Ingredient SPINAT = new Ingredient("SPINAT", IngredientType.GEMUESE);
    private final Ingredient TOMATE = new Ingredient("TOMATE", IngredientType.GEMUESE);
    private final Ingredient PILZE = new Ingredient("PILZE", IngredientType.GEMUESE);
    private final Ingredient ZUCCHINI = new Ingredient("ZUCCHINI", IngredientType.GEMUESE);
    private final Ingredient BROKKOLI = new Ingredient("BROKKOLI", IngredientType.GEMUESE);
    private final Ingredient SAHNESAUCE = new Ingredient("SAHNESAUCE", IngredientType.SAUCE);
    private final Ingredient TOMATENSAUCE = new Ingredient("TOMATENSAUCE", IngredientType.SAUCE);
    private final Ingredient PFEFFERSAUCE = new Ingredient("PFEFFERSAUCE", IngredientType.SAUCE);
    private final Ingredient SOUR_CREAM = new Ingredient("SOUR_CREAM", IngredientType.SAUCE);

    public MenuPlan generateDemoData() {
        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal("1. Pasta mit Hähnchen und Spinat in Sahnesauce", 1, PASTA, HAEHNCHEN, List.of(PASTA, HAEHNCHEN, SPINAT, SAHNESAUCE)));
        meals.add(new Meal("2. Bratreis mit Schwein, Brokkoli und Pfeffersauce", 2, REIS, SCHWEIN, List.of(REIS, SCHWEIN, BROKKOLI, PFEFFERSAUCE)));
        meals.add(new Meal("3. Tofu-Zucchini Curry im Reisrand", 3, REIS, TOFU, List.of(REIS, TOFU, ZUCCHINI)));
        meals.add(new Meal("4. Ofenkartoffel mit Sour Cream", 4, KARTOFFELN, null, List.of(KARTOFFELN, SOUR_CREAM)));
        meals.add(new Meal("5. Pasta mit Pilzen in Sahnesauce", 5, PASTA, null, List.of(PASTA, PILZE, SAHNESAUCE)));
        meals.add(new Meal("6. Reisbowl mit Tofu, Spinat und Sour Cream", 6, REIS, TOFU, List.of(REIS, TOFU, SPINAT, SOUR_CREAM)));
        meals.add(new Meal("7. Pasta mit Zucchini, Tomaten und Pfeffersauce", 7, PASTA, null, List.of(PASTA, ZUCCHINI, TOMATE, PFEFFERSAUCE)));
        meals.add(new Meal("8. Gefüllte Kartoffel mit Hähnchen, Zwiebeln und Sahnesauce", 8, KARTOFFELN, HAEHNCHEN, List.of(KARTOFFELN, HAEHNCHEN, ZWIEBELN, SAHNESAUCE)));
        meals.add(new Meal("9. Reispfanne mit Brokkoli, Bohnen und Tomatensauce", 9, REIS, null, List.of(REIS, BROKKOLI, BOHNEN, TOMATENSAUCE)));
        meals.add(new Meal("10. Kartoffelauflauf mexikanisch mit Schwein", 10, KARTOFFELN, SCHWEIN, List.of(KARTOFFELN, SCHWEIN, BOHNEN, TOMATENSAUCE, SOUR_CREAM)));
        meals.add(new Meal("11. Bratkartoffeln mit Fisch, Spinatbeilage und Pfeffersauce", 11, KARTOFFELN, FISCH, List.of(KARTOFFELN, FISCH, SPINAT, PFEFFERSAUCE)));
        meals.add(new Meal("12. Reispfanne mit Tofu, Pilzen und Brokkoli an Tomatensauce", 12, REIS, TOFU, List.of(REIS, TOFU, PILZE, BROKKOLI, TOMATENSAUCE)));
        meals.add(new Meal("13. Pasta mit Bohnen, Zwiebeln und Pfeffersauce", 13, PASTA, null, List.of(PASTA, BOHNEN, ZWIEBELN, PFEFFERSAUCE)));
        meals.add(new Meal("14. Nudelauflauf mit Hähnchen, Tomaten und Tomatensauce", 14, PASTA, HAEHNCHEN, List.of(PASTA, HAEHNCHEN, TOMATE, TOMATENSAUCE)));
        meals.add(new Meal("15. Reispfanne mit Schwein, Zucchini und Pfeffersauce", 15, REIS, SCHWEIN, List.of(REIS, SCHWEIN, ZUCCHINI, PFEFFERSAUCE)));
        meals.add(new Meal("16. Kartoffelsuppe mit Zwiebeln und Fleischersatzeinlage", 16, KARTOFFELN, FLEISCHERSATZ, List.of(KARTOFFELN, ZWIEBELN, FLEISCHERSATZ)));
        meals.add(new Meal("17. Bohneneintopf mit Zucchini und Tomaten mit Reiseinlage", 17, REIS, null, List.of(BOHNEN, ZUCCHINI, TOMATE, REIS)));
        meals.add(new Meal("18. Pilzpfanne Stroganoff Art mit Pasta und Sahnesauce", 18, PASTA, null, List.of(PILZE, PASTA, SAHNESAUCE)));
        meals.add(new Meal("19. Hähnchen Stroganoff Art mit Pilzen, Pasta und Sahnesauce", 19, PASTA, HAEHNCHEN, List.of(HAEHNCHEN, PILZE, PASTA, SAHNESAUCE)));
        meals.add(new Meal("20. Reispfanne mit Hähnchen, Brokkoli und Pfeffersauce", 20, REIS, HAEHNCHEN, List.of(REIS, HAEHNCHEN, BROKKOLI, PFEFFERSAUCE)));
        meals.add(new Meal("21. Kartoffelreibekuchen mit Zucchini und Zwiebeln", 21, KARTOFFELN, null, List.of(KARTOFFELN, ZUCCHINI, ZWIEBELN)));
        meals.add(new Meal("22. Tomatiger Fischeintopf mit Tomaten und Bohnen", 22, null, FISCH, List.of(TOMATE, FISCH, BOHNEN)));
        meals.add(new Meal("23. Curryeintopf mit Tofu, Spinat und Brokkoli", 23, null, TOFU, List.of(TOFU, SPINAT, BROKKOLI)));
        meals.add(new Meal("24. Bohnenchili mit Tomaten, Zucchini und Reisbeilage", 24, REIS, null, List.of(BOHNEN, TOMATE, ZUCCHINI, REIS)));
        meals.add(new Meal("25. Kartoffelpfanne mit Brokkoli und Pfeffersauce", 25, KARTOFFELN, null, List.of(KARTOFFELN, BROKKOLI, PFEFFERSAUCE)));
        meals.add(new Meal("26. Pasta Primavera mit Zucchini, Tomaten, Zwiebeln und Sahnesauce", 26, PASTA, null, List.of(PASTA, ZUCCHINI, TOMATE, ZWIEBELN, SAHNESAUCE)));
        meals.add(new Meal("27. Hähncheneintopf mit Bohnen, Tomaten und Nudeleinlage", 27, PASTA, HAEHNCHEN, List.of(HAEHNCHEN, BOHNEN, TOMATE, PASTA)));
        meals.add(new Meal("28. Pilzrisotto mit Fleischersatz", 28, REIS, FLEISCHERSATZ, List.of(PILZE, REIS, FLEISCHERSATZ)));
        meals.add(new Meal("29. Kartoffelauflauf mit Spinat und Sour Cream", 29, KARTOFFELN, null, List.of(KARTOFFELN, SPINAT, SOUR_CREAM)));
        meals.add(new Meal("30. Schweineschnitzel mit Bratkartoffeln und Pfeffersauce", 30, KARTOFFELN, SCHWEIN, List.of(SCHWEIN, KARTOFFELN, PFEFFERSAUCE)));
        meals.add(new Meal("31. Gebratener Tofu mit Pilzen auf Nudeln mit Sahnesauce", 31, PASTA, TOFU, List.of(TOFU, PILZE, PASTA, SAHNESAUCE)));

        List<MealAssignment> assignments = new ArrayList<>();
        long idCounter = 1;
        for (int day = 1; day <= 30; day++) {
            assignments.add(new MealAssignment(idCounter++, day, 1));
            assignments.add(new MealAssignment(idCounter++, day, 2));
        }

        return new MenuPlan(meals, assignments);
    }
}