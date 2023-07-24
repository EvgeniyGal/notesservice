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
        ModelAndView result = new ModelAndView("note/notes_list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @GetMapping("/create")
    public ModelAndView getMarkupToCreateNote() {
        ModelAndView result = new ModelAndView("note/create_note");
        result.addObject("note", new NoteCreateDTO());
        return result;
    }

    @PostMapping("/create")
    public String addNewNote(@Valid @ModelAttribute NoteCreateDTO note) {
        noteService.add(note);
        return REDIRECT_TO_LIST;
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@RequestParam(name = "id") UUID id) {
        ModelAndView result = new ModelAndView("note/edit_note");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @GetMapping("/share")
    public ModelAndView getNoteForShare(@RequestParam(name = "id") UUID id) {
        if (noteService.isPossibleToShowNote(id)) {
            ModelAndView result = new ModelAndView("note/share");
            result.addObject("note", noteService.getById(id));
            return result;
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
