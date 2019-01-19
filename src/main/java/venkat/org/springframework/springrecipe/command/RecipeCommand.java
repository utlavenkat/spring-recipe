package venkat.org.springframework.springrecipe.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private DifficultyCommand difficulty;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Byte[] image;

    public boolean addCategory(final CategoryCommand categoryCommand) {
        if (categories == null) {
            categories = new HashSet<>();
        }
        return categories.add(categoryCommand);
    }

    public boolean addIngredient(final IngredientCommand ingredientCommand) {
        if (ingredients == null) {
            ingredients = new HashSet<>();
        }
        return ingredients.add(ingredientCommand);
    }
}
