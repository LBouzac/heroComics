package com.example.herocomics.dtos;

public class MissionPeriodeDto {
    private String nom;
    private long nbSuperheros;
    private String statutGlobal;
    private long dureeJours;
    private String niveauMenace;

    public MissionPeriodeDto(String nom, long nbSuperheros, String statutGlobal, long dureeJours, String niveauMenace) {
        this.nom = nom;
        this.nbSuperheros = nbSuperheros;
        this.statutGlobal = statutGlobal;
        this.dureeJours = dureeJours;
        this.niveauMenace = niveauMenace;
    }


    public String getNomMission() {
        return nom;
    }

    public void setNomMission(String nom) {
        this.nom = nom;
    }

    public String getNiveauMenace() {
        return niveauMenace;
    }

    public void setNiveauMenace(String niveauMenace) {
        this.niveauMenace = niveauMenace;
    }

    public long getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(long dureeJours) {
        this.dureeJours = dureeJours;
    }

    public String getStatutGlobal() {
        return statutGlobal;
    }

    public void setStatutGlobal(String statutGlobal) {
        this.statutGlobal = statutGlobal;
    }

    public long getNbSuperheros() {
        return nbSuperheros;
    }

    public void setNbSuperheros(long nbSuperheros) {
        this.nbSuperheros = nbSuperheros;
    }
}