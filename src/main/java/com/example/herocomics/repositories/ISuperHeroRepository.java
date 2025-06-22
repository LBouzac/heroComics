package com.example.herocomics.repositories;

import com.example.herocomics.entities.SuperHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISuperHeroRepository extends JpaRepository<SuperHeroEntity, Integer> {
    /**
     * Requête pour récupérer les super-héros par leur pouvoir principal.
     */
    @Query("SELECT s FROM SuperHeroEntity s WHERE LOWER(s.pouvoir) = LOWER(:pouvoir)")
    List<SuperHeroEntity> findByPouvoirPrincipal(String pouvoir);
}
