package venkat.org.springframework.springrecipe.mappers;

import lombok.val;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import venkat.org.springframework.springrecipe.command.NotesCommand;
import venkat.org.springframework.springrecipe.domain.Notes;

@Component
public class NotesMapper {
    private final MapperFacade mapperFacade;

    public NotesMapper() {
        val mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(NotesCommand.class, Notes.class).byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    public Notes convertCommandToDomain(final NotesCommand notesCommand) {
        return mapperFacade.map(notesCommand, Notes.class);
    }

    public NotesCommand convertDomainToCommand(final Notes notes) {
        return mapperFacade.map(notes, NotesCommand.class);
    }
}
