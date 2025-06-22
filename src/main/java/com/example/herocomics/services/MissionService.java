package com.example.herocomics.services;

import com.example.herocomics.entities.MissionEntity;
import com.example.herocomics.entities.ParticiperEntity;
import com.example.herocomics.repositories.IMissionRepository;
import com.example.herocomics.repositories.ParticiperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissionService implements IMissionService {

    @Autowired
    private IMissionRepository missionRepository;

    @Autowired
    private ParticiperRepository participerRepository;

    @Override
    public Integer ajouterMission(String nom_mission, String description, String niveau_menace, LocalDate date_debut, LocalDate date_fin) {
        // On crée une entité MissionEntity
        MissionEntity entity = new MissionEntity();
        entity.setNomMission(nom_mission);
        entity.setDescription(description);
        entity.setNiveauMenace(MissionEntity.NiveauMenace.valueOf(niveau_menace));
        entity.setDateDebut(date_debut);
        entity.setDateFin(date_fin);


        // Sauvegarde en base
        missionRepository.saveAndFlush(entity);
        return entity.getID(); // Renvoie l'ID de la mission
    }

    @Override
    public List<ParticiperEntity> getEquipeMission(Integer id) {
        return participerRepository.findByMissionId(id);
    }

    public List<MissionEntity> getLongestMissions(int limit) {
        return missionRepository.findLongestMissions(Pageable.ofSize(limit));
    }

}
