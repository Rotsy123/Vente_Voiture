package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Historique;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.service.HistoriqueService;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
@RequestMapping(path = "historique")
public class HistoriqueController {
    private HistoriqueService historiqueService;
    public HistoriqueController(HistoriqueService historiqueService){
        this.historiqueService = historiqueService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Historique historique) {
        this.historiqueService.ChangerBouquet(historique);
    }

    }
