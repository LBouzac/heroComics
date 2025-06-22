package com.example.herocomics.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table (name = "Super_hero")
public class SuperHeroEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "nom_hero")
    private String nomHero;

    @Column(name = "vraie_identite")
    private String vraieIdentite;

    @Column(name = "pouvoir")
    private String pouvoir;

    @Column(name = "faiblesse")
    private String faiblesse;

    public String getFaiblesse() {
        return faiblesse;
    }

    public void setFaiblesse(String faiblesse) {
        this.faiblesse = faiblesse;
    }

    public String getPouvoir() {
        return pouvoir;
    }

    public void setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
    }

    public String getVraieIdentite() {
        return vraieIdentite;
    }

    public void setVraieIdentite(String vraieIdentite) {
        this.vraieIdentite = vraieIdentite;
    }

    public String getNomHero() {
        return nomHero;
    }

    public void setNomHero(String nomHero) {
        this.nomHero = nomHero;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
