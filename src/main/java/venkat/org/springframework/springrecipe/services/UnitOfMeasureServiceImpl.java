package venkat.org.springframework.springrecipe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.command.UnitOfMeasureCommand;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;
import venkat.org.springframework.springrecipe.mappers.UnitOfMeasureMapper;
import venkat.org.springframework.springrecipe.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureMapper unitOfMeasureMapper = new UnitOfMeasureMapper();
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Transactional
    public UnitOfMeasureCommand getUnitOfMeasureByUom(String uom) {
        UnitOfMeasureCommand unitOfMeasureCommand = null;
        Optional<UnitOfMeasure> savedUnitOfMeasure = unitOfMeasureRepository.findByUom(uom);
        if (savedUnitOfMeasure.isPresent()) {
            unitOfMeasureCommand = unitOfMeasureMapper.convertDomainToCommand(savedUnitOfMeasure.get());
        }
        return unitOfMeasureCommand;
    }

    @Transactional
    public Map<String, UnitOfMeasureCommand> getUnitOfMeasureMap() {
        Map<String, UnitOfMeasureCommand> unitOfMeasureMap = new HashMap<>();

        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> unitOfMeasureMap.put(unitOfMeasure.getUom(), unitOfMeasureMapper.convertDomainToCommand(unitOfMeasure)));

        return unitOfMeasureMap;

    }
}
