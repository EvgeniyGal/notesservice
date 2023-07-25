package edu.goit.notesservice.note;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteCreateDTO {
    @NotBlank
    @Size(min = 3, max = 100, message = "Найменування нотатки має мати від 5 до 100 символів")
    private String title;

    @NotBlank
    @Size(min = 3, max = 10000, message = "Текст нотатки має мати від 5 до 10000 символів")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
}