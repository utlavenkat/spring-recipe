package venkat.org.springframework.springrecipe.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.springrecipe.domain.UnitOfMeasure;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitOfMeasureServiceTest {

    @Autowired
    private UnitOfMeasureService unitOfMeasureService;

    @Test
    public void getUnitOfMeasureByUom() {
        UnitOfMeasure unitOfMeasure = unitOfMeasureService.getUnitOfMeasureByUom("Cup");
        Assert.assertNotNull(unitOfMeasure);
        Assert.assertNotNull(unitOfMeasure.getId());
        Assert.assertEquals("Cup",unitOfMeasure.getUom());
    }

    @Test
    public void testGetUnitOfMeasureByUom_Invalid() {
        Assert.assertNull(unitOfMeasureService.getUnitOfMeasureByUom("doesnotexists"));
    }

    @Test
    public void getUnitOfServiceMap() {
        Map<String,UnitOfMeasure> unitOfMeasureMap = unitOfMeasureService.getUnitOfServiceMap();
        Assert.assertNotNull(unitOfMeasureMap);
        Assert.assertTrue(unitOfMeasureMap.size() > 0);
    }
}