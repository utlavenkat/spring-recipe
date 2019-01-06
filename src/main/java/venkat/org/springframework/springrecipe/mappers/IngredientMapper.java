package venkat.org.springframework.springrecipe.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.command.IngredientCommand;
import venkat.org.springframework.springrecipe.domain.Ingredient;

@Component
public class IngredientMapper {


    private MapperFacade mapperFacade;

    public IngredientMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Ingredient.class, IngredientCommand.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Ingredient convertCommandToDomain(final IngredientCommand ingredientCommand) {
        return mapperFacade.map(ingredientCommand, Ingredient.class);
    }

    public IngredientCommand convertDomainToCommand(final Ingredient ingredient) {
        return mapperFacade.map(ingredient, IngredientCommand.class);
    }
}
