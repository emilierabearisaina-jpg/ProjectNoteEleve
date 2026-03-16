package com.notation.repository;

import com.notation.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {
    List<Parametre> findByMatiereId(Long idMatiere);
}
