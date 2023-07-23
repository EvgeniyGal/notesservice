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
        Note fullNote = new Note(note);
        fullNote.setId(UUID.randomUUID().toString());
        fullNote.setUser(authService.getUser());
        return noteRepository.save(fullNote);
    }

    public boolean isExist(String id) {
        return noteRepository.existsById(id);
    }

    public void deleteById(String id) {
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

    public Note getById(String id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Нотатка з id '" + id + "' не існує");
        }
        return noteRepository.getReferenceById(id);
    }

    public boolean isPossibleToShowNote(String id) {
        if (!isExist(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Нотатка з id '" + id + "' не існує");
        }
        Note note = getById(id);
        if (note.getAccessType().equals(AccessType.PRIVATE) && note.getUser().getId() != authService.getUser().getId()) {
            return false;
        }
        return true;
    }
}
