package venkat.org.springframework.springrecipe.mappers;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.command.CategoryCommand;
import venkat.org.springframework.springrecipe.domain.Category;

@Component
public class CategoryMapper {
    private final MapperFacade mapperFacade;

    public CategoryMapper() {
        val mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(CategoryCommand.class, Category.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Category convertCommandToDomain(CategoryCommand categoryCommand) {
        return mapperFacade.map(categoryCommand, Category.class);
    }

    public CategoryCommand convertDomainToCommand(Category category) {
        return mapperFacade.map(category, CategoryCommand.class);
    }
}
