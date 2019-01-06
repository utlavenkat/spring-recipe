package venkat.org.springframework.springrecipe.mappers;

import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import venkat.org.springframework.springrecipe.command.DifficultyCommand;
import venkat.org.springframework.springrecipe.command.NotesCommand;
import venkat.org.springframework.springrecipe.command.RecipeCommand;
import venkat.org.springframework.springrecipe.domain.Difficulty;
import venkat.org.springframework.springrecipe.domain.Notes;
import venkat.org.springframework.springrecipe.domain.Recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NotesMapperTest {

    private NotesMapper notesMapper;

    @Before
    public void setUp() {
        notesMapper = new NotesMapper();
    }

    @After
    public void tearDown() {
        notesMapper = null;
    }

    @Test
    public void convertCommandToDomain() {
        val recipeCommand = RecipeCommand.builder().id(1L).cookTime(10).description("Test Recipe")
                .difficulty(DifficultyCommand.EASY).directions("1.2.3.4").prepTime(20).servings(4).source("My Source")
                .url("http://my.recipe.com").build();
        val notesCommand = NotesCommand.builder().id(1L).notes("Test Notes").recipe(recipeCommand).build();
        recipeCommand.setNotes(notesCommand);

        val notes = notesMapper.convertCommandToDomain(notesCommand);

        assertNotNull(notes);
        assertEquals(notesCommand.getId(), notes.getId());
        assertEquals(notesCommand.getNotes(), notes.getNotes());

        val recipe = notes.getRecipe();
        assertNotNull(recipe);
        assertEquals(recipeCommand.getCookTime(), recipe.getCookTime());
        assertEquals(recipeCommand.getDescription(), recipe.getDescription());
        assertEquals(recipeCommand.getDifficulty().name(), recipe.getDifficulty().name());
        assertEquals(recipeCommand.getDirections(), recipe.getDirections());
        assertEquals(recipeCommand.getId(), recipe.getId());
        assertEquals(recipeCommand.getPrepTime(), recipe.getPrepTime());
        assertEquals(recipeCommand.getServings(), recipe.getServings());
        assertEquals(recipeCommand.getSource(), recipe.getSource());
        assertEquals(recipeCommand.getUrl(), recipe.getUrl());

    }

    @Test
    public void convertDomainToCommand() {
        val recipe = Recipe.builder().id(1L).description("Test Recipe").cookTime(10).difficulty(Difficulty.EASY)
                .directions("1.2.3.4").prepTime(10).servings(4).source("Test Source")
                .url("http://test.url.com").build();
        val notes = new Notes("Test Notes", recipe);
        notes.setId(1L);

        val notesCommand = notesMapper.convertDomainToCommand(notes);

        assertNotNull(notesCommand);
        assertEquals(notes.getId(), notesCommand.getId());
        assertEquals(notes.getNotes(), notesCommand.getNotes());

        val recipeCommand = notesCommand.getRecipe();
        assertNotNull(recipeCommand);
        assertEquals(recipeCommand.getUrl(), recipe.getUrl());
        assertEquals(recipeCommand.getSource(), recipe.getSource());
        assertEquals(recipeCommand.getServings(), recipe.getServings());
        assertEquals(recipeCommand.getPrepTime(), recipe.getPrepTime());
        assertEquals(recipeCommand.getId(), recipe.getId());
        assertEquals(recipeCommand.getDirections(), recipe.getDirections());
        assertEquals(recipeCommand.getDifficulty().name(), recipe.getDifficulty().name());
        assertEquals(recipeCommand.getDescription(), recipe.getDescription());
        assertEquals(recipeCommand.getCookTime(), recipe.getCookTime());
    }
}