package org.acme.MenuPlanning.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.MenuPlanning.domain.Meal;
import org.acme.MenuPlanning.domain.MealAssignment;
import org.acme.MenuPlanning.domain.MenuPlan;
import org.acme.MenuPlanning.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DemoDataGenerator {

    public MenuPlan generateDemoData() {
        List<Meal> meals = new ArrayList<>();


        // Format: new Meal(name, mealNumber, mainCarb, mainProtein, ingredients)

        meals.add(new Meal("1. Pasta mit Hähnchen und Spinat in Sahnesauce", 1, Ingredient.PASTA, Ingredient.HAEHNCHEN, List.of(Ingredient.PASTA, Ingredient.HAEHNCHEN, Ingredient.SPINAT, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("2. Bratreis mit Schwein, Brokkoli und Pfeffersauce", 2, Ingredient.REIS, Ingredient.SCHWEIN, List.of(Ingredient.REIS, Ingredient.SCHWEIN, Ingredient.BROKKOLI, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("3. Tofu-Zucchini Curry im Reisrand", 3, Ingredient.REIS, Ingredient.TOFU, List.of(Ingredient.REIS, Ingredient.TOFU, Ingredient.ZUCCHINI)));
        meals.add(new Meal("4. Ofenkartoffel mit Sour Cream", 4, Ingredient.KARTOFFELN, null, List.of(Ingredient.KARTOFFELN, Ingredient.SOUR_CREAM)));
        meals.add(new Meal("5. Pasta mit Pilzen in Sahnesauce", 5, Ingredient.PASTA, null, List.of(Ingredient.PASTA, Ingredient.PILZE, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("6. Reisbowl mit Tofu, Spinat und Sour Cream", 6, Ingredient.REIS, Ingredient.TOFU, List.of(Ingredient.REIS, Ingredient.TOFU, Ingredient.SPINAT, Ingredient.SOUR_CREAM)));
        meals.add(new Meal("7. Pasta mit Zucchini, Tomaten und Pfeffersauce", 7, Ingredient.PASTA, null, List.of(Ingredient.PASTA, Ingredient.ZUCCHINI, Ingredient.TOMATE, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("8. Gefüllte Kartoffel mit Hähnchen, Zwiebeln und Sahnesauce", 8, Ingredient.KARTOFFELN, Ingredient.HAEHNCHEN, List.of(Ingredient.KARTOFFELN, Ingredient.HAEHNCHEN, Ingredient.ZWIEBELN, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("9. Reispfanne mit Brokkoli, Bohnen und Tomatensauce", 9, Ingredient.REIS, null, List.of(Ingredient.REIS, Ingredient.BROKKOLI, Ingredient.BOHNEN, Ingredient.TOMATENSAUCE)));
        meals.add(new Meal("10. Kartoffelauflauf mexikanisch mit Schwein", 10, Ingredient.KARTOFFELN, Ingredient.SCHWEIN, List.of(Ingredient.KARTOFFELN, Ingredient.SCHWEIN, Ingredient.BOHNEN, Ingredient.TOMATENSAUCE, Ingredient.SOUR_CREAM)));
        meals.add(new Meal("11. Bratkartoffeln mit Fisch, Spinatbeilage und Pfeffersauce", 11, Ingredient.KARTOFFELN, Ingredient.FISCH, List.of(Ingredient.KARTOFFELN, Ingredient.FISCH, Ingredient.SPINAT, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("12. Reispfanne mit Tofu, Pilzen und Brokkoli an Tomatensauce", 12, Ingredient.REIS, Ingredient.TOFU, List.of(Ingredient.REIS, Ingredient.TOFU, Ingredient.PILZE, Ingredient.BROKKOLI, Ingredient.TOMATENSAUCE)));
        meals.add(new Meal("13. Pasta mit Bohnen, Zwiebeln und Pfeffersauce", 13, Ingredient.PASTA, null, List.of(Ingredient.PASTA, Ingredient.BOHNEN, Ingredient.ZWIEBELN, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("14. Nudelauflauf mit Hähnchen, Tomaten und Tomatensauce", 14, Ingredient.PASTA, Ingredient.HAEHNCHEN, List.of(Ingredient.PASTA, Ingredient.HAEHNCHEN, Ingredient.TOMATE, Ingredient.TOMATENSAUCE)));
        meals.add(new Meal("15. Reispfanne mit Schwein, Zucchini und Pfeffersauce", 15, Ingredient.REIS, Ingredient.SCHWEIN, List.of(Ingredient.REIS, Ingredient.SCHWEIN, Ingredient.ZUCCHINI, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("16. Kartoffelsuppe mit Zwiebeln und Fleischersatzeinlage", 16, Ingredient.KARTOFFELN, Ingredient.FLEISCHERSATZ, List.of(Ingredient.KARTOFFELN, Ingredient.ZWIEBELN, Ingredient.FLEISCHERSATZ)));
        meals.add(new Meal("17. Bohneneintopf mit Zucchini und Tomaten mit Reiseinlage", 17, Ingredient.REIS, null, List.of(Ingredient.BOHNEN, Ingredient.ZUCCHINI, Ingredient.TOMATE, Ingredient.REIS)));
        meals.add(new Meal("18. Pilzpfanne Stroganoff Art mit Pasta und Sahnesauce", 18, Ingredient.PASTA, null, List.of(Ingredient.PILZE, Ingredient.PASTA, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("19. Hähnchen Stroganoff Art mit Pilzen, Pasta und Sahnesauce", 19, Ingredient.PASTA, Ingredient.HAEHNCHEN, List.of(Ingredient.HAEHNCHEN, Ingredient.PILZE, Ingredient.PASTA, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("20. Reispfanne mit Hähnchen, Brokkoli und Pfeffersauce", 20, Ingredient.REIS, Ingredient.HAEHNCHEN, List.of(Ingredient.REIS, Ingredient.HAEHNCHEN, Ingredient.BROKKOLI, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("21. Kartoffelreibekuchen mit Zucchini und Zwiebeln", 21, Ingredient.KARTOFFELN, null, List.of(Ingredient.KARTOFFELN, Ingredient.ZUCCHINI, Ingredient.ZWIEBELN)));
        meals.add(new Meal("22. Tomatiger Fischeintopf mit Tomaten und Bohnen", 22, null, Ingredient.FISCH, List.of(Ingredient.TOMATE, Ingredient.FISCH, Ingredient.BOHNEN)));
        meals.add(new Meal("23. Curryeintopf mit Tofu, Spinat und Brokkoli", 23, null, Ingredient.TOFU, List.of(Ingredient.TOFU, Ingredient.SPINAT, Ingredient.BROKKOLI)));
        meals.add(new Meal("24. Bohnenchili mit Tomaten, Zucchini und Reisbeilage", 24, Ingredient.REIS, null, List.of(Ingredient.BOHNEN, Ingredient.TOMATE, Ingredient.ZUCCHINI, Ingredient.REIS)));
        meals.add(new Meal("25. Kartoffelpfanne mit Brokkoli und Pfeffersauce", 25, Ingredient.KARTOFFELN, null, List.of(Ingredient.KARTOFFELN, Ingredient.BROKKOLI, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("26. Pasta Primavera mit Zucchini, Tomaten, Zwiebeln und Sahnesauce", 26, Ingredient.PASTA, null, List.of(Ingredient.PASTA, Ingredient.ZUCCHINI, Ingredient.TOMATE, Ingredient.ZWIEBELN, Ingredient.SAHNESAUCE)));
        meals.add(new Meal("27. Hähncheneintopf mit Bohnen, Tomaten und Nudeleinlage", 27, Ingredient.PASTA, Ingredient.HAEHNCHEN, List.of(Ingredient.HAEHNCHEN, Ingredient.BOHNEN, Ingredient.TOMATE, Ingredient.PASTA)));
        meals.add(new Meal("28. Pilzrisotto mit Fleischersatz", 28, Ingredient.REIS, Ingredient.FLEISCHERSATZ, List.of(Ingredient.PILZE, Ingredient.REIS, Ingredient.FLEISCHERSATZ)));
        meals.add(new Meal("29. Kartoffelauflauf mit Spinat und Sour Cream", 29, Ingredient.KARTOFFELN, null, List.of(Ingredient.KARTOFFELN, Ingredient.SPINAT, Ingredient.SOUR_CREAM)));
        meals.add(new Meal("30. Schweineschnitzel mit Bratkartoffeln und Pfeffersauce", 30, Ingredient.KARTOFFELN, Ingredient.SCHWEIN, List.of(Ingredient.SCHWEIN, Ingredient.KARTOFFELN, Ingredient.PFEFFERSAUCE)));
        meals.add(new Meal("31. Gebratener Tofu mit Pilzen auf Nudeln mit Sahnesauce", 31, Ingredient.PASTA, Ingredient.TOFU, List.of(Ingredient.TOFU, Ingredient.PILZE, Ingredient.PASTA, Ingredient.SAHNESAUCE)));

        // 30 Tage generieren mit jeweils 2 Gerichten (Slot 1 und Slot 2)
        List<MealAssignment> assignments = new ArrayList<>();
        long idCounter = 1;
        for (int day = 1; day <= 30; day++) {
            assignments.add(new MealAssignment(idCounter++, day, 1));
            assignments.add(new MealAssignment(idCounter++, day, 2));
        }

        return new MenuPlan(meals, assignments);
    }
}