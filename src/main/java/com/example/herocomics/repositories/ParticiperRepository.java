package com.example.herocomics.repositories;

import com.example.herocomics.dtos.RapportActiviteMensuelDto;
import com.example.herocomics.entities.MissionEntity;
import com.example.herocomics.entities.ParticiperEntity;
import com.example.herocomics.enumerations.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface ParticiperRepository extends JpaRepository<ParticiperEntity, Integer> {
    /**
     * Requête pour récupérer les participations d'un super-héros
     *
     * @param missionId
     * @return
     */
    @Query("SELECT p FROM ParticiperEntity p WHERE p.mission.ID = :missionId")
    List<ParticiperEntity> findByMissionId(@Param("missionId") Integer missionId);

    /**
     * Vérifie si une participation existe pour un super-héros et une mission donnés
     *
     * @param superHeroId
     * @param missionId
     * @return true si la participation existe, false sinon
     */
    @Query("SELECT COUNT(p) > 0 FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :superHeroId AND p.mission.ID = :missionId")
    Boolean existsBySuperHeroIdAndMissionId(@Param("superHeroId") Integer superHeroId, @Param("missionId") Integer missionId);

    /**
     * Récupère les participations d'un super-héros avec un statut spécifique
     *
     * @param idHero
     * @param actif
     * @return
     */
    @Query("SELECT p FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :idHero AND p.status = :actif")
    List<ParticiperEntity> findBySuperHeroIdAndStatus(@Param("idHero") Integer idHero, @Param("actif") Status actif);
    /**
     * Supprime une participation par l'ID du super-héros et l'ID de la mission
     *
     * @param idHero
     * @param idMission
     * @return
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :idHero AND p.mission.ID = :idMission")
    void deleteBySuperHeroIdAndMissionId(@Param("idHero") Integer idHero, @Param("idMission") Integer idMission);

    /**
     * Récupère les participations d'un super-héros pour une liste de missions
     *
     * @param idHero
     * @return
     */
    @Query("SELECT COUNT(p) FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :idHero AND p.status = 'COMPLETE'")
    long countMissionsTerminees(@Param("idHero") int idHero);

    @Query("SELECT COUNT(p) FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :idHero AND p.role = 'LEADER'")
    long countMissionsLeader(@Param("idHero") int idHero);

    @Query("SELECT COUNT(p) FROM ParticiperEntity p WHERE p.superHeroEntity.ID = :idHero AND (p.status = 'ACTIF' OR p.status = 'COMPLETE')")
    long countMissionsActifOuCompletee(@Param("idHero") int idHero);

    /**
     * Récupère le rapport d'activité mensuel pour un super-héros
     * @param annee
     * @param mois
     * @return
     */
//    @Query("SELECT new com.example.herocomics.dtos.RapportActiviteMensuelDto(" +
//            "COUNT(DISTINCT m.ID), " + // nombre de missions
//            "COUNT(DISTINCT CASE WHEN m.terminee = true THEN m.ID END), " + // missions complétées
//            "COUNT(DISTINCT p.superHeroEntity.ID), " + // super-héros participants
//            "MAX(m.nom), " + // nom mission la plus longue (à adapter)
//            "MAX(m.duree) " + // durée mission la plus longue (à adapter)
//            ") " +
//            "FROM ParticiperEntity p JOIN p.mission m " +
//            "WHERE YEAR(m.dateDebut) = :annee AND MONTH(m.dateDebut) = :mois " +
//            "AND p.status = 'COMPLETE'")
//    List<RapportActiviteMensuelDto> findRapportActiviteMensuel(@Param("annee") int annee, @Param("mois") int mois);
}