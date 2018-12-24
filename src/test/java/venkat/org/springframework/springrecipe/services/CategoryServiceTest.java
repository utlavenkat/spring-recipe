package venkat.org.springframework.springrecipe.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import venkat.org.springframework.springrecipe.domain.Category;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testGetByCategoryName_Valid() {
        Assert.assertNotNull(categoryService);
        Category category = categoryService.getByCategoryName("AMERICAN");
        Assert.assertNotNull(category);
        Assert.assertNotNull(category.getId());
        Assert.assertEquals("AMERICAN",category.getCategoryName());

    }

    @Test
    public void testGetByCategoryName_Invalid() {
        Assert.assertNull(categoryService.getByCategoryName("doesnotexists"));
    }
}