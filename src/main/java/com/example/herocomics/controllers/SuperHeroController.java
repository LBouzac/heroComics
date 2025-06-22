package com.example.herocomics.controllers;

import com.example.herocomics.dtos.SuperHeroDto;
import com.example.herocomics.entities.MissionEntity;
import com.example.herocomics.entities.ParticiperEntity;
import com.example.herocomics.entities.SuperHeroEntity;
import com.example.herocomics.enumerations.Status;
import com.example.herocomics.repositories.IMissionRepository;
import com.example.herocomics.repositories.ISuperHeroRepository;
import com.example.herocomics.repositories.ParticiperRepository;
import com.example.herocomics.services.ISuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.example.herocomics.enumerations.Status;

@RestController
@RequestMapping
public class SuperHeroController {

    private final ParticiperRepository participerRepository;

    @Autowired
    ISuperHeroService superHeroService;

    public SuperHeroController(ParticiperRepository participerRepository) {
        this.participerRepository = participerRepository;
    }

    /**
     * Récupère tous les super-héros.
     *
     * @return Une liste de tous les super-héros.
     * Exemple de requête :
     * GET <a href="http://localhost:8081/superheros/all">http://localhost:8081/superheros/all</a>
     */
    @GetMapping("/superheros/all")
    public ResponseEntity<List<SuperHeroEntity>> getAllSuperHeroes() {
        List<SuperHeroEntity> superHeroes = superHeroService.getAllSuperHeroes();
        if (superHeroes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHeroes);
    }

    /**
     * Récupère les missions actives pour un super-héros spécifique.
     *
     * @param idHero L'ID du super-héros pour lequel on veut récupérer les missions actives.
     * @return Une liste de missions actives pour le super-héros spécifié.
     * Exemple de requête :
     * GET <a href="http://localhost:8081/superheros/1/missions-actuelles">http://localhost:8081/superheros/1/missions-actuelles</a>
     */
    @GetMapping("/superheros/{idHero}/missions-actuelles")
    public List<MissionEntity> getMissionsActivesPourHero(@PathVariable Integer idHero) {
        return participerRepository.findBySuperHeroIdAndStatus(idHero, Status.valueOf(String.valueOf(Status.ACTIF)))
                .stream()
                .map(ParticiperEntity::getMission)
                .collect(Collectors.toList());
    }

    /**
     * Ajoute un nouveau super-héros.
     *
     * @param dto Le DTO contenant les informations du super-héros à ajouter.
     * @return Une réponse indiquant si l'ajout a réussi ou échoué.
     * Exemple de requête :
     * POST <a href="http://localhost:8081/superheros/add">http://localhost:8081/superheros/add</a>
     * Le corps de la requête doit contenir un JSON avec les champs suivants :
     * {
     *   "vraieIdentite": "Identité réelle du super-héros",
     *   "nomHero": "Nom du super-héros",
     *   "pouvoir": "Pouvoir principal du super-héros",
     *   "faiblesse": "Faiblesse du super-héros"
     * }
     */
    @PostMapping("/superheros/add")
    public ResponseEntity<?> ajouterSuperHero(@RequestBody SuperHeroDto dto) {
        // Exemple de vérification d’un champ obligatoire
        if (dto.getNomHero() == null || dto.getNomHero().isEmpty()) {
            return ResponseEntity.badRequest().body("Le nom du héros est obligatoire.");
        }

        Integer id = superHeroService.ajouterSuperHero(
                dto.getVraieIdentite(),
                dto.getNomHero(),
                dto.getPouvoir(),
                dto.getFaiblesse()
        );
        return ResponseEntity.status(201)
                .body("Super-héro ajouté avec succès, ID: " + id);
    }

    /**
     * Récupère une liste de super-héros par leur pouvoir principal, insensible à la casse.
     *
     * @param pouvoir Le pouvoir principal à rechercher.
     * @return Une liste de super-héros ayant le pouvoir principal spécifié.
     * Exemple de requête :
     * GET <a href="http://localhost:8081/superheros/pouvoir/vol">http://localhost:8081/superheros/pouvoir/vol</a>
     */
    @GetMapping("/superheros/pouvoir/{pouvoir}")
    public ResponseEntity<List<SuperHeroEntity>> findByPouvoirPrincipal(@PathVariable String pouvoir) {
        List<SuperHeroEntity> superHeroes = superHeroService.findByPouvoirPrincipal(pouvoir);
        if (superHeroes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHeroes);
    }

    /**
     * Récupère le rapport de performance d'un super-héros spécifique.
     *
     * @param idHero L'ID du super-héros pour lequel on veut récupérer le rapport de performance.
     * @return Un DTO contenant les statistiques de performance du super-héros.
     * Exemple de requête :
     * GET <a href="http://localhost:8081/superheros/rapport-performance/1">http://localhost:8081/superheros/rapport-performance/1</a>
     */
    @GetMapping("/superheros/rapport-performance/{idHero}")
    public ResponseEntity<com.example.herocomics.dtos.RapportPerformanceDto> getRapportPerformance(@PathVariable int idHero) {
        long terminees = participerRepository.countMissionsTerminees(idHero);
        long leader = participerRepository.countMissionsLeader(idHero);
        long total = participerRepository.countMissionsActifOuCompletee(idHero);
        double tauxSucces = total > 0 ? (double) terminees / total * 100 : 0.0;

        com.example.herocomics.dtos.RapportPerformanceDto rapport = new com.example.herocomics.dtos.RapportPerformanceDto(terminees, leader, tauxSucces);
        return ResponseEntity.ok(rapport);
    }


    /**
     * Retire un super-héros d'une mission spécifique.
     *
     * @param idHero    L'ID du super-héros à retirer de la mission.
     * @param idMission L'ID de la mission dont le super-héros doit être retiré.
     * @return Une réponse indiquant si le retrait a réussi ou échoué.
     * Exemple de requête :
     * DELETE <a href="http://localhost:8081/superheros/1/retirer-mission/2">http://localhost:8081/superheros/1/retirer-mission/2</a>
     */
    @DeleteMapping("/superheros/{idHero}/retirer-mission/{idMission}")
    public ResponseEntity<String> retirerMission(@PathVariable Integer idHero, @PathVariable Integer idMission) {
        if (participerRepository.existsBySuperHeroIdAndMissionId(idHero, idMission)) {
            participerRepository.deleteBySuperHeroIdAndMissionId(idHero, idMission);
            return ResponseEntity.ok("Le super-héros a été retiré de la mission.");
        } else {
            return ResponseEntity.status(404).body("Le super-héros n'est pas assigné à cette mission.");
        }
    }
}
