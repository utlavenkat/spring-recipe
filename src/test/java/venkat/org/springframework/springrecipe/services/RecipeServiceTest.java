package venkat.org.springframework.springrecipe.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.springrecipe.domain.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceTest {
    @Autowired
    private RecipeService recipeService;

    @Test
    public void saveRecipe() {
        Recipe recipe = new Recipe();
        Notes recipeNotes = new Notes();
        recipeNotes.setNotes("Test Note");
        recipe.setNotes(recipeNotes);
        recipe.setPrepTime(10);
        recipe.setUrl("http://myrecipe.com");
        recipe.setDifficulty(Difficulty.HARD);
        recipe.setSource("My source");
        recipe.setDirections("My Directions");
        recipe.setServings(3);
        recipe.setCookTime(30);
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);
        Category category = new Category();
        category.setCategoryName("MEXICAN");
        category.setId(2L);
        category.setRecipes(recipeSet);
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category);

        recipe.setCategories(categorySet);

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(BigDecimal.ONE);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setUom("Teaspoon");
        unitOfMeasure.setId(1L);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredients.add(ingredient);
        recipe.setIngredients(ingredients);

        recipe.setDescription("My Recipe");

        Recipe savedRecipe = recipeService.saveRecipe(recipe);

        Assert.assertNotNull(savedRecipe);
        Assert.assertNotNull(savedRecipe.getId());
        Assert.assertNotNull(savedRecipe.getNotes());
        Assert.assertNotNull(savedRecipe.getNotes().getId());
        Assert.assertNotNull(savedRecipe.getCategories());
        Assert.assertEquals(categorySet.size(), savedRecipe.getCategories().size());
        Assert.assertEquals(recipe.getCookTime(), savedRecipe.getCookTime());
        Assert.assertEquals(recipe.getDescription(),savedRecipe.getDescription());
        Assert.assertEquals(recipe.getDifficulty(),savedRecipe.getDifficulty());
        Assert.assertEquals(recipe.getDirections(),savedRecipe.getDirections());
        Assert.assertEquals(recipe.getPrepTime(),savedRecipe.getPrepTime());
        Assert.assertEquals(recipe.getServings(),savedRecipe.getServings());
        Assert.assertEquals(recipe.getSource(),savedRecipe.getSource());
        Assert.assertEquals(recipe.getUrl(),savedRecipe.getUrl());
        Assert.assertNotNull(savedRecipe.getIngredients());
        Assert.assertTrue(savedRecipe.getIngredients().size() ==1);
        Ingredient savedIngredient = savedRecipe.getIngredients().iterator().next();
        Assert.assertNotNull(savedIngredient.getId());

    }

    @Test
    public void getAllRecipes() {
       Set<Recipe> recipeSet = recipeService.getAllRecipes();
       Assert.assertNotNull(recipeSet);
       Assert.assertTrue(recipeSet.size() == 2);
    }
}