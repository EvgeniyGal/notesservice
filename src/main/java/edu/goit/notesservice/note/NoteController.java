package edu.goit.notesservice.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/note")
@Controller
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping ("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("note/notes_list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @GetMapping("/create")
    public ModelAndView getMarkupToCreateNote() {
        ModelAndView result = new ModelAndView("note/create_note");
        result.addObject("note", new Note());
        return result;
    }

    @PostMapping("/create")
    public String addNewNote(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView getNoteById(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("note/edit_note");
        result.addObject("note", noteService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String updateNote(@ModelAttribute Note note) {;
        noteService.update(note);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") String id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}
