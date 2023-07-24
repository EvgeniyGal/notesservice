package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @NotNull
    private UUID id;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

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

    public Note(UUID id, String title, String content, AccessType accessType, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.accessType = accessType;
        this.user = user;
    }
}