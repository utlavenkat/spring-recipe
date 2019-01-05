package venkat.org.springframework.springrecipe.services;

import lombok.val;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;
import venkat.org.springframework.springrecipe.repositories.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;


public class UnitOfMeasureServiceTest {

    private UnitOfMeasureService unitOfMeasureService;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository);
    }
    @After
    public void tearDown() {
        unitOfMeasureRepository = null;
        unitOfMeasureService = null;
    }

    @Test
    public void getUnitOfMeasureByUom() {
        val uom = "Cup";
        val savedUnitOfMeasure = new UnitOfMeasure(uom);
        savedUnitOfMeasure.setId(1234L);
        when(unitOfMeasureRepository.findByUom(uom)).thenReturn(Optional.of(savedUnitOfMeasure));
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.getUnitOfMeasureByUom(uom);
        Assert.assertNotNull(unitOfMeasure);
        Assert.assertNotNull(unitOfMeasure.getId());
        Assert.assertEquals(uom,unitOfMeasure.getUom());
        verify(unitOfMeasureRepository,times(1)).findByUom(uom);
    }

    @Test
    public void testGetUnitOfMeasureByUom_Invalid() {
        val uom ="doenotexits";
        when(unitOfMeasureRepository.findByUom(uom)).thenReturn(Optional.ofNullable(null));
        Assert.assertNull(unitOfMeasureService.getUnitOfMeasureByUom(uom));
    }

    @Test
    public void getUnitOfServiceMap() {
        val uom1 = new UnitOfMeasure("Cup");
        uom1.setId(1L);
        val uom2 = new UnitOfMeasure("Pint");
        uom2.setId(2L);
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>(2);
        unitOfMeasures.add(uom1);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        Map<String,UnitOfMeasure> unitOfMeasureMap = unitOfMeasureService.getUnitOfMeasureMap();
        Assert.assertNotNull(unitOfMeasureMap);
        Assert.assertTrue(unitOfMeasureMap.size() == 2);
    }
}