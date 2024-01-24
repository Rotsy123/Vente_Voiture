package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.entites.Modele;
import tech.chillo.sa.service.ModeleService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ModeleController {
    private ModeleService modeleservice;

    public ModeleController(ModeleService ms ){
        this.modeleservice = ms;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void Creer(@RequestBody Modele modele) {
        this.modeleservice.Creer(modele);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Modele> GetAll() {
        return this.modeleservice.findAll();
    }
}
