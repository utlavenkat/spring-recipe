package venkat.org.springframework.springrecipe.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.exceptions.NotFoundException;
import venkat.org.springframework.springrecipe.services.RecipeService;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private static final String VIEW_NAME_RECIPE_FORM = "recipe/recipeform";
    private static final String VIEW_NAME_RECIPE_SHOW = "recipe/show";
    private static final String VIEW_NAME_INDEX = "index";
    private static final String VIEW_NAME_ERROR_PAGE_404 = "errorpages/404";
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
    public String saveRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return VIEW_NAME_RECIPE_FORM;
        }
        val savedRecipe = recipeService.saveRecipe(recipeCommand);
        log.info("Saved Recipe ::" + savedRecipe);
        return "redirect:/recipe/" + savedRecipe.getId() + "/view";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/delete")
    public String deleteRecipe(@PathVariable final String id) {
        log.info("Delete Recipe, Input Recipe ID::" + id);
        recipeService.deleteRecipe(Long.valueOf(id));
        return "redirect:/" + VIEW_NAME_INDEX;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex) {
        log.error("Exception occurred. Root Cause is ::" + ex.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(VIEW_NAME_ERROR_PAGE_404);
        modelAndView.addObject("exception", ex);
        return modelAndView;
    }
}
