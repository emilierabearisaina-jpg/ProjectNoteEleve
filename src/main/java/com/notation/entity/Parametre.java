package com.notation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parametres")
@Data
public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_resolution", nullable = false)
    private Resolution resolution;

    @Column(name = "valeur", nullable = false)
    private Double valeur;

    @ManyToOne
    @JoinColumn(name = "id_operateur", nullable = false)
    private Operateur operateur;
}
