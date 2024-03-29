package tech.chillo.sa.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Voiture;
import tech.chillo.sa.model.StatistiqueComission;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.service.AnnonceService;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin
@RequestMapping(path = "annonce")
public class AnnonceController {
    private AnnonceService annonceService;

    public AnnonceController(AnnonceService bouquetService) {
        this.annonceService = bouquetService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @GetMapping(path = "validation/{idannonce}", produces = APPLICATION_JSON_VALUE)
    public void ValiderAnnonce(@PathVariable int idannonce) {
        this.annonceService.Validation(idannonce);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> createAnnonceWithDetails(@RequestBody AnnonceCreationRequest request) {
        Annonce annonceOptional = this.annonceService.createsaveAnnonceWithDetails(request);
        return new ResponseEntity<>(annonceOptional, HttpStatus.OK);
    }

    // @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    // public List<Annonce> findById(@PathVariable int id) {
    // return this.annonceService.GetByIdPersonne(id);
    // }

    @GetMapping(path = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
    public List<Annonce> findByIdPersonne(@PathVariable int idpersonne) {
        return this.annonceService.GetAllOfPersonne(idpersonne);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Annonce> getAll() {
        return this.annonceService.GetAllOrderByBouquet();
    }
     @GetMapping(produces = APPLICATION_JSON_VALUE)
     public List<Annonce> getAll() {
         return this.annonceService.GetAllNonValiderOrderByBouquet();
     }

   @GetMapping("/etat")
   public ResponseEntity<Object> getAllAnnonceNonlue(@RequestParam("etat") int etat) {
       return new ResponseEntity<>(this.annonceService.getAnnonceByEtat(etat), HttpStatus.OK);
   }
     @GetMapping(path = "/annoncevalidee", produces = APPLICATION_JSON_VALUE)
     public List<Annonce> getAllValider ()
     {return this.annonceService.GetAllValiderOrderByBouquet();}

//    @GetMapping("/etat")
//    public ResponseEntity<Object> getAllAnnonceNonlue(@RequestParam("etat") int etat) {
//        return new ResponseEntity<>(this.annonceService.getAnnonceByEtat(etat), HttpStatus.OK);
//    }

    // @GetMapping("/etat")
    // public ResponseEntity<Object> getAllAnnonceNonlue(@RequestParam("etat") int
    // etat) {
    // return new ResponseEntity<>(this.annonceService.getAnnonceByEtat(etat),
    // HttpStatus.OK);
    // }

    // @GetMapping("/annoncenonlue")
    // public List<Annonce> getAnnonceNonLue(@RequestParam("idpersonne")int
    // idpersonne){
    // return this.annonceService.getAnnoncesByEtatAndPersonneNotEqual(idpersonne);
    // }
    //
    // @GetMapping("/nombrenonlue")
    // public long getNombreAnnonceNonLue(@RequestParam("idpersonne")int
    // idpersonne){
    // return this.annonceService.getNombreAnnonceNonLue(idpersonne);
    // }
    @DeleteMapping("/deleteannonce")
    public void DeleteAnnonce(@RequestParam("idannonce") int idannonce) {
        this.annonceService.DeleteAnnonce(idannonce);
    }

    @PutMapping("/updateEtat")
    public ResponseEntity<String> updateEtat(@RequestParam("idannonce") int idannonce,
            @RequestParam("nouvelEtat") int nouvelEtat) {
        this.annonceService.updateEtatAnnonce(idannonce, nouvelEtat);
        return new ResponseEntity<>("État mis à jour avec succès.", HttpStatus.OK);
    }

    @GetMapping("/nombreAnnoncePersonne")
    public long getNombreAnnoncePersonne(@RequestParam("idpersonne") int idpersonne) {
        return this.annonceService.getNombreAnnoncePersonne(idpersonne);
    }

    @GetMapping("/statistique")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatistiqueComission> getStatistiqueComission(@RequestParam("annee")int annee){
        System.out.println("Role de l'utilisateur: " + annee);
        // org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // String role = authentication.getAuthorities().iterator().next().getAuthority();
        // System.out.println("Role de l'utilisateur: " + role);
        // System.out.println("Okkkkkkkkk");
        return this.annonceService.getStatistiqueComission(annee);
    }

    @GetMapping("/listeannonce")
    public List<Annonce> getAnnonceWithLastBouquet() throws Exception {
        return this.annonceService.GetAnnonce();
    }

}
