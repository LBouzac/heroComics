package com.example.herocomics.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "Mission")
public class MissionEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "nom_mission")
    private String nomMission;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "description")
    private String description;

    @Column(name = "niveau_menace")
    @Enumerated(EnumType.STRING)
    private NiveauMenace niveauMenace;

    public enum NiveauMenace {
        FAIBLE, MOYENNE, ELEVEE
    }

    public NiveauMenace getNiveauMenace() {
        return niveauMenace;
    }

    public void setNiveauMenace(NiveauMenace niveauMenace) {
        this.niveauMenace = niveauMenace;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNomMission() {
        return nomMission;
    }

    public void setNomMission(String nomMission) {
        this.nomMission = nomMission;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Object> getParticipations() {
        return List.of(); // Retourne une liste vide pour l'instant
    }
}
