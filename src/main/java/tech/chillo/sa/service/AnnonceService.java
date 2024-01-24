package tech.chillo.sa.service;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.controller.AnnonceCreationRequest;
import tech.chillo.sa.entites.*;
import tech.chillo.sa.repository.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class AnnonceService {
    private AnnonceRepository annoncerepository;
    private VoitureRepository voitureRepository;
    private DetailsVoitureRepository detailsVoitureRepository;
    private MarqueRepository marqueRepository;
    private ModeleRepository modeleRepository;
    private PersonneRepository personneRepository;

    public AnnonceService(AnnonceRepository annoncerepository, VoitureRepository vp, DetailsVoitureRepository dvp, MarqueRepository mr, ModeleRepository mdr,PersonneRepository personneRepository){
        this.annoncerepository = annoncerepository;
        this.voitureRepository = vp;
        this.detailsVoitureRepository = dvp;
        this.marqueRepository = mr;
        this.modeleRepository = mdr;
        this.personneRepository = personneRepository;
    }
    public List<Voiture> GetByIdPersonne(int idpersonne){
        return  this.annoncerepository.findVoituresByPersonneId(idpersonne);
    }
    public List<Annonce> GetAllOfPersonne(int idpersonne){
        return this.annoncerepository.findByPersonneId(idpersonne);
    }
    public List<Annonce> GetAllOrderByBouquet(){
        return this.annoncerepository.GetAllAnnonceOrderByBouquet();
    }

    @Transactional
    public Annonce createsaveAnnonceWithDetails(AnnonceCreationRequest request) {
        Voiture voiture = request.getVoiture();
        DetailsVoiture detailsVoiture = request.getDetailsVoiture();
        Annonce annonce = request.getAnnonce();
       voiture.setDetailsVoiture(detailsVoiture);
        this.voitureRepository.save(voiture);
        System.out.println(voiture.getMarque().getNom()+"NOM MOARQUE");

        detailsVoiture.setVoiture(voiture);
        this.detailsVoitureRepository.save(detailsVoiture);
        annonce.setVoiture(voiture);
        // Optional<Personne> personne = personneRepository.findById(annonce.getPersonne().getId());
        // if (personne.isPresent()) {
            // Personne pers = personne.get();
            // System.out.println(pers.getNom());
            // System.out.println("Ok");
    
        annonce.setPersonne(request.getAnnonce().getPersonne());
        return this.annoncerepository.save(annonce);
        
    }

    // public Optional<Annonce> getAnnonce(Annonce annonce) {
    //     System.out.println(annonce.getDateplublication()+" ------------------------ "+annonce.getPersonne().getId());

    //     return annoncerepository.getAnnoncesByDatePublicationAndIdpersonne(annonce.getDateplublication(), annonce.getPersonne().getId());
    // }
    


}
