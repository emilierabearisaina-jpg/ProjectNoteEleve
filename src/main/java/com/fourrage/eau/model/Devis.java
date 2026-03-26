package com.fourrage.eau.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "type_devis_id")
    private TypeDevis typeDevis;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

    @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DevisDetail> details = new ArrayList<>();

    public Devis() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TypeDevis getTypeDevis() {
        return typeDevis;
    }

    public void setTypeDevis(TypeDevis typeDevis) {
        this.typeDevis = typeDevis;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public List<DevisDetail> getDetails() {
        return details;
    }

    public void setDetails(List<DevisDetail> details) {
        this.details = details;
    }
    
    public void addDetail(DevisDetail detail) {
        details.add(detail);
        detail.setDevis(this);
    }

    @Transient
    public Double getMontantTotal() {
        return details.stream().mapToDouble(d -> d.getMontant() != null ? d.getMontant() : 0.0).sum();
    }
}
