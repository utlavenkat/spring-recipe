package venkat.org.springframework.springrecipe.services;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import venkat.org.springframework.springrecipe.domain.*;
import venkat.org.springframework.springrecipe.repositories.RecipeRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class RecipeServiceTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public  void  setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeService(recipeRepository);
    }

    @After
    public void tearDown() {
        recipeRepository = null;
        recipeService = null;
    }
    @Test
    public void saveRecipe() {

        Notes recipeNotes = new Notes();
        recipeNotes.setNotes("Test Note");

        Set<Ingredient> ingredients = new HashSet<>();
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(BigDecimal.ONE);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setUom("Teaspoon");
        unitOfMeasure.setId(1L);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredients.add(ingredient);

        Recipe recipe = Recipe.builder().notes(recipeNotes).prepTime(10).url("http://myrecipe.com")
                .difficulty(Difficulty.HARD).source("My source").directions("My Directions").servings(3)
                .cookTime(30).ingredients(ingredients).description("My Recipe").build();
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(recipe);

        Category category = new Category();
        category.setCategoryName("MEXICAN");
        category.setId(2L);
        category.setRecipes(recipeSet);
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category);

        recipe.setCategories(categorySet);
        val mockedRecipe = recipe;
        mockedRecipe.setId(1234L);
        mockedRecipe.getNotes().setId(1234L);
        mockedRecipe.getIngredients().iterator().next().setId(1234L);
        when(recipeRepository.save(recipe)).thenReturn(mockedRecipe);


        Recipe savedRecipe = recipeService.saveRecipe(recipe);

        Assert.assertNotNull(savedRecipe);
        Assert.assertNotNull(savedRecipe.getId());
        assertEquals(mockedRecipe.getId(),savedRecipe.getId());
        Assert.assertNotNull(savedRecipe.getNotes());
        Assert.assertNotNull(savedRecipe.getNotes().getId());
        assertEquals(mockedRecipe.getNotes().getId(),savedRecipe.getNotes().getId());
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
        verify(recipeRepository,times(1)).save(recipe);

    }

    @Test
    public void getAllRecipes() {
        val recipe1 = new Recipe();
        val recipe2 = new Recipe();

        Set<Recipe> mockedRecipeSet = new HashSet<>();
        mockedRecipeSet.add(recipe1);
        mockedRecipeSet.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(mockedRecipeSet);

        Set<Recipe> recipeSet = recipeService.getAllRecipes();
        Assert.assertNotNull(recipeSet);
        Assert.assertTrue(recipeSet.size() == 2);
    }
}