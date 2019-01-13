package venkat.org.springframework.springrecipe.mappers;

import lombok.extern.slf4j.Slf4j;
import venkat.org.springframework.springrecipe.command.NotesCommand;
import venkat.org.springframework.springrecipe.domain.Notes;

@Slf4j
public class NotesMapper {

    public Notes convertCommandToDomain(final NotesCommand notesCommand) {
        log.info("Converting Notes Command to Domain");
        Notes notes = null;
        if (notesCommand != null) {
            notes = new Notes();
            notes.setId(notesCommand.getId());
            notes.setNotes(notesCommand.getNotes());
        }
        log.info("Notes Domain ::" + notes);
        return notes;
    }

    public NotesCommand convertDomainToCommand(final Notes notes) {
        log.info("Converting Notes Domain to Command");
        NotesCommand notesCommand = null;
        if (notes != null) {
            notesCommand = NotesCommand.builder().notes(notes.getNotes()).id(notes.getId()).build();
        }
        log.info("Notes Command ::" + notesCommand);
        return notesCommand;
    }
}
