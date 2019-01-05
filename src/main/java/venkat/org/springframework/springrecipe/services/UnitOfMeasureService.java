package venkat.org.springframework.springrecipe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;
import venkat.org.springframework.springrecipe.repositories.UnitOfMeasureRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitOfMeasureService {
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasure getUnitOfMeasureByUom(String uom) {
        UnitOfMeasure unitOfMeasure = null;
        Optional<UnitOfMeasure> savedUnitOfMeasure = unitOfMeasureRepository.findByUom(uom);
        if(savedUnitOfMeasure.isPresent()) {
            unitOfMeasure = savedUnitOfMeasure.get();
        }
        return  unitOfMeasure;
    }

    /**
     * This method returns all Unit Of Measure objects map. The key is uom string.
     * @return
     */
    public Map<String,UnitOfMeasure> getUnitOfMeasureMap() {
        Map<String,UnitOfMeasure> unitOfMeasureMap = new HashMap<>();

        unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> {
            unitOfMeasureMap.put(unitOfMeasure.getUom(),unitOfMeasure);
        });

        return unitOfMeasureMap;

    }
}
