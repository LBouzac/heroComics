package com.example.herocomics.services;


import com.example.herocomics.entities.ParticiperEntity;

import java.time.LocalDate;
import java.util.List;

public interface IMissionService {
    Integer ajouterMission(String nom_mission, String description, String niveau_menace, LocalDate date_debut, LocalDate date_fin);

    List<ParticiperEntity> getEquipeMission(Integer id);

}
