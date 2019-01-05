package venkat.org.springframework.springrecipe.controllers;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.services.RecipeService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
public class RecipeControllerTest {

    private MockMvc mockMvc;
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void findRecipeById() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).description("Test Recipe").build();
        when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);
        ResultActions resultActions = mockMvc.perform(get("/recipe/{id}", 1));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name("recipe/show"));
        resultActions.andExpect(model().size(1));
        resultActions.andExpect(model().attributeExists("recipe"));
        resultActions.andExpect(model().attribute("recipe", recipe));
        verify(recipeService, times(1)).findRecipeById(1L);
    }
}