package tech.chillo.sa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.chillo.sa.entites.Transmission;
import tech.chillo.sa.service.TransmissionService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "transmission")
public class TransmissionController {
    private TransmissionService transmissionservice;
    public TransmissionController(TransmissionService transmissionservice){
        this.transmissionservice = transmissionservice;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Transmission transmission) {
        transmissionservice.Creer(transmission);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Transmission> GetAll() {
        return this.transmissionservice.findAll();
    }
}
