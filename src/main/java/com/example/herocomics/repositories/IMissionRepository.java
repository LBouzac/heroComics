package com.example.herocomics.repositories;

import com.example.herocomics.entities.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMissionRepository extends JpaRepository<MissionEntity, Integer> {
    /**
     * Requête pour récupérer les missions avec un niveau de menace elevé et qui sont en cours.
     * @return
     */
    @Query("SELECT m FROM MissionEntity m, ParticiperEntity p WHERE p.mission.ID = m.ID AND m.niveauMenace = 'ELEVEE' AND p.status = 'ACTIF'")
    List<MissionEntity> findHighThreatMissionsInProgress();

    /**
     * Requête pour récupérer les missions avec un nombre de super-héros inférieur à un seuil donné.
     * @param minHeroes Le nombre minimum de super-héros.
     * @return
     */
    @Query("SELECT m FROM MissionEntity m WHERE (SELECT COUNT(p) FROM ParticiperEntity p WHERE p.mission.ID = m.ID) < :minHeroes")
    List<MissionEntity> findMissionsWithLessThanMinHeroes(@Param("minHeroes") int minHeroes);

    /**
     * Requête pour récupérer les missions les plus longues en termes de durée.
     * @param pageable Pagination information.
     * @return
                         */     /**
     * Requête pour récupérer les missions les plus longues en termes de durée.
     * @param pageable Pagination information.
     *                 @return
     */
    @Query(
            value = "SELECT * FROM mission WHERE date_debut IS NOT NULL AND date_fin IS NOT NULL ORDER BY DATEDIFF(day, date_debut, date_fin) DESC",
            nativeQuery = true
    )
    List<MissionEntity> findLongestMissions(org.springframework.data.domain.Pageable pageable);

    /**
     * Requête pour récupérer les missions qui se chevauchent avec une période donnée.
     * @param dateDebut La date de début de la période.
     * @param dateFin La date de fin de la période.
     * @return
     */
    @Query("SELECT m FROM MissionEntity m WHERE m.dateDebut >= :dateDebut AND m.dateFin <= :dateFin")
    List<MissionEntity> findMissionsByPeriode(@Param("dateDebut") java.time.LocalDate dateDebut, @Param("dateFin") java.time.LocalDate dateFin);

}
