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

    public AnnonceController(AnnonceService bouquetService,AnnoncesService annoncesService) {
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
        return this.bouquetService.GetByIdPersonne(id);
    }

    @GetMapping(path = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findByIdPersonne(@PathVariable int idpersonne) {
        return this.bouquetService.GetAllOfPersonne(idpersonne);
    }

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    public List<Annonce> getAll(){
        return this.bouquetService.GetAllOrderByBouquet();
    }

    @GetMapping("/etat")
    public ResponseEntity<Object> getAllAnnonceNonlue(@RequestParam("etat") int etat) {
        return new ResponseEntity<>(this.bouquetService.getAnnonceByEtat(etat), HttpStatus.OK);
    }


    // public List<Annonces> getAllAnnonces(){
    //     return this.annoncesService.getListeAnnonceOrderByBouquet();
    // }

    // @GetMapping("/annonce")
    // public List<Annonces> getAnnoncesByPersonneId(@RequestParam("idpersonne")int idpersonne){
    //     return this.annoncesService.getListeAnnoncePersonneId(idpersonne);
    // }

    @GetMapping("/annoncenonlue")
    public List<Annonce> getAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
        return this.bouquetService.getAnnoncesByEtatAndPersonneNotEqual(idpersonne);
    }

    @GetMapping("/nombrenonlue")
    public long getNombreAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
        return this.bouquetService.getNombreAnnonceNonLue(idpersonne);
    }

    @PutMapping("/updateEtat")
    public ResponseEntity<String> updateEtat(@RequestParam("idannonce") int idannonce, 
                                                @RequestParam("nouvelEtat") int nouvelEtat) {
        bouquetService.updateEtatAnnonce(idannonce, nouvelEtat);
        return new ResponseEntity<>("État mis à jour avec succès.", HttpStatus.OK);
    }
}
