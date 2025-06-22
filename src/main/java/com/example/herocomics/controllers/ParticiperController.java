package com.example.herocomics.controllers;

import com.example.herocomics.repositories.ParticiperRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ParticiperController {

    private final ParticiperRepository participerRepository;

    public ParticiperController(ParticiperRepository participerRepository) {
        this.participerRepository = participerRepository;
    }

    /**
     * Retire un super-héros d'une mission spécifique.
     *
     * @param idHero    L'ID du super-héros à retirer de la mission.
     * @param idMission L'ID de la mission dont le super-héros doit être retiré.
     * @return Une réponse indiquant si le retrait a réussi ou échoué.
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
