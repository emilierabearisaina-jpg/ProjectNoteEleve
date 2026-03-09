package com.notation.repository;

import com.notation.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n JOIN FETCH n.correcteur JOIN FETCH n.matiere WHERE n.idEleve = :idEleve ORDER BY n.idMatiere, n.id")
    List<Note> findByIdEleveWithDetails(@Param("idEleve") Long idEleve);

    void deleteByIdEleve(Long idEleve);
    void deleteByIdMatiere(Long idMatiere);
    void deleteByIdCorrecteur(Long idCorrecteur);
}
