package tech.chillo.sa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.service.BouquetService;
import tech.chillo.sa.service.VoitureService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@RestController
@RequestMapping(path = "voiture")
public class VoitureController {
    private VoitureService voitureService;
    private static final Logger logger = LoggerFactory.getLogger(VoitureController.class);
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Voiture> rechercher() {
        List<Voiture> voitures = this.voitureService.GetAll();
        logger.debug("Liste des voitures : {}", voitures);
        return voitures;
    }
}
