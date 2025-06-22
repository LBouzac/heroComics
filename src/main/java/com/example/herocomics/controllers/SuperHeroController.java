package com.example.herocomics.controllers;

import com.example.herocomics.dtos.SuperHeroDto;
import com.example.herocomics.entities.SuperHeroEntity;
import com.example.herocomics.services.ISuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SuperHeroController {
    @Autowired
    ISuperHeroService superHeroService;

    @GetMapping("/superheros/all")
    public ResponseEntity<List<SuperHeroEntity>> getAllSuperHeroes() {
        List<SuperHeroEntity> superHeroes = superHeroService.getAllSuperHeroes();
        if (superHeroes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHeroes);
    }

    // src/main/java/com/example/herocomics/controllers/SuperHeroController.java
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
     */
    @GetMapping("/superheros/pouvoir/{pouvoir}")
    public ResponseEntity<List<SuperHeroEntity>> findByPouvoirPrincipal(@PathVariable String pouvoir) {
        List<SuperHeroEntity> superHeroes = superHeroService.findByPouvoirPrincipal(pouvoir);
        if (superHeroes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(superHeroes);
    }
}
