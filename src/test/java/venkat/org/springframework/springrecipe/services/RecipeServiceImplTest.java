package venkat.org.springframework.springrecipe.services;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import venkat.org.springframework.springrecipe.command.*;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.exceptions.NotFoundException;
import venkat.org.springframework.springrecipe.mappers.RecipeMapper;
import venkat.org.springframework.springrecipe.repositories.RecipeRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class RecipeServiceImplTest {

    private RecipeService recipeService;
    private RecipeMapper recipeMapper;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public  void  setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
        recipeMapper = new RecipeMapper();
    }

    @After
    public void tearDown() {
        recipeRepository = null;
        recipeService = null;
    }
    @Test
    public void saveRecipe() {

        NotesCommand recipeNotes = NotesCommand.builder().id(1234L).notes("Test Note").build();
        UnitOfMeasureCommand unitOfMeasure = UnitOfMeasureCommand.builder().uom("Teaspoon").id(1L).build();
        IngredientCommand ingredient = IngredientCommand.builder().amount(BigDecimal.ONE).unitOfMeasure(unitOfMeasure)
                .description("Each").build();
        Set<IngredientCommand> ingredients = new HashSet<>(1);
        ingredients.add(ingredient);
        RecipeCommand recipe = RecipeCommand.builder().id(1234L).notes(recipeNotes).prepTime(10).url("http://myrecipe.com")
                .difficulty(DifficultyCommand.HARD).source("My source").directions("My Directions").servings(3)
                .cookTime(30).ingredients(ingredients).description("My Recipe").build();
        recipeNotes.setRecipe(recipe);
        //Set<RecipeCommand> recipeSet = new HashSet<>();

        CategoryCommand category = new CategoryCommand();
        category.setCategoryName("MEXICAN");
        category.setId(2L);
        //category.setRecipes(recipeSet);
        Set<CategoryCommand> categorySet = new HashSet<>();
        categorySet.add(category);

        recipe.setCategories(categorySet);
        recipe.getIngredients().iterator().next().setId(1234L);
        //recipeSet.add(recipe);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipeMapper.convertCommandToDomain(recipe));

        RecipeCommand savedRecipe = recipeService.saveRecipe(recipe);

        Assert.assertNotNull(savedRecipe);
        Assert.assertNotNull(savedRecipe.getId());
        assertEquals(recipe.getId(), savedRecipe.getId());
        Assert.assertNotNull(savedRecipe.getNotes());
        Assert.assertNotNull(savedRecipe.getNotes().getId());
        assertEquals(recipe.getNotes().getId(), savedRecipe.getNotes().getId());
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
        assertEquals(1, savedRecipe.getIngredients().size());
        IngredientCommand savedIngredient = savedRecipe.getIngredients().iterator().next();
        Assert.assertNotNull(savedIngredient.getId());
        verify(recipeRepository, times(1)).save(any());

    }

    @Test
    public void getAllRecipes() {
        val recipe1 = Recipe.builder().id(1L).build();
        val recipe2 = Recipe.builder().id(2L).build();

        Set<Recipe> mockedRecipeSet = new HashSet<>();
        mockedRecipeSet.add(recipe1);
        mockedRecipeSet.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(mockedRecipeSet);

        Set<RecipeCommand> recipeSet = recipeService.getAllRecipes();
        Assert.assertNotNull(recipeSet);
        assertEquals(2, recipeSet.size());
    }

    @Test
    public void findRecipeById() {
        Long id = anyLong();
        when(recipeRepository.findById(id)).thenReturn(Optional.of(Recipe.builder().id(id).build()));
        val recipe = recipeService.findRecipeById(1L);
        assertNotNull("Recipe should not be null", recipe);
        assertNotNull("Recipe ID should not be null", recipe.getId());
        assertEquals("Id value is not matching", id, recipe.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void findRecipeById_Invalid() {
        Long id = anyLong();
        when(recipeRepository.findById(id)).thenReturn(Optional.empty());
        RecipeCommand recipe = recipeService.findRecipeById(id);
        // assertNull("Recipe should be null", recipe);
        verify(recipeRepository, times(1)).findById(id);
    }

    @Test
    public void deleteRecipe() {
        //given
        Long id = 1000L;
        //when
        recipeService.deleteRecipe(id);
        //then
        verify(recipeRepository, times(1)).deleteById(id);

    }

    @Test(expected = RuntimeException.class)
    public void deleteRecipe_invalidId() {
        //given
        Long id = 1000L;
        doThrow(RuntimeException.class).when(recipeRepository).deleteById(id);
        //when
        recipeService.deleteRecipe(id);

        //then
        //see the @Test annotation
    }
}