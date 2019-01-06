package venkat.org.springframework.springrecipe.mappers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.command.UnitOfMeasureCommand;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;

@Component
public class UnitOfMeasureMapper {
    private final MapperFacade mapperFacade;

    public UnitOfMeasureMapper() {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UnitOfMeasureCommand.class, UnitOfMeasure.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public UnitOfMeasure convertCommandToDomain(final UnitOfMeasureCommand unitOfMeasureCommand) {
        return mapperFacade.map(unitOfMeasureCommand, UnitOfMeasure.class);
    }

    public UnitOfMeasureCommand convertDomainToCommand(final UnitOfMeasure unitOfMeasure) {
        return mapperFacade.map(unitOfMeasure, UnitOfMeasureCommand.class);
    }
}
