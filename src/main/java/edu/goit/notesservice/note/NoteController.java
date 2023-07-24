package edu.goit.notesservice.note;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import java.util.UUID;

@RequestMapping("/note")
@Controller
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private static final String REDIRECT_TO_LIST = "redirect:/note/list";

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        return new ModelAndView("note/notes_list", "notes", noteService.listAll());
    }

    @GetMapping("/create")
    public ModelAndView getMarkupToCreateNote() {
        return new ModelAndView("note/create_note", "note", new NoteCreateDTO());
    }

    @PostMapping("/create")
    public String addNewNote(@Valid @ModelAttribute NoteCreateDTO note) {
        noteService.add(note);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@RequestParam(name = "id") UUID id) {
        return new ModelAndView("note/edit_note", "note", noteService.getById(id));
    }

    @GetMapping("/share")
    public ModelAndView getNoteForShare(@RequestParam(name = "id") UUID id) {
        if (noteService.isPossibleToShowNote(id)) {
            return new ModelAndView("note/share", "note", noteService.getById(id));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Нотатка з id '" + id + "' не існує");
        }
    }

    @PostMapping("/edit")
    public String updateNote(@Valid @ModelAttribute Note note) {
        noteService.update(note);
        return REDIRECT_TO_LIST;
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") UUID id) {
        noteService.deleteById(id);
        return REDIRECT_TO_LIST;
    }
}
