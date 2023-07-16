package edu.goit.notesservice.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {

    @Query(value = "SELECT * FROM NOTES WHERE user_id=?", nativeQuery = true)
    public List<Note> findByUserId(int user_id);
}