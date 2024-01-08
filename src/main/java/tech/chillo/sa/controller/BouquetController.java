package tech.chillo.sa.controller;

import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.service.BouquetService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "bouquet")
public class BouquetController {
    private BouquetService bouquetService;

    public BouquetController(BouquetService bouquetService) {
        this.bouquetService = bouquetService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Bouquet> rechercher() {
        return this.bouquetService.findAll();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Bouquet> findById(@PathVariable int id) {
        return this.bouquetService.findById(id);
    }
}
