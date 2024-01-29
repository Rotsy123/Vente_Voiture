package tech.chillo.sa.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.AnnonceFavoris;
import tech.chillo.sa.service.AnnonceFavorisService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "annoncefavoris")
public class AnnonceFavorisController {
    private AnnonceFavorisService annonceFavorisService;

    public AnnonceFavorisController(AnnonceFavorisService afs){
        this.annonceFavorisService = afs;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/creerfavoris")
    public void CreerFavoris(@RequestParam("idpersonne") int idpersonne, @RequestParam("idannonce") int idannonce){
        this.annonceFavorisService.createAnnonceFavoris(idannonce, idpersonne);
    }

    @GetMapping(value = "list/{idpersonne}", produces = APPLICATION_JSON_VALUE)
    public List<AnnonceFavoris> favorisListe(@PathVariable int idpersonne){
        return this.annonceFavorisService.GetFavorisByPersonne(idpersonne);
    }
}
