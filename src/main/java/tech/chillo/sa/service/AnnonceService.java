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
//    private CommissionRepository commissionRepository;
    public AnnonceService( AnnonceRepository annoncerepository, VoitureRepository vp, DetailsVoitureRepository dvp, MarqueRepository mr, ModeleRepository mdr){
//        this.commissionRepository = commissionRepository;
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
        System.out.println(annonce.getVoiture().getMarque().getNom()+"MARIKA");
        this.annoncerepository.save(annonce);
    }

    public void UpdateEtat(int etat, int idannonce) throws Exception {
        Optional<Annonce> optionalAnnonce = annoncerepository.findById(idannonce);
        if (optionalAnnonce.isPresent()) {
            Annonce annonce = optionalAnnonce.get();
            annonce.setEtat(etat);
            annoncerepository.save(annonce);
            if(etat==20){
                double commision = annoncerepository.prixCommission(idannonce);
                LocalDate currentDate = LocalDate.now();
                java.sql.Date sqlDate = Date.valueOf(currentDate);
//                Commission commission = new Commission(annonce,sqlDate , commision);
//                this.commissionRepository.save(commission);
            }
        } else {
            throw new Exception("ANNONCE NON EXISTANTE");
        }
    }


}
