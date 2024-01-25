package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.model.StatistiqueComission;
import tech.chillo.sa.service.AnnonceService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "annonce")
public class AnnonceController {
    private AnnonceService annonceService;

    public AnnonceController(AnnonceService bouquetService) {
        this.annonceService = bouquetService;
    }

    @GetMapping(path = "validation/{idannonce}", produces = APPLICATION_JSON_VALUE)
    public void ValiderAnnonce(@PathVariable int idannonce){
        this.annonceService.Validation(idannonce);
    }
     @ResponseStatus(value = HttpStatus.CREATED)
     @PostMapping
     public ResponseEntity<Object> createAnnonceWithDetails(@RequestBody AnnonceCreationRequest request) {
         Annonce annonceOptional = this.annonceService.createsaveAnnonceWithDetails(request);
         return new ResponseEntity<>(annonceOptional, HttpStatus.OK);
     }

     @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
     public List<Voiture> findById(@PathVariable int id) {
         return this.annonceService.GetByIdPersonne(id);
     }

     @GetMapping(path = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
     public List<Annonce> findByIdPersonne(@PathVariable int idpersonne) {
         return this.annonceService.GetAllOfPersonne(idpersonne);
     }

     @GetMapping(produces = APPLICATION_JSON_VALUE)
     public List<Annonce> getAll() {
         return this.annonceService.GetAllOrderByBouquet();
     }

//    @GetMapping("/etat")
//    public ResponseEntity<Object> getAllAnnonceNonlue(@RequestParam("etat") int etat) {
//        return new ResponseEntity<>(this.annonceService.getAnnonceByEtat(etat), HttpStatus.OK);
//    }

//    @GetMapping("/annoncenonlue")
//    public List<Annonce> getAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
//        return this.annonceService.getAnnoncesByEtatAndPersonneNotEqual(idpersonne);
//    }
//
//    @GetMapping("/nombrenonlue")
//    public long getNombreAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
//        return this.annonceService.getNombreAnnonceNonLue(idpersonne);
//    }

    @PutMapping("/updateEtat")
    public ResponseEntity<String> updateEtat(@RequestParam("idannonce") int idannonce, 
                                                @RequestParam("nouvelEtat") int nouvelEtat) {
        this.annonceService.updateEtatAnnonce(idannonce, nouvelEtat);
        return new ResponseEntity<>("État mis à jour avec succès.", HttpStatus.OK);
    }
    
    @GetMapping("/nombreAnnoncePersonne")
    public long getNombreAnnoncePersonne(@RequestParam("idpersonne")int idpersonne){
        return this.annonceService.getNombreAnnoncePersonne(idpersonne);
    }
    
    @GetMapping("/statistique")
    public List<StatistiqueComission> getStatistiqueComission(@RequestParam("annee")int annee){
        return this.annonceService.getStatistiqueComission(annee);
    }
    
    // public List<Annonces> getAllAnnonces() {
    //     return this.annoncesService.getListeAnnonceOrderByBouquet();
    // }

    // public List<Annonces> getAnnoncesByPersonneId(@RequestParam("idpersonne") int idpersonne) {
    //     return this.annoncesService.getListeAnnoncePersonneId(idpersonne);
    // }

    // http://localhost:8080/api/annonces/update?etat=1&idannonce=1
//     @PostMapping("/update")
//     public ResponseEntity<String> updateAnnonce(@RequestParam int etat, @RequestParam int idannonce) {
//         // Appel de votre service pour mettre à jour l'annonce
//         try {
//             this.annoncesService.UpdateEtat(etat, idannonce);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return ResponseEntity.ok("Annonce mise à jour avec succès");
//     }

}
