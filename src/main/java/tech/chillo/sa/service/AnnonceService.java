package tech.chillo.sa.service;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.chillo.sa.controller.AnnonceCreationRequest;
import tech.chillo.sa.entites.*;
import tech.chillo.sa.repository.*;
import tech.chillo.sa.model.StatistiqueComission;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
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
    
        annonce.setPersonne(request.getAnnonce().getPersonne());
        return this.annoncerepository.save(annonce);
        
    }

    public List<Annonce> getAnnonceByEtat(int etat) {
        List<Annonce> annconces = this.annoncerepository.findByEtat(etat);
        // System.out.println(annconces.get(0).getVoiture().getMarque().getNom());
        return annconces;
    }

    public List<Annonce> getAnnonceNonLue() {
        List<Annonce> annconces = this.annoncerepository.findByEtat(0);
        // System.out.println(annconces.get(0).getVoiture().getMarque().getNom());
        return annconces;
    }
    
    public long getNombreAnnonceNonLue(int idpersonne) {
        return annoncerepository.countByEtatAndPersonneNotEqual(0, idpersonne);
    }

    public List<Annonce> getAnnoncesByEtatAndPersonneNotEqual(int idpersonne) {
        int etat = 0;  // annonce non lu
        return annoncerepository.findByEtatAndPersonne_IdNot(etat, idpersonne);
    }

    @Transactional
    public void updateEtatAnnonce(int id, int nouvelEtat) {
        annoncerepository.updateEtat(id, nouvelEtat);
    }
    
    public int getNombreAnnoncePersonne(int idpersonne) {
        return annoncerepository.countByPersonneId(idpersonne);
    }
    
    public int getNombreAnnonceVenduPersonne(int idpersonne) {
        return annoncerepository.countByPersonneIdAndEtat(idpersonne,10);
    }

    public List<StatistiqueComission> getStatistiqueComission(int annee){
        List<StatistiqueComission> statistiqueComissions = new ArrayList<StatistiqueComission>();
        for(int i=1; i< 13; i++) {
            StatistiqueComission statistiqueComission = new StatistiqueComission();
            statistiqueComission.setAnnee(annee);
            statistiqueComission.setMois(i);
            statistiqueComission.setPrix(getComission(i,annee));
            statistiqueComissions.add(statistiqueComission);
        }
        return statistiqueComissions;
    }

    public double getComission (int mois, int annee) {
        double rep =0;
        List<Annonce> list =  annoncerepository.findAnnonceByMonthAndYearAndEtat(mois,annee);
        for(int i=0; i<list.size(); i++) {
            rep = rep+ annoncerepository.prixCommission(list.get(i).getId());
        }
        return rep;

    }
}
