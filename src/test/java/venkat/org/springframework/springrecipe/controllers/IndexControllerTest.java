package venkat.org.springframework.springrecipe.controllers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import venkat.org.springframework.springrecipe.domain.Recipe;

import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexControllerTest {
    @Autowired
    private IndexController indexController;

    @Test
    public void getIndexPage() {
        Model model = new ConcurrentModel();
        Assert.assertThat(indexController.getIndexPage(model), Matchers.equalToIgnoringCase("index"));
        Set<Recipe> recipeSet = (Set<Recipe>)((ConcurrentModel) model).get("recipes");
        Assert.assertNotNull(recipeSet);
        Assert.assertTrue(recipeSet.size() == 2);
    }
}