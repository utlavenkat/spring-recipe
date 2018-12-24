package venkat.org.springframework.springrecipe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.domain.Recipe;
import venkat.org.springframework.springrecipe.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeService {
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> {
            recipes.add(recipe);
        });
        return recipes;
    }
}
