package com.fourrage.eau.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    private String lieu;
    private String district;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Demande() {}

    public Demande(LocalDate date, String lieu, String district, Client client) {
        this.date = date;
        this.lieu = lieu;
        this.district = district;
        this.client = client;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
