package venkat.org.springframework.springrecipe.bootstrap;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.domain.*;
import venkat.org.springframework.springrecipe.services.CategoryService;
import venkat.org.springframework.springrecipe.services.RecipeService;
import venkat.org.springframework.springrecipe.services.UnitOfMeasureService;

import java.math.BigDecimal;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private UnitOfMeasureService unitOfMeasureService;
    private RecipeService recipeService;
    private CategoryService categoryService;

    Map<String,UnitOfMeasure> unitOfMeasureMap;

    @Override
    public void run(String... args) {
        unitOfMeasureMap = unitOfMeasureService.getUnitOfServiceMap();
        prepareGuacamoleRecipe();
        prepareGrillChickenRecipe();
    }

    private void prepareGuacamoleRecipe() {
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Guacamole");
        prepareGuacamoliIngredients(guacamole);
        guacamole.addCategory(categoryService.getByCategoryName("MEXICAN"));
        guacamole.setCookTime(20);
        guacamole.setServings(4);
        guacamole.setDirections("Be careful handling chiles if using. Wash your hands throughly  after handling and do not touch your eyes.");
        guacamole.setSource("Simply Recipe");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("http://www.simplyrecipes.com");
        guacamole.setPrepTime(10);
        Notes guacamoleNote = new Notes("Garnish with red radishes or jicama. Serve with tortilla chips",guacamole);
        guacamole.setNotes(guacamoleNote);
        recipeService.saveRecipe(guacamole);
    }

    private void prepareGrillChickenRecipe() {
        Recipe grilledChicken = new Recipe();
        grilledChicken.setDescription("Spicy Grilled Chicken Tacos");
        prepareGrillChickenIngredients(grilledChicken);
        grilledChicken.addCategory(categoryService.getByCategoryName("MEXICAN"));
        grilledChicken.setCookTime(15);
        grilledChicken.setServings(6);
        grilledChicken.setSource("Simply Recipe");
        grilledChicken.setDifficulty(Difficulty.EASY);
        grilledChicken.setUrl("http://www.simplyrecipes.com");
        grilledChicken.setPrepTime(20);

        recipeService.saveRecipe(grilledChicken);
    }

    private void prepareGuacamoliIngredients(Recipe guacamoli) {
        guacamoli.addIngredient(new Ingredient("Ripe Avocados", BigDecimal.valueOf(2),unitOfMeasureMap.get("Ripe")));
        guacamoli.addIngredient(new Ingredient("Kosher Salt", BigDecimal.valueOf(0.5),unitOfMeasureMap.get("Teaspoon")));
        guacamoli.addIngredient(new Ingredient("Lime Juice", BigDecimal.valueOf(1),unitOfMeasureMap.get("Tablespoon")));
        guacamoli.addIngredient(new Ingredient("Minced Red Onion", BigDecimal.valueOf(2),unitOfMeasureMap.get("Tablespoon")));
        guacamoli.addIngredient(new Ingredient("Serrano Chiles", BigDecimal.valueOf(2),unitOfMeasureMap.get("Quantity")));
        guacamoli.addIngredient(new Ingredient("Serrano Stems", BigDecimal.valueOf(2),unitOfMeasureMap.get("Quantity")));
        guacamoli.addIngredient(new Ingredient("Serrano Seeds", BigDecimal.valueOf(2),unitOfMeasureMap.get("Quantity")));
        guacamoli.addIngredient(new Ingredient("Cilantro", BigDecimal.valueOf(2),unitOfMeasureMap.get("Quantity")));
        guacamoli.addIngredient(new Ingredient("Black Pepper", BigDecimal.valueOf(1),unitOfMeasureMap.get("Quantity")));
        guacamoli.addIngredient(new Ingredient("Tomato", BigDecimal.valueOf(0.5),unitOfMeasureMap.get("Quantity")));
    }

    private void prepareGrillChickenIngredients(Recipe grillChicken) {
        grillChicken.addIngredient(new Ingredient("Ancho Chilli Powder", BigDecimal.valueOf(2),unitOfMeasureMap.get("Tablespoon")));
        grillChicken.addIngredient(new Ingredient("Dried Oregano", BigDecimal.valueOf(1),unitOfMeasureMap.get("Teaspoon")));
        grillChicken.addIngredient(new Ingredient("Dried Cumin", BigDecimal.valueOf(1),unitOfMeasureMap.get("Teaspoon")));
        grillChicken.addIngredient(new Ingredient("Sugar", BigDecimal.valueOf(1),unitOfMeasureMap.get("Teaspoon")));
        grillChicken.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(0.5),unitOfMeasureMap.get("Teaspoon")));
        grillChicken.addIngredient(new Ingredient("Clove Garlic", BigDecimal.valueOf(1),unitOfMeasureMap.get("Quantity")));
        grillChicken.addIngredient(new Ingredient("Orange Zest", BigDecimal.valueOf(1),unitOfMeasureMap.get("Tablespoon")));
        grillChicken.addIngredient(new Ingredient("Orange Juice", BigDecimal.valueOf(3),unitOfMeasureMap.get("Tablespoon")));
        grillChicken.addIngredient(new Ingredient("Olive Oil", BigDecimal.valueOf(2),unitOfMeasureMap.get("Tablespoon")));
        grillChicken.addIngredient(new Ingredient("Chicken Thighs", BigDecimal.valueOf(6),unitOfMeasureMap.get("Quantity")));
        grillChicken.addIngredient(new Ingredient("Corn Tortillas", BigDecimal.valueOf(3),unitOfMeasureMap.get("Cup")));
        grillChicken.addIngredient(new Ingredient("Avocados", BigDecimal.valueOf(2),unitOfMeasureMap.get("Ripe")));
        grillChicken.addIngredient(new Ingredient("Cherry Tomato", BigDecimal.valueOf(0.5),unitOfMeasureMap.get("Pint")));
        grillChicken.addIngredient(new Ingredient("Red Onion", BigDecimal.valueOf(0.25),unitOfMeasureMap.get("Quantity")));
        grillChicken.addIngredient(new Ingredient("Sour Cream", BigDecimal.valueOf(0.5),unitOfMeasureMap.get("Cup")));
        grillChicken.addIngredient(new Ingredient("Milk", BigDecimal.valueOf(0.25),unitOfMeasureMap.get("Cup")));
        grillChicken.addIngredient(new Ingredient("Lime", BigDecimal.valueOf(1),unitOfMeasureMap.get("Quantity")));
    }
}
