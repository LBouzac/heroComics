package com.example.herocomics.services;

import com.example.herocomics.dtos.ParticiperDto;
import com.example.herocomics.entities.ParticiperEntity;

public interface IParticiperService {
    Integer ajouterParticiper(Integer id_super_hero, Integer id_mission);

    ParticiperEntity ajouterParticiper(ParticiperDto dto);

    Boolean exist(Integer id);

    ParticiperEntity get(Integer id);
}
