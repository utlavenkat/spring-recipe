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
        unitOfMeasureMap = unitOfMeasureService.getUnitOfMeasureMap();
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
        guacamoli.addIngredient(Ingredient.builder().description("Ripe Avocados").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Ripe")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Kosher Salt").amount(BigDecimal.valueOf(0.5)).unitOfMeasure(unitOfMeasureMap.get("Teaspoon")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Lime Juice").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Minced Red Onion").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Serrano Chiles").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Serrano Stems").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Serrano Seeds").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Cilantro").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Black Pepper").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        guacamoli.addIngredient(Ingredient.builder().description("Tomato").amount(BigDecimal.valueOf(0.5)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
    }

    private void prepareGrillChickenIngredients(Recipe grillChicken) {
        grillChicken.addIngredient(Ingredient.builder().description("Ancho Chilli Powder").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Dried Oregano").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Teaspoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Dried Cumin").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Teaspoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Sugar").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Teaspoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Salt").amount(BigDecimal.valueOf(0.5)).unitOfMeasure(unitOfMeasureMap.get("Teaspoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Clove Garlic").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Orange Zest").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Orange Juice").amount(BigDecimal.valueOf(3)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Olive Oil").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Tablespoon")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Chicken Thighs").amount(BigDecimal.valueOf(6)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Corn Tortillas").amount(BigDecimal.valueOf(3)).unitOfMeasure(unitOfMeasureMap.get("Cup")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Avocados").amount(BigDecimal.valueOf(2)).unitOfMeasure(unitOfMeasureMap.get("Ripe")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Cherry Tomato").amount(BigDecimal.valueOf(0.5)).unitOfMeasure(unitOfMeasureMap.get("Pint")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Red Onion").amount(BigDecimal.valueOf(0.25)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Sour Cream").amount(BigDecimal.valueOf(0.5)).unitOfMeasure(unitOfMeasureMap.get("Cup")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Milk").amount(BigDecimal.valueOf(0.25)).unitOfMeasure(unitOfMeasureMap.get("Cup")).build());
        grillChicken.addIngredient(Ingredient.builder().description("Lime").amount(BigDecimal.valueOf(1)).unitOfMeasure(unitOfMeasureMap.get("Quantity")).build());
    }
}
