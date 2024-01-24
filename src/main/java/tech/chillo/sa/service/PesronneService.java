package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import tech.chillo.sa.entites.Personne;
import tech.chillo.sa.repository.PersonneRepository;
import tech.chillo.sa.service.AnnonceService;
import tech.chillo.sa.model.StatistiqueUtilisateur;

import java.util.List;
import java.util.Optional;

@Service
public class PesronneService {
    private PersonneRepository personneRepository;
    private AnnonceService annonceService;

    public PesronneService (PersonneRepository personneRepository, AnnonceService annonceService){
        this.personneRepository = personneRepository;
        this.annonceService = annonceService;
    }

    // public StatistiqueUtilisateur getStatistiqueUtilisateur (int idpersonne) {
    //     StatistiqueUtilisateur statistiqueUtilisateur = new StatistiqueUtilisateur();

    // }
    
}
