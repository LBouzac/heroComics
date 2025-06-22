package com.example.herocomics.controllers;

import com.example.herocomics.dtos.AssignHeroDto;
import com.example.herocomics.dtos.ParticiperDto;
import com.example.herocomics.entities.MissionEntity;
import com.example.herocomics.entities.ParticiperEntity;
import com.example.herocomics.entities.SuperHeroEntity;
import com.example.herocomics.repositories.IMissionRepository;
import com.example.herocomics.repositories.ISuperHeroRepository;
import com.example.herocomics.repositories.ParticiperRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class MissionController {

    private final ParticiperRepository participerRepository;
    private final ISuperHeroRepository superHeroRepository;
    private final IMissionRepository missionRepository;

    public MissionController(ParticiperRepository participerRepository, ISuperHeroRepository superHeroRepository, IMissionRepository missionRepository) {
        this.participerRepository = participerRepository;
        this.superHeroRepository = superHeroRepository;
        this.missionRepository = missionRepository;

    }

    /**
     * Récupère la liste des participants à une mission spécifique.
     *
     * @param missionId L'ID de la mission pour laquelle on veut récupérer les participants.
     * @return Une liste de DTO contenant les informations des participants.
     */
    @GetMapping("missions/{missionId}/equipe")
    public List<ParticiperDto> getParticipants(@PathVariable Integer missionId) {
        return participerRepository.findByMissionId(missionId).stream()
                .map(p -> new ParticiperDto(
                        p.getID(),
                        p.getSuperHeroEntity().getNomHero(),
                        p.getMission().getNomMission(),
                        p.getRole(),
                        p.getStatus(),
                        p.getSuperHeroEntity().getID(),
                        p.getMission().getID(
                ))
                ).collect(Collectors.toList());
    }

    /**
     * Assigne un super-héros à une mission.
     *
     * @param idMission L'ID de la mission à laquelle le super-héros doit être assigné.
     * @param dto       Le DTO contenant les informations du super-héros et son rôle dans la mission.
     * @return Une réponse indiquant si l'assignation a réussi ou échoué.
     */
    @PostMapping("missions/{idMission}/assigner-hero")
    public ResponseEntity<String> assignerHero(
            @PathVariable Integer idMission,
            @RequestBody AssignHeroDto dto
    ) {
        // Vérification de l'existence de la mission
        if (idMission == null || dto.getSuperHeroId() == null) {
            throw new IllegalArgumentException("Mission ID et Super Hero ID sont requis.");
        }
        // Vérification de l'existence du super héros
        if (dto.getSuperHeroId() <= 0) {
            throw new IllegalArgumentException("ID du super héros invalide.");
        }

        // Verification si le super héros est déjà assigné à la mission
        if (participerRepository.existsBySuperHeroIdAndMissionId(dto.getSuperHeroId(), idMission) == true)  {
            return ResponseEntity.badRequest().body("Le super-héros est déjà assigné à cette mission.");
        }

        // Récupération du super héros
        SuperHeroEntity hero = superHeroRepository.findById(dto.getSuperHeroId())
                .orElseThrow(() -> new RuntimeException("Super-héros non trouvé avec l'ID: " + dto.getSuperHeroId()));

        // Récupération de la mission
        MissionEntity mission = missionRepository.findById(idMission)
                .orElseThrow(() -> new RuntimeException("Mission non trouvée avec l'ID: " + idMission));

        // Création de l'entité de participation
        ParticiperEntity participation = new ParticiperEntity();
        participation.setMission(mission);
        participation.setSuperHeroEntity(hero);
        participation.setRole(dto.getRole());
        participation.setStatus(dto.getStatus());

        participerRepository.save(participation);

        return ResponseEntity.ok("Super-héros assigné à la mission avec succès.");
    }

    /**
     * Récupère les missions actives pour un super-héros spécifique.
     *
     * @param idHero L'ID du super-héros pour lequel on veut récupérer les missions actives.
     * @return Une liste de missions actives pour le super-héros spécifié.
     */
    @GetMapping("/superheros/{idHero}/missions-actuelles")
    public List<MissionEntity> getMissionsActivesPourHero(@PathVariable Integer idHero) {
        return participerRepository.findBySuperHeroIdAndStatus(idHero, "Actif").stream()
                .map(ParticiperEntity::getMission)
                .collect(Collectors.toList());
    }

    /**
     * Récupère les missions avec un niveau de menace élevé et un statut actif.
     *
     * @return Une liste de missions avec un niveau de menace élevé et un statut actif.
     */
    @GetMapping("/missions/danger-eleve")
    public List<MissionEntity> getMissionsDangerEleve() {
        return missionRepository.findHighThreatMissionsInProgress();
    }

    /**
     * Récupère les missions avec un nombre de super-héros inférieur à un seuil donné.
     *
     * @param minHeroes Le nombre minimum de super-héros.
     * @return Une liste de missions avec moins de super-héros que le seuil spécifié.
     */
    @GetMapping("/missions/heros-requis")
    public ResponseEntity<List<MissionEntity>> getMissionsWithLessThanMinHeroes(@RequestParam("minHeroes") int minHeroes) {
        List<MissionEntity> missions = missionRepository.findMissionsWithLessThanMinHeroes(minHeroes);
        return ResponseEntity.ok(missions);
    }

    /**
     * Récupère les missions les plus longues en termes de durée.
     *
     * @param limit Le nombre maximum de missions à retourner.
     * @return Une liste des missions les plus longues.
     */
    @GetMapping("/missions/les-plus-longues")
    public ResponseEntity<List<MissionEntity>> getLongestMissions(@RequestParam("limit") int limit) {
        List<MissionEntity> missions = missionRepository.findLongestMissions(Pageable.ofSize(limit));
        return ResponseEntity.ok(missions);
    }

    /**
     * Récupère les missions qui se chevauchent avec une période donnée.
     *
     * @param dateDebut La date de début de la période.
     * @param dateFin   La date de fin de la période.
     * @return Une liste de missions qui se chevauchent avec la période spécifiée.
     */
    @GetMapping("/missions/periode")
    public ResponseEntity<List<com.example.herocomics.dtos.MissionPeriodeDto>> getMissionsByPeriode(
            @RequestParam("date_debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("date_fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {

        List<MissionEntity> missions = missionRepository.findMissionsByPeriode(dateDebut, dateFin);
        List<com.example.herocomics.dtos.MissionPeriodeDto> result = missions.stream().map(m -> {
            long nbSuperheros = m.getParticipations().size(); // ou requête COUNT si besoin
            String statut;
            LocalDate now = LocalDate.now();
            if (now.isBefore(m.getDateDebut().atStartOfDay().toLocalDate())) {
                statut = "À Venir";
            } else if (now.isAfter(m.getDateFin().atStartOfDay().toLocalDate())) {
                statut = "Terminée";
            } else {
                statut = "En Cours";
            }
            long duree = ChronoUnit.DAYS.between(m.getDateDebut().atStartOfDay().toLocalDate(), m.getDateFin().atStartOfDay().toLocalDate());
            return new com.example.herocomics.dtos.MissionPeriodeDto(
                    m.getNomMission(),
                    nbSuperheros,
                    statut,
                    duree,
                    m.getNiveauMenace()
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    /**
     * Récupère le rapport de performance d'un super-héros spécifique.
     *
     * @param idHero L'ID du super-héros pour lequel on veut récupérer le rapport de performance.
     * @return Un DTO contenant les statistiques de performance du super-héros.
     */
    @GetMapping("/superheros/rapport-performance/{idHero}")
    public ResponseEntity<com.example.herocomics.dtos.RapportPerformanceDto> getRapportPerformance(@PathVariable int idHero) {
        long terminees = participerRepository.countMissionsTerminees(idHero);
        long leader = participerRepository.countMissionsLeader(idHero);
        long total = participerRepository.countMissionsActifOuCompletee(idHero);
        double tauxSucces = total > 0 ? (double) terminees / total : 0.0;

        com.example.herocomics.dtos.RapportPerformanceDto rapport = new com.example.herocomics.dtos.RapportPerformanceDto(terminees, leader, tauxSucces);
        return ResponseEntity.ok(rapport);
    }

}