package venkat.org.springframework.springrecipe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import venkat.org.springframework.springrecipe.command.IngredientCommand;
import venkat.org.springframework.springrecipe.domain.Ingredient;
import venkat.org.springframework.springrecipe.mappers.IngredientMapper;
import venkat.org.springframework.springrecipe.repositories.IngredientRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper = new IngredientMapper();

    @Override
    public IngredientCommand findIngredientById(Long id) {
        final Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (ingredient == null) {
            throw new RuntimeException("No ingredient exists for the given Id " + id);
        }
        return ingredientMapper.convertDomainToCommand(ingredient);
    }

    @Override
    @Transactional
    public IngredientCommand save(IngredientCommand ingredientCommand) {
        final Ingredient savedIngredient = ingredientRepository.save(ingredientMapper.convertCommandToDomain(ingredientCommand));
        return ingredientMapper.convertDomainToCommand(savedIngredient);
    }

    @Override
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
