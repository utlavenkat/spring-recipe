package venkat.org.springframework.springrecipe.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeTest {
    @Test
    public void testShouldConstruct() {
        Recipe recipe = new Recipe();
        Assert.assertNotNull("Object cannot be null",recipe);
    }
}