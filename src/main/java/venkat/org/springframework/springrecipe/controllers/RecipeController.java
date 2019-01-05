package venkat.org.springframework.springrecipe.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import venkat.org.springframework.springrecipe.services.RecipeService;

@Controller
@RequestMapping(path = "/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String findRecipeById(@PathVariable final String id, final Model model) {
        log.info("Input recipe id::" + id);
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }
}
