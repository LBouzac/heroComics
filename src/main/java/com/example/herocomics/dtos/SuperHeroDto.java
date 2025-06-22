package com.example.herocomics.dtos;

public class SuperHeroDto {
    private String nomHero;
    private String vraieIdentite;
    private String pouvoir;
    private String faiblesse;


    public SuperHeroDto(String nomHero) {
        this.nomHero = nomHero;
    }

    public String getNomHero() {
        return nomHero;
    }

    public void setNomHero(String nomHero) {
        this.nomHero = nomHero;
    }

    public String getVraieIdentite() {
        return vraieIdentite;
    }

    public void setVraieIdentite(String vraieIdentite) {
        this.vraieIdentite = vraieIdentite;
    }

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
}
