package venkat.org.springframework.springrecipe.controllers;

import lombok.val;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    }

    @Test
    public void testMockMVC() throws Exception {
        val recipe1 = RecipeCommand.builder().id(1L).build();
        val recipe2 = RecipeCommand.builder().id(2L).build();
        val mockedRecipeSet = new HashSet<RecipeCommand>();
        mockedRecipeSet.add(recipe1);
        mockedRecipeSet.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(mockedRecipeSet);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().size(1))
                .andExpect(model().attribute("recipes", Matchers.hasSize(2)));
    }

    @Test
    public void getIndexPage() {
        val recipe1 = RecipeCommand.builder().id(1L).build();
        val recipe2 = RecipeCommand.builder().id(2L).build();
        val mockedRecipeSet = new HashSet<RecipeCommand>();
        mockedRecipeSet.add(recipe1);
        mockedRecipeSet.add(recipe2);

        when(recipeService.getAllRecipes()).thenReturn(mockedRecipeSet);
        Assert.assertThat(indexController.getIndexPage(model), Matchers.equalToIgnoringCase("index"));
        verify(recipeService,times(1)).getAllRecipes();

        ArgumentCaptor<Set> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Assert.assertEquals(2, argumentCaptor.getValue().size());
    }
}