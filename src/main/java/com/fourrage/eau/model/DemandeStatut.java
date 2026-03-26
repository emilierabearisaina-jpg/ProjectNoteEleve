package com.fourrage.eau.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demande_statut")
public class DemandeStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

    // Optional link if travaux exists
    @Column(name = "travaux_id")
    private Long travauxId;

    public DemandeStatut() {}

    public DemandeStatut(LocalDate date, Status status, Demande demande) {
        this.date = date;
        this.status = status;
        this.demande = demande;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Demande getDemande() { return demande; }
    public void setDemande(Demande demande) { this.demande = demande; }

    public Long getTravauxId() { return travauxId; }
    public void setTravauxId(Long travauxId) { this.travauxId = travauxId; }
}
