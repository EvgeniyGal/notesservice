package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.User;
import edu.goit.notesservice.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public List<Note> listAll() {
        //temporary search for User with id 1
        return noteRepository.findNotesByUserId(1);
    }

    public Note add(Note note) {
        note.setId(UUID.randomUUID().toString());
        //temporary all notes to User 1
        User user = userRepository.getReferenceById(1);
        note.setUser(user);
        return noteRepository.save(note);
    }

    public boolean isExist(String id) {
        return noteRepository.existsById(id);
    }

    public void deleteById(String id) {
        if(!isExist(id)){
            throw new IllegalArgumentException("Нотатка з id '" + id + "' не існує");
        }
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        //temporary all notes to User 1
        User user = userRepository.getReferenceById(1);
        note.setUser(user);
        noteRepository.save(note);
    }

    public Note getById(String id) {
        if(!isExist(id)){
            throw new IllegalArgumentException("Нотатка з id '" + id + "' не існує");
        }
        return noteRepository.getReferenceById(id);
    }
}
