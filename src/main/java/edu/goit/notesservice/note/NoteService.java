package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.AuthService;
import edu.goit.notesservice.auth.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final AuthService authService;

    public List<Note> listAll() {
        User currentUser = authService.getUser();
        return noteRepository.findNotesByUserId(currentUser.getId());
    }

    public Note add(NoteCreateDTO note) {
        Note fullNote = new Note(UUID.randomUUID(),
                note.getTitle(),
                note.getContent(),
                note.getAccessType(),
                authService.getUser());
        return noteRepository.save(fullNote);
    }

    public boolean isExist(UUID id) {
        return noteRepository.existsById(id);
    }

    public void deleteById(UUID id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Нотатка з id '" + id + "' не існує");
        }
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        note.setUser(authService.getUser());
        noteRepository.save(note);
    }

    public Note getById(UUID id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Нотатка з id '" + id + "' не існує");
        }
        return noteRepository.getReferenceById(id);
    }

    public boolean isPossibleToShowNote(UUID id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Нотатка з id '" + id + "' не існує");
        }
        Note note = getById(id);
        return note.getAccessType().equals(AccessType.PUBLIC)
                || note.getUser().getId() == authService.getUser().getId();
    }
}
