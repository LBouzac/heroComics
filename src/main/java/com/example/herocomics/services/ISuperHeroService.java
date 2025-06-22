package com.example.herocomics.services;

import com.example.herocomics.entities.SuperHeroEntity;

import java.util.List;

public interface ISuperHeroService {

    Integer ajouterSuperHero(String vraie_identite, String nom_hero, String pouvoir, String faiblesse);

    List<SuperHeroEntity> getAllSuperHeroes();

    List<SuperHeroEntity> findByPouvoirPrincipal(String pouvoir);
}
