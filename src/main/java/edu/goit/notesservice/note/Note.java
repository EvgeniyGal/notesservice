package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @Column(length = 36, nullable = false)
    @NotBlank
    private String id;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(length = 10000, nullable = false)
    @NotBlank
    @Size(min = 3, max = 10000)
    private String content;

    @Column(name = "access_type", length = 10, nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note() {
    }

    public Note(NoteCreateDTO note) {
        this.name = note.getName();
        this.content = note.getContent();
        this.accessType = note.getAccessType();
    }
}