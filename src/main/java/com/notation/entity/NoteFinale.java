package com.notation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notefinales")
@org.hibernate.annotations.Immutable
@Data
public class NoteFinale {

    @Id
    private Long id;

    @Column(name = "id_eleve")
    private Long idEleve;

    @Column(name = "nom_eleve")
    private String nomEleve;

    @Column(name = "id_matiere")
    private Long idMatiere;

    @Column(name = "nom_matiere")
    private String nomMatiere;

    @Column(name = "notefinale")
    private Double noteFinale;
}
