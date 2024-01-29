package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.entites.Marque;
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

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@RequestBody Bouquet bouquet) {
        bouquetService.Creer(bouquet);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Optional<Bouquet> findById(@PathVariable int id) {
        return this.bouquetService.findById(id);
    }
}
