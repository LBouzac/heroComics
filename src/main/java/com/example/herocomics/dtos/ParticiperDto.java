package com.example.herocomics.dtos;

public class ParticiperDto {
    private Integer id;
    private Integer superHeroId;
    private Integer missionId;
    private String nomHero;
    private String nomMission;
    private String role;
    private String status;

    public ParticiperDto(Integer id, String nomHero, String nomMission, String role, String status, Integer superHeroId, Integer missionId) {
        this.id = id;
        this.nomHero = nomHero;
        this.nomMission = nomMission;
        this.role = role;
        this.status = status;
        this.superHeroId = superHeroId;
        this.missionId = missionId;
    }

    public Integer getSuperHeroId() {
        return superHeroId;
    }

    public void setSuperHeroId(Integer superHeroId) {
        this.superHeroId = superHeroId;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomHero() {
        return nomHero;
    }

    public void setNomHero(String nomHero) {
        this.nomHero = nomHero;
    }

    public String getNomMission() {
        return nomMission;
    }

    public void setNomMission(String nomMission) {
        this.nomMission = nomMission;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
