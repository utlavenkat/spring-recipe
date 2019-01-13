package venkat.org.springframework.springrecipe.mappers;

import lombok.extern.slf4j.Slf4j;
import venkat.org.springframework.springrecipe.command.UnitOfMeasureCommand;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;

@Slf4j
public class UnitOfMeasureMapper {

    public UnitOfMeasure convertCommandToDomain(final UnitOfMeasureCommand unitOfMeasureCommand) {
        log.info("Converting UnitOfMeasure Command to Domain");
        UnitOfMeasure unitOfMeasure = null;
        if (unitOfMeasureCommand != null) {
            unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setId(unitOfMeasureCommand.getId());
            unitOfMeasure.setUom(unitOfMeasureCommand.getUom());
        }
        log.info("UnitOfMeasure Domain ::" + unitOfMeasure);
        return unitOfMeasure;
    }

    public UnitOfMeasureCommand convertDomainToCommand(final UnitOfMeasure unitOfMeasure) {
        log.info("Converting UnitOfMeasure Domain to Command");
        UnitOfMeasureCommand unitOfMeasureCommand = null;
        if (unitOfMeasure != null) {
            unitOfMeasureCommand = UnitOfMeasureCommand.builder().uom(unitOfMeasure
                    .getUom()).id(unitOfMeasure.getId()).build();
        }
        log.info("UnitOfMeasure Command ::" + unitOfMeasureCommand);
        return unitOfMeasureCommand;
    }
}
