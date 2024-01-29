package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Annonce;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.model.StatistiqueComission;
import tech.chillo.sa.security.token.JwtUtils;
import tech.chillo.sa.service.AnnonceService;
import tech.chillo.sa.service.PersonneService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin
@RequestMapping(path = "annonce")
public class AnnonceController {
    private AnnonceService annonceService;
    private PersonneService personne;
    public AnnonceController(AnnonceService bouquetService,PersonneService personne) {
        this.annonceService = bouquetService;
        this.personne = personne;
    }

    @GetMapping(path = "validation/{idannonce}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public void ValiderAnnonce(@PathVariable int idannonce){
        this.annonceService.Validation(idannonce);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createAnnonceWithDetails(@RequestHeader(name="Authorization") String authorizationHeader,@RequestBody AnnonceCreationRequest request) {
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        System.out.println("Token "+ token);
        try {
            int id = jwt.getId(token);
            System.out.println("ID "+ id);

            request.getAnnonce().setPersonne(personne.findById(id).orElseThrow());
            Annonce annonceOptional = this.annonceService.createsaveAnnonceWithDetails(request);
            return new ResponseEntity<>(annonceOptional, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

//     @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
//     public List<Annonce> findById(@PathVariable int id) {
//         return this.annonceService.GetByIdPersonne(id);
//     }

     @GetMapping(path = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
     public List<Annonce> findByIdPersonne(@PathVariable int idpersonne) {
         return this.annonceService.GetAllOfPersonne(idpersonne);
     }

     @GetMapping(path = "list", produces = APPLICATION_JSON_VALUE)
     public ResponseEntity<Object> findByIdPersonne1(@RequestHeader(name="Authorization") String authorizationHeader) {
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        try {
            int id = jwt.getId(token);
            return new ResponseEntity<>(this.annonceService.GetAllOfPersonne(id), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        

        //  return this.annonceService.GetAllOfPersonne(idpersonne);
     }
     
     @GetMapping(produces = APPLICATION_JSON_VALUE)
     @PreAuthorize("hasRole('ADMIN')")
     public List<Annonce> getAll() {
         return this.annonceService.GetAllNonValiderOrderByBouquet();
     }

    @GetMapping("/etat")
    @PreAuthorize("hasRole('ADMIN')")
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

//    @GetMapping("/annoncenonlue")
//    public List<Annonce> getAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
//        return this.annonceService.getAnnoncesByEtatAndPersonneNotEqual(idpersonne);
//    }
//
//    @GetMapping("/nombrenonlue")
//    public long getNombreAnnonceNonLue(@RequestParam("idpersonne")int idpersonne){
//        return this.annonceService.getNombreAnnonceNonLue(idpersonne);
//    }

    @DeleteMapping("/deleteannonce")
    public void DeleteAnnonce(@RequestParam("idannonce") int idannonce){ //idpersonne
        this.annonceService.DeleteAnnonce(idannonce);
    }

    @PutMapping("/updateEtatVendu")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateEtat(@RequestParam("idannonce") int idannonce) {
        this.annonceService.updateEtatAnnonce(idannonce);
        return new ResponseEntity<>("État mis à jour avec succès.", HttpStatus.OK);
    }

    @GetMapping("/nombreAnnoncePersonne")
    public long getNombreAnnoncePersonne(@RequestParam("idpersonne")int idpersonne){
        return this.annonceService.getNombreAnnoncePersonne(idpersonne);
    }

    @GetMapping("/statistique")
    @PreAuthorize("hasRole('ADMIN')")
    public List<StatistiqueComission> getStatistiqueComission(@RequestParam("annee")int annee){
        System.out.println("Role de l'utilisateur: " + annee);
        return this.annonceService.getStatistiqueComission(annee);
    }

    @GetMapping("/listeannonce")
    public List<Annonce> getAnnonceWithLastBouquet() throws Exception {
        return this.annonceService.GetAnnonce();
    }

}
