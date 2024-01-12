package tech.chillo.sa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.controller.AnnonceCreationRequest;
import tech.chillo.sa.entites.*;
import tech.chillo.sa.repository.*;

import java.util.List;

@Service
public class AnnonceService {
    private AnnonceRepository annoncerepository;
    private VoitureRepository voitureRepository;
    private DetailsVoitureRepository detailsVoitureRepository;
    private MarqueRepository marqueRepository;
    private ModeleRepository modeleRepository;

    public AnnonceService(AnnonceRepository annoncerepository, VoitureRepository vp, DetailsVoitureRepository dvp, MarqueRepository mr, ModeleRepository mdr){
        this.annoncerepository = annoncerepository;
        this.voitureRepository = vp;
        this.detailsVoitureRepository = dvp;
        this.marqueRepository = mr;
        this.modeleRepository = mdr;
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
    public void createAnnonceWithDetails(AnnonceCreationRequest request) {
        Voiture voiture = request.getVoiture();
        DetailsVoiture detailsVoiture = request.getDetailsVoiture();
        Annonce annonce = request.getAnnonce();
//        voiture.setDetailsVoiture(detailsVoiture);
        this.voitureRepository.save(voiture);
        System.out.println(voiture.getMarque().getId()+"ID MOARQUE");

        detailsVoiture.setVoiture(voiture);
        this.detailsVoitureRepository.save(detailsVoiture);
        annonce.setVoiture(voiture);
        this.annoncerepository.save(annonce);
    }

}
