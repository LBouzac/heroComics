package com.example.herocomics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Participer")
public class ParticiperEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_super_hero")
    private SuperHeroEntity superHeroEntity;

    @ManyToOne
    @JoinColumn(name = "id_mission") // adapte ici
    private MissionEntity mission;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    public SuperHeroEntity getSuperHeroEntity() {
        return superHeroEntity;
    }

    public void setSuperHeroEntity(SuperHeroEntity superHeroEntity) {
        this.superHeroEntity = superHeroEntity;
    }

    public MissionEntity getMission() {
        return mission;
    }

    public void setMission(MissionEntity mission) {
        this.mission = mission;
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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}