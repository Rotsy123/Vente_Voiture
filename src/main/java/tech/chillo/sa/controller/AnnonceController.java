package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Bouquet;
import tech.chillo.sa.entites.DetailsVoiture;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.entites.Annonces;
import tech.chillo.sa.service.AnnonceService;
import tech.chillo.sa.service.BouquetService;
import tech.chillo.sa.service.AnnoncesService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "annonce")
public class AnnonceController {
    private AnnonceService bouquetService;
    private AnnoncesService annoncesService;

    public AnnonceController(AnnonceService bouquetService, AnnoncesService annoncesService) {
        this.bouquetService = bouquetService;
        this.annoncesService = annoncesService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAnnonceWithDetails(@RequestBody AnnonceCreationRequest request) {
        Annonce annonceOptional = this.bouquetService.createsaveAnnonceWithDetails(request);
        return new ResponseEntity<>(annoncesService.saveAnnonces(annonceOptional), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public List<Voiture> findById(@PathVariable int id) {
        return this.annoncesService.GetByIdPersonne(id);
    }

    @GetMapping(path = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findByIdPersonne(@PathVariable int idpersonne) {
        return this.annoncesService.GetAllOfPersonne(idpersonne);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Annonce> getAll() {
        return this.annoncesService.GetAllOrderByBouquet();
    }

    public List<Annonces> getAllAnnonces() {
        return this.annoncesService.getListeAnnonceOrderByBouquet();
    }

    public List<Annonces> getAnnoncesByPersonneId(@RequestParam("idpersonne") int idpersonne) {
        return this.annoncesService.getListeAnnoncePersonneId(idpersonne);
    }

    // http://localhost:8080/api/annonces/update?etat=1&idannonce=1
    @PostMapping("/update")
    public ResponseEntity<String> updateAnnonce(@RequestParam int etat, @RequestParam int idannonce) {
        // Appel de votre service pour mettre à jour l'annonce
        try {
            this.annoncesService.UpdateEtat(etat, idannonce);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Annonce mise à jour avec succès");
    }

}
