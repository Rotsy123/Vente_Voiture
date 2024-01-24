package tech.chillo.sa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.model.StatistiqueUtilisateur;
import tech.chillo.sa.service.PersonneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "login")
public class PersonneController {
    private PersonneService personneService;
    private static final Logger logger = LoggerFactory.getLogger(PersonneController.class);
    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkPersonne(
            @RequestParam("mail") String mail,
            @RequestParam("motdepasse") String motdepasse) {
        try {
            Personne personne = personneService.connected(mail, motdepasse);
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // @ResponseStatus(value = HttpStatus.CREATED)
    // @PostMapping(consumes = APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> createAccount(@RequestBody Personne personne,String motdepasse1,String motdepasse2) {

    //     return new ResponseEntity<>(annoncesService.saveAnnonces(annonceOptional), HttpStatus.OK);
    // }

    @GetMapping("/statistique")
    public StatistiqueUtilisateur getStatistiqueUtilisateur(@RequestParam("idpersonne")int idpersonne){
        return this.personneService.getStatistiqueUtilisateur(idpersonne);
    }
}
