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
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @NotBlank
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
}