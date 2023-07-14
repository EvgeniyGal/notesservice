package edu.goit.notesservice.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
