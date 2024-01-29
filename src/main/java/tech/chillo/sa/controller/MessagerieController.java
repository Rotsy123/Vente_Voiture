package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Messagerie;
import tech.chillo.sa.security.token.JwtUtils;
import tech.chillo.sa.service.MessagerieService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin
@RequestMapping(path = "messagerie")
public class MessagerieController {
    private MessagerieService messagerieService;

    public MessagerieController(MessagerieService messagerieService) {
        this.messagerieService = messagerieService;
    }


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> saveMessage(@RequestBody Messagerie request) {
        request = this.messagerieService.saveMessagerie(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> listeMessages(@RequestParam("personne1")int idpersonne1,@RequestHeader(name="Authorization") String authorizationHeader) {
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        try {
            int id = jwt.getId(token);
            List<Messagerie> messages = messagerieService.listMessages(idpersonne1,id);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);

        }
    } 

    @GetMapping("/nombreMessage")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> countMessageNonlue(@RequestHeader(name="Authorization") String authorizationHeader) {
        JwtUtils jwt = new JwtUtils();
        String token = authorizationHeader.substring(7);
        try {
            int id = jwt.getId(token);
            long nombre = messagerieService.countMessageNonlue(id);
            return new ResponseEntity<>(nombre, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping("/updateEtat")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateEtatMessage(@RequestParam("messageId") int messageId) {
        messagerieService.updateEtatMessage(messageId, 10);
        return new ResponseEntity<>("État mis à jour avec succès.", HttpStatus.OK);
    }

    
}
