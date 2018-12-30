package venkat.org.springframework.springrecipe.controllers;

import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
        //model = new ConcurrentModel();

    }
    @Test
    public void getIndexPage() {
        val recipe1 = new Recipe();
        val recipe2 = new Recipe();
        val mockedRecipeSet = new HashSet<Recipe>();
        mockedRecipeSet.add(recipe1);
        mockedRecipeSet.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(mockedRecipeSet);
        Assert.assertThat(indexController.getIndexPage(model), Matchers.equalToIgnoringCase("index"));
        verify(recipeService,times(1)).getAllRecipes();

        ArgumentCaptor<Set> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Assert.assertTrue(argumentCaptor.getValue().size()==2);
    }
}