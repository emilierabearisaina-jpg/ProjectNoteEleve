package com.fourrage.eau.model;

import jakarta.persistence.*;

@Entity
@Table(name = "details_devis")
public class DevisDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private Double pu;
    private Double qtt;
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "devis_id")
    private Devis devis;

    public DevisDetail() {}

    public DevisDetail(String libelle, Double pu, Double qtt, Devis devis) {
        this.libelle = libelle;
        this.pu = pu;
        this.qtt = qtt;
        this.montant = pu * qtt;
        this.devis = devis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Double getQtt() {
        return qtt;
    }

    public void setQtt(Double qtt) {
        this.qtt = qtt;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
}
