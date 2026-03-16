package com.notation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operateur")
@Data
public class Operateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 2)
    private String nom;
}
