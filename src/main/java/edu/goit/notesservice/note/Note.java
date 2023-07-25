package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @NotNull
    private UUID id;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Size(min = 5, max = 100, message = "Найменування нотатки має мати від 5 до 100 символів")
    private String title;

    @Column(length = 10000, nullable = false)
    @NotBlank
    @Size(min = 5, max = 10000, message = "Текст нотатки має мати від 5 до 10000 символів")
    private String content;

    @Column(name = "access_type", length = 10, nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}