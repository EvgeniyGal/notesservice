package edu.goit.notesservice.note;

import edu.goit.notesservice.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @Column(length = 36, nullable = false)
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10000, nullable = false)
    private String content;

    @Column(name = "access_type", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}