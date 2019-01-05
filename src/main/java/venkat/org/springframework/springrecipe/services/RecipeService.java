package venkat.org.springframework.springrecipe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    public Recipe findRecipeById(final Long id) {
        return recipeRepository.findById(id).orElse(null);
    }
}
