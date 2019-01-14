package venkat.org.springframework.springrecipe.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.services.RecipeService;

@Controller
@RequestMapping(path = "/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private static final String VIEW_NAME_RECIPE_FORM = "recipe/recipeform";
    private static final String VIEW_NAME_RECIPE_SHOW = "recipe/show";

    private final RecipeService recipeService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/view")
    public String viewRecipe(@PathVariable final String id, final Model model) {
        log.info("Input recipe id::" + id);
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return VIEW_NAME_RECIPE_SHOW;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editRecipe(@PathVariable final String id, final Model model) {
        log.info("Input recipe id::" + id);
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return VIEW_NAME_RECIPE_FORM;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String getNewRecipe(final Model model) {
        log.info("Get New Recipe::");
        model.addAttribute("recipe", RecipeCommand.builder().build());
        return VIEW_NAME_RECIPE_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        val savedRecipe = recipeService.saveRecipe(recipeCommand);
        log.info("Saved Recipe ::" + savedRecipe);
        return "redirect:/recipe/" + savedRecipe.getId() + "/view";
    }
}
