package org.acme.MenuPlanning.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.MenuPlanning.domain.*;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DemoDataGenerator {

    private final IngredientType KOHLENHYDRAT = new IngredientType("KOHLENHYDRAT");
    private final IngredientType PROTEIN = new IngredientType("PROTEIN");
    private final IngredientType GEMUESE = new IngredientType("GEMUESE");
    private final IngredientType SAUCE = new IngredientType("SAUCE");

    private final Ingredient PASTA = new Ingredient("PASTA", KOHLENHYDRAT);
    private final Ingredient KARTOFFELN = new Ingredient("KARTOFFELN", KOHLENHYDRAT);
    private final Ingredient REIS = new Ingredient("REIS", KOHLENHYDRAT);
    private final Ingredient HAEHNCHEN = new Ingredient("HAEHNCHEN", PROTEIN);
    private final Ingredient SCHWEIN = new Ingredient("SCHWEIN", PROTEIN);
    private final Ingredient FISCH = new Ingredient("FISCH", PROTEIN);
    private final Ingredient TOFU = new Ingredient("TOFU", PROTEIN);
    private final Ingredient FLEISCHERSATZ = new Ingredient("FLEISCHERSATZ", PROTEIN);
    private final Ingredient ZWIEBELN = new Ingredient("ZWIEBELN", GEMUESE);
    private final Ingredient BOHNEN = new Ingredient("BOHNEN", GEMUESE);
    private final Ingredient SPINAT = new Ingredient("SPINAT", GEMUESE);
    private final Ingredient TOMATE = new Ingredient("TOMATE", GEMUESE);
    private final Ingredient PILZE = new Ingredient("PILZE", GEMUESE);
    private final Ingredient ZUCCHINI = new Ingredient("ZUCCHINI", GEMUESE);
    private final Ingredient BROKKOLI = new Ingredient("BROKKOLI", GEMUESE);
    private final Ingredient SAHNESAUCE = new Ingredient("SAHNESAUCE", SAUCE);
    private final Ingredient TOMATENSAUCE = new Ingredient("TOMATENSAUCE", SAUCE);
    private final Ingredient PFEFFERSAUCE = new Ingredient("PFEFFERSAUCE", SAUCE);
    private final Ingredient SOUR_CREAM = new Ingredient("SOUR_CREAM", SAUCE);

    private final Popularity UNPOPULAR = new Popularity("UNPOPULAR", 1.5);
    private final Popularity NEUTRAL = new Popularity("NEUTRAL", 2.2);
    private final Popularity POPULAR = new Popularity("POPULAR", 2.5);
    private final Popularity DESIRED = new Popularity("DESIRED", 3.0);

    public MenuPlan generateDemoData() {
        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal(1, "1. Pasta mit Hähnchen und Spinat in Sahnesauce", List.of(PASTA, HAEHNCHEN, SPINAT, SAHNESAUCE), POPULAR));
        meals.add(new Meal(2, "2. Bratreis mit Schwein, Brokkoli und Pfeffersauce", List.of(REIS, SCHWEIN, BROKKOLI, PFEFFERSAUCE), DESIRED));
        meals.add(new Meal(3, "3. Tofu-Zucchini Curry im Reisrand", List.of(REIS, TOFU, ZUCCHINI), NEUTRAL));
        meals.add(new Meal(4, "4. Ofenkartoffel mit Sour Cream", List.of(KARTOFFELN, SOUR_CREAM), UNPOPULAR));
        meals.add(new Meal(5, "5. Pasta mit Pilzen in Sahnesauce", List.of(PASTA, PILZE, SAHNESAUCE), UNPOPULAR));
        meals.add(new Meal(6, "6. Reisbowl mit Tofu, Spinat und Sour Cream", List.of(REIS, TOFU, SPINAT, SOUR_CREAM), UNPOPULAR));
        meals.add(new Meal(7, "7. Pasta mit Zucchini, Tomaten und Pfeffersauce", List.of(PASTA, ZUCCHINI, TOMATE, PFEFFERSAUCE), NEUTRAL));
        meals.add(new Meal(8, "8. Gefüllte Kartoffel mit Hähnchen, Zwiebeln und Sahnesauce", List.of(KARTOFFELN, HAEHNCHEN, ZWIEBELN, SAHNESAUCE), NEUTRAL));
        meals.add(new Meal(9, "9. Reispfanne mit Brokkoli, Bohnen und Tomatensauce", List.of(REIS, BROKKOLI, BOHNEN, TOMATENSAUCE), POPULAR));
        meals.add(new Meal(10, "10. Kartoffelauflauf mexikanisch mit Schwein", List.of(KARTOFFELN, SCHWEIN, BOHNEN, TOMATENSAUCE, SOUR_CREAM), UNPOPULAR));
        meals.add(new Meal(11, "11. Bratkartoffeln mit Fisch, Spinatbeilage und Pfeffersauce", List.of(KARTOFFELN, FISCH, SPINAT, PFEFFERSAUCE), NEUTRAL));
        meals.add(new Meal(12, "12. Reispfanne mit Tofu, Pilzen und Brokkoli an Tomatensauce", List.of(REIS, TOFU, PILZE, BROKKOLI, TOMATENSAUCE), DESIRED));
        meals.add(new Meal(13, "13. Pasta mit Bohnen, Zwiebeln und Pfeffersauce", List.of(PASTA, BOHNEN, ZWIEBELN, PFEFFERSAUCE), NEUTRAL));
        meals.add(new Meal(14, "14. Nudelauflauf mit Hähnchen, Tomaten und Tomatensauce", List.of(PASTA, HAEHNCHEN, TOMATE, TOMATENSAUCE), POPULAR));
        meals.add(new Meal(15, "15. Reispfanne mit Schwein, Zucchini und Pfeffersauce", List.of(REIS, SCHWEIN, ZUCCHINI, PFEFFERSAUCE), POPULAR));
        meals.add(new Meal(16, "16. Kartoffelsuppe mit Zwiebeln und Fleischersatzeinlage", List.of(KARTOFFELN, ZWIEBELN, FLEISCHERSATZ), UNPOPULAR));
        meals.add(new Meal(17, "17. Bohneneintopf mit Zucchini und Tomaten mit Reiseinlage", List.of(BOHNEN, ZUCCHINI, TOMATE, REIS), DESIRED));
        meals.add(new Meal(18, "18. Pilzpfanne Stroganoff Art mit Pasta und Sahnesauce", List.of(PILZE, PASTA, SAHNESAUCE), NEUTRAL));
        meals.add(new Meal(19, "19. Hähnchen Stroganoff Art mit Pilzen, Pasta und Sahnesauce", List.of(HAEHNCHEN, PILZE, PASTA, SAHNESAUCE), NEUTRAL));
        meals.add(new Meal(20, "20. Reispfanne mit Hähnchen, Brokkoli und Pfeffersauce", List.of(REIS, HAEHNCHEN, BROKKOLI, PFEFFERSAUCE), POPULAR));
        meals.add(new Meal(21, "21. Kartoffelreibekuchen mit Zucchini und Zwiebeln", List.of(KARTOFFELN, ZUCCHINI, ZWIEBELN), POPULAR));
        meals.add(new Meal(22, "22. Tomatiger Fischeintopf mit Tomaten und Bohnen", List.of(TOMATE, FISCH, BOHNEN), UNPOPULAR));
        meals.add(new Meal(23, "23. Curryeintopf mit Tofu, Spinat und Brokkoli", List.of(TOFU, SPINAT, BROKKOLI), NEUTRAL));
        meals.add(new Meal(24, "24. Bohnenchili mit Tomaten, Zucchini und Reisbeilage", List.of(BOHNEN, TOMATE, ZUCCHINI, REIS), POPULAR));
        meals.add(new Meal(25, "25. Kartoffelpfanne mit Brokkoli und Pfeffersauce", List.of(KARTOFFELN, BROKKOLI, PFEFFERSAUCE), POPULAR));
        meals.add(new Meal(26, "26. Pasta Primavera mit Zucchini, Tomaten, Zwiebeln und Sahnesauce", List.of(PASTA, ZUCCHINI, TOMATE, ZWIEBELN, SAHNESAUCE), DESIRED));
        meals.add(new Meal(27, "27. Hähncheneintopf mit Bohnen, Tomaten und Nudeleinlage", List.of(HAEHNCHEN, BOHNEN, TOMATE, PASTA), UNPOPULAR));
        meals.add(new Meal(28, "28. Pilzrisotto mit Fleischersatz", List.of(PILZE, REIS, FLEISCHERSATZ), NEUTRAL));
        meals.add(new Meal(29, "29. Kartoffelauflauf mit Spinat und Sour Cream", List.of(KARTOFFELN, SPINAT, SOUR_CREAM), UNPOPULAR));
        meals.add(new Meal(30, "30. Schweineschnitzel mit Bratkartoffeln und Pfeffersauce", List.of(SCHWEIN, KARTOFFELN, PFEFFERSAUCE), NEUTRAL));
        meals.add(new Meal(31, "31. Gebratener Tofu mit Pilzen auf Nudeln mit Sahnesauce", List.of(TOFU, PILZE, PASTA, SAHNESAUCE), NEUTRAL));

        List<MealAssignment> assignments = new ArrayList<>();
        long idCounter = 1;
        for (int day = 1; day <= 30; day++) {
            assignments.add(new MealAssignment(idCounter++, day, 1));
            assignments.add(new MealAssignment(idCounter++, day, 2));
        }

        return new MenuPlan(meals, assignments);
    }
}