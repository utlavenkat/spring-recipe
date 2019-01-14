package venkat.org.springframework.springrecipe.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import venkat.org.springframework.springrecipe.command.DifficultyCommand;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.services.RecipeService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
public class RecipeControllerTest {

    private static final String VIEW_NAME_SAVE_RECIPE = "recipe/recipeform";
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
        RecipeCommand recipe = RecipeCommand.builder().id(1L).description("Test Recipe").build();
        when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);
        ResultActions resultActions = mockMvc.perform(get("/recipe/{id}", 1));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name("recipe/show"));
        resultActions.andExpect(model().size(1));
        resultActions.andExpect(model().attributeExists("recipe"));
        resultActions.andExpect(model().attribute("recipe", recipe));
        verify(recipeService, times(1)).findRecipeById(1L);
    }

    @Test
    public void testNewRecipe() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/recipe/new"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name(VIEW_NAME_SAVE_RECIPE));
        resultActions.andExpect(model().size(1));
        resultActions.andExpect(model().attributeExists("recipe"));
        resultActions.andExpect(model().attribute("recipe", Matchers.notNullValue()));
        resultActions.andExpect(model().attribute("recipe.id", Matchers.nullValue()));
        verify(recipeService, times(0)).findRecipeById(anyLong());
    }

    @Test
    public void testCreateRecipe() throws Exception {
        //Given
        RecipeCommand inputRecipeCommand = RecipeCommand.builder().difficulty(DifficultyCommand.HARD).prepTime(20)
                .url("https://testRecipes.com").source("My source").directions("1.2.3.4").servings(4).cookTime(20)
                .description("Test Recipe").build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inputRecipeCommand);
        inputRecipeCommand.setId(100L);
        when(recipeService.saveRecipe(any(RecipeCommand.class))).thenReturn(inputRecipeCommand);

        //when
        ResultActions resultActions = mockMvc.perform(post("/recipe").content(jsonInString));

        //then
        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(view().name("redirect:/recipe/" + inputRecipeCommand.getId().toString()));
        verify(recipeService, times(1)).saveRecipe(any(RecipeCommand.class));
    }
}