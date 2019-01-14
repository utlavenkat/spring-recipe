package venkat.org.springframework.springrecipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.springrecipe.command.IngredientCommand;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.services.IngredientService;
import venkat.org.springframework.springrecipe.services.RecipeService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    private static final String VIEW_NAME_INGREDIENT_LIST = "/recipe/ingredients/list";
    private static final String VIEW_NAME_INGREDIENT_SHOW = "/recipe/ingredients/show";


    @Mock
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    private IngredientController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getRecipeIngredients() throws Exception {
        //Given
        Long recipeId = 1L;
        RecipeCommand recipeCommand = RecipeCommand.builder().id(recipeId).build();
        when(recipeService.findRecipeById(recipeId)).thenReturn(recipeCommand);

        //when
        ResultActions resultActions = mockMvc.perform(get("/recipe/" + recipeId + "/ingredients"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name(VIEW_NAME_INGREDIENT_LIST));

        verify(recipeService, times(1)).findRecipeById(anyLong());
    }

    @Test
    public void getIngredientById() throws Exception {
        //Given
        Long id = 1L;

        //When
        when(ingredientService.findIngredientById(anyLong())).thenReturn(IngredientCommand.builder().id(1L).build());
        ResultActions resultActions = mockMvc.perform(get("/recipe/ingredient/" + id + "/view"));

        //Then
        resultActions.andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().size(1))
                .andExpect(view().name(VIEW_NAME_INGREDIENT_SHOW));
        verify(ingredientService, times(1)).findIngredientById(anyLong());
    }
}