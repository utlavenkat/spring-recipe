package venkat.org.springframework.springrecipe.mappers;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.domain.Recipe;

@Component
public class RecipeMapper {

    private final MapperFacade mapperFacade;

    public RecipeMapper() {
        val mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(RecipeCommand.class, Recipe.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Recipe convertCommandToDomain(final RecipeCommand recipeCommand) {
        return mapperFacade.map(recipeCommand, Recipe.class);
    }

    public RecipeCommand convertDomainToCommand(final Recipe recipe) {
        return mapperFacade.map(recipe, RecipeCommand.class);
    }
}
