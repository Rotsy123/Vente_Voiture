package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.service.MarqueService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
@RequestMapping(path = "marque")
public class MarqueController {
    private MarqueService marqueService;

    public MarqueController(MarqueService marqueService){
        this.marqueService = marqueService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Marque marque) {
        marqueService.Creer(marque);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Marque> GetAll() {
        return this.marqueService.findAll();
    }
}
