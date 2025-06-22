package com.example.herocomics.services;

import com.example.herocomics.dtos.ParticiperDto;
import com.example.herocomics.entities.ParticiperEntity;
import com.example.herocomics.repositories.ISuperHeroRepository;
import com.example.herocomics.repositories.ParticiperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticiperService implements IParticiperService {
    @Autowired
    ParticiperRepository participerRepository;

    @Autowired
    ISuperHeroRepository superHeroRepository;

    /**
     * Méthode pour ajouter une participation d'un super-héros à une mission.
     *
     * @param superHeroId L'identifiant du super-héros.
     * @param missionId L'identifiant de la mission.
     * @return L'identifiant du super-héros.
     */
    @Override
    public Integer ajouterParticiper(Integer superHeroId, Integer missionId) {
        // Implémentation à adapter selon votre logique métier
        ParticiperEntity entity = new ParticiperEntity();
        entity.setSuperHeroEntity(superHeroRepository.findById(superHeroId)
                .orElseThrow(() -> new RuntimeException("Super-héros non trouvé")));


        participerRepository.save(entity);
        return superHeroId;
    }

// TODO: Ajouter la logique pour lier la mission à la participation
    @Override
    public ParticiperEntity ajouterParticiper(ParticiperDto dto) {
        ParticiperEntity entity = new ParticiperEntity();

        // On recupere le super-héros
        entity.setSuperHeroEntity(superHeroRepository.findById(dto.getSuperHeroId())
                .orElseThrow(() -> new RuntimeException("Super-héros non trouvé")));

        // On recupere la mission
        // entity.setMissionEntity(missionRepository.findById(dto.getId_mission())
        //         .orElseThrow(() -> new RuntimeException("Mission non trouvée")));

        participerRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * Méthode pour supprimer une participation d'un super-héros à une mission.
     *
     * @param id L'identifiant de la participation.
     */
    @Override
    public Boolean exist(Integer id) {
        return participerRepository.existsById(Math.toIntExact(Long.valueOf(id)));
    }

    /**
     * Méthode pour récupérer une participation par son identifiant.
     *
     * @param id L'identifiant de la participation.
     * @return L'entité de participation.
     */
    @Override
    public ParticiperEntity get(Integer id) {
        return participerRepository.findById(Math.toIntExact(Long.valueOf(id)))
                .orElseThrow(() -> new RuntimeException("Participation non trouvée"));
    }
}

