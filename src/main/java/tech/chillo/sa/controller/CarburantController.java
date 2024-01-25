package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Carburant;
import tech.chillo.sa.entites.Marque;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.model.StatistiqueComission;
import tech.chillo.sa.service.AnnonceService;
import tech.chillo.sa.service.CarburantService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "carburant")
public class CarburantController {
    private CarburantService carburantservice;
    public CarburantController(CarburantService carburantservice){
        this.carburantservice = carburantservice;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Carburant carburant) {
        carburantservice.create(carburant);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Carburant> GetAll() {
        return this.carburantservice.GetAll();
    }
}
