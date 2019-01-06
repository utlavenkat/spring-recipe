package venkat.org.springframework.springrecipe.mappers;

import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.springrecipe.command.*;
import venkat.org.springframework.springrecipe.domain.Difficulty;
import venkat.org.springframework.springrecipe.domain.Ingredient;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientMapperTest {

    private IngredientMapper ingredientMapper;

    @Before
    public void setUp() {
        ingredientMapper = new IngredientMapper();
    }

    @After
    public void tearDown() {
        ingredientMapper = null;
    }

    @Test
    public void convertCommandToDomain() {
        val unitOfMeasureCommand = UnitOfMeasureCommand.builder().id(1L).uom("Cup").build();
        val recipeCommand = RecipeCommand.builder().id(1L).cookTime(10).description("Test Recipe")
                .difficulty(DifficultyCommand.EASY).directions("1.2.3.4").prepTime(20).servings(4).source("My Source")
                .url("http://my.recipe.com").build();

        NotesCommand notesCommand = NotesCommand.builder().id(1L).notes("Test Notes").recipe(recipeCommand).build();

        recipeCommand.setNotes(notesCommand);

        val ingredientCommand = IngredientCommand.builder().id(1L).description("Test Ingredient").amount(BigDecimal.TEN)
                .unitOfMeasure(unitOfMeasureCommand).recipe(recipeCommand).build();

        val ingredient = ingredientMapper.convertCommandToDomain(ingredientCommand);
        assertNotNull(ingredient);
        assertEquals(ingredientCommand.getId(), ingredient.getId());
        assertEquals(ingredientCommand.getDescription(), ingredient.getDescription());
        assertEquals(ingredientCommand.getAmount(), ingredient.getAmount());
        assertNotNull(ingredient.getUnitOfMeasure());

        val unitOfMeasure = ingredient.getUnitOfMeasure();
        assertEquals(unitOfMeasureCommand.getId(), unitOfMeasure.getId());
        assertEquals(unitOfMeasureCommand.getUom(), unitOfMeasure.getUom());

        val recipe = ingredient.getRecipe();
        assertNotNull(recipe);
        assertEquals(recipeCommand.getCookTime(), recipe.getCookTime());
        assertEquals(recipeCommand.getDescription(), recipe.getDescription());
        assertEquals(recipeCommand.getDifficulty().name(), recipe.getDifficulty().name());
        assertEquals(recipeCommand.getDirections(), recipe.getDirections());
        assertEquals(recipeCommand.getId(), recipe.getId());
        assertEquals(recipeCommand.getPrepTime(), recipe.getPrepTime());
        assertEquals(recipeCommand.getServings(), recipe.getServings());
        assertEquals(recipeCommand.getSource(), recipe.getSource());
        assertEquals(recipeCommand.getUrl(), recipe.getUrl());
        assertNotNull(recipe.getNotes());
        val notes = recipe.getNotes();
        assertNotNull(notes);

        assertEquals(notesCommand.getId(), notes.getId());
        assertEquals(notesCommand.getNotes(), notes.getNotes());
    }

    @Test
    public void convertDomainToCommand() {
        val unitOfMeasure = UnitOfMeasure.builder().id(1L).uom("Cup").build();
        val recipe = Recipe.builder().id(1L).description("Test Recipe").cookTime(10).difficulty(Difficulty.EASY)
                .directions("1.2.3.4").prepTime(10).servings(4).source("Test Source")
                .url("http://test.url.com").build();

        val ingredient = Ingredient.builder().description("Test Ingredient").amount(BigDecimal.TEN).id(1L)
                .unitOfMeasure(unitOfMeasure).recipe(recipe).build();

        val ingredientCommand = ingredientMapper.convertDomainToCommand(ingredient);
        assertNotNull(ingredientCommand);
        assertEquals(ingredientCommand.getAmount(), ingredient.getAmount());
        assertEquals(ingredientCommand.getDescription(), ingredient.getDescription());
        assertEquals(ingredientCommand.getId(), ingredient.getId());

        val unitOfMeasureCommand = ingredientCommand.getUnitOfMeasure();
        assertNotNull(unitOfMeasureCommand);
        assertEquals(unitOfMeasureCommand.getUom(), unitOfMeasure.getUom());
        assertEquals(unitOfMeasureCommand.getId(), unitOfMeasure.getId());

        val recipeCommand = ingredientCommand.getRecipe();
        assertNotNull(recipeCommand);
        assertEquals(recipeCommand.getUrl(), recipe.getUrl());
        assertEquals(recipeCommand.getSource(), recipe.getSource());
        assertEquals(recipeCommand.getServings(), recipe.getServings());
        assertEquals(recipeCommand.getPrepTime(), recipe.getPrepTime());
        assertEquals(recipeCommand.getId(), recipe.getId());
        assertEquals(recipeCommand.getDirections(), recipe.getDirections());
        assertEquals(recipeCommand.getDifficulty().name(), recipe.getDifficulty().name());
        assertEquals(recipeCommand.getDescription(), recipe.getDescription());
        assertEquals(recipeCommand.getCookTime(), recipe.getCookTime());
    }


}