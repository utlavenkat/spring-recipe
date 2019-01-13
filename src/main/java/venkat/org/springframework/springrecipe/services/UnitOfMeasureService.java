package venkat.org.springframework.springrecipe.services;

import venkat.org.springframework.springrecipe.command.UnitOfMeasureCommand;

import java.util.Map;

public interface UnitOfMeasureService {
    UnitOfMeasureCommand getUnitOfMeasureByUom(String uom);

    Map<String, UnitOfMeasureCommand> getUnitOfMeasureMap();
}
