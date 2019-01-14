package venkat.org.springframework.springrecipe.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import venkat.org.springframework.springrecipe.command.IngredientCommand;
import venkat.org.springframework.springrecipe.domain.Ingredient;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;
import venkat.org.springframework.springrecipe.repositories.IngredientRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    private IngredientService ingredientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientRepository);
    }

    @Test
    public void findIngredientById() {
        //Given
        Long id = 1L;
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().id(1L).uom("Teaspoon").build();
        Ingredient ingredient = Ingredient.builder().id(1L).description("Test Ingredient").amount(BigDecimal.ONE)
                .unitOfMeasure(unitOfMeasure).build();

        //When
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient));
        IngredientCommand ingredientCommand = ingredientService.findIngredientById(id);

        //Then
        assertNotNull(ingredient);
        assertEquals(ingredient.getId().longValue(), id.longValue());

    }

    @Test(expected = RuntimeException.class)
    public void findIngredientById_invalidId() {
        //Given
        Long id = 1L;

        //When
        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.empty());
        IngredientCommand ingredientCommand = ingredientService.findIngredientById(id);

        //Then
        //see @Test above for exception.
    }
}