package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.AnnonceFavoris;
import tech.chillo.sa.security.token.JwtUtils;
import tech.chillo.sa.service.AnnonceFavorisService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "annoncefavoris")
public class AnnonceFavorisController {
    private AnnonceFavorisService annonceFavorisService;

    public AnnonceFavorisController(AnnonceFavorisService afs) {
        this.annonceFavorisService = afs;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/creerfavoris")
    @PreAuthorize("hasRole('USER')")
    public void CreerFavoris(@RequestParam("idannonce") int idannonce,
            @RequestHeader(name = "Authorization") String authorizationHeader) {
        System.out.println("Token ---------------------------------------------- >>> : " + authorizationHeader);
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        System.out.println("Token : " + token);
        try {
            int id = jwt.getId(token);
            System.out.println(id);
            this.annonceFavorisService.createAnnonceFavoris(idannonce, id);

        } catch (Exception e) {

        }
    }

    @GetMapping(value = "list", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> favorisListe(
            @RequestHeader(name = "Authorization") String authorizationHeader) {
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        System.out.println("Token : " + token);
        try {
            int id = jwt.getId(token);
            System.out.println(id);
            return new ResponseEntity<>(this.annonceFavorisService.GetFavorisByPersonne(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}