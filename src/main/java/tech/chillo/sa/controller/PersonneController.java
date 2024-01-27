package tech.chillo.sa.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;

import org.springframework.web.bind.annotation.RequestParam;
import tech.chillo.sa.dto.AuthentificationDTO;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.model.StatistiqueUtilisateur;
import tech.chillo.sa.repository.CompteRepository;
import tech.chillo.sa.security.token.JwtUtils;
import tech.chillo.sa.service.LoginService;
import tech.chillo.sa.service.PersonneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "compte")
public class PersonneController {
    // private AuthenticationManager authenticationManager;
    @Autowired
    private LoginService personneService;
    @Autowired
    private JwtUtils jwt;

    private PersonneService personneservice;
    private CompteRepository compteRepository;

    public PersonneController(PersonneService personneService, CompteRepository compteRepository) {
        this.personneservice = personneService;
        this.compteRepository = compteRepository;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkPersonne(@RequestBody AuthentificationDTO authentificationDTO) {
        System.out.println(
        "ETTTTTTTTTTTTTTTTTTOOOOOOOO" +
        personneService.loadUserByUsername(authentificationDTO.mail()));
        final UserDetails user = personneService.loadUserByUsername(authentificationDTO.mail());
        String token = jwt.generateJwt(user);
        System.out.println("ETTTTTTTTTTTTTTTTTTOOOOOOOO" + token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // @GetMapping(produces = APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> checkPersonne(
    // @RequestParam("mail") String mail,
    // @RequestParam("motdepasse") String motdepasse) {
    // try {
    //// Personne personne = personneService.connected(mail, motdepasse);
    //// return new ResponseEntity<>(personne, HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    // }
    // }

    @PostMapping("/sign")
    public ResponseEntity<Object> createAccount(@RequestBody CompteRequest compte_personne)
            throws Exception {
        System.out.println("ENAOOOOOOOOOOO" + compte_personne.getPersonne().getNom());
        Personne personne = personneservice.savePersonne(compte_personne.getPersonne());
        compte_personne.getCompte().setPersonne(personne);
        return new ResponseEntity<>(compteRepository.save(compte_personne.getCompte()), HttpStatus.OK);
    }

    // @GetMapping("/nombreAnnoncePersonne")

    // @GetMapping("/statistique")
    // public StatistiqueUtilisateur
    // getStatistiqueUtilisateur(@RequestParam("idpersonne")int idpersonne){
    // return this.personneService.getStatistiqueUtilisateur(idpersonne);
    // }
}
