package venkat.org.springframework.springrecipe.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import venkat.org.springframework.springrecipe.services.IngredientService;
import venkat.org.springframework.springrecipe.services.RecipeService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private static final String VIEW_NAME_INGREDIENT_LIST = "/recipe/ingredients/list";
    private static final String VIEW_NAME_INGREDIENT_SHOW = "/recipe/ingredients/show";

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @RequestMapping(path = "/recipe/{recipeId}/ingredients")
    public String getRecipeIngredients(@PathVariable final String recipeId, final Model model) {
        log.info("Get Recipe Ingredients. Recipe Id::" + recipeId);
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(recipeId)));
        return VIEW_NAME_INGREDIENT_LIST;
    }

    @RequestMapping(path = "/recipe/ingredient/{id}/view")
    public String getIngredientById(@PathVariable final Long id, final Model model) {
        log.info("Get Ingredient by Id::" + id);
        model.addAttribute("ingredient", ingredientService.findIngredientById(id));
        return VIEW_NAME_INGREDIENT_SHOW;
    }
}
