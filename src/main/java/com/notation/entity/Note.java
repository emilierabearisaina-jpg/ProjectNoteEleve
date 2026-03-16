package com.notation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notes")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_eleve", nullable = false)
    private Long idEleve;

    @Column(name = "id_correcteur", nullable = false)
    private Long idCorrecteur;

    @Column(name = "id_matiere", nullable = false)
    private Long idMatiere;

    @Column(name = "note", nullable = false)
    private Double note;

    // Jointure pour afficher le nom du correcteur
    @ManyToOne
    @JoinColumn(name = "id_correcteur", insertable = false, updatable = false)
    private Correcteur correcteur;

    // Jointure pour afficher la matière
    @ManyToOne
    @JoinColumn(name = "id_matiere", insertable = false, updatable = false)
    private Matiere matiere;
}
