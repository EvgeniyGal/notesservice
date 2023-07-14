package edu.goit.notesservice.note;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(length = 10, nullable = false)
    private String access_type;

    @Column(nullable = false)
    private int user_id;
}