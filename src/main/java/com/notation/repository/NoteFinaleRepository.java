package com.notation.repository;

import com.notation.entity.NoteFinale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteFinaleRepository extends JpaRepository<NoteFinale, Long> {
    List<NoteFinale> findByIdEleve(Long idEleve);
}
