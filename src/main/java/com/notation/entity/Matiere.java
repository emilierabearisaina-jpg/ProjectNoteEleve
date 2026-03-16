package com.notation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "matieres")
@Data
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;
}
