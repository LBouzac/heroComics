package com.example.herocomics.services;

import com.example.herocomics.entities.SuperHeroEntity;
import com.example.herocomics.repositories.ISuperHeroRepository;
import com.example.herocomics.repositories.ParticiperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroService implements ISuperHeroService {
    @Autowired
    private ISuperHeroRepository superHeroRepository;

    @Autowired
    private ParticiperRepository participerRepository;

    @Autowired
    private IParticiperService participerService;

    /**
     * Méthode pour ajouter un super-héro.
     *
     * @param vraie_identite La vraie identité du super-héro.
     * @param nom_hero Le nom du super-héros.
     * @param pouvoir Le pouvoir principal du super-héros.
     * @param faiblesse La faiblesse du super-héros.
     * @return L'identifiant du super-héros ajouté.
     */
    @Override
    public Integer ajouterSuperHero(String vraie_identite, String nom_hero, String pouvoir, String faiblesse) {
        // On crée une entité SuperHeroEntity
        SuperHeroEntity entity = new SuperHeroEntity();
        entity.setVraieIdentite(vraie_identite);
        entity.setNomHero(nom_hero);
        entity.setPouvoir(pouvoir);
        entity.setFaiblesse(faiblesse);

        // Sauvegarde en base
        superHeroRepository.saveAndFlush(entity);
        return entity.getID(); // Renvoie l'ID du super-héros
    }

    /**
     * Méthode pour supprimer un super-héros.
     *
     * @return true si le super-héros a été supprimé, false sinon.
     */
    @Override
    public List<SuperHeroEntity> getAllSuperHeroes() {
        return superHeroRepository.findAll();
    }

    /**
     * Méthode pour vérifier si un super-héros existe.
     *
     * @param pouvoir Le pouvoir du super-héros.
     * @return true si le super-héros existe, false sinon.
     */
    @Override
    public List<SuperHeroEntity> findByPouvoirPrincipal(String pouvoir) {
        return superHeroRepository.findByPouvoirPrincipal(pouvoir);
    }

}
